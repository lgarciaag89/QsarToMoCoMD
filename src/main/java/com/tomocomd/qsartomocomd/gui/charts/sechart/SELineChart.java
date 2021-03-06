/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomocomd.qsartomocomd.gui.charts.sechart;

import com.sun.javafx.charts.Legend;
import com.sun.javafx.css.converters.BooleanConverter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.NamedArg;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.WritableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.css.StyleableBooleanProperty;
import javafx.css.StyleableProperty;
import javafx.scene.AccessibleRole;
import javafx.scene.Node;
import javafx.scene.chart.Axis;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;
import javafx.scene.shape.StrokeLineJoin;
import javafx.util.Duration;

/**
 *
 * @author potter
 */
public class SELineChart<X,Y> extends SEXYChart<X,Y> {

    // -------------- PRIVATE FIELDS ------------------------------------------

    /** A multiplier for the Y values that we store for each series, it is used to animate in a new series */
    private Map<SEXYChart.Series<X,Y>, DoubleProperty> seriesYMultiplierMap = new HashMap<>();
    private Legend legend = new Legend();
    private Timeline dataRemoveTimeline;
    private SEXYChart.Series<X,Y> seriesOfDataRemoved = null;
    private SEXYChart.Data<X,Y> dataItemBeingRemoved = null;
    private FadeTransition fadeSymbolTransition = null;
    private Map<SEXYChart.Data<X,Y>, Double> XYValueMap =
                                new HashMap<SEXYChart.Data<X,Y>, Double>();
    private Timeline seriesRemoveTimeline = null;
    // -------------- PUBLIC PROPERTIES ----------------------------------------

    /** When true, CSS styleable symbols are created for any data items that don't have a symbol node specified. */
    private BooleanProperty createSymbols = new StyleableBooleanProperty(true) {
        @Override protected void invalidated() {
            for (int seriesIndex=0; seriesIndex < getData().size(); seriesIndex ++) {
                SEXYChart.Series<X,Y> series = getData().get(seriesIndex);
                for (int itemIndex=0; itemIndex < series.getData().size(); itemIndex ++) {
                    SEXYChart.Data<X,Y> item = series.getData().get(itemIndex);
                    Node symbol = item.getNode();
                    if(get() && symbol == null) { // create any symbols
                        symbol = createSymbol(series, getData().indexOf(series), item, itemIndex);
                        getPlotChildren().add(symbol);
                    } else if (!get() && symbol != null) { // remove symbols
                        getPlotChildren().remove(symbol);
                        symbol = null;
                        item.setNode(null);
                    }
                }
            }
            requestChartLayout();
        }

        public Object getBean() {
            return SELineChart.this;
        }

        public String getName() {
            return "createSymbols";
        }

        public CssMetaData<SELineChart<?,?>,Boolean> getCssMetaData() {
            return StyleableProperties.CREATE_SYMBOLS;
        }
    };

    /**
     * Indicates whether symbols for data points will be created or not.
     *
     * @return true if symbols for data points will be created and false otherwise.
     */
    public final boolean getCreateSymbols() { return createSymbols.getValue(); }
    public final void setCreateSymbols(boolean value) { createSymbols.setValue(value); }
    public final BooleanProperty createSymbolsProperty() { return createSymbols; }


    /**
     * Indicates whether the data passed to SELineChart should be sorted by natural order of one of the axes.
     * If this is set to {@link SortingPolicy#NONE}, the order in {@link #dataProperty()} will be used.
     *
     * @since JavaFX 8u40
     * @see SortingPolicy
     * @defaultValue SortingPolicy#X_AXIS
     */
    private ObjectProperty<SortingPolicy> axisSortingPolicy = new ObjectPropertyBase<SortingPolicy>(SortingPolicy.X_AXIS) {
        @Override protected void invalidated() {
            requestChartLayout();
        }

        public Object getBean() {
            return SELineChart.this;
        }

        public String getName() {
            return "axisSortingPolicy";
        }

    };

    public final SortingPolicy getAxisSortingPolicy() { return axisSortingPolicy.getValue(); }
    public final void setAxisSortingPolicy(SortingPolicy value) { axisSortingPolicy.setValue(value); }
    public final ObjectProperty<SortingPolicy> axisSortingPolicyProperty() { return axisSortingPolicy; }

    // -------------- CONSTRUCTORS ----------------------------------------------

