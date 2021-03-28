/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomocomd.qsartomocomd.gui.buildmodels;

import com.tomocomd.qsartomocomdlib.data.TomocomdInstances;
import com.tomocomd.qsartomocomdlib.descriptors.descriptors.TomocomdDescriptor;
import com.tomocomd.qsartomocomdlib.io.SDFFiles;
import com.tomocomd.qsartomocomdlib.modelssearch.BuildSeveralModels;
import com.tomocomd.qsartomocomdlib.modelssearch.ModelInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javafx.concurrent.Task;
import weka.attributeSelection.ASEvaluation;
import weka.attributeSelection.ASSearch;
import weka.attributeSelection.BestFirst;
import weka.attributeSelection.CfsSubsetEval;
import weka.attributeSelection.GeneticSearch;
import weka.attributeSelection.GreedyStepwise;
import weka.attributeSelection.WrapperSubsetEval;
import weka.classifiers.AbstractClassifier;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.functions.SMOreg;
import weka.classifiers.lazy.IBk;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.SelectedTag;

/**
 *
 * @author potter
 */
public class SelClasService extends AbstractServiceValue {

    private String act, pathSDF, pathCSV;
    private Instances train;
    private int modelId;
    private String method;
    List<ModelInfo> infos;

    @Override
    public List<ModelInfo> getValues() {
        return infos;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return super.cancel(true); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override 
    protected void cancelled() {
        super.cancel(true);
    }

    public SelClasService(String act, String pathSDF, String pathCSV, Instances train, int modelId, String method) {
        this.act = act;
        this.pathSDF = pathSDF;
        this.pathCSV = pathCSV;
        this.train = new Instances(train);
        this.modelId = modelId;
        this.method = method;
        infos = new LinkedList<>();
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public String getPathSDF() {
        return pathSDF;
    }

    public void setPathSDF(String pathSDF) {
        this.pathSDF = pathSDF;
    }

    public String getPathCSV() {
        return pathCSV;
    }

    public void setPathCSV(String pathCSV) {
        this.pathCSV = pathCSV;
    }

    public Instances getTrain() {
        return train;
    }

    public void setTrain(Instances train) {
        this.train = new Instances(train);
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    protected List<ModelInfo> call() throws Exception {
        if (method.equals("cls")) {
            cls();
        } else {
            selClas();

        }
        return infos;
    }

    private void cls() throws Exception {
        // setting classifiers
        LinearRegression lr = new LinearRegression();
        lr.setAttributeSelectionMethod(new SelectedTag(LinearRegression.SELECTION_NONE, LinearRegression.TAGS_SELECTION));
        lr.setEliminateColinearAttributes(false);
        ArrayList<AbstractClassifier> classifiers = new ArrayList<AbstractClassifier>(Arrays.asList(
                lr, new SMOreg(), new IBk(3), new IBk(4), new IBk(5), new IBk(6), new RandomForest(), new MultilayerPerceptron())); // new RandomForest(),

        BuildSeveralModels models = new BuildSeveralModels(classifiers, modelId,infos);

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
        train.setClassIndex(cIdx);
        models.setDesc(mdNames.stream().toArray(String[]::new));
        models.buildModels(train, inst);
    }

    private void selClas() throws Exception {

        // setting classifiers
        LinearRegression lr = new LinearRegression();
        lr.setAttributeSelectionMethod(new SelectedTag(LinearRegression.SELECTION_NONE, LinearRegression.TAGS_SELECTION));
        lr.setEliminateColinearAttributes(false);
        ArrayList<AbstractClassifier> classifiers = new ArrayList<AbstractClassifier>(Arrays.asList(
                lr, new SMOreg(), new IBk(3), new IBk(4), new IBk(5), new IBk(6))); // new RandomForest(),

        // fitness functions
        List<ASEvaluation> evas = new LinkedList<>();
        evas.add(new CfsSubsetEval());
        for (AbstractClassifier c : classifiers) {
            WrapperSubsetEval wrapper = new WrapperSubsetEval();
            wrapper.setClassifier(c);
            wrapper.setEvaluationMeasure(new SelectedTag(WrapperSubsetEval.EVAL_MAE, WrapperSubsetEval.TAGS_EVALUATION));
            wrapper.setSeed(new Random(System.currentTimeMillis()).nextInt());
            evas.add(wrapper);
        }
        classifiers.add(new RandomForest());
        classifiers.add(new MultilayerPerceptron());

        ASSearch[] searchs = {new BestFirst(), new GreedyStepwise(), new GeneticSearch()};

        BuildSeveralModels models = new BuildSeveralModels(classifiers, evas, Arrays.asList(searchs), modelId, infos);

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
        train.setClassIndex(cIdx);
        models.featureSelection(train, inst);
    }

}
