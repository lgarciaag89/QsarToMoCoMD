/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomocomd.qsartomocomd.gui.charts.sechart;

import com.sun.javafx.scene.control.skin.Utils;
import javafx.scene.chart.Chart;

/**
 *
 * @author potter
 */
public abstract class SEChart extends Chart {

    public SEChart() {
        super();
    }

    @Override
    protected void layoutChildren() {

        double top = snappedTopInset();
        double left = snappedLeftInset();
        double bottom = snappedBottomInset();
        double right = snappedRightInset();
        final double width = getWidth();
        final double height = getHeight();

        if (getTitle() != null) {
            final double titleHeight = snapSize(getChildren().get(0).prefHeight(width - left - right));
            getChildren().get(0).resizeRelocate(left, top, width - left - right, titleHeight);
            top += titleHeight;
        } else {
            getChildren().get(0).setVisible(false);
        }

        if (getLegend() != null) {
            final double legendHeight = snapSize(getLegend().prefHeight(width - left - right));
            final double legendWidth = Utils.boundedSize(snapSize(getLegend().prefWidth(legendHeight)), 0, width - left - right);
            getLegend().resizeRelocate(width - right - 3 * left - legendWidth, bottom + top + legendHeight, legendWidth, legendHeight);
        }

        // whats left is for the chart content
        getChildren().get(1).resizeRelocate(left, top, width - left - right, height - top - bottom);
    }
}