    /**
     * Construct a new SELineChart with the given axis.
     *
     * @param xAxis The x axis to use
     * @param yAxis The y axis to use
     */
    public SELineChart(@NamedArg("xAxis") Axis<X> xAxis, @NamedArg("yAxis") Axis<Y> yAxis) {
        this(xAxis, yAxis, FXCollections.<SEXYChart.Series<X, Y>>observableArrayList());
    }

    /**
     * Construct a new SELineChart with the given axis and data.
     *
     * @param xAxis The x axis to use
     * @param yAxis The y axis to use
     * @param data The data to use, this is the actual list used so any changes to it will be reflected in the chart
     */
    public SELineChart(@NamedArg("xAxis") Axis<X> xAxis, @NamedArg("yAxis") Axis<Y> yAxis, @NamedArg("data") ObservableList<SEXYChart.Series<X,Y>> data) {
        super(xAxis,yAxis);
        setLegend(legend);
        setData(data);
    }

    // -------------- METHODS ------------------------------------------------------------------------------------------

    /** @inheritDoc */
    @Override protected void updateAxisRange() {
        final Axis<X> xa = getXAxis();
        final Axis<Y> ya = getYAxis();
        List<X> xData = null;
        List<Y> yData = null;
        if(xa.isAutoRanging()) xData = new ArrayList<X>();
        if(ya.isAutoRanging()) yData = new ArrayList<Y>();
        if(xData != null || yData != null) {
            for(SEXYChart.Series<X,Y> series : getData()) {
                for(SEXYChart.Data<X,Y> data: series.getData()) {
                    if(xData != null) xData.add(data.getXValue());
                    if(yData != null) yData.add(data.getYValue());
                }
            }
            // RT-32838 No need to invalidate range if there is one data item - whose value is zero.
            if(xData != null && !(xData.size() == 1 && getXAxis().toNumericValue(xData.get(0)) == 0)) {
                xa.invalidateRange(xData);
            }
            if(yData != null && !(yData.size() == 1 && getYAxis().toNumericValue(yData.get(0)) == 0)) {
                ya.invalidateRange(yData);
            }

        }
    }

    @Override protected void dataItemAdded(final SEXYChart.Series<X,Y> series, int itemIndex, final SEXYChart.Data<X,Y> item) {
        final Node symbol = createSymbol(series, getData().indexOf(series), item, itemIndex);
        if (shouldAnimate()) {
            if (dataRemoveTimeline != null && dataRemoveTimeline.getStatus().equals(Animation.Status.RUNNING)) {
                if (seriesOfDataRemoved == series) {
                    dataRemoveTimeline.stop();
                    dataRemoveTimeline = null;
                    getPlotChildren().remove(dataItemBeingRemoved.getNode());
                    removeDataItemFromDisplay(seriesOfDataRemoved, dataItemBeingRemoved);
                    seriesOfDataRemoved = null;
                    dataItemBeingRemoved = null;
                }
            }
            boolean animate = false;
            if (itemIndex > 0 && itemIndex < (series.getData().size()-1)) {
                animate = true;
                SEXYChart.Data<X,Y> p1 = series.getData().get(itemIndex - 1);
                SEXYChart.Data<X,Y> p2 = series.getData().get(itemIndex + 1);
                if (p1 != null && p2 != null) {
                    double x1 = getXAxis().toNumericValue(p1.getXValue());
                    double y1 = getYAxis().toNumericValue(p1.getYValue());
                    double x3 = getXAxis().toNumericValue(p2.getXValue());
                    double y3 = getYAxis().toNumericValue(p2.getYValue());

                    double x2 = getXAxis().toNumericValue(item.getXValue());
                    //double y2 = getYAxis().toNumericValue(item.getYValue());
                    if (x2 > x1 && x2 < x3) {
                         //1. y intercept of the line : y = ((y3-y1)/(x3-x1)) * x2 + (x3y1 - y3x1)/(x3 -x1)
                        double y = ((y3-y1)/(x3-x1)) * x2 + (x3*y1 - y3*x1)/(x3-x1);
                        item.setCurrentY(getYAxis().toRealValue(y));
                        item.setCurrentX(getXAxis().toRealValue(x2));
                    } else {
                        //2. we can simply use the midpoint on the line as well..
                        double x = (x3 + x1)/2;
                        double y = (y3 + y1)/2;
                        item.setCurrentX(getXAxis().toRealValue(x));
                        item.setCurrentY(getYAxis().toRealValue(y));
                    }
                }
            } else if (itemIndex == 0 && series.getData().size() > 1) {
                animate = true;
                item.setCurrentX(series.getData().get(1).getXValue());
                item.setCurrentY(series.getData().get(1).getYValue());
            } else if (itemIndex == (series.getData().size() - 1) && series.getData().size() > 1) {
                animate = true;
                int last = series.getData().size() - 2;
                item.setCurrentX(series.getData().get(last).getXValue());
                item.setCurrentY(series.getData().get(last).getYValue());
            } else if(symbol != null) {
                // fade in new symbol
                symbol.setOpacity(0);
                getPlotChildren().add(symbol);
                FadeTransition ft = new FadeTransition(Duration.millis(500),symbol);
                ft.setToValue(1);
                ft.play();
            }
            if (animate) {
//                animate(
//                    new KeyFrame(Duration.ZERO,
//                            (e) -> { if (symbol != null && !getPlotChildren().contains(symbol)) getPlotChildren().add(symbol); },
//                                   new KeyValue(item.currentYProperty(),
//                                        item.getCurrentY()),
//                                        new KeyValue(item.currentXProperty(),
//                                        item.getCurrentX())),
//                    new KeyFrame(Duration.millis(700), new KeyValue(item.currentYProperty(),
//                                        item.getYValue(), Interpolator.EASE_BOTH),
//                                        new KeyValue(item.currentXProperty(),
//                                        item.getXValue(), Interpolator.EASE_BOTH))
//                );
            }

        } else {
            if (symbol != null) getPlotChildren().add(symbol);
        }
    }

