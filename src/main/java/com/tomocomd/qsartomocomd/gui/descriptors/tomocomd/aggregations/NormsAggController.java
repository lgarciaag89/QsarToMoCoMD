/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomocomd.qsartomocomd.gui.descriptors.tomocomd.aggregations;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.tomocomd.qsartomocomdlib.configuration.descriptors.tomocomd.params.aggregations.NormsAggConf;
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
public class NormsAggController implements Initializable {
    private Stage stage;
    private NormsAggConf norm;
    

    @FXML
    private JFXCheckBox n1AgrCheck;
    @FXML
    private JFXCheckBox N2AgrCheck;
    @FXML
    private JFXCheckBox n3AgreCheck;
    @FXML
    private JFXButton okNormMidasButton;
    @FXML
    private JFXButton utaNormBut;
    @FXML
    private JFXButton taNormBut;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public Stage getStage() {
        return stage;
    }

    public void setFromConf(){
        n1AgrCheck.setSelected(norm.isN1());
        N2AgrCheck.setSelected(norm.isN2());
        n3AgreCheck.setSelected(norm.isN3());
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public NormsAggConf getNorm() {
        return norm;
    }

    public void setNorm(NormsAggConf norm) {
        this.norm = new NormsAggConf(norm);
        setFromConf();
    }
    
    public void setALL(boolean sel){
        n1AgrCheck.setSelected(sel);
        N2AgrCheck.setSelected(sel);
        n3AgreCheck.setSelected(sel);       
    }  

    @FXML
    private void okNormAction(ActionEvent event) {
        norm.setN1(n1AgrCheck.isSelected());
        norm.setN2(N2AgrCheck.isSelected());
        norm.setN3(n3AgreCheck.isSelected());
        stage.close();
    }

    @FXML
    private void unSelectNormAction(ActionEvent event) {
        setALL(false);
    }

    @FXML
    private void selectNormAction(ActionEvent event) {
        setALL(true);
    }
    
}
