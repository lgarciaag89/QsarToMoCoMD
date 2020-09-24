/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomocomd.qsartomocomd.gui.descriptors.tomocomd.aggregations;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.tomocomd.qsartomocomdlib.configuration.descriptors.tomocomd.params.aggregations.ClassAggConf;
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
public class ClasicalAggController implements Initializable {
    
    private Stage stage;
    private ClassAggConf clas;

    @FXML
    private JFXCheckBox acAgreCheck;
    @FXML
    private JFXCheckBox tsAgreCheck;
    @FXML
    private JFXCheckBox gvAgreCheck;
    @FXML
    private JFXCheckBox ibAgreCheck;
    @FXML
    private JFXCheckBox khAgreCheck;
    @FXML
    private JFXCheckBox esAgreCheck;
    @FXML
    private JFXCheckBox ticAgreCheck;
    @FXML
    private JFXCheckBox micAgreCheck;
    @FXML
    private JFXCheckBox sicAgreCheck;
    @FXML
    private JFXButton taClaBut;
    @FXML
    private JFXButton utaClaBut;
    @FXML
    private JFXButton okClaMidasButton;
    @FXML
    private JFXCheckBox noneAgreCheck;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setClas(ClassAggConf clas) {
        this.clas = new ClassAggConf(clas);
        setFromConf();
    }

    public void setFromConf() {
        ticAgreCheck.setSelected(clas.isTic());
        micAgreCheck.setSelected(clas.isMic());
        sicAgreCheck.setSelected(clas.isSic());
        esAgreCheck.setSelected(clas.isEs());
        acAgreCheck.setSelected(clas.isAc());
        gvAgreCheck.setSelected(clas.isGv());
        tsAgreCheck.setSelected(clas.isTs());
        ibAgreCheck.setSelected(clas.isIb());
        khAgreCheck.setSelected(clas.isKh());
        noneAgreCheck.setSelected(clas.isNone());
    }

    private void setAll(boolean set) {
        ticAgreCheck.setSelected(set);
        micAgreCheck.setSelected(set);
        sicAgreCheck.setSelected(set);
        esAgreCheck.setSelected(set);
        acAgreCheck.setSelected(set);
        gvAgreCheck.setSelected(set);
        tsAgreCheck.setSelected(set);
        ibAgreCheck.setSelected(set);
        khAgreCheck.setSelected(set);
        noneAgreCheck.setSelected(set);
        
    }

    @FXML
    private void selectClaAction(ActionEvent event) {
        setAll(true);
    }

    public ClassAggConf getClas() {
        return clas;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void unSelectClaAction(ActionEvent event) {
        setAll(false);
    }

    @FXML
    private void okClaAction(ActionEvent event) {
        clas = new ClassAggConf(acAgreCheck.isSelected(), gvAgreCheck.isSelected(),
                tsAgreCheck.isSelected(), ibAgreCheck.isSelected(), esAgreCheck.isSelected(),
                khAgreCheck.isSelected(), micAgreCheck.isSelected(), ticAgreCheck.isSelected(),
                sicAgreCheck.isSelected(),noneAgreCheck.isSelected());
        stage.close();
    }

}