    @Override protected  void dataItemRemoved(final SEXYChart.Data<X,Y> item, final SEXYChart.Series<X,Y> series) {
        final Node symbol = item.getNode();

        if (symbol != null) {
            symbol.focusTraversableProperty().unbind();
        }

        // remove item from sorted list
        int itemIndex = series.getItemIndex(item);
        if (shouldAnimate()) {
            XYValueMap.clear();
            boolean animate = false;
            // dataSize represents size of currently visible data. After this operation, the number will decrement by 1
            final int dataSize = series.getDataSize();
            // This is the size of current data list in Series. Note that it might be totaly different from dataSize as
            // some big operation might have happened on the list.
            final int dataListSize = series.getData().size();
            if (itemIndex > 0 && itemIndex < dataSize - 1) {
                animate = true;
                SEXYChart.Data<X,Y> p1 = series.getItem(itemIndex - 1);
                SEXYChart.Data<X,Y> p2 = series.getItem(itemIndex + 1);
                double x1 = getXAxis().toNumericValue(p1.getXValue());
                double y1 = getYAxis().toNumericValue(p1.getYValue());
                double x3 = getXAxis().toNumericValue(p2.getXValue());
                double y3 = getYAxis().toNumericValue(p2.getYValue());

                double x2 = getXAxis().toNumericValue(item.getXValue());
                double y2 = getYAxis().toNumericValue(item.getYValue());
                if (x2 > x1 && x2 < x3) {
//                //1.  y intercept of the line : y = ((y3-y1)/(x3-x1)) * x2 + (x3y1 - y3x1)/(x3 -x1)
                    double y = ((y3-y1)/(x3-x1)) * x2 + (x3*y1 - y3*x1)/(x3-x1);
                    item.setCurrentX(getXAxis().toRealValue(x2));
                    item.setCurrentY(getYAxis().toRealValue(y2));
                    item.setXValue(getXAxis().toRealValue(x2));
                    item.setYValue(getYAxis().toRealValue(y));
                } else {
                //2.  we can simply use the midpoint on the line as well..
                    double x = (x3 + x1)/2;
                    double y = (y3 + y1)/2;
                    item.setCurrentX(getXAxis().toRealValue(x));
                    item.setCurrentY(getYAxis().toRealValue(y));
                }
            } else if (itemIndex == 0 && dataListSize > 1) {
                animate = true;
                item.setXValue(series.getData().get(0).getXValue());
                item.setYValue(series.getData().get(0).getYValue());
            } else if (itemIndex == (dataSize - 1) && dataListSize > 1) {
                animate = true;
                int last = dataListSize - 1;
                item.setXValue(series.getData().get(last).getXValue());
                item.setYValue(series.getData().get(last).getYValue());
            } else if (symbol != null) {
                // fade out symbol
                fadeSymbolTransition = new FadeTransition(Duration.millis(500),symbol);
                fadeSymbolTransition.setToValue(0);
                fadeSymbolTransition.setOnFinished(actionEvent -> {
                    item.setSeries(null);
                    getPlotChildren().remove(symbol);
                    removeDataItemFromDisplay(series, item);
                    symbol.setOpacity(1.0);
                });
                fadeSymbolTransition.play();
            }
            if (animate) {
                dataRemoveTimeline = createDataRemoveTimeline(item, symbol, series);
                seriesOfDataRemoved = series;
                dataItemBeingRemoved = item;
                dataRemoveTimeline.play();
            }
        } else {
            item.setSeries(null);
            if (symbol != null) getPlotChildren().remove(symbol);
            removeDataItemFromDisplay(series, item);
        }
        //Note: better animation here, point should move from old position to new position at center point between prev and next symbols
    }

