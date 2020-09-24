/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomocomd.qsartomocomd.gui.fitBoxChange;

import java.util.List;
import javafx.scene.Node;

/**
 *
 * @author Potter
 */
public interface IFitBoxChields {
    public List<Node> getChilds();  
    public String getOptions();
    public void setOptions(String []opts);
}
