/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomocomd.qsartomocomd.gui.fitBoxChange;

import com.jfoenix.controls.JFXComboBox;
import com.tomocomd.qsartomocomdlib.evaluation.subsetfitnessfunction.ElectionMethod;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;


/**
 *
 * @author Potter
 */
public class CfSSEReliefBox implements IFitBoxChields {

    private ReliefBox relief;
    JFXComboBox<ElectionMethod> election;

    public CfSSEReliefBox() {
        relief = new ReliefBox();
        election = new JFXComboBox<>();
        election = new JFXComboBox<>();
        // list of selection operatios 
        ObservableList<ElectionMethod> listSeleData = FXCollections.observableArrayList();
        listSeleData.addAll(Arrays.asList(ElectionMethod.values()));
        election.setItems(listSeleData);
        election.setValue(ElectionMethod.bestKCfS);
        election.setLabelFloat(true);
        election.setPromptText("Seletion method");
        election.setId("methodElec");
        election.setPadding(new Insets(20, 10, 10, 10));
        election.setPrefWidth(Double.MAX_VALUE);
    }

    
    @Override
    public List<Node> getChilds() {        

        List<Node> chields = new LinkedList<>();
        chields.add(election);        
        chields.addAll(relief.getChilds());
        return chields;
    }

    @Override
    public String getOptions() {
        String rOptLine = relief.getOptions();
        return String.format("election %s relief %s", election.getValue().toString(), rOptLine);
    }

    public ElectionMethod getElection() {
        return election.getValue();
    }

    @Override
    public void setOptions(String[] opts) {
        election.setValue(ElectionMethod.valueOf(opts[1]));
        String []reliefOpts = opts[3].split(" ");
        relief.setOptions(reliefOpts);
    }

}
