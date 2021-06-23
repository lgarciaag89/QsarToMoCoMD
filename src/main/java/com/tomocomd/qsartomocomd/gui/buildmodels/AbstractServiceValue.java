/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomocomd.qsartomocomd.gui.buildmodels;

import com.tomocomd.qsartomocomdlib.modelssearch.ModelRegressionInfo;
import java.util.List;
import javafx.concurrent.Task;

/**
 *
 * @author lgarc
 */
public abstract class AbstractServiceValue extends Task<List<ModelRegressionInfo>> {
    public abstract List<ModelRegressionInfo> getValues();
}
