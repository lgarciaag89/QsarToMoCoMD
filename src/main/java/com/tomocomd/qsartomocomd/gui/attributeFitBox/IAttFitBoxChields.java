/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomocomd.qsartomocomd.gui.attributeFitBox;

import java.util.List;
import javafx.scene.Node;

/**
 *
 * @author potter
 */
public interface IAttFitBoxChields {
    public List<Node> getChilds();  
    public String getOptions();
    public void setOptions(String []opts);
}
