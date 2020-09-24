/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomocomd.qsartomocomd.gui.descriptors.tomocomd.algForms;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.tomocomd.qsartomocomdlib.configuration.descriptors.tomocomd.params.algforms.ThreelinearConf;
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
public class ThreetuplesController implements Initializable {

    private Stage stage;
    private ThreelinearConf conf;

    @FXML
    private JFXCheckBox trCheck;
    @FXML
    private JFXCheckBox trqbCheck;
    @FXML
    private JFXCheckBox trfCheck;
    @FXML
    private JFXButton ok3tuplasBut;
    @FXML
    private JFXButton uta3tuplasBut;
    @FXML
    private JFXButton ta3tuplasBut;
    @FXML
    private JFXCheckBox trbCheck;
    @FXML
    private JFXCheckBox trcCheck;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public ThreelinearConf getConf() {
        return conf;
    }

    public void setConf(ThreelinearConf conf) {
        this.conf = new ThreelinearConf(conf);
        setFromConf();
    }

    public void setFromConf() {
        trCheck.setSelected(conf.isTr());
        trbCheck.setSelected(conf.isTrb());
        trcCheck.setSelected(conf.isTrc());
        trfCheck.setSelected(conf.isTrf());
        trqbCheck.setSelected(conf.isTrqb());
    }

    @FXML
    private void ok3tuplasAction(ActionEvent event) {
        conf = new ThreelinearConf(trCheck.isSelected(), trbCheck.isSelected(), trcCheck.isSelected(), trqbCheck.isSelected(), trfCheck.isSelected());
        stage.close();
    }

    @FXML
    private void uta3tuplasAction(ActionEvent event) {
        trCheck.setSelected(false);
        trbCheck.setSelected(false);
        trcCheck.setSelected(false);
        trfCheck.setSelected(false);
        trqbCheck.setSelected(false);
    }

    @FXML
    private void ta3tuplasAction(ActionEvent event) {
        trCheck.setSelected(true);
        trbCheck.setSelected(true);
        trcCheck.setSelected(true);
        trfCheck.setSelected(true);
        trqbCheck.setSelected(true);
    }
}
