/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomocomd.qsartomocomd.gui.charts;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.tomocomd.qsartomocomd.gui.alerts.ShowAlerts;
import com.tomocomd.qsartomocomd.gui.charts.sechart.ContainerChart;
import com.tomocomd.qsartomocomd.gui.charts.sechart.SEXYChart;
import com.tomocomd.qsartomocomdlib.io.CSVFileManage;
import com.tomocomd.qsartomocomdlib.utils.Statistics;
import java.io.File;
import java.net.URL;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import weka.core.Instances;

/**
 * FXML Controller class
 *
 * @author potter
 */
public class SEGraphController implements Initializable {

    @FXML
    private StackPane stackPanel;
    @FXML
    private JFXButton saveChartButton;
    @FXML
    private JFXButton loadDBButton;
//    @FXML
//    private JFXComboBox<String> nameListCombo;
//    @FXML
//    private JFXCheckBox normDatCheck;

    private Scene scene;
    private Stage stage;
//    private NumberAxis yAxis;
//    private NumberAxis xAxis;
    private ContainerChart container ;
//    private LineChartWithMarkers lineChart;
//    HashMap<String, Double> namesAtt;
//    HashMap<String, XYChart.Data<Number, Number>> seMarket;
//    private ObservableList<XYChart.Series<Number, Number>> dataComplete;
//    private ObservableList<XYChart.Series<Number, Number>> dataCompleteNorm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       

//        lineChart = new LineChartWithMarkers(xAxis, yAxis);
        container = new ContainerChart(stackPanel);
        
//        stackPanel.getChildren().add(lineChart);

    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void saveChartAction(ActionEvent event) {
        try {
            FileChooser fileChooser = new FileChooser();
            File file;
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("png Files", "*.png")
            );

            file = fileChooser.showSaveDialog(stage);
            if (file != null) {
                SnapshotParameters spa = new SnapshotParameters();
                spa.setFill(Color.TRANSPARENT);
                WritableImage writableImage = new WritableImage((int) stage.getScene().getWidth(), (int) stage.getScene().getHeight());
                WritableImage img = container.getLines().snapshot(spa, writableImage);
                ImageIO.write(SwingFXUtils.fromFXImage(img, null), "png", file);
                new ShowAlerts().showInfo(String.format("Image file %s created successfuly", file.getName()));
            }
        } catch (Exception ex) {
            new ShowAlerts().showError(String.format("Problems saving chart image, %s", ex.getMessage()));
        }
    }

    @FXML
    private void loadDBAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("csv Files(coma separator)", "*.csv"));
        List<File> files = fileChooser.showOpenMultipleDialog(stage);

        double maxE;
        int maxNumAtt = -1000;
        ObservableList<SEXYChart.Series<Number, Number>> dataComplete = FXCollections.observableArrayList();
//        dataCompleteNorm = FXCollections.observableArrayList();
        for (File path : files) {
            Instances inst = CSVFileManage.loadCSV(path.getAbsolutePath());
            if (inst == null) {
                new ShowAlerts().showInfo(String.format("Error loaded %s file", path.getName()));
                return;
            }

            maxE = Statistics.log2(inst.numInstances());
            List<Double> values = new LinkedList<>();
            List<Double> valuesNorm = new LinkedList<>();
            if (inst.numAttributes() > maxNumAtt) {
                maxNumAtt = inst.numAttributes();
            }

            for (int i = 0; i < inst.numAttributes(); i++) {
                double[] att = inst.attributeToDoubleArray(i);
                valuesNorm.add(Statistics.SE(att) / maxE);
                values.add(Statistics.SE(att));
//                namesAtt.put(inst.attribute(i).name(), Statistics.SE(att) / maxE);
            }

            SEXYChart.Series<Number, Number> SE = new SEXYChart.Series<>();
//            XYChart.Series<Number, Number> SENorm = new XYChart.Series<>();
            values.sort(Comparator.reverseOrder());
            for (int i = 0; i < values.size(); i++) {
                SE.getData().add(new SEXYChart.Data<>(i + 1, values.get(i)));
//                SENorm.getData().add(new XYChart.Data<>(i + 1, valuesNorm.get(i)));
            }
            String[] namesS = path.getName().split("_");
            String name = namesS[namesS.length - 2] + "_" + namesS[namesS.length - 1];
            SE.setName(name);
//            SENorm.setName(name);
            dataComplete.add(SE);
//            dataCompleteNorm.add(SENorm);
        }
        
        container = new ContainerChart(stackPanel, dataComplete);

//        namesAtt = namesAtt.entrySet().stream()
//                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
//                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
//
//        ObservableList<String> archData = FXCollections.observableArrayList();
//        archData.addAll(namesAtt.keySet());
//        nameListCombo.setItems(archData);

//        showLines(false);
    }

