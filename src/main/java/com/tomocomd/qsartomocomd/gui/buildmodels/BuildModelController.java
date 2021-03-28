/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomocomd.qsartomocomd.gui.buildmodels;

import com.tomocomd.qsartomocomdlib.modelssearch.ModelInfo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.tomocomd.qsartomocomd.gui.alerts.ShowAlerts;
import com.tomocomd.qsartomocomdlib.data.TomocomdInstances;
import com.tomocomd.qsartomocomdlib.descriptors.descriptors.TomocomdDescriptor;
import com.tomocomd.qsartomocomdlib.io.CSVFileManage;
import com.tomocomd.qsartomocomdlib.io.SDFFiles;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.SelectionMode;
import javafx.stage.DirectoryChooser;
import weka.classifiers.AbstractClassifier;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.functions.SMOreg;
import weka.classifiers.lazy.IBk;
import weka.core.Instances;
import weka.core.SelectedTag;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

/**
 * FXML Controller class
 *
 * @author potter
 */
public class BuildModelController implements Initializable {

    private Stage stage;
    private String pathCSV;
    private String pathSDF;
    private String act;
    private Instances train;
    private static String lr = "Linear regresion";
    private static String svm = "SVM";
    private static String knn3 = "KNN(3)";
    private static String knn4 = "KNN(4)";
    private static String knn5 = "KNN(5)";
    private static String knn6 = "KNN(6)";
    private AbstractServiceValue taskService;
    private Thread thread;
    @FXML
    private JFXButton modelBuildBut;
    @FXML
    private JFXTextField sdfExtFiled;
    @FXML
    private TableColumn<ModelInfo, String> modelIdColumn;
    @FXML
    private TableColumn<ModelInfo, Double> R2LooColumn;
    @FXML
    private TableColumn<ModelInfo, Double> MaeLooColumn;
    @FXML
    private TableColumn<ModelInfo, Double> R2ExtColumn;
    @FXML
    private TableColumn<ModelInfo, Double> MaeExtColumn;
    @FXML
    private TableView<ModelInfo> tableModelInfo;
    @FXML
    private JFXComboBox<String> actCombo;
    @FXML
    private JFXComboBox<String> ClasCombo;
    @FXML
    private TableColumn<ModelInfo, Integer> sizeColumn;
    @FXML
    private TableColumn<ModelInfo, String> selColumn;
    @FXML
    private TableColumn<ModelInfo, String> clasColumn;
    @FXML
    private ContextMenu modelCM;
    @FXML
    private MenuItem modelSaveItem;
    @FXML
    private MenuItem modelMDsSaveItem;
    @FXML
    private JFXTextField csvTrainFiled;
    @FXML
    private JFXComboBox<String> operationCombo;
    @FXML
    private JFXButton saveMoBut;
    @FXML
    private JFXButton cleanBut;
    @FXML
    private JFXButton filterBut;
    @FXML
    private MenuItem selectionItem;
    @FXML
    private MenuItem clasificationItem;
    @FXML
    private MenuItem selclasificationItem;
    @FXML
    private MenuItem saveIDataItem;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        sdfExtFiled.setDisable(false);

