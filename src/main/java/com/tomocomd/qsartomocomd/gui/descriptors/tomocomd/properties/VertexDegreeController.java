/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomocomd.qsartomocomd.gui.descriptors.tomocomd.properties;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.tomocomd.qsartomocomdlib.configuration.descriptors.tomocomd.params.properties.PhyChemPropConf;
import com.tomocomd.qsartomocomdlib.configuration.descriptors.tomocomd.params.properties.VerDegPropConf;
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
public class VertexDegreeController implements Initializable {

    private Stage stage;
    private VerDegPropConf ver;

    @FXML
    private JFXCheckBox alkPropCheck;
    @FXML
    private JFXCheckBox estPropCheck;
    @FXML
    private JFXCheckBox kuPropCheck;
    @FXML
    private JFXCheckBox liPropCheck;
    @FXML
    private JFXCheckBox dcPropCheck;
    @FXML
    private JFXCheckBox hxPropCheck;
    @FXML
    private JFXCheckBox ecPropCheck;
    @FXML
    private JFXButton verDegBut;
    @FXML
    private JFXButton utaVerDegBu;
    @FXML
    private JFXButton okVerDegMidasButton;

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

    public VerDegPropConf getVer() {
        return ver;
    }

    public void setVer(VerDegPropConf ver) {
        this.ver = new VerDegPropConf(ver);
        setFromConf();
    }

    public void setFromConf() {
        alkPropCheck.setSelected(ver.isAlk());
        estPropCheck.setSelected(ver.isEst());
        kuPropCheck.setSelected(ver.isKu());
        liPropCheck.setSelected(ver.isLi());
        dcPropCheck.setSelected(ver.isDc());
        hxPropCheck.setSelected(ver.isHx());
        ecPropCheck.setSelected(ver.isEc());
    }

    private void setAll(boolean set) {
        alkPropCheck.setSelected(set);
        estPropCheck.setSelected(set);
        kuPropCheck.setSelected(set);
        liPropCheck.setSelected(set);
        dcPropCheck.setSelected(set);
        hxPropCheck.setSelected(set);
        ecPropCheck.setSelected(set);
    }

    @FXML
    private void selectverDegAction(ActionEvent event) {
        setAll(true);
    }

    @FXML
    private void unSelectVerDegAction(ActionEvent event) {
        setAll(false);
    }

    @FXML
    private void okVerDegAction(ActionEvent event) {
        ver.setAlk(alkPropCheck.isSelected());
        ver.setEst(estPropCheck.isSelected());
        ver.setKu(kuPropCheck.isSelected());
        ver.setDc(dcPropCheck.isSelected());
        ver.setLi(liPropCheck.isSelected());
        ver.setEc(ecPropCheck.isSelected());
        ver.setHx(hxPropCheck.isSelected());
        stage.close();
    }

}