//    private void showLines(boolean norm) {
//        Double minY = Double.MAX_VALUE;
//        Double maxY = Double.MIN_VALUE;
//        Integer maxX = Integer.MIN_VALUE;
//        ObservableList<XYChart.Series<Number, Number>> datas;
//
//        if (norm) {
//            datas = dataCompleteNorm;
//        } else {
//            datas = dataComplete;
//        }
//
//        for (XYChart.Series<Number, Number> serie : datas) {
//            if (serie.getData().get(0).getYValue().doubleValue() < minY) {
//                minY = serie.getData().get(0).getYValue().doubleValue();
//            }
//
//            if (serie.getData().get(serie.getData().size() - 1).getYValue().doubleValue() > maxY) {
//                maxY = serie.getData().get(serie.getData().size() - 1).getYValue().doubleValue();
//            }
//
//            if (serie.getData().size() > maxX) {
//                maxX = serie.getData().size();
//            }
//        }
//        yAxis.setAutoRanging(false);
//        yAxis.setLowerBound(minY - 0.1);
//        yAxis.setUpperBound(maxY + 0.1);
//        yAxis.setTickUnit((maxY - minY) / 10);
//
//        if (maxX > 20) {
//            xAxis.setAutoRanging(false);
//            xAxis.setTickUnit((int) ((maxX - 1) / 20));
//            xAxis.setUpperBound(maxX + 2);
//        }
//        lineChart.setTitle("SE");
//        lineChart.setData(datas);
//
//        for (XYChart.Series<Number, Number> SE : lineChart.getData()) {
//// 
//            for (XYChart.Data<Number, Number> dataSE : SE.getData()) {
//                dataSE.getNode().setCursor(Cursor.OPEN_HAND);
//                Tooltip tipData = new Tooltip();
//
//                dataSE.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
//
//                    @Override
//                    public void handle(MouseEvent event) {
//                        double val = dataSE.getYValue().doubleValue();
//
//                        String Names = "";
//                        for (Map.Entry<String, Double> entry : namesAtt.entrySet()) {
//                            if (entry.getValue().equals(val)) {
//                                if (Names.isEmpty()) {
//                                    Names = entry.getKey();
//                                } else {
//                                    Names = String.format("%s\n%s", Names, entry.getKey());
//                                }
//                            }
//                        }
//                        Names = String.format("%s\n%.4f", Names, val);
//                        tipData.setText(Names);
//                        Tooltip.install(dataSE.getNode(), tipData);
//                        tipData.show(dataSE.getNode(), event.getScreenX(), event.getScreenY());
//                    }
//                });
//                dataSE.getNode().addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
//
//                    @Override
//                    public void handle(MouseEvent event) {
//                        tipData.hide();
//                    }
//                });
//            }
//
//        }
//
//        lineChart.setLegendSide(Side.BOTTOM);
//    }

//    @FXML
//    private void showTargetNameAction(ActionEvent event) {
//        String name = nameListCombo.getValue();
//        if (namesAtt.containsKey(name)) {
//            if (seMarket.containsKey(name)) {
//                lineChart.removeHorizontalValueMarker(seMarket.remove(name));
//            } else {
//                seMarket.put(name, new XYChart.Data<>(0, namesAtt.get(name)));
//                lineChart.addHorizontalValueMarker(seMarket.get(name));
//            }
//        }
//    }
//
//    @FXML
//    private void normDatAction(ActionEvent event) {
//        if (normDatCheck.isSelected()) {
//            showLines(true);
//        } else {
//            showLines(false);
//        }
//    }

//    private class LineChartWithMarkers extends LineChart<Number, Number> {
//
//        private ObservableList<XYChart.Data<Number, Number>> horizontalMarkers;
//
//        public LineChartWithMarkers(NumberAxis xAxis, NumberAxis yAxis) {
//            super(xAxis, yAxis);
//            horizontalMarkers = FXCollections.observableArrayList(data -> new Observable[]{data.YValueProperty()});
//            horizontalMarkers.addListener((InvalidationListener) observable -> layoutPlotChildren());
//        }
//
//        public void addHorizontalValueMarker(XYChart.Data<Number, Number> marker) {
//            Objects.requireNonNull(marker, "the marker must not be null");
//            if (horizontalMarkers.contains(marker)) {
//                return;
//            }
//            Line line = new Line();
//            line.setStroke(Color.GREY);
//            marker.setNode(line);
//            getPlotChildren().add(line);
//            horizontalMarkers.add(marker);
//        }
//
//        public void removeHorizontalValueMarker(XYChart.Data<Number, Number> marker) {
//            Objects.requireNonNull(marker, "the marker must not be null");
//            if (marker.getNode() != null) {
//                getPlotChildren().remove(marker.getNode());
//                marker.setNode(null);
//            }
//            horizontalMarkers.remove(marker);
//        }
//
//        @Override
//        protected void layoutPlotChildren() {
//            super.layoutPlotChildren();
//            for (XYChart.Data<Number, Number> horizontalMarker : horizontalMarkers) {
//                Line line = (Line) horizontalMarker.getNode();
//                line.setStartX(0);
//                line.setEndX(getBoundsInLocal().getWidth());
//                line.setStartY(getYAxis().getDisplayPosition(horizontalMarker.getYValue()) + 0.5); // 0.5 for crispness
//                line.setEndY(line.getStartY());
//                line.toFront();
//            }
//        }
//
//    }

}
