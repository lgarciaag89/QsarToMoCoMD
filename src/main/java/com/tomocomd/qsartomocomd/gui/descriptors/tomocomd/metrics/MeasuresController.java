/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomocomd.qsartomocomd.gui.descriptors.tomocomd.metrics;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.tomocomd.qsartomocomdlib.configuration.descriptors.tomocomd.midas.params.metrics.ClusterMidasMetricsConf;
import com.tomocomd.qsartomocomdlib.configuration.descriptors.tomocomd.midas.params.metrics.GeometricalMidasMetricsConf;
import com.tomocomd.qsartomocomdlib.configuration.descriptors.tomocomd.midas.params.metrics.MidasMetricsConf;
import com.tomocomd.qsartomocomdlib.configuration.descriptors.tomocomd.midas.params.metrics.StatisticalMidasMetricsConf;
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
public class MeasuresController implements Initializable {

    private Stage stage;
    private GeometricalMidasMetricsConf geo;
    private ClusterMidasMetricsConf clus;
    private StatisticalMidasMetricsConf sta;

    @FXML
    private JFXCheckBox perimeterCheck;
    @FXML
    private JFXCheckBox volumeCheck;
    @FXML
    private JFXCheckBox dihedralAngleCheck;
    @FXML
    private JFXCheckBox triangleAreaCheck;
    @FXML
    private JFXCheckBox fullBondAngleCheck;
    @FXML
    private JFXCheckBox fullPerimeterCheck;
    @FXML
    private JFXCheckBox fullVolumeCheck;
    @FXML
    private JFXCheckBox fullDihedralAngleCheck;
    @FXML
    private JFXCheckBox fullTriangleAreaCheck;
    @FXML
    private JFXCheckBox bondAngleCheck;
    @FXML
    private JFXButton taGeoMeaBut;
    @FXML
    private JFXButton utaGeoMeaBut;
    @FXML
    private JFXCheckBox minRuleCheck;
    @FXML
    private JFXCheckBox fullMinRuleCheck;
    @FXML
    private JFXCheckBox maxRuleCheck;
    @FXML
    private JFXCheckBox fullMaxRuleCheck;
    @FXML
    private JFXCheckBox aveRuleCheck;
    @FXML
    private JFXCheckBox fullAveRuleCheck;
    @FXML
    private JFXCheckBox wardRuleCheck;
    @FXML
    private JFXCheckBox fullWardRuleCheck;
    @FXML
    private JFXButton taClusMeaBut;
    @FXML
    private JFXButton utaClusMeaBut;
    @FXML
    private JFXCheckBox fullHarmonicCheck;
    @FXML
    private JFXCheckBox varianceCheck;
    @FXML
    private JFXCheckBox fullVarCheck;
    @FXML
    private JFXCheckBox skewnessCheck;
    @FXML
    private JFXCheckBox fullSkewnessCheck;
    @FXML
    private JFXCheckBox varCoeCheck;
    @FXML
    private JFXCheckBox fullVarCoeCheck;
    @FXML
    private JFXCheckBox harmonicCheck;
    @FXML
    private JFXButton taStaMeaBut;
    @FXML
    private JFXButton utaStaMeaBut;
    @FXML
    private JFXButton taMeasuresBut;
    @FXML
    private JFXButton utaMeasuresBut;
    @FXML
    private JFXButton okMeasuresBut;

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

    public void setConf(MidasMetricsConf conf){
        this.geo = conf.getGeo();
        this.clus = conf.getClus();
        this.sta = conf.getSta();
        setFromConfs();
    }

    private void setFromConfs() {
        perimeterCheck.setSelected(geo.isM19());
        fullPerimeterCheck.setSelected(geo.isM20());
        triangleAreaCheck.setSelected(geo.isM21());
        fullTriangleAreaCheck.setSelected(geo.isM22());
        volumeCheck.setSelected(geo.isM23());
        fullVolumeCheck.setSelected(geo.isM24());
        bondAngleCheck.setSelected(geo.isM27());
        fullBondAngleCheck.setSelected(geo.isM28());
        dihedralAngleCheck.setSelected(geo.isM29());
        fullDihedralAngleCheck.setSelected(geo.isM30());
        
        minRuleCheck.setSelected(clus.isM31());
        fullMinRuleCheck.setSelected(clus.isM32());
        maxRuleCheck.setSelected(clus.isM33());
        fullMaxRuleCheck.setSelected(clus.isM34());
        aveRuleCheck.setSelected(clus.isM35());
        fullAveRuleCheck.setSelected(clus.isM36());
        wardRuleCheck.setSelected(clus.isM37());
        fullWardRuleCheck.setSelected(clus.isM38());
        
        harmonicCheck.setSelected(sta.isM39());
        fullHarmonicCheck.setSelected(sta.isM40());
        varianceCheck.setSelected(sta.isM41());
        fullVarCheck.setSelected(sta.isM42());
        skewnessCheck.setSelected(sta.isM43());
        fullSkewnessCheck.setSelected(sta.isM44());
        varCoeCheck.setSelected(sta.isM45());
        fullVarCoeCheck.setSelected(sta.isM46());
    }