    /** @inheritDoc */
    @Override protected void dataItemChanged(SEXYChart.Data<X, Y> item) {
    }

    @Override protected void seriesChanged(ListChangeListener.Change<? extends SEXYChart.Series> c) {
        // Update style classes for all series lines and symbols
        // Note: is there a more efficient way of doing this?
        for (int i = 0; i < getDataSize(); i++) {
            final SEXYChart.Series<X,Y> s = getData().get(i);
            Node seriesNode = s.getNode();
            if(seriesNode != null) seriesNode.getStyleClass().setAll("chart-series-line", "series" + i, s.defaultColorStyleClass);
        }
    }

    @Override protected  void seriesAdded(SEXYChart.Series<X,Y> series, int seriesIndex) {
        // create new path for series
        Path seriesLine = new Path();
        seriesLine.setStrokeLineJoin(StrokeLineJoin.BEVEL);
        series.setNode(seriesLine);
        // create series Y multiplier
        DoubleProperty seriesYAnimMultiplier = new SimpleDoubleProperty(this, "seriesYMultiplier");
        seriesYMultiplierMap.put(series, seriesYAnimMultiplier);
        // handle any data already in series
        if (shouldAnimate()) {
            seriesLine.setOpacity(0);
            seriesYAnimMultiplier.setValue(0d);
        } else {
            seriesYAnimMultiplier.setValue(1d);
        }
        getPlotChildren().add(seriesLine);

        List<KeyFrame> keyFrames = new ArrayList<KeyFrame>();
        if (shouldAnimate()) {
            // animate in new series
            keyFrames.add(new KeyFrame(Duration.ZERO,
                new KeyValue(seriesLine.opacityProperty(), 0),
                new KeyValue(seriesYAnimMultiplier, 0)
            ));
            keyFrames.add(new KeyFrame(Duration.millis(200),
                new KeyValue(seriesLine.opacityProperty(), 1)
            ));
            keyFrames.add(new KeyFrame(Duration.millis(500),
                new KeyValue(seriesYAnimMultiplier, 1)
            ));
        }
        for (int j=0; j<series.getData().size(); j++) {
            SEXYChart.Data<X,Y> item = series.getData().get(j);
            final Node symbol = createSymbol(series, seriesIndex, item, j);
            if(symbol != null) {
                if (shouldAnimate()) symbol.setOpacity(0);
                getPlotChildren().add(symbol);
                if (shouldAnimate()) {
                    // fade in new symbol
                    keyFrames.add(new KeyFrame(Duration.ZERO, new KeyValue(symbol.opacityProperty(), 0)));
                    keyFrames.add(new KeyFrame(Duration.millis(200), new KeyValue(symbol.opacityProperty(), 1)));
                }
            }
        }
//        if (shouldAnimate()) animate(keyFrames.toArray(new KeyFrame[keyFrames.size()]));
    }
    private void updateDefaultColorIndex(final SEXYChart.Series<X,Y> series) {
        int clearIndex = seriesColorMap.get(series);
        series.getNode().getStyleClass().remove(DEFAULT_COLOR+clearIndex);
        for (int j=0; j < series.getData().size(); j++) {
            final Node node = series.getData().get(j).getNode();
            if(node!=null) {
                node.getStyleClass().remove(DEFAULT_COLOR+clearIndex);
            }
        }
    }

