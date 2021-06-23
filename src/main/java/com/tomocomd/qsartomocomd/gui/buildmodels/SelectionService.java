/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomocomd.qsartomocomd.gui.buildmodels;

import com.tomocomd.qsartomocomdlib.modelssearch.ModelRegressionInfo;
import com.tomocomd.qsartomocomd.gui.alerts.ShowAlerts;
import com.tomocomd.qsartomocomdlib.data.TomocomdInstances;
import com.tomocomd.qsartomocomdlib.descriptors.descriptors.TomocomdDescriptor;
import com.tomocomd.qsartomocomdlib.io.SDFFiles;
import com.tomocomd.qsartomocomdlib.modelssearch.OptimizationRegressionParam;
import com.tomocomd.qsartomocomdlib.modelssearch.SearchRegresionModelByExternalValidation;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javafx.concurrent.Task;
import javafx.scene.control.TableView;
import weka.attributeSelection.AttributeSelection;
import weka.attributeSelection.BestFirst;
import weka.classifiers.AbstractClassifier;
import weka.core.Instances;

/**
 *
 * @author potter
 */
public class SelectionService extends AbstractServiceValue {

    private String act, pathSDF, pathCSV;
    private Instances train;
    private List<AbstractClassifier> classifiers;
    private int mId;
    private OptimizationRegressionParam opt;
    List<ModelRegressionInfo> infos ;

    public SelectionService(String act, String pathSDF, String pathCSV, Instances train, List<AbstractClassifier> classifiers, 
            int mId, OptimizationRegressionParam oP) {
        this.act = act;
        this.pathSDF = pathSDF;
        this.pathCSV = pathCSV;
        this.train = new Instances(train);
        this.classifiers = new LinkedList<>(classifiers);
        this.mId = mId;
        infos = new LinkedList<>();
        opt = oP;
    }

    public OptimizationRegressionParam getOpt() {
        return opt;
    }

    public void setOpt(OptimizationRegressionParam opt) {
        this.opt = opt;
    }
    
    
    @Override
    public List<ModelRegressionInfo> getValues() {
        return infos;
    }
    
    @Override
    protected List<ModelRegressionInfo> call() throws Exception {
        selection();
        return infos;
    }

    public List<ModelRegressionInfo> getInfos() {
        return infos;
    }

    public void setInfos(List<ModelRegressionInfo> infos) {
        this.infos = infos;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }
    
    public void setAct(String act) {
        this.act = act;
    }

    public void setPathSDF(String pathSDF) {
        this.pathSDF = pathSDF;
    }

    public void setPathCSV(String pathCSV) {
        this.pathCSV = pathCSV;
    }

    public void setTrain(Instances train) {
        this.train = new Instances(train);
    }

    public void setClassifiers(List<AbstractClassifier> classifier) {
        this.classifiers = new LinkedList<>(classifier);
    }
    
    public void setClassifiers(AbstractClassifier classifier) {
        this.classifiers = new LinkedList<>();
        this.classifiers.add(classifier);
    }


    private void  selection() throws Exception {
        for(AbstractClassifier abstractClassifier :classifiers){
            selectionOne(abstractClassifier);
        }
    }
    
    private void  selectionOne(AbstractClassifier classifier) throws Exception {
        TomocomdInstances actInst = null;
        try {
            actInst = SDFFiles.getActivity(pathSDF, act);
        } catch (Exception ex) {
            throw new Exception(ex);
        }

        Set<String> mdNames = new LinkedHashSet<>();
        TomocomdInstances inst;
        int cIdx = -1;
        try {
            int numAtt = train.numAttributes();
            
            for (int i = 0; i < numAtt; i++) {
                if (train.attribute(i).name().equals(act)) {
                    cIdx = i;
                } else {
                    mdNames.add(train.attribute(i).name());
                }
            }
            TomocomdDescriptor tom = new TomocomdDescriptor(mdNames);
            inst = tom.compute(pathSDF);
        } catch (Exception ex) {            
            throw new Exception(ex);
        }

        try {
            inst = TomocomdInstances.merge(actInst, inst);
        } catch (Exception ex) {
            throw new Exception(ex);
        }

        inst.setClassIndex(cIdx);
        AttributeSelection asSubset = new AttributeSelection();
        SearchRegresionModelByExternalValidation search = new SearchRegresionModelByExternalValidation(inst, pathCSV + "_sel.csv", 
                act, classifier,infos,mId+infos.size(),opt);
        asSubset.setSearch(new BestFirst());
        asSubset.setEvaluator(search);
        asSubset.setXval(false);

        try {
            asSubset.SelectAttributes(train);
            asSubset.selectedAttributes();
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }
}
