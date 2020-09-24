/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomocomd.qsartomocomd.gui.descriptors.tomocomd.properties;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.tomocomd.qsartomocomdlib.configuration.descriptors.tomocomd.params.properties.PhyChemPropConf;
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
public class PhysicochemicalpropController implements Initializable {
    
    private Stage stage;
    private PhyChemPropConf phy;

    @FXML
    private JFXCheckBox aPropCheck;
    @FXML
    private JFXCheckBox cPropCheck;
    @FXML
    private JFXCheckBox ePropCheck;
    @FXML
    private JFXCheckBox hPropCheck;
    @FXML
    private JFXCheckBox mPropCheck;
    @FXML
    private JFXCheckBox pPropCheck;
    @FXML
    private JFXCheckBox psaPropCheck;
    @FXML
    private JFXCheckBox rPropCheck;
    @FXML
    private JFXCheckBox sPropCheck;
    @FXML
    private JFXCheckBox vPropCheck;
    @FXML
    private JFXButton okGroupsMidasButton;
    @FXML
    private JFXButton utaButtonGroupBut;
    @FXML
    private JFXButton taGroupsrBut;

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

    public PhyChemPropConf getPhy() {
        return phy;
    }

    public void setPhy(PhyChemPropConf phy) {
        this.phy = new PhyChemPropConf(phy);
        setFromConf();
    }

    public void setFromConf() {
        aPropCheck.setSelected(phy.isA());
        cPropCheck.setSelected(phy.isC());
        ePropCheck.setSelected(phy.isE());
        hPropCheck.setSelected(phy.isH());
        mPropCheck.setSelected(phy.isM());
        pPropCheck.setSelected(phy.isP());
        psaPropCheck.setSelected(phy.isPsa());
        rPropCheck.setSelected(phy.isR());
        sPropCheck.setSelected(phy.isS());
        vPropCheck.setSelected(phy.isV());
    }

    public void setAll(boolean set) {
        aPropCheck.setSelected(set);
        cPropCheck.setSelected(set);
        ePropCheck.setSelected(set);
        hPropCheck.setSelected(set);
        mPropCheck.setSelected(set);
        pPropCheck.setSelected(set);
        psaPropCheck.setSelected(set);
        rPropCheck.setSelected(set);
        sPropCheck.setSelected(set);
        vPropCheck.setSelected(set);        
    }
    
    @FXML
    private void okGroupsAction(ActionEvent event) {
        phy.setA(aPropCheck.isSelected());
        phy.setC(cPropCheck.isSelected());
        phy.setE(ePropCheck.isSelected());
        phy.setH(hPropCheck.isSelected());
        phy.setM(mPropCheck.isSelected());
        phy.setP(pPropCheck.isSelected());
        phy.setPsa(psaPropCheck.isSelected());
        phy.setR(rPropCheck.isSelected());
        phy.setS(sPropCheck.isSelected());
        phy.setV(vPropCheck.isSelected());
        stage.close();
    }

    @FXML
    private void unSelectGroupAction(ActionEvent event) {
        setAll(false);
    }

    @FXML
    private void selectGroupsAction(ActionEvent event) {
        setAll(true);
    }
    
}
