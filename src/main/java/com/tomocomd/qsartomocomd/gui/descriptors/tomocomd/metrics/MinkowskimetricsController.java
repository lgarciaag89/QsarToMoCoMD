/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomocomd.qsartomocomd.gui.descriptors.tomocomd.metrics;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.tomocomd.qsartomocomdlib.configuration.descriptors.tomocomd.midas.params.metrics.MinkoskiMidasMetricsConf;
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
public class MinkowskimetricsController implements Initializable {

    private Stage stage;
    private MinkoskiMidasMetricsConf mink;

    @FXML
    private JFXCheckBox m3MetricsCheck;
    @FXML
    private JFXCheckBox m8MetricsCheck;
    @FXML
    private JFXCheckBox m1MetricsCheck;
    @FXML
    private JFXCheckBox m2MetricsCheck;
    @FXML
    private JFXCheckBox m4MetricsCheck;
    @FXML
    private JFXCheckBox m6MetricsCheck;
    @FXML
    private JFXCheckBox m5MetricsCheck;
    @FXML
    private JFXCheckBox m7MetricsCheck;
    @FXML
    private JFXButton taButtonMinMetrBut;
    @FXML
    private JFXButton utaButtonMinMetrBut;
    @FXML
    private JFXButton okMinkMetriMidasButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setFromConf() {
        m3MetricsCheck.setSelected(mink.isM3());
        m8MetricsCheck.setSelected(mink.isM8());
        m1MetricsCheck.setSelected(mink.isM1());
        m2MetricsCheck.setSelected(mink.isM2());
        m4MetricsCheck.setSelected(mink.isM4());
        m6MetricsCheck.setSelected(mink.isM6());
        m5MetricsCheck.setSelected(mink.isM5());
        m7MetricsCheck.setSelected(mink.isM7());
    }

    public void setStage(Stage s) {
        stage = s;
    }

    public void setMink(MinkoskiMidasMetricsConf mink) {
        this.mink = mink;
        setFromConf();
    }

    public void setAll(boolean sel) {
        m3MetricsCheck.setSelected(sel);
        m8MetricsCheck.setSelected(sel);
        m1MetricsCheck.setSelected(sel);
        m2MetricsCheck.setSelected(sel);
        m4MetricsCheck.setSelected(sel);
        m6MetricsCheck.setSelected(sel);
        m5MetricsCheck.setSelected(sel);
        m7MetricsCheck.setSelected(sel);
        mink = new MinkoskiMidasMetricsConf(m1MetricsCheck.isSelected(), m2MetricsCheck.isSelected(), m3MetricsCheck.isSelected(),
                m4MetricsCheck.isSelected(), m5MetricsCheck.isSelected(), m6MetricsCheck.isSelected(), m7MetricsCheck.isSelected(),
                m8MetricsCheck.isSelected());
    }

    @FXML
    private void selectMinkAction(ActionEvent event) {
        setAll(true);
    }

    @FXML
    private void unSelectMinkAction(ActionEvent event) {
        setAll(false);
    }

    @FXML
    private void okMinkMidAction(ActionEvent event) {
        mink = new MinkoskiMidasMetricsConf(m1MetricsCheck.isSelected(), m2MetricsCheck.isSelected(), m3MetricsCheck.isSelected(),
                m4MetricsCheck.isSelected(), m5MetricsCheck.isSelected(), m6MetricsCheck.isSelected(), m7MetricsCheck.isSelected(),
                m8MetricsCheck.isSelected());
        stage.close();
    }

    public MinkoskiMidasMetricsConf getMink() {
        return mink;
    }

}