        tableModelInfo.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableModelInfo.setPlaceholder(new Label(""));
        tableModelInfo.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        modelIdColumn.setCellValueFactory(new PropertyValueFactory<>("modelId"));
        selColumn.setCellValueFactory(new PropertyValueFactory<>("selName"));
        clasColumn.setCellValueFactory(new PropertyValueFactory<>("claName"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
        R2LooColumn.setText("R\u00B2\u2097\u2092\u2092");
        R2LooColumn.setCellValueFactory(new PropertyValueFactory<>("r2Loo"));
        MaeLooColumn.setText("Mae\u2097\u2092\u2092");
        MaeLooColumn.setCellValueFactory(new PropertyValueFactory<>("maeLoo"));
        R2ExtColumn.setText("R\u00B2\u2091\u2093\u209C");
        R2ExtColumn.setCellValueFactory(new PropertyValueFactory<>("r2Ext"));
        MaeExtColumn.setText("Mae\u2091\u2093\u209C");
        MaeExtColumn.setCellValueFactory(new PropertyValueFactory<>("maeExt"));

        modelIdColumn.prefWidthProperty().bind(tableModelInfo.widthProperty().multiply(0.07));
        sizeColumn.prefWidthProperty().bind(tableModelInfo.widthProperty().multiply(0.05));
        R2LooColumn.prefWidthProperty().bind(tableModelInfo.widthProperty().multiply(0.1));
        MaeLooColumn.prefWidthProperty().bind(tableModelInfo.widthProperty().multiply(0.1));
        R2ExtColumn.prefWidthProperty().bind(tableModelInfo.widthProperty().multiply(0.1));
        MaeExtColumn.prefWidthProperty().bind(tableModelInfo.widthProperty().multiply(0.1));
        selColumn.prefWidthProperty().bind(tableModelInfo.widthProperty().multiply(0.24));
        clasColumn.prefWidthProperty().bind(tableModelInfo.widthProperty().multiply(0.24));

        selColumn.setResizable(false);
        clasColumn.setResizable(false);
        sizeColumn.setResizable(false);
        modelIdColumn.setResizable(false);
        R2LooColumn.setResizable(false);
        MaeLooColumn.setResizable(false);
        R2ExtColumn.setResizable(false);
        MaeExtColumn.setResizable(false);

        ObservableList<String> clasNames = FXCollections.observableArrayList();
        clasNames.add("ALL");
        clasNames.add(lr);
        clasNames.add(svm);
        clasNames.add(knn3);
        clasNames.add(knn4);
        clasNames.add(knn5);
        clasNames.add(knn6);
        ClasCombo.setItems(clasNames);
        ClasCombo.getSelectionModel().selectFirst();
        clasNames = FXCollections.observableArrayList();
        clasNames.add("Selection");
        clasNames.add("Selection+Classification");
        clasNames.add("Classification");
        operationCombo.setItems(clasNames);
        operationCombo.getSelectionModel().selectFirst();

        pathCSV = "";
        pathSDF = "";
    }

    public void setStage(Stage myStage) {
        stage = stage;
    }

    public void setPath(String name) {
        pathCSV = name;
        train = CSVFileManage.loadCSV(pathCSV);

        ObservableList<String> clasNames = FXCollections.observableArrayList();
        for (int i = 0; i < train.numAttributes(); i++) {
            clasNames.add(train.attribute(i).name());
        }
        actCombo.setItems(clasNames);
        actCombo.getSelectionModel().selectFirst();
        csvTrainFiled.setText(new File(pathCSV).getName());
        csvTrainFiled.setDisable(true);
    }

    public void setAct(String target) {
        act = target;
    }

    @FXML
    private void modelBuildAction(MouseEvent event) {
        if (pathCSV.isEmpty()) {
            new ShowAlerts().showError("CSV file is mandatory");
            return;
        }
        if (pathSDF.isEmpty()) {
            new ShowAlerts().showError("SDF file is mandatory");
            return;
        } else {
            if (modelBuildBut.getText().equals("Build models")) {
                modelBuildBut.setText("Cancel proccess");
                modelBuildBut.setStyle("-fx-background-color: #ff0000");
                if (operationCombo.getSelectionModel().getSelectedItem().equals("Selection")) {
                    selectionExtVal();
                } else if (operationCombo.getSelectionModel().getSelectedItem().equals("Selection+Classification")) {
                    selClas();
                } else {
                    clasificacion();
                }
            } else {
                modelBuildBut.setText("Build models");
                modelBuildBut.setStyle("-fx-background-color: #207ce5");
                taskService.cancel();
            }
        }
    }

    private void selectionExtVal() {
        taskService = new SelectionService(actCombo.getSelectionModel().getSelectedItem(),
                pathSDF, pathCSV, train, getClassifier(), tableModelInfo.getItems().size());
        executeService();
    }

    private void clasificacion() {
        taskService = new SelClasService(actCombo.getSelectionModel().getSelectedItem(),
                pathSDF, pathCSV, train, tableModelInfo.getItems().size(), "cls");
        executeService();
    }

    private void selClas() {
        taskService = new SelClasService(actCombo.getSelectionModel().getSelectedItem(),
                pathSDF, pathCSV, train, tableModelInfo.getItems().size(), "sel+clas");
        executeService();
    }

    private void executeService() {
        List<ModelInfo> oldModels = new LinkedList<>(tableModelInfo.getItems());
        Node oldPlaceHolder = tableModelInfo.getPlaceholder();
        tableModelInfo.getItems().clear();
        taskService.setOnSucceeded(eventFinish -> {
            oldModels.addAll(taskService.getValue());
            tableModelInfo.getItems().setAll(oldModels);
            tableModelInfo.setPlaceholder(oldPlaceHolder);
            modelBuildBut.setText("Build models");
            modelBuildBut.setStyle("-fx-background-color: #207ce5");
            new ShowAlerts().showInfo("Execution completed");
        });
        taskService.setOnFailed(evt -> {
            Throwable exception = evt.getSource().getException();
            new ShowAlerts().showError(exception.getMessage());
            modelBuildBut.setText("Build models");
            modelBuildBut.setStyle("-fx-background-color: #207ce5");
        });

        taskService.setOnCancelled(eventFinish -> {
            oldModels.addAll(taskService.getValues());
            tableModelInfo.getItems().setAll(oldModels);
            tableModelInfo.setPlaceholder(oldPlaceHolder);
            modelBuildBut.setText("Build models");
            modelBuildBut.setStyle("-fx-background-color: #207ce5");
        });

        ProgressIndicator progressIndicator = new ProgressIndicator();
        progressIndicator.setMaxSize(90, 90);
        progressIndicator.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
        tableModelInfo.setPlaceholder(progressIndicator);

        thread = new Thread(taskService);
        thread.start();
    }

    private List<AbstractClassifier> getClassifier() {

        List<AbstractClassifier> cls = new LinkedList<>();
        switch (ClasCombo.getSelectionModel().getSelectedItem()) {
            case ("Linear regresion"):
                LinearRegression classifier = new LinearRegression();
                classifier.setAttributeSelectionMethod(new SelectedTag(LinearRegression.SELECTION_NONE, LinearRegression.TAGS_SELECTION));
                classifier.setEliminateColinearAttributes(false);
                cls.add(classifier);
                return cls;
            case ("SVM"):
                cls.add(new SMOreg());
                return cls;
            case ("KNN(3)"):
                cls.add(new IBk(3));
                return cls;
            case ("KNN(4)"):
                cls.add(new IBk(4));
                return cls;
            case ("KNN(5)"):
                cls.add(new IBk(5));
                return cls;
            case ("KNN(6)"):
                cls.add(new IBk(6));
                return cls;
            case ("ALL"):
                classifier = new LinearRegression();
                classifier.setAttributeSelectionMethod(new SelectedTag(LinearRegression.SELECTION_NONE, LinearRegression.TAGS_SELECTION));
                classifier.setEliminateColinearAttributes(false);
                cls.add(classifier);
                cls.add(new SMOreg());
                cls.add(new IBk(3));
                cls.add(new IBk(4));
                cls.add(new IBk(5));
                cls.add(new IBk(6));
                return cls;
        }
        return null;
    }

    @FXML
    private void findSDFExtActionMouse(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        File file;
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("SDF Files", "*.sdf"),
                new FileChooser.ExtensionFilter("SDF Files", "*.SDF")
        );
        if (!pathCSV.isEmpty()) {
            if (new File(pathCSV).getParentFile().exists()) {
                fileChooser.setInitialDirectory(new File(pathCSV).getParentFile());
            }
        }

        file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            sdfExtFiled.setText(file.getName());
            pathSDF = file.getAbsolutePath();
            try {
                ObservableList<String> repSubData = FXCollections.observableArrayList();
            } catch (Exception ex) {
                new ShowAlerts().showError(ex.getMessage());
                sdfExtFiled.setText("");
            }
        }
    }

    @FXML
    private void findCSVTrainActionMouse(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        File file;
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV Files", "*.csv"),
                new FileChooser.ExtensionFilter("CSV Files", "*.CSV")
        );

        if (!pathCSV.isEmpty()) {
            if (new File(pathCSV).getParentFile().exists()) {
                fileChooser.setInitialDirectory(new File(pathCSV).getParentFile());
            }
        }

        file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            csvTrainFiled.setText(file.getName());
            pathCSV = file.getAbsolutePath();
            train = CSVFileManage.loadCSV(pathCSV);
            train.setClassIndex(0);
            ObservableList<String> clasNames = FXCollections.observableArrayList();
            for (int i = 0; i < train.numAttributes(); i++) {
                clasNames.add(train.attribute(i).name());
            }
            actCombo.setItems(clasNames);
            actCombo.getSelectionModel().selectFirst();
        }
    }

    @FXML
    private void modelSaveAction(ActionEvent event) throws Exception {

        ObservableList<ModelInfo> infos = tableModelInfo.getSelectionModel().getSelectedItems();

        if (infos != null) {
            DirectoryChooser fileChooser = new DirectoryChooser();
            File folder;

            if (!pathCSV.isEmpty()) {
                if (new File(pathCSV).getParentFile().exists()) {
                    fileChooser.setInitialDirectory(new File(pathCSV).getParentFile());
                }
            }

            folder = fileChooser.showDialog(stage);

            for (ModelInfo info : infos) {
                try {
                    FileOutputStream fileOut = new FileOutputStream(new File(folder, String.format("model%d.qtmodel", info.getModelId())));
                    ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
                    objectOut.writeObject(info.getClas());
                    objectOut.close();

                } catch (Exception ex) {
                    new ShowAlerts().showError(ex.getMessage());
                    throw new Exception(ex);
                }
            }
            new ShowAlerts().showInfo("Saved " + infos + " models");
        }
    }

    @FXML
    private void modelMDsSaveAction(ActionEvent event) throws Exception {
        ObservableList<ModelInfo> infos = tableModelInfo.getSelectionModel().getSelectedItems();

        if (infos != null) {
            DirectoryChooser fileChooser = new DirectoryChooser();
            File file;

            if (!pathCSV.isEmpty()) {
                if (new File(pathCSV).getParentFile().exists()) {
                    fileChooser.setInitialDirectory(new File(pathCSV).getParentFile());
                }
            }

            file = fileChooser.showDialog(stage);

            Set<String> desc = new LinkedHashSet<>();
            for (ModelInfo info : infos) {
                desc.addAll(Arrays.asList(info.getDesc()));
                try {
                    PrintWriter pw = new PrintWriter(new File(file, String.format("model%d.descs", info.getModelId())));
                    pw.println(String.join("\n", info.getDesc()));
                    pw.close();

                } catch (Exception ex) {
                    new ShowAlerts().showError(ex.getMessage());
                    throw new Exception(ex);
                }

            }

            if (infos.size() > 1) {
                try {
                    PrintWriter pw = new PrintWriter(new File(file, "allDescriptors.descs"));
                    pw.println(String.join("\n", desc));
                    pw.close();
                    new ShowAlerts().showInfo("Molecular descriptors were succesfully written to a " + file.getName() + " file");

                } catch (Exception ex) {
                    new ShowAlerts().showError(ex.getMessage());
                    throw new Exception(ex);
                }
            }
            new ShowAlerts().showInfo("Molecular descriptors were succesfully in " + infos.size() + " files");
        }
    }

    @FXML
    private void operationAction(ActionEvent event) {
        if (operationCombo.getSelectionModel().getSelectedItem().equals("Selection")) {
            ClasCombo.setDisable(false);
        } else {
            ClasCombo.setDisable(true);
        }
    }

    @FXML
    private void saveMoAction(ActionEvent event) throws Exception {
        FileChooser fileChooser = new FileChooser();
        File file;
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV Files", "*.csv"),
                new FileChooser.ExtensionFilter("CSV Files", "*.CSV")
        );

        if (!pathCSV.isEmpty()) {
            if (new File(pathCSV).getParentFile().exists()) {
                fileChooser.setInitialDirectory(new File(pathCSV).getParentFile());
            }
        }

        file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            try {
                FileWriter fw = new FileWriter(file, true);
                BufferedWriter w = new BufferedWriter(fw);
                w.write("id,model,feature select, classifier,size,R2,R2Ext,Mae,MaeExt,desc\n");

                for (ModelInfo info : tableModelInfo.getItems()) {
                    String res = String.format("%s,%s,%s,%s,%d,%.6f,%.6f,%.6f,%.6f,%s\n",
                            info.getModelId(), info.getClas().toString().replaceAll(" ", "").replaceAll("\n", " "),
                            info.getSelName(),
                            info.getClaName(), info.getSize(), info.getR2Loo(), info.getR2Ext(),
                            info.getMaeLoo(), info.getMaeExt(), String.join(" ", info.getDesc()));
                    w.write(res);
                }
                w.close();
                fw.close();
                new ShowAlerts().showInfo("Models info were succesfully written to a " + file.getName() + " file");
            } catch (IOException ex) {
                new ShowAlerts().showError(ex.getMessage());
                throw new Exception(ex);
            }
        }
    }

    @FXML
    private void cleanAction(ActionEvent event) {
        tableModelInfo.getItems().clear();
    }

    @FXML
    private void filterId(ActionEvent event) {
        int cont = 0;
        for (int i = tableModelInfo.getItems().size() - 1; i >= 0; i--) {
            if (tableModelInfo.getItems().get(i).getR2Ext() < 0.5
                    || tableModelInfo.getItems().get(i).getR2Loo() < 0.5) {
                tableModelInfo.getItems().remove(i);
                cont++;
            }
        }
        new ShowAlerts().showInfo(String.format("Deleted %d models", cont));
    }

    @FXML
    private void selectionMDAction(ActionEvent event) throws Exception {
        ModelInfo info = tableModelInfo.getSelectionModel().getSelectedItem();

        if (info != null) {
            taskService = new SelectionService(actCombo.getSelectionModel().getSelectedItem(),
                    pathSDF, pathCSV, getDescAndFilter(info), getClassifier(), tableModelInfo.getItems().size());
            executeService();
        }
    }

    @FXML
    private void clasificationAction(ActionEvent event) throws Exception {
        ModelInfo info = tableModelInfo.getSelectionModel().getSelectedItem();

        if (info != null) {

            ClasCombo.getSelectionModel().selectFirst();
            taskService = new SelClasService(actCombo.getSelectionModel().getSelectedItem(),
                    pathSDF, pathCSV, getDescAndFilter(info), tableModelInfo.getItems().size(), "cls");
            executeService();
        }
    }

    @FXML
    private void selclasificationAction(ActionEvent event) throws Exception {
        ModelInfo info = tableModelInfo.getSelectionModel().getSelectedItem();

        if (info != null) {
            taskService = new SelClasService(actCombo.getSelectionModel().getSelectedItem(),
                    pathSDF, pathCSV, getDescAndFilter(info), tableModelInfo.getItems().size(), "sel+clas");
            executeService();
        }

    }

    private Instances getDescAndFilter(ModelInfo info) throws Exception {
        List<String> desc = Arrays.asList(info.getDesc());

        List<Integer> pos = new LinkedList<>();
        pos.add(train.classIndex());
        for (int i = 0; i < train.numAttributes(); i++) {
            if (desc.contains(train.attribute(i).name())) {
                pos.add(i);
            }
        }

        int[] posA = pos.stream().sorted().mapToInt(i -> i).toArray();
        int clx = Arrays.binarySearch(posA, train.classIndex());

        Remove rem = new Remove();
        rem.setAttributeIndicesArray(posA);
        rem.setInvertSelection(true);
        Instances instTrain = null;//new Instances(train);
        try {
            rem.setInputFormat(train);
            instTrain = Filter.useFilter(train, rem);
            instTrain.setClassIndex(clx);
        } catch (Exception ex) {
            new ShowAlerts().showError(ex.getMessage());
            throw new Exception(ex);
        }
        return instTrain;
    }

    @FXML
    private void saveIDataAction(ActionEvent event) throws Exception {
        ObservableList<ModelInfo> infos = tableModelInfo.getSelectionModel().getSelectedItems();

        if (infos != null) {
            DirectoryChooser fileChooser = new DirectoryChooser();
            File file;

            if (!pathCSV.isEmpty()) {
                if (new File(pathCSV).getParentFile().exists()) {
                    fileChooser.setInitialDirectory(new File(pathCSV).getParentFile());
                }
            }

            file = fileChooser.showDialog(stage);

            Set<Integer> descId = new LinkedHashSet<>();
            for (ModelInfo info : infos) {
                Set<String> localDesc = new LinkedHashSet<>(Arrays.asList(info.getDesc()));
                List<Integer> localDescId = new LinkedList<>();
                
                for (String cad : localDesc) {
                    for (int i = 0; i < train.numAttributes(); i++) {
                        if (train.attribute(i).name().equals(cad)) {
                            localDescId.add(i);
                        }
                    }
                }

//                localDescId.sort(Comparator.naturalOrder());
                localDescId.add(0, train.classIndex());
                descId.addAll(localDescId);
                int[] ids = localDescId.
                        stream().mapToInt(i -> i).toArray();
//                Arrays.sort(ids);
                // delete train MDs
                Remove rem = new Remove();
                rem.setAttributeIndicesArray(ids);
                rem.setInvertSelection(true);
                Instances instTrain = null;//new Instances(train);
                rem.setInputFormat(train);
                instTrain = Filter.useFilter(train, rem);
                          

                TomocomdDescriptor tom = new TomocomdDescriptor(localDesc);
                TomocomdInstances test = TomocomdInstances.merge(SDFFiles.getActivity(pathSDF, actCombo.getValue()), tom.compute(pathSDF));
                CSVFileManage.saveDescriptorMResult(new TomocomdInstances(instTrain),
                        new File(file, String.format("model%d_train.csv", info.getModelId())).getAbsolutePath());
                CSVFileManage.saveDescriptorMResult(test,
                        new File(file, String.format("model%d_test.csv", info.getModelId())).getAbsolutePath());
                instTrain.addAll(test);
                CSVFileManage.saveDescriptorMResult(new TomocomdInstances(instTrain),
                        new File(file, String.format("model%d_merge.csv", info.getModelId())).getAbsolutePath());
            }

            if (infos.size() > 1) {
                int[] ids = descId.
                        stream().mapToInt(i -> i).toArray();
//                Arrays.sort(ids);
                Set<String> mdNames = new LinkedHashSet<>();
                for (int i : ids) {
                    if(train.classIndex() != i )
                        mdNames.add(train.attribute(i).name());
                }

                Remove rem = new Remove();
                rem.setAttributeIndicesArray(ids);
                rem.setInvertSelection(true);
                Instances instTrain = null;//new Instances(train);
                rem.setInputFormat(train);
                instTrain = Filter.useFilter(train, rem);

                TomocomdDescriptor tom = new TomocomdDescriptor(mdNames);
                TomocomdInstances test = TomocomdInstances.merge(SDFFiles.getActivity(pathSDF, actCombo.getValue()), tom.compute(pathSDF));
                CSVFileManage.saveDescriptorMResult(new TomocomdInstances(instTrain),
                        new File(file, String.format("allDesc_train.csv")).getAbsolutePath());
                CSVFileManage.saveDescriptorMResult(test,
                        new File(file, String.format("allDesc_test.csv")).getAbsolutePath());
                instTrain.addAll(test);
                CSVFileManage.saveDescriptorMResult(new TomocomdInstances(instTrain),
                        new File(file, String.format("allDesc_merge.csv")).getAbsolutePath());
            }
            new ShowAlerts().showInfo("Saved "+infos.size()+" databases");
        }
    }
}
