/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomocomd.qsartomocomd.gui.descriptors.tomocomd.aggregations;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.tomocomd.qsartomocomdlib.configuration.descriptors.tomocomd.params.aggregations.StatisticsAggConf;
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
public class StatisticsAggController implements Initializable {
    
    private Stage stage;
    private StatisticsAggConf sta;

    @FXML
    private JFXCheckBox sdAgreCheck;
    @FXML
    private JFXCheckBox vcAgreCheck;
    @FXML
    private JFXCheckBox q1AgreCheck;
    @FXML
    private JFXCheckBox q2AgreCheck;
    @FXML
    private JFXCheckBox q3AgreCheck;
    @FXML
    private JFXCheckBox i50AgreCheck;
    @FXML
    private JFXCheckBox vAgreCheck;
    @FXML
    private JFXCheckBox sAgreCheck;
    @FXML
    private JFXCheckBox mnAgreCheck;
    @FXML
    private JFXCheckBox mxAgreCheck;
    @FXML
    private JFXCheckBox raAgreCheck;
    @FXML
    private JFXCheckBox kAgreCheck;
    @FXML
    private JFXButton okStaMidasButton;
    @FXML
    private JFXButton utaStaBut;
    @FXML
    private JFXButton taStaBut;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public StatisticsAggConf getSta() {
        return sta;
    }

    public void setSta(StatisticsAggConf sta) {
        this.sta = sta;
        setFromConf();
    }

    public void setFromConf() {
        vAgreCheck.setSelected(sta.getV());
        sAgreCheck.setSelected(sta.getS());
        kAgreCheck.setSelected(sta.getK());
        sdAgreCheck.setSelected(sta.getSd());
        vcAgreCheck.setSelected(sta.getVc());
        raAgreCheck.setSelected(sta.getRa());
        q1AgreCheck.setSelected(sta.getQ1());
        q2AgreCheck.setSelected(sta.getQ2());
        q3AgreCheck.setSelected(sta.getQ3());
        i50AgreCheck.setSelected(sta.getI50());
        mnAgreCheck.setSelected(sta.getMn());
        mxAgreCheck.setSelected(sta.getMx());
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setAll(boolean set) {
        vAgreCheck.setSelected(set);
        sAgreCheck.setSelected(set);
        kAgreCheck.setSelected(set);
        sdAgreCheck.setSelected(set);
        vcAgreCheck.setSelected(set);
        raAgreCheck.setSelected(set);
        q1AgreCheck.setSelected(set);
        q2AgreCheck.setSelected(set);
        q3AgreCheck.setSelected(set);
        i50AgreCheck.setSelected(set);
        mnAgreCheck.setSelected(set);
        mxAgreCheck.setSelected(set);
    }

    @FXML
    private void okStaAction(ActionEvent event) {
        sta = new StatisticsAggConf(vAgreCheck.isSelected(), sAgreCheck.isSelected(), kAgreCheck.isSelected(),
                sdAgreCheck.isSelected(), vcAgreCheck.isSelected(), raAgreCheck.isSelected(), q1AgreCheck.isSelected(),
                q2AgreCheck.isSelected(), q3AgreCheck.isSelected(), i50AgreCheck.isSelected(), mxAgreCheck.isSelected(),
                mnAgreCheck.isSelected());
        stage.close();
    }

    @FXML
    private void unSelectStaAction(ActionEvent event) {
        setAll(false);
    }

    @FXML
    private void selectStaAction(ActionEvent event) {
        setAll(true);
    }

}
