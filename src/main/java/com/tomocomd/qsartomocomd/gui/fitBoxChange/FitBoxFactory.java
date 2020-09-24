/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomocomd.qsartomocomd.gui.fitBoxChange;

import com.tomocomd.qsartomocomdlib.configuration.evaluation.fitnessfunction.EvaluationFunctionType;
import javafx.stage.Stage;


/**
 *
 * @author Potter
 */
public class FitBoxFactory {

    public static IFitBoxChields getChield(EvaluationFunctionType type,Stage stage) {
        switch (type) {
            case SE_SUM_Relief:
                return new SeReliefBox();
            case ReliefRankMean:
                return new ReliefBox();
            case CfsSubsetReliefSE:
                return new CfSSEReliefBox();
                    
        }
        return null;
    }
}
