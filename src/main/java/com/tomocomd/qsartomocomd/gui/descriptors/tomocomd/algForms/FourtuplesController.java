/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomocomd.qsartomocomd.gui.descriptors.tomocomd.algForms;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.tomocomd.qsartomocomdlib.configuration.descriptors.tomocomd.params.algforms.FourlinearConf;
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
public class FourtuplesController implements Initializable {

    private Stage stage;
    private FourlinearConf conf;

    @FXML
    private JFXCheckBox Quheck;
    @FXML
    private JFXCheckBox quqdCheck;
    @FXML
    private JFXCheckBox qufCheck;
    @FXML
    private JFXButton ok4tuplasBut;
    @FXML
    private JFXButton uta4tuplasBut;
    @FXML
    private JFXButton ta4tuplasBut;
    @FXML
    private JFXCheckBox qubCheck;
    @FXML
    private JFXCheckBox qutrCheck;
    @FXML
    private JFXCheckBox qucbCheck;
    @FXML
    private JFXCheckBox quqtrCheck;

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

    public FourlinearConf getConf() {
        return conf;
    }

    public void setConf(FourlinearConf conf) {
        this.conf = conf;
        setFromConf();
    }

    public void setFromConf() {
        Quheck.setSelected(conf.isQu());
        qubCheck.setSelected(conf.isQub());
        qucbCheck.setSelected(conf.isQucb());
        qufCheck.setSelected(conf.isQuf());
        quqdCheck.setSelected(conf.isQuqd());
        quqtrCheck.setSelected(conf.isQuqtr());
        qutrCheck.setSelected(conf.isQutr());
    }

    @FXML
    private void ok4tuplasAction(ActionEvent event) {
        conf  = new FourlinearConf(Quheck.isSelected(), quqtrCheck.isSelected(),qutrCheck.isSelected(), 
                qucbCheck.isSelected(), qubCheck.isSelected(), qufCheck.isSelected(), quqdCheck.isSelected());
        stage.close();
    }

    @FXML
    private void uta4tuplasAction(ActionEvent event) {
        Quheck.setSelected(false);
        qubCheck.setSelected(false);
        qucbCheck.setSelected(false);
        qufCheck.setSelected(false);
        quqdCheck.setSelected(false);
        quqtrCheck.setSelected(false);
        qutrCheck.setSelected(false);
    }

    @FXML
    private void ta4tuplasAction(ActionEvent event) {
        Quheck.setSelected(true);
        qubCheck.setSelected(true);
        qucbCheck.setSelected(true);
        qufCheck.setSelected(true);
        quqdCheck.setSelected(true);
        quqtrCheck.setSelected(true);
        qutrCheck.setSelected(true);
    }
}
