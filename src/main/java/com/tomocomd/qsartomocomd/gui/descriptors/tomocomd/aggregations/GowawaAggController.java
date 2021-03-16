/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomocomd.qsartomocomd.gui.descriptors.tomocomd.aggregations;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.tomocomd.qsartomocomdlib.configuration.descriptors.tomocomd.params.aggregations.GowawaAggConf;
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
public class GowawaAggController implements Initializable {

    @FXML
    private JFXCheckBox gowActCheck;
    @FXML
    private JFXCheckBox noneGowaCheck;
    @FXML
    private JFXCheckBox sowaGOWAAgreCheck;
    @FXML
    private JFXCheckBox wowaGOWAAgreCheck;
    @FXML
    private JFXCheckBox agre1AgreCheck;
    @FXML
    private JFXCheckBox agre2AgreCheck;
    @FXML
    private JFXCheckBox exp2GOWAAgreCheck;
    @FXML
    private JFXCheckBox exp1GOWAAgreCheck;
    @FXML
    private JFXCheckBox exp1WGMAgreCheck;
    @FXML
    private JFXCheckBox noneWGMCheck;
    @FXML
    private JFXCheckBox sowaWGMAgreCheck;
    @FXML
    private JFXCheckBox wowaWGMAgreCheck;
    @FXML
    private JFXCheckBox exp2WGMAgreCheck;
    @FXML
    private JFXButton okGowMidasButton;
    @FXML
    private JFXButton taNormBut;

    private Stage stage;

    private GowawaAggConf gow;
    @FXML
    private HBox boxOptions;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        boxOptions.setDisable(true);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setGow(GowawaAggConf gow) {
        this.gow = gow;
        setFromConf();
    }

    public void setFromConf() {
        sowaGOWAAgreCheck.setSelected(gow.isSowaGOWA());
        wowaGOWAAgreCheck.setSelected(gow.isWowaGOWA());
        agre1AgreCheck.setSelected(gow.isAgre1Gowa());
        agre2AgreCheck.setSelected(gow.isAgre2Gowa());
        exp1GOWAAgreCheck.setSelected(gow.isExp1GOWA());
        exp2GOWAAgreCheck.setSelected(gow.isExp2GOWA());
        sowaWGMAgreCheck.setSelected(gow.isSowaWgm());
        wowaWGMAgreCheck.setSelected(gow.isWowaWgm());
        exp1WGMAgreCheck.setSelected(gow.isExp1Wgm());
        exp2WGMAgreCheck.setSelected(gow.isExp2Wgm());
        noneGowaCheck.setSelected(gow.isNoneGowa());
        noneWGMCheck.setSelected(gow.isNoneWgm());

        if (gow.isSelected()) {
            gowActCheck.setSelected(true);
        } else {
            gowActCheck.setSelected(false);
        }
//        setDisable(!gow.isSelected());
    }

    private void setDisable(boolean flag) {
        boxOptions.setDisable(flag);
    }

    public GowawaAggConf getGow() {
        return gow;
    }

    private void setAll(boolean set) {
        sowaGOWAAgreCheck.setSelected(set);
        wowaGOWAAgreCheck.setSelected(set);
        agre1AgreCheck.setSelected(set);
        agre2AgreCheck.setSelected(set);
        exp1GOWAAgreCheck.setSelected(set);
        exp2GOWAAgreCheck.setSelected(set);
        sowaWGMAgreCheck.setSelected(set);
        wowaWGMAgreCheck.setSelected(set);
        exp1WGMAgreCheck.setSelected(set);
        exp2WGMAgreCheck.setSelected(set);
        noneGowaCheck.setSelected(set);
        noneWGMCheck.setSelected(set);
        gow = new GowawaAggConf(set, set, set, set, set, set, set, set, set, set, set, set);
//        setDisable(!set);
    }

    @FXML
    private void actGowAction(ActionEvent event) {
        if (gowActCheck.isSelected()) {
            sowaGOWAAgreCheck.setDisable(false);
            wowaGOWAAgreCheck.setDisable(false);
            agre1AgreCheck.setDisable(false);
            agre2AgreCheck.setDisable(false);
            exp1GOWAAgreCheck.setDisable(false);
            exp2GOWAAgreCheck.setDisable(false);
            sowaWGMAgreCheck.setDisable(false);
            wowaWGMAgreCheck.setDisable(false);
            exp1WGMAgreCheck.setDisable(false);
            exp2WGMAgreCheck.setDisable(false);
            noneGowaCheck.setDisable(false);
            noneWGMCheck.setDisable(false);
            setAll(true);
        } else {
            setAll(false);
            sowaGOWAAgreCheck.setDisable(true);
            wowaGOWAAgreCheck.setDisable(true);
            agre1AgreCheck.setDisable(true);
            agre2AgreCheck.setDisable(true);
            exp1GOWAAgreCheck.setDisable(true);
            exp2GOWAAgreCheck.setDisable(true);
            sowaWGMAgreCheck.setDisable(true);
            wowaWGMAgreCheck.setDisable(true);
            exp1WGMAgreCheck.setDisable(true);
            exp2WGMAgreCheck.setDisable(true);
            noneGowaCheck.setDisable(true);
            noneWGMCheck.setDisable(true);
        }

    }

    @FXML
    private void okGowAction(ActionEvent event) {
//        if (gowActCheck.isSelected()) {
//            gow = new GowawaAggConf(sowaGOWAAgreCheck.isSelected(), wowaGOWAAgreCheck.isSelected(),
//                    agre1AgreCheck.isSelected(), agre2AgreCheck.isSelected(), exp1GOWAAgreCheck.isSelected(),
//                    exp2GOWAAgreCheck.isSelected(), noneGowaCheck.isSelected(), sowaWGMAgreCheck.isSelected(),
//                    wowaWGMAgreCheck.isSelected(), exp1WGMAgreCheck.isSelected(),
//                    exp2WGMAgreCheck.isSelected(), noneWGMCheck.isSelected());
//        } else {
//            setAll(false);
//        }
        stage.close();
    }

    @FXML
    private void selectNormAction(ActionEvent event) {
        setAll(true);
    }

}
