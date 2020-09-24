/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomocomd.qsartomocomd.gui.descriptors.tomocomd.aggregations;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.tomocomd.qsartomocomdlib.configuration.descriptors.tomocomd.params.aggregations.MeansAggConf;
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
public class MeansAggController implements Initializable {
    
    private Stage stage;

    private MeansAggConf means;

    @FXML
    private JFXButton okMeanMidasButton;
    @FXML
    private JFXCheckBox gmAgreCheck;
    @FXML
    private JFXCheckBox amAgreCheck;
    @FXML
    private JFXCheckBox p2AgreCheck;
    @FXML
    private JFXCheckBox p3AgreCheck;
    @FXML
    private JFXCheckBox hmAgreCheck;
    @FXML
    private JFXButton utaMeanBut;
    @FXML
    private JFXButton taMeanBut;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public MeansAggConf getMeans() {
        return means;
    }

    public void setMeans(MeansAggConf means) {
        this.means = new MeansAggConf(means);
        setFromConf();
    }

    public void setFromConf() {
        gmAgreCheck.setSelected(means.isGm());
        amAgreCheck.setSelected(means.isAm());
        p2AgreCheck.setSelected(means.isP2());
        p3AgreCheck.setSelected(means.isP3());
        hmAgreCheck.setSelected(means.isHm());
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setAll(boolean set) {
        gmAgreCheck.setSelected(set);
        amAgreCheck.setSelected(set);
        p2AgreCheck.setSelected(set);
        p3AgreCheck.setSelected(set);
        hmAgreCheck.setSelected(set);
    }

    @FXML
    private void okMeanAction(ActionEvent event) {
        means = new MeansAggConf(gmAgreCheck.isSelected(), amAgreCheck.isSelected(),
                p2AgreCheck.isSelected(), p3AgreCheck.isSelected(), hmAgreCheck.isSelected());
        stage.close();
    }

    @FXML
    private void unSelectMeanAction(ActionEvent event) {
        setAll(false);
    }

    @FXML
    private void selectMeanAction(ActionEvent event) {
        setAll(true);
    }
    
}
