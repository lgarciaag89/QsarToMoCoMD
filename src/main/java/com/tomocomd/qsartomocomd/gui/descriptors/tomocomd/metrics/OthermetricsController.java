/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomocomd.qsartomocomd.gui.descriptors.tomocomd.metrics;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.tomocomd.qsartomocomdlib.configuration.descriptors.tomocomd.midas.params.metrics.OtherMidasMetricsConf;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author potter
 */
public class OthermetricsController implements Initializable {

    private Stage stage;
    private OtherMidasMetricsConf other;

    @FXML
    private JFXCheckBox m12MetricsCheck;
    @FXML
    private JFXCheckBox m10MetricsCheck;
    @FXML
    private JFXCheckBox m11MetricsCheck;
    @FXML
    private JFXCheckBox m13MetricsCheck;
    @FXML
    private JFXCheckBox m15MetricsCheck;
    @FXML
    private JFXCheckBox m14MetricsCheck;
    @FXML
    private JFXCheckBox m16MetricsCheck;
    @FXML
    private JFXButton utaButtonOthMetrBut;
    @FXML
    private JFXButton taButtonOthMetrBut;
    @FXML
    private JFXButton okOthMetrBut;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setOther(OtherMidasMetricsConf other) {
        this.other = other;
        setFromConf();
    }

    public void setFromConf() {
        m10MetricsCheck.setSelected(other.isM10());
        m11MetricsCheck.setSelected(other.isM11());
        m12MetricsCheck.setSelected(other.isM12());
        m13MetricsCheck.setSelected(other.isM13());
        m14MetricsCheck.setSelected(other.isM14());
        m16MetricsCheck.setSelected(other.isM15());
        m15MetricsCheck.setSelected(other.isM16());
    }

    public void setStage(Stage s) {
        stage = s;
    }

    public void setAll(boolean sel) {
        m10MetricsCheck.setSelected(sel);
        m11MetricsCheck.setSelected(sel);
        m12MetricsCheck.setSelected(sel);
        m13MetricsCheck.setSelected(sel);
        m14MetricsCheck.setSelected(sel);
        m16MetricsCheck.setSelected(sel);
        m15MetricsCheck.setSelected(sel);
        other = new OtherMidasMetricsConf(m10MetricsCheck.isSelected(), m11MetricsCheck.isSelected(), m12MetricsCheck.isSelected(),
                m13MetricsCheck.isSelected(), m14MetricsCheck.isSelected(), m15MetricsCheck.isSelected(), m16MetricsCheck.isSelected());
    }

    @FXML
    private void unSelectOthAction(ActionEvent event) {
        setAll(false);
    }

    @FXML
    private void selectOthAction(ActionEvent event) {
        setAll(true);
    }

    @FXML
    private void okOthMidasAction(ActionEvent event) {
        other = new OtherMidasMetricsConf(m10MetricsCheck.isSelected(), m11MetricsCheck.isSelected(), m12MetricsCheck.isSelected(),
                m13MetricsCheck.isSelected(), m14MetricsCheck.isSelected(), m15MetricsCheck.isSelected(), m16MetricsCheck.isSelected());
        stage.close();
    }

    public OtherMidasMetricsConf getOther() {
        return other;
    }

}
