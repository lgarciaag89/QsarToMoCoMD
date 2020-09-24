/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomocomd.qsartomocomd.gui.descriptors.tomocomd.algForms;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.tomocomd.qsartomocomdlib.configuration.descriptors.tomocomd.params.algforms.BilinearConf;
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
public class BituplesController implements Initializable {

    private Stage stage;
    private BilinearConf conf;

    @FXML
    private JFXCheckBox bilinear2Tuplas;
    @FXML
    private JFXCheckBox linear2Tuplas;
    @FXML
    private JFXCheckBox qua2Tuplas;
    @FXML
    private JFXButton ok2tuplasBut;
    @FXML
    private JFXButton uta2tuplasBut;
    @FXML
    private JFXButton ta2tuplasBut;

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

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public BilinearConf getConf() {
        return conf;
    }

    public void setConf(BilinearConf conf) {
        this.conf = new BilinearConf(conf);
        setFromConf();
    }
        
    public void setFromConf(){
        linear2Tuplas.setSelected(conf.isL());
        bilinear2Tuplas.setSelected(conf.isB());
        qua2Tuplas.setSelected(conf.isQ()); 
    }

    @FXML
    private void ok2tuplasAction(ActionEvent event) {
        conf = new BilinearConf(linear2Tuplas.isSelected(), bilinear2Tuplas.isSelected(), qua2Tuplas.isSelected());
        stage.close();
    }

    @FXML
    private void uta2tuplasAction(ActionEvent event) {
        linear2Tuplas.setSelected(false);
        bilinear2Tuplas.setSelected(false);
        qua2Tuplas.setSelected(false);
    }

    @FXML
    private void ta2tuplasAction(ActionEvent event) {
        linear2Tuplas.setSelected(true);
        bilinear2Tuplas.setSelected(true);
        qua2Tuplas.setSelected(true);
    }
}
