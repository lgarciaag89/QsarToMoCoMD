/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomocomd.qsartomocomd.gui.charts.sechart;

import com.sun.javafx.charts.Legend;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.chart.Axis;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author potter
 */
public class LineChartLegend extends SELineChart<Number, Number> {

    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;

    public LineChartLegend(Axis xAxis, Axis yAxis) {
        super(xAxis, yAxis);
        getLegend().setOnMousePressed(legendOnMousePressedEventHandler);
        getLegend().setOnMouseDragged(legendOnMouseDraggedEventHandler);

    }

    public Node getLegendMovil() {
        return getLegend();
    }

    EventHandler<MouseEvent> legendOnMousePressedEventHandler
            = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            if (t.getButton() == MouseButton.PRIMARY) {
                orgSceneX = t.getSceneX();
                orgSceneY = t.getSceneY();
                orgTranslateX = ((Legend) (t.getSource())).getTranslateX();
                orgTranslateY = ((Legend) (t.getSource())).getTranslateY();
            }
        }
    };

    EventHandler<MouseEvent> legendOnMouseDraggedEventHandler
            = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            if (t.getButton() == MouseButton.PRIMARY) {
                double offsetX = t.getSceneX() - orgSceneX;
                double offsetY = t.getSceneY() - orgSceneY;
                double newTranslateX = orgTranslateX + offsetX;
                double newTranslateY = orgTranslateY + offsetY;

                ((Legend) (t.getSource())).setTranslateX(newTranslateX);
                ((Legend) (t.getSource())).setTranslateY(newTranslateY);
            }
        }
    };
}
