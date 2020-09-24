/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomocomd.qsartomocomd.gui;

import com.tomocomd.qsartomocomdlib.configuration.ProjectConf;
import com.tomocomd.qsartomocomdlib.configuration.search.geneticalgorithm.GAConf;
import com.tomocomd.qsartomocomdlib.data.TomocomdInstances;
import com.tomocomd.qsartomocomdlib.search.geneticalgorithm.AbstractGeneticAlgorithm;
import com.tomocomd.qsartomocomdlib.search.geneticalgorithm.GAFactory;
import java.io.File;

import javafx.concurrent.Task;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;

/**
 *
 * @author Potter
 */
public class GAJFXTask extends Task<Boolean> {

    static String PATHSEPARATOR = File.separator;

    private int numIter;
    private AbstractGeneticAlgorithm ga;
    private TreeTableView<ExecutionInfo> treeTable;
    private String path;

    public GAJFXTask(ProjectConf conf, TreeTableView<ExecutionInfo> t) throws Exception {
        ga = GAFactory.getGA(conf);
        numIter = ((GAConf)conf.getSearch()).getNumIter();
        treeTable = t;
        path = conf.getOutFileFile();
        path = path.substring(0, path.lastIndexOf(PATHSEPARATOR));
    }

    @Override
    public Boolean call() throws Exception {
        TomocomdInstances tomInst;
        TreeItem execNew = new TreeItem<ExecutionInfo>(new ExecutionInfo(path,"", ""));
        treeTable.getRoot().getChildren().add(execNew);
        for (int i = 0; i < numIter; i++) {
            if (isCancelled()) {
                System.out.println("Canceling...");
                return false;
            }
            tomInst = ga.executeIter();
            String name = ga.upateBestSubset(tomInst,i+1);
            if (name.length() > 0) {                
                execNew.getChildren().add(new TreeItem<>(new ExecutionInfo(name, Integer.toString(ga.getBestSubset().numAttributes()), 
                        Double.toString(ga.getBestSubset().getEvaSub()))));
            }
            updateProgress(i + 1, numIter);
            updateMessage(String.format("%d%%",(100*(i+1))/numIter));
        }
        return true;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        boolean b=true;
        try {
            b = ga.cancel();
        } catch (Exception ex) {
            b = false;
        }
        if(!isDone())
            return b && super.cancel(mayInterruptIfRunning);
        return b ; //To change body of generated methods, choose Tools | Templates.
    }

    
}
