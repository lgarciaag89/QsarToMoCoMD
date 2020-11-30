/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomocomd.qsartomocomd.gui.charts;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.sun.javafx.charts.Legend;
import com.tomocomd.qsartomocomd.gui.alerts.ShowAlerts;
import com.tomocomd.qsartomocomdlib.io.CSVFileManage;
import com.tomocomd.qsartomocomdlib.utils.Statistics;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
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
 * @author Potter
 */
public class SEChartController implements Initializable {

    @FXML
    private JFXButton saveChartButton;
    @FXML
    private StackPane stackPanel;
    @FXML
    private JFXCheckBox showTargetCheck;

    private NumberAxis yAxis;
    private NumberAxis xAxis;
    private Scene scene;
    private Stage stage;
    private ContextMenu cM;
    private LineChartWithMarkers lineChart;

    private List<String> paths;
    private double maxE, seClass;
    XYChart.Data<Number, Number> seMarket;
    private ObservableList<XYChart.Series<Number, Number>> dataComplete, dataReduced;
    private String dataName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cM = new ContextMenu();
        MenuItem hideItemSerie = new MenuItem("Hide/Show Serie");
        hideItemSerie.setOnAction(event -> {
            showHideSerie();
        });
        cM.getItems().add(hideItemSerie);
        MenuItem hideItemLine = new MenuItem("Hide/Show Line");
        hideItemLine.setOnAction(event -> {
            showHideLine();
        });
        cM.getItems().add(hideItemLine);

        xAxis = new NumberAxis();
        yAxis = new NumberAxis();
        xAxis.setLabel("Molecular descriptor index");
        yAxis.setLabel("SE");

        lineChart = new LineChartWithMarkers(xAxis, yAxis);
        lineChart.setLegendSide(Side.LEFT);
        stackPanel.getChildren().add(lineChart);