    public GeometricalMidasMetricsConf getGeo() {
        return geo;
    }

    public void setGeo(GeometricalMidasMetricsConf geo) {
        this.geo = geo;
    }

    public ClusterMidasMetricsConf getClus() {
        return clus;
    }

    public void setClus(ClusterMidasMetricsConf clus) {
        this.clus = clus;
    }

    public StatisticalMidasMetricsConf getSta() {
        return sta;
    }

    public void setSta(StatisticalMidasMetricsConf sta) {
        this.sta = sta;
    }

    
    @FXML
    private void taGeoMeaAction(ActionEvent event) {
        setGeoValue(true);
    }

    @FXML
    private void utaGeoMeaAct(ActionEvent event) {
        setGeoValue(false);
    }

    private void setGeoValue(boolean g) {
        perimeterCheck.setSelected(g);
        fullPerimeterCheck.setSelected(g);
        triangleAreaCheck.setSelected(g);
        fullTriangleAreaCheck.setSelected(g);
        volumeCheck.setSelected(g);
        fullVolumeCheck.setSelected(g);
        bondAngleCheck.setSelected(g);
        fullBondAngleCheck.setSelected(g);
        dihedralAngleCheck.setSelected(g);
        fullDihedralAngleCheck.setSelected(g);
    }

    private void setClusValue(boolean c) {
        minRuleCheck.setSelected(c);
        fullMinRuleCheck.setSelected(c);
        maxRuleCheck.setSelected(c);
        fullMaxRuleCheck.setSelected(c);
        aveRuleCheck.setSelected(c);
        fullAveRuleCheck.setSelected(c);
        wardRuleCheck.setSelected(c);
        fullWardRuleCheck.setSelected(c);
    }
    
    private void setStaValue(boolean s){
        harmonicCheck.setSelected(s);
        fullHarmonicCheck.setSelected(s);
        varianceCheck.setSelected(s);
        fullVarCheck.setSelected(s);
        skewnessCheck.setSelected(s);
        fullSkewnessCheck.setSelected(s);
        varCoeCheck.setSelected(s);
        fullVarCoeCheck.setSelected(s);
    }
    @FXML
    private void taClusMeaAction(ActionEvent event) {
        setClusValue(true);
    }

    @FXML
    private void utaClusMeaAct(ActionEvent event) {
        setClusValue(false);
    }

    @FXML
    private void taMeasuresAction(ActionEvent event) {
        setGeoValue(true);
        setClusValue(true);
        setStaValue(true);
    }

    @FXML
    private void utaMeasuresAction(ActionEvent event) {
        setGeoValue(false);
        setClusValue(false);
        setStaValue(false);
    }

    @FXML
    private void okMeasuresAction(ActionEvent event) {
        geo = new GeometricalMidasMetricsConf(perimeterCheck.isSelected(),fullPerimeterCheck.isSelected(),
                triangleAreaCheck.isSelected(),fullTriangleAreaCheck.isSelected(),volumeCheck.isSelected(),
                fullVolumeCheck.isSelected(),bondAngleCheck.isSelected(),fullBondAngleCheck.isSelected(),
                dihedralAngleCheck.isSelected(),fullDihedralAngleCheck.isSelected());
        
        clus = new ClusterMidasMetricsConf(minRuleCheck.isSelected(),fullMinRuleCheck.isSelected(),
                maxRuleCheck.isSelected(),fullMaxRuleCheck.isSelected(),aveRuleCheck.isSelected(),
                fullAveRuleCheck.isSelected(), wardRuleCheck.isSelected(),fullWardRuleCheck.isSelected());
        
        sta = new StatisticalMidasMetricsConf(harmonicCheck.isSelected(),fullHarmonicCheck.isSelected(),
                varianceCheck.isSelected(),fullVarCheck.isSelected(),skewnessCheck.isSelected(),
                fullSkewnessCheck.isSelected(),varCoeCheck.isSelected(),fullVarCoeCheck.isSelected());
        stage.close();
    }

    @FXML
    private void taStaMeaAct(ActionEvent event) {
        setStaValue(true);
    }

    @FXML
    private void utaStaMeaAction(ActionEvent event) {
        setStaValue(false);
    }
}