    @Override protected  void seriesRemoved(final SEXYChart.Series<X,Y> series) {
        updateDefaultColorIndex(series);
        // remove all symbol nodes
        seriesYMultiplierMap.remove(series);
        if (shouldAnimate()) {
            seriesRemoveTimeline = new Timeline(createSeriesRemoveTimeLine(series, 900));
            seriesRemoveTimeline.play();
        } else {
            getPlotChildren().remove(series.getNode());
            for (SEXYChart.Data<X,Y> d:series.getData()) getPlotChildren().remove(d.getNode());
            removeSeriesFromDisplay(series);
        }
    }

    /** @inheritDoc */
    @Override protected void layoutPlotChildren() {
        List<LineTo> constructedPath = new ArrayList<>(getDataSize());
        for (int seriesIndex=0; seriesIndex < getDataSize(); seriesIndex++) {
            SEXYChart.Series<X,Y> series = getData().get(seriesIndex);
            final DoubleProperty seriesYAnimMultiplier = seriesYMultiplierMap.get(series);
            if(series.getNode() instanceof  Path) {
                final ObservableList<PathElement> seriesLine = ((Path)series.getNode()).getElements();
                seriesLine.clear();
                constructedPath.clear();
                for (Iterator<SEXYChart.Data<X, Y>> it = getDisplayedDataIterator(series); it.hasNext(); ) {
                    SEXYChart.Data<X, Y> item = it.next();
                    double x = getXAxis().getDisplayPosition(item.getCurrentX());
                    double y = getYAxis().getDisplayPosition(
                            getYAxis().toRealValue(getYAxis().toNumericValue(item.getCurrentY()) * seriesYAnimMultiplier.getValue()));
                    if (Double.isNaN(x) || Double.isNaN(y)) {
                        continue;
                    }
                    constructedPath.add(new LineTo(x, y));

                    Node symbol = item.getNode();
                    if (symbol != null) {
                        final double w = symbol.prefWidth(-1);
                        final double h = symbol.prefHeight(-1);
                        symbol.resizeRelocate(x-(w/2), y-(h/2),w,h);
                    }
                }
                switch (getAxisSortingPolicy()) {
                    case X_AXIS:
                        Collections.sort(constructedPath, (e1, e2) -> Double.compare(e1.getX(), e2.getX()));
                        break;
                    case Y_AXIS:
                        Collections.sort(constructedPath, (e1, e2) -> Double.compare(e1.getY(), e2.getY()));
                        break;
                }

                if (!constructedPath.isEmpty()) {
                    LineTo first = constructedPath.get(0);
                    seriesLine.add(new MoveTo(first.getX(), first.getY()));
                    seriesLine.addAll(constructedPath);
                }
            }
        }
    }
    /** @inheritDoc */
    @Override void dataBeingRemovedIsAdded(SEXYChart.Data item, SEXYChart.Series series) {
        if (fadeSymbolTransition != null) {
            fadeSymbolTransition.setOnFinished(null);
            fadeSymbolTransition.stop();
        }
        if (dataRemoveTimeline != null) {
            dataRemoveTimeline.setOnFinished(null);
            dataRemoveTimeline.stop();
        }
        final Node symbol = item.getNode();
        if (symbol != null) getPlotChildren().remove(symbol);

        item.setSeries(null);
        removeDataItemFromDisplay(series, item);

        // restore values to item
        Double value = XYValueMap.get(item);
        if (value != null) {
            item.setYValue(value);
            item.setCurrentY(value);
        }
        XYValueMap.clear();
    }
    /** @inheritDoc */
    @Override void seriesBeingRemovedIsAdded(SEXYChart.Series<X,Y> series) {
        if (seriesRemoveTimeline != null) {
            seriesRemoveTimeline.setOnFinished(null);
            seriesRemoveTimeline.stop();
            getPlotChildren().remove(series.getNode());
            for (SEXYChart.Data<X,Y> d:series.getData()) getPlotChildren().remove(d.getNode());
            removeSeriesFromDisplay(series);
        }
    }

