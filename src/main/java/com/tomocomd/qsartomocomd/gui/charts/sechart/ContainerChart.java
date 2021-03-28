/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomocomd.qsartomocomd.gui.charts.sechart;

import com.sun.javafx.charts.Legend;
import java.util.Collection;
import java.util.Iterator;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.chart.Axis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author potter
 */
public class ContainerChart {

    private final StackPane rootPanel;

    private LineChartLegend lines;
    private ObservableList<SEXYChart.Series<Number, Number>> seriesOri;

    private volatile boolean zoomed;
    private Rectangle zoomRect;
    private final NumberAxis xAxisOri;
    private final NumberAxis yAxisOri;

    double minY, maxY, minX, maxX;

    public ContainerChart(StackPane pane) {

        rootPanel = pane;
        this.xAxisOri = new NumberAxis();
        this.yAxisOri = new NumberAxis();

        this.xAxisOri.setLabel("Molecular descriptor index");
        this.yAxisOri.setLabel("SE");

        lines = new LineChartLegend(this.xAxisOri, this.yAxisOri);
        rootPanel.getChildren().add(lines);
        seriesOri = FXCollections.observableArrayList();
    }

    public void setSeriesOri(ObservableList<SEXYChart.Series<Number, Number>> series) {
        this.seriesOri = deepCopySeries(series);
    }

    public ContainerChart(StackPane pane, final Collection<SEXYChart.Series<Number, Number>> series) {

        rootPanel = pane;
        this.xAxisOri = new NumberAxis();
        this.yAxisOri = new NumberAxis();

        this.xAxisOri.setLabel("Molecular descriptor index");
        this.yAxisOri.setLabel("SE");

        this.seriesOri = deepCopySeries(series);

        setLines(seriesOri);
    }

    public LineChartLegend getLines() {
        return lines;
    }

    private void setLines(ObservableList<SEXYChart.Series<Number, Number>> series) {

        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel(xAxisOri.getLabel());

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel(yAxisOri.getLabel());

        lines = new LineChartLegend(xAxis, yAxis);
//        lines.setTitle("SE");
        lines.prefHeightProperty().bind(rootPanel.heightProperty());
        lines.getData().setAll(deepCopySeries(series));
        lines.setCreateSymbols(false);

        setLinesEvent();

        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(minY - 0.1);
        yAxis.setUpperBound(maxY + 0.1);
        yAxis.setTickUnit(((maxY + 0.1) - (minY - 0.1)) / 10);

        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(minX - 1);
        xAxis.setUpperBound(maxX + 1);
        xAxis.setTickUnit((int) (((maxX + 1) - (minX - 1)) / ((maxX+1)-(minX-1))));

        zoomRect = new Rectangle();
        zoomRect.setManaged(false);
        zoomRect.setFill(Color.LIGHTSEAGREEN.deriveColor(0, 1, 1, 0.5));

        if (rootPanel.getChildren().size() > 0) {
            rootPanel.getChildren().remove(0, rootPanel.getChildren().size());
        }
        rootPanel.getChildren().add(lines);
        rootPanel.getChildren().add(zoomRect);
        setUpZooming();
    }

    private void setLinesEvent() {
        // poner action
        minX = Double.MAX_VALUE;
        maxX = Double.MIN_VALUE;
        minY = Double.MAX_VALUE;
        maxY = Double.MIN_VALUE;

        Legend l = (Legend) lines.getLegendMovil();
        for (Legend.LegendItem li : l.getItems()) {
            for (SEXYChart.Series<Number, Number> s : lines.getData()) {
                if (s.getName().equals(li.getText())) {
                    li.getSymbol().setCursor(Cursor.HAND); // Hint user that legend symbol is clickable
                    li.getSymbol().setOnMouseClicked(me -> {
                        if (me.getButton() == MouseButton.PRIMARY) {
                            s.getNode().setVisible(!s.getNode().isVisible()); // Toggle visibility of line
                            for (SEXYChart.Data<Number, Number> d : s.getData()) {
                                if (d.getNode() != null) {
                                    d.getNode().setVisible(s.getNode().isVisible()); // Toggle visibility of every node in the series
                                }
                            }
                        }
                    });
                }

                double[] bounds = getBoundsValues(s.getData());
                minX = bounds[0] < minX ? bounds[0] : minX;
                maxX = bounds[1] > maxX ? bounds[1] : maxX;
                minY = bounds[2] < minY ? bounds[2] : minY;
                maxY = bounds[3] > maxY ? bounds[3] : maxY;
            }
        }
    }

    private double[] getBoundsValues(ObservableList<SEXYChart.Data<Number, Number>> data) {
        double[] bounds = new double[4];
        bounds[0] = Double.MAX_VALUE;
        bounds[1] = Double.MIN_VALUE;
        bounds[2] = Double.MAX_VALUE;
        bounds[3] = Double.MIN_VALUE;

        for (SEXYChart.Data<Number, Number> point : data) {
            bounds[0] = point.getXValue().doubleValue() < bounds[0] ? point.getXValue().doubleValue() : bounds[0];
            bounds[1] = point.getXValue().doubleValue() > bounds[1] ? point.getXValue().doubleValue() : bounds[1];

            bounds[2] = point.getYValue().doubleValue() < bounds[2] ? point.getYValue().doubleValue() : bounds[2];
            bounds[3] = point.getYValue().doubleValue() > bounds[3] ? point.getYValue().doubleValue() : bounds[3];
        }

        return bounds;
    }