        showTargetCheck.setSelected(true);
    }

    private void showHideSerie() {
        for (XYChart.Series<Number, Number> s : lineChart.getData()) {
            if (s.getName().equals(dataName)) {
                s.getNode().setVisible(!s.getNode().isVisible());
                for (XYChart.Data<Number, Number> d : s.getData()) {
                    if (d.getNode() != null) {
                        d.getNode().setVisible(s.getNode().isVisible()); // Toggle visibility of every node in the series
                    }
                }
            }
        }
    }

    private void showHideLine() {
        for (XYChart.Series<Number, Number> s : lineChart.getData()) {
            if (s.getName().equals(dataName)) {
                s.getNode().setVisible(!s.getNode().isVisible());
            }
        }
    }

    public void createLineChart(String nameTarget) throws IOException {

        getSE(nameTarget);
        showLines(dataComplete);

    }

    private void showLines(ObservableList<XYChart.Series<Number, Number>> d) {
        Double minY = Double.MAX_VALUE;
        Integer maxX = Integer.MIN_VALUE;

        for (XYChart.Series<Number, Number> serie : d) {
            if (serie.getData().get(0).getYValue().doubleValue() < minY) {
                minY = serie.getData().get(0).getYValue().doubleValue();
            }

            if (serie.getData().size() > maxX) {
                maxX = serie.getData().size();
            }
        }
        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(minY - 0.1);
        yAxis.setUpperBound(maxE);
        yAxis.setTickUnit((maxE - minY) / 10);

        if (maxX > 20) {
            xAxis.setAutoRanging(false);
            xAxis.setTickUnit((int) ((maxX - 1) / 20));
            xAxis.setUpperBound(maxX+2);
        }
        lineChart.setTitle("SE");
        lineChart.setData(d);

        addEvents();

        seMarket = new XYChart.Data<>(0, seClass);
        lineChart.addHorizontalValueMarker(seMarket);
    }

    private void addEvents() {
        for (Node n : lineChart.getChildrenUnmodifiable()) {
            if (n instanceof Legend) {
                Legend l = (Legend) n;
                for (Legend.LegendItem li : l.getItems()) {
                    li.getSymbol().setCursor(Cursor.HAND);
                    li.getSymbol().setOnMouseClicked(event -> {
                        if (event.getButton() == MouseButton.SECONDARY) {
                            cM.show(l, event.getScreenX(), event.getScreenY());
                            dataName = li.getText();
                        }
                    });
                }
            }
        }
    }

    private void getSE(String nameTarget) {

        dataComplete = FXCollections.observableArrayList();
        int maxNumAtt = -1000;
        for (String path : paths) {
            Instances inst = CSVFileManage.loadCSV(path);
            maxE = Statistics.log2(inst.numInstances());
            XYChart.Series<Number, Number> SE = new XYChart.Series<>();
            List<Double> values = new LinkedList<>();
            if (inst.numAttributes() > maxNumAtt) {
                maxNumAtt = inst.numAttributes();
            }
            for (int i = 0; i < inst.numAttributes(); i++) {
                if (!inst.attribute(i).name().equals(nameTarget)) {
                    double[] att = inst.attributeToDoubleArray(i);
                    values.add(Statistics.SE(att));
                } else {
                    seClass = Statistics.SE(inst.attributeToDoubleArray(i));
                }
            }
            values.sort(Comparator.naturalOrder());
            for (int i = 0; i < values.size(); i++) {
                SE.getData().add(new XYChart.Data<>(i + 1, values.get(i)));
            }
            String []namesS = new File(path).getName().split("_");
            String name = namesS[namesS.length-2]+"_"+namesS[namesS.length-1];
            SE.setName(name);
            dataComplete.add(SE);
        }
        dataReduced = FXCollections.observableArrayList();
        dataReduced.setAll(dataComplete);
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

    public List<String> getPaths() {
        return paths;
    }

    public void setPaths(List<String> paths) {
        this.paths = new LinkedList<>(paths);
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
                WritableImage img = lineChart.snapshot(spa, writableImage);
                ImageIO.write(SwingFXUtils.fromFXImage(img, null), "png", file);
                new ShowAlerts().showInfo(String.format("Image file %s created successfuly", file.getName()));
            }
        } catch (Exception ex) {
            new ShowAlerts().showError(String.format("Problems saving chart image, %s", ex.getMessage()));
        }
    }

    @FXML
    private void showTargetAction(ActionEvent event) {
        if (showTargetCheck.isSelected()) {
            seMarket = new XYChart.Data<>(0, seClass);
            lineChart.addHorizontalValueMarker(seMarket);
        } else {
            lineChart.removeHorizontalValueMarker(seMarket);
        }
    }

    private class LineChartWithMarkers extends LineChart<Number, Number> {

        private ObservableList<XYChart.Data<Number, Number>> horizontalMarkers;

        public LineChartWithMarkers(NumberAxis xAxis, NumberAxis yAxis) {
            super(xAxis, yAxis);
            horizontalMarkers = FXCollections.observableArrayList(data -> new Observable[]{data.YValueProperty()});
            horizontalMarkers.addListener((InvalidationListener) observable -> layoutPlotChildren());
        }

        public void addHorizontalValueMarker(XYChart.Data<Number, Number> marker) {
            Objects.requireNonNull(marker, "the marker must not be null");
            if (horizontalMarkers.contains(marker)) {
                return;
            }
            Line line = new Line();
            line.setStroke(Color.GREY);
            marker.setNode(line);
            getPlotChildren().add(line);
            horizontalMarkers.add(marker);
        }

        public void removeHorizontalValueMarker(XYChart.Data<Number, Number> marker) {
            Objects.requireNonNull(marker, "the marker must not be null");
            if (marker.getNode() != null) {
                getPlotChildren().remove(marker.getNode());
                marker.setNode(null);
            }
            horizontalMarkers.remove(marker);
        }

        @Override
        protected void layoutPlotChildren() {
            super.layoutPlotChildren();
            for (XYChart.Data<Number, Number> horizontalMarker : horizontalMarkers) {
                Line line = (Line) horizontalMarker.getNode();
                line.setStartX(0);
                line.setEndX(getBoundsInLocal().getWidth());
                line.setStartY(getYAxis().getDisplayPosition(horizontalMarker.getYValue()) + 0.5); // 0.5 for crispness
                line.setEndY(line.getStartY());
                line.toFront();
            }
        }

    }
}
