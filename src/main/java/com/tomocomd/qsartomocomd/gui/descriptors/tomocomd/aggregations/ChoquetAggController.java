/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomocomd.qsartomocomd.gui.descriptors.tomocomd.aggregations;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.tomocomd.qsartomocomdlib.configuration.descriptors.tomocomd.params.aggregations.ChoquetAggConf;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author potter
 */
public class ChoquetAggController implements Initializable {

     @FXML
    private JFXCheckBox actChockCheck;
    @FXML
    private JFXCheckBox agr1ChoqCheck;
    @FXML
    private JFXCheckBox agr2ChoqCheck;
    @FXML
    private JFXCheckBox desChoqCheck;
    @FXML
    private JFXCheckBox ascChoqCheck;
    @FXML
    private JFXButton okChoMidasButton;
    @FXML
    private JFXButton taChoBut;

    private Stage stage;
    private ChoquetAggConf cho;
    @FXML
    private HBox boxChoquet;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        setDisable();
        boxChoquet.setDisable(true);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public ChoquetAggConf getCho() {
        return cho;
    }

    public void setCho(ChoquetAggConf cho) {
        this.cho = cho;
        setFromConf();
    }

    public void setFromConf() {
//        ascChoqCheck.setSelected(cho.isAsc());
//        desChoqCheck.setSelected(cho.isDes());
//        agr1ChoqCheck.setSelected(cho.isAgg1());
//        agr2ChoqCheck.setSelected(cho.isAgg2());

        if(cho.isSelected()){
            actChockCheck.setSelected(true);
        }else{
            actChockCheck.setSelected(false);
        }
    
//        setDisable();
    }

    @FXML
    private void actChockAction(ActionEvent event) {
//        setDisable();
        cho.setSelected(actChockCheck.isSelected());
    }

    private void setDisable() {
        
        if (actChockCheck.isSelected()) {
            ascChoqCheck.setDisable(false);
            desChoqCheck.setDisable(false);
            agr1ChoqCheck.setDisable(false);
            agr2ChoqCheck.setDisable(false);
            taChoBut.setDisable(false);
            setAll(true);
        } else {
            setAll(false);
            ascChoqCheck.setDisable(true);
            desChoqCheck.setDisable(true);
            agr1ChoqCheck.setDisable(true);
            agr2ChoqCheck.setDisable(true);
            taChoBut.setDisable(true);
        }
    }

    public void setDefault() {
        actChockCheck.setSelected(true);
//        setAll(true);
    }

    @FXML
    private void okChoAction(ActionEvent event) {
//        if (actChockCheck.isSelected()) {
//            cho = new ChoquetAggConf(ascChoqCheck.isSelected(), desChoqCheck.isSelected(),
//                    agr1ChoqCheck.isSelected(), agr2ChoqCheck.isSelected());
//        } else {
//            cho = new ChoquetAggConf(false, false, false, false);
//        }
        stage.close();
    }

    @FXML
    private void selectChoAction(ActionEvent event) {
        setAll(true);
    }

    public void setAll(boolean set) {
        ascChoqCheck.setSelected(set);
        desChoqCheck.setSelected(set);
        agr1ChoqCheck.setSelected(set);
        agr2ChoqCheck.setSelected(set);
        cho = new ChoquetAggConf(set, set, set, set);
    }
    
}