    static Object getObject(final Axis<?> axis, final double cooridnate) {
        final Object object = axis.getValueForDisplay(cooridnate);
        return object;
    }

    static Node getRootNode(final Node node) {
        Node n = node;
        while (n.getParent() != null) {
            n = n.getParent();
        }
        return n;
    }

    public StackPane getRootPanel() {
        return rootPanel;
    }

    private void setUpZooming() {
        setUpZoomingRectangle(zoomRect);
    }

    private void setUpZoomingRectangle(final Rectangle rect) {
        final Node chartBackground = lines.lookup(".chart-plot-background");
        final ObjectProperty<Point2D> mouseAnchor = new SimpleObjectProperty<>();

        lines.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                mouseAnchor.set(new Point2D(event.getX(), event.getY()));
            }
        });
        lines.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    if (zoomed && event.getClickCount() == 2) {
                        setLines(seriesOri);
                        zoomed = false;
                        event.consume();
                    }
                }
            }
        });
        lines.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                if (event.getButton().equals(MouseButton.SECONDARY)) {
                    final double x = event.getX();
                    final double y = event.getY();
                    rect.setX(Math.min(x, mouseAnchor.get().getX()));
                    rect.setY(Math.min(y, mouseAnchor.get().getY()));
                    rect.setWidth(Math.abs(x - mouseAnchor.get().getX()));
                    rect.setHeight(Math.abs(y - mouseAnchor.get().getY()));
                }
            }
        });
        lines.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                if (event.getButton().equals(MouseButton.SECONDARY)) {
//				final Bounds bb = chartBackground.sceneToLocal(rect.getBoundsInLocal());
                    final Bounds bb = chartBackground.parentToLocal(rect.getBoundsInParent());

                    final double minx = bb.getMinX();
                    final double maxx = bb.getMaxX();

                    final double miny = bb.getMinY();
                    final double maxy = bb.getMaxY();

                    doZoom(true, lines.getXAxis().getValueForDisplay(minx), lines.getXAxis().getValueForDisplay(maxx));

                    doZoom(false, lines.getYAxis().getValueForDisplay(miny), lines.getYAxis().getValueForDisplay(maxy));

                    rect.setWidth(0);
                    rect.setHeight(0);
                    setLines(lines.getData());
                    event.consume();
                }
            }
        });
    }

    private void doZoom(final boolean x, final Number n1, final Number n2) {
        final double min = Math.min(n1.doubleValue(), n2.doubleValue());
        final double max = Math.max(n1.doubleValue(), n2.doubleValue());
        if (max > min) {
            zoomed = true;
            final Iterator<SEXYChart.Series<Number, Number>> it = lines.getData().iterator();
            while (it.hasNext()) {
                final SEXYChart.Series<Number, Number> s = it.next();
                final Iterator<SEXYChart.Data<Number, Number>> it2 = s.getData().iterator();
                while (it2.hasNext()) {
                    final SEXYChart.Data<Number, Number> d = it2.next();
                    final Object value;
                    if (x) {
                        value = d.getXValue();
                    } else {
                        value = d.getYValue();
                    }
                    if (value instanceof Number) {
                        final Number n = (Number) value;
                        final double dd = n.doubleValue();
                        if (dd < min || dd > max) {
                            it2.remove();
                        } else {
                        }
                    }
                    if (s.getData().isEmpty()) {
                        it.remove();
                    }
                }
            }
        } else {
            // System.out.println("Skip tiny zoom");
        }

    }

    private void restoreData() {
        final ObservableList<SEXYChart.Series<Number, Number>> backup2 = deepCopySeries(seriesOri);
        lines.getData().setAll(backup2);
    }

    static <X, Y> ObservableList<SEXYChart.Series<X, Y>> deepCopySeries(final Collection<SEXYChart.Series<X, Y>> data) {
        final ObservableList<SEXYChart.Series<X, Y>> backup = FXCollections.observableArrayList();
        for (final SEXYChart.Series<X, Y> s : data) {
            backup.add(deepCopySeries(s));
        }
        return backup;
    }

    static <X, Y> SEXYChart.Series<X, Y> deepCopySeries(final SEXYChart.Series<X, Y> series) {
        final SEXYChart.Series<X, Y> result = new SEXYChart.Series<>();
        result.setName(series.getName());
        result.setData(deepCopySeriesData(series.getData()));
        return result;
    }

    static <X, Y> ObservableList<SEXYChart.Data<X, Y>> deepCopySeriesData(
            final Collection<? extends SEXYChart.Data<X, Y>> data) {
        final ObservableList<SEXYChart.Data<X, Y>> result = FXCollections.observableArrayList();

        for (final SEXYChart.Data<X, Y> i : data) {
            result.add(new SEXYChart.Data(i.getXValue(), i.getYValue()));
        }
        return result;
    }

}
