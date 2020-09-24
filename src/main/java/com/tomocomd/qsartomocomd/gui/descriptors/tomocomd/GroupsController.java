/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomocomd.qsartomocomd.gui.descriptors.tomocomd;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.tomocomd.qsartomocomd.gui.alerts.ShowAlerts;
import com.tomocomd.qsartomocomdlib.Exception.GroupsConfException;
import com.tomocomd.qsartomocomdlib.configuration.descriptors.tomocomd.params.GroupsConf;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author potter
 */
public class GroupsController implements Initializable {

    private Stage stage;
    private GroupsConf g;

    @FXML
    private JFXCheckBox cGroupCheck;
    @FXML
    private JFXCheckBox mGroupCheck;
    @FXML
    private JFXCheckBox pGroupCheck;
    @FXML
    private JFXCheckBox xGroupCheck;
    @FXML
    private JFXCheckBox aGroupCheck;
    @FXML
    private JFXCheckBox dGroupCheck;
    @FXML
    private JFXCheckBox gGroupCheck;
    @FXML
    private JFXButton taGroupsrBut;
    @FXML
    private JFXButton utaButtonGroupBut;
    @FXML
    private JFXButton okGroupsMidasButton;

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

    public void setG(GroupsConf g) {
        this.g = new GroupsConf(g);
        setFromConf();
    }

    public void setFromConf() {
        cGroupCheck.setSelected(g.isC());
        mGroupCheck.setSelected(g.isM());
        pGroupCheck.setSelected(g.isP());
        xGroupCheck.setSelected(g.isX());
        aGroupCheck.setSelected(g.isA());
        dGroupCheck.setSelected(g.isD());
        gGroupCheck.setSelected(g.isG());
    }

    public void setAll(boolean set) {
        cGroupCheck.setSelected(set);
        mGroupCheck.setSelected(set);
        pGroupCheck.setSelected(set);
        xGroupCheck.setSelected(set);
        aGroupCheck.setSelected(set);
        dGroupCheck.setSelected(set);
        gGroupCheck.setSelected(set);
    }

    @FXML
    private void selectGroupsAction(ActionEvent event) {
        setAll(true);
    }

    @FXML
    private void unSelectGroupAction(ActionEvent event) {
        setAll(false);
    }

    @FXML
    private void okGroupsAction(ActionEvent event) {
        try {
            g.setA(aGroupCheck.isSelected());
            g.setC(cGroupCheck.isSelected());
            g.setD(dGroupCheck.isSelected());
            g.setG(gGroupCheck.isSelected());
            g.setM(mGroupCheck.isSelected());
            g.setP(pGroupCheck.isSelected());
            g.setX(xGroupCheck.isSelected());            
        } catch (GroupsConfException ex) {
            new ShowAlerts().showWarning(ex.getMessage());
        }
        stage.close();

    }

    public GroupsConf getG() {
        return g;
    }
}
