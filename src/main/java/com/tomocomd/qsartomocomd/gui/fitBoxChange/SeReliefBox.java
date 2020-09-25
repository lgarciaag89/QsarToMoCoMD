/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomocomd.qsartomocomd.gui.fitBoxChange;

import com.tomocomd.qsartomocomdlib.configuration.evaluation.attributeevaluation.AttributeQualityType;
import java.util.List;
import javafx.scene.Node;

/**
 *
 * @author Potter
 */
public class SeReliefBox implements IFitBoxChields{

    private ReliefBox relief;

    public SeReliefBox() {
        relief = new ReliefBox();
    }   
    
     @Override
    public List<Node> getChilds() {               
        return relief.getChilds();
    }

    @Override
    public String getOptions() {
        String rOptLine = relief.getOptions();
        return String.format("%s %s %s %s", AttributeQualityType.SE.toString(), "", AttributeQualityType.ReliefF.toString(),rOptLine);
    }


    @Override
    public void setOptions(String[] opts) {
        String []reliefOpts = opts[3].split(" ");
        relief.setOptions(reliefOpts);
    }
    
}