    private Timeline createDataRemoveTimeline(final SEXYChart.Data<X,Y> item, final Node symbol, final SEXYChart.Series<X,Y> series) {
        Timeline t = new Timeline();
        // save data values in case the same data item gets added immediately.
        XYValueMap.put(item, ((Number)item.getYValue()).doubleValue());

        t.getKeyFrames().addAll(new KeyFrame(Duration.ZERO, new KeyValue(item.currentYProperty(),
                item.getCurrentY()), new KeyValue(item.currentXProperty(),
                item.getCurrentX())),
                new KeyFrame(Duration.millis(500), actionEvent -> {
                    if (symbol != null) getPlotChildren().remove(symbol);
                    removeDataItemFromDisplay(series, item);
                    XYValueMap.clear();
                },
                new KeyValue(item.currentYProperty(),
                item.getYValue(), Interpolator.EASE_BOTH),
                new KeyValue(item.currentXProperty(),
                item.getXValue(), Interpolator.EASE_BOTH))
        );
        return t;
    }

    private Node createSymbol(SEXYChart.Series<X, Y> series, int seriesIndex, final SEXYChart.Data<X,Y> item, int itemIndex) {
        Node symbol = item.getNode();
        // check if symbol has already been created
        if (symbol == null && getCreateSymbols()) {
            symbol = new StackPane();
            symbol.setAccessibleRole(AccessibleRole.TEXT);
            symbol.setAccessibleRoleDescription("Point");
            symbol.focusTraversableProperty().bind(Platform.accessibilityActiveProperty());
            item.setNode(symbol);
        }
        // set symbol styles
        if (symbol != null) symbol.getStyleClass().addAll("chart-line-symbol", "series" + seriesIndex,
                "data" + itemIndex, series.defaultColorStyleClass);
        return symbol;
    }

    /**
     * This is called whenever a series is added or removed and the legend needs to be updated
     */
     @Override protected void updateLegend() {
        legend.getItems().clear();
        if (getData() != null) {
            for (int seriesIndex=0; seriesIndex < getData().size(); seriesIndex++) {
                SEXYChart.Series<X,Y> series = getData().get(seriesIndex);
                Legend.LegendItem legenditem = new Legend.LegendItem(series.getName());
                legenditem.getSymbol().getStyleClass().addAll("chart-line-symbol", "series"+seriesIndex, series.defaultColorStyleClass);
                legend.getItems().add(legenditem);
            }
        }
        if (legend.getItems().size() > 0) {
            if (getLegend() == null) {
                setLegend(legend);
            }
        } else {
            setLegend(null);
        }
    }

    // -------------- STYLESHEET HANDLING --------------------------------------

    private static class StyleableProperties {
        private static final CssMetaData<SELineChart<?,?>,Boolean> CREATE_SYMBOLS =
            new CssMetaData<SELineChart<?,?>,Boolean>("-fx-create-symbols",
                BooleanConverter.getInstance(), Boolean.TRUE) {

            @Override
            public boolean isSettable(SELineChart<?,?> node) {
                return node.createSymbols == null || !node.createSymbols.isBound();
            }

            @Override
            public StyleableProperty<Boolean> getStyleableProperty(SELineChart<?,?> node) {
                return (StyleableProperty<Boolean>)(WritableValue<Boolean>)node.createSymbolsProperty();
            }
        };

        private static final List<CssMetaData<? extends Styleable, ?>> STYLEABLES;
        static {
            final List<CssMetaData<? extends Styleable, ?>> styleables =
                new ArrayList<CssMetaData<? extends Styleable, ?>>(SEXYChart.getClassCssMetaData());
            styleables.add(CREATE_SYMBOLS);
            STYLEABLES = Collections.unmodifiableList(styleables);
        }
    }

    /**
     * @return The CssMetaData associated with this class, which may include the
     * CssMetaData of its super classes.
     * @since JavaFX 8.0
     */
    public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {
        return StyleableProperties.STYLEABLES;
    }

    /**
     * {@inheritDoc}
     * @since JavaFX 8.0
     */
    @Override
    public List<CssMetaData<? extends Styleable, ?>> getCssMetaData() {
        return getClassCssMetaData();
    }

    /**
     * This enum defines a policy for {@link SELineChart#axisSortingPolicyProperty()}.
     * @since JavaFX 8u40
     */
    public static enum SortingPolicy {
        /**
         * The data should be left in the order defined by the list in {@link javafx.scene.chart.SELineChart#dataProperty()}.
         */
        NONE,
        /**
         * The data is ordered by x axis.
         */
        X_AXIS,
        /**
         * The data is ordered by y axis.
         */
        Y_AXIS
    }
}
