/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomocomd.qsartomocomd.gui.attributeFitBox;

import com.tomocomd.qsartomocomdlib.configuration.evaluation.attributeevaluation.AttributeQualityType;
import javafx.stage.Stage;

/**
 *
 * @author potter
 */
public class AttFitBoxFactory {
    public static IAttFitBoxChields getChield(AttributeQualityType type,Stage stage) {
        switch (type) {
            case Choquet:
                return new ChoquetAttBox();
            case ReliefF:
                return new ReliefAttBox();
            case SE_ReliefF:
                return new ReliefAttBox();
//            case CfsSubsetReliefSE:
//                return new CfSSEReliefBox();
                    
        }
        return null;
    }
}
