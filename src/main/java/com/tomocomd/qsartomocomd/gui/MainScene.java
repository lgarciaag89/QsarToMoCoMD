/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomocomd.qsartomocomd.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.tomocomd.qsartomocomd.gui.alerts.ShowAlerts;
import com.tomocomd.qsartomocomd.gui.attributeFitBox.AttFitBoxFactory;
import com.tomocomd.qsartomocomd.gui.attributeFitBox.IAttFitBoxChields;
import com.tomocomd.qsartomocomd.gui.charts.SEChartController;
import com.tomocomd.qsartomocomd.gui.charts.SEGraphController;
import com.tomocomd.qsartomocomd.gui.descriptors.tomocomd.GroupsController;
import com.tomocomd.qsartomocomd.gui.descriptors.tomocomd.aggregations.ClasicalAggController;
import com.tomocomd.qsartomocomd.gui.descriptors.tomocomd.aggregations.MeansAggController;
import com.tomocomd.qsartomocomd.gui.descriptors.tomocomd.aggregations.NormsAggController;
import com.tomocomd.qsartomocomd.gui.descriptors.tomocomd.aggregations.StatisticsAggController;
import com.tomocomd.qsartomocomd.gui.descriptors.tomocomd.algForms.BituplesController;
import com.tomocomd.qsartomocomd.gui.descriptors.tomocomd.algForms.FourtuplesController;
import com.tomocomd.qsartomocomd.gui.descriptors.tomocomd.algForms.ThreetuplesController;
import com.tomocomd.qsartomocomd.gui.descriptors.tomocomd.metrics.MeasuresController;
import com.tomocomd.qsartomocomd.gui.descriptors.tomocomd.metrics.MinkowskimetricsController;
import com.tomocomd.qsartomocomd.gui.descriptors.tomocomd.metrics.OthermetricsController;
import com.tomocomd.qsartomocomd.gui.descriptors.tomocomd.properties.PhysicochemicalpropController;
import com.tomocomd.qsartomocomd.gui.descriptors.tomocomd.properties.VertexDegreeController;
import com.tomocomd.qsartomocomd.gui.fitBoxChange.FitBoxFactory;
import com.tomocomd.qsartomocomd.gui.fitBoxChange.IFitBoxChields;
import com.tomocomd.qsartomocomdlib.Exception.AggregationsConfException;
import com.tomocomd.qsartomocomdlib.Exception.ChiralConfExeption;
import com.tomocomd.qsartomocomdlib.Exception.ClassicalAggregationConfException;
import com.tomocomd.qsartomocomdlib.Exception.CutoffConfException;
import com.tomocomd.qsartomocomdlib.Exception.DiagValuesException;
import com.tomocomd.qsartomocomdlib.Exception.GroupsConfException;
import com.tomocomd.qsartomocomdlib.Exception.HAtomsException;
import com.tomocomd.qsartomocomdlib.Exception.MasInvalidAlgFormException;
import com.tomocomd.qsartomocomdlib.Exception.MatrixBaseException;
import com.tomocomd.qsartomocomdlib.Exception.MatrixFormException;
import com.tomocomd.qsartomocomdlib.Exception.PropertiesConfException;
import com.tomocomd.qsartomocomdlib.configuration.ExecutionArch;
import com.tomocomd.qsartomocomdlib.configuration.ProjectConf;
import com.tomocomd.qsartomocomdlib.configuration.descriptors.DescriptorFamilyType;
import com.tomocomd.qsartomocomdlib.configuration.evaluation.attributeevaluation.AttributeQualityConf;
import com.tomocomd.qsartomocomdlib.configuration.evaluation.attributeevaluation.AttributeQualityType;
import com.tomocomd.qsartomocomdlib.configuration.evaluation.fitnessfunction.EvaluationFunctionConf;
import com.tomocomd.qsartomocomdlib.configuration.evaluation.fitnessfunction.EvaluationFunctionType;
import com.tomocomd.qsartomocomdlib.configuration.evaluation.subsetqualityfunction.SubSetQualityConf;
import com.tomocomd.qsartomocomdlib.configuration.evaluation.subsetqualityfunction.SubSetQualityType;
import com.tomocomd.qsartomocomdlib.configuration.filters.FilterConfig;
import com.tomocomd.qsartomocomdlib.configuration.filters.FilterType;
import com.tomocomd.qsartomocomdlib.configuration.filters.ImputationMissingValuesType;
import com.tomocomd.qsartomocomdlib.configuration.filters.ImputationReplaceValue;
import com.tomocomd.qsartomocomdlib.configuration.search.geneticalgorithm.GAConf;
import com.tomocomd.qsartomocomdlib.configuration.search.geneticalgorithm.operators.GACrossoverConf;
import com.tomocomd.qsartomocomdlib.configuration.search.geneticalgorithm.operators.GACrossoverType;
import com.tomocomd.qsartomocomdlib.configuration.search.geneticalgorithm.operators.GAReplacePoblationConf;
import com.tomocomd.qsartomocomdlib.configuration.search.geneticalgorithm.operators.GAReplacePoblationType;
import com.tomocomd.qsartomocomdlib.configuration.search.geneticalgorithm.operators.GAReplaceSubPoblationConfig;
import com.tomocomd.qsartomocomdlib.configuration.search.geneticalgorithm.operators.GAReplaceSubPoblationType;
import com.tomocomd.qsartomocomdlib.configuration.search.geneticalgorithm.operators.GASelectionConfig;
import com.tomocomd.qsartomocomdlib.configuration.search.geneticalgorithm.operators.GASelectionType;
import com.tomocomd.qsartomocomdlib.configuration.search.geneticalgorithm.searchaggroperators.SearchAggrType;
import com.tomocomd.qsartomocomdlib.io.SDFFiles;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.UnaryOperator;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * FXML Controller class
 *
 * @author potter
 */
public class MainScene implements Initializable {

    private static final Logger LOGGER = (Logger) LogManager.getLogger(MainScene.class);

    private ProjectConf conf;

    private ExecutorService service;
    GAJFXTask ga;

    private IFitBoxChields fitOptValues;
    private IAttFitBoxChields AttFitOptValues;
    private Stage stage;

    @FXML
    private JFXTextField sdfFileTextFiled;
    @FXML
    private JFXComboBox<String> targetComboBox;
    @FXML
    private JFXTextField csvFileTextFiled;
    @FXML
    private ContextMenu tableCM;
    @FXML
    private MenuItem seGraphItem;
    @FXML
    private TreeTableView<ExecutionInfo> treeTableExecution;
    @FXML
    private TreeTableColumn<ExecutionInfo, String> nameTreeColumn;
    @FXML
    private TreeTableColumn<ExecutionInfo, Integer> numDescTreeColumn;
    @FXML
    private TreeTableColumn<ExecutionInfo, Double> evaTreeColumn;
    @FXML
    private JFXComboBox<ExecutionArch> parSchComboBox;
    @FXML
    private JFXButton loadProjBut;
    @FXML
    private JFXButton saveProjBut;
    @FXML
    private JFXButton cancelProjBut;
    @FXML
    private JFXButton runProjBut;
    @FXML
    private MenuItem loadMenuItem;
    @FXML
    private MenuItem saveMenuItem;
    @FXML
    private MenuItem exxitItem;
    @FXML
    private MenuItem cancelExecutionItem;
    @FXML
    private MenuItem runExdecutionItem;
    @FXML
    private MenuItem seGraphItem1;
    @FXML
    private JFXCheckBox midasSelectCheck;
    @FXML
    private JFXButton thrTuplasBut;
    @FXML
    private JFXButton quTuplasBut;
    @FXML
    private JFXButton biTuplasBut;
    @FXML
    private VBox boxMidas;
    @FXML
    private JFXCheckBox hAtomMidasCheck;
    @FXML
    private JFXCheckBox lPairMidasCheck;
    @FXML
    private JFXCheckBox dCenterMidasCheck;
    @FXML
    private JFXCheckBox offHAtomMidasCheck;
    @FXML
    private JFXCheckBox offDiagonalCheck;
    @FXML
    private GridPane tuplesGrid;
    @FXML
    private GridPane diagGrid;
    @FXML
    private JFXCheckBox NSCheckM;
    @FXML
    private JFXCheckBox SSCheckM;
    @FXML
    private JFXCheckBox MPCheckM;
    @FXML
    private JFXButton minkMetricButton;
    @FXML
    private JFXButton otherMetricButton;
    @FXML
    private JFXButton measuresBut;
    @FXML
    private JFXCheckBox tGroupCheck;
    @FXML
    private JFXCheckBox groupsMidasCheck;
    @FXML
    private JFXButton groupsButton;
    @FXML
    private JFXCheckBox nCiMidasCheck;
    @FXML
    private JFXCheckBox CiMidasCheck;
    @FXML
    private JFXButton phyChePropMidasButton;
    @FXML
    private JFXButton verDegPropMidasButton;
    @FXML
    private JFXButton normsAggMidasButton;
    @FXML
    private JFXButton meansAggMidasButton;
    @FXML
    private JFXButton staAggMidasButton;
    @FXML
    private JFXButton choAggMidasButton;
    @FXML
    private JFXButton claAggMidasButton;
    @FXML
    private JFXButton gowAggMidasButton;
    @FXML
    private JFXCheckBox masSelectCheck;
    @FXML
    private JFXCheckBox NSMasCheckM;
    @FXML
    private JFXCheckBox SSMasCheckM;
    @FXML
    private JFXCheckBox MPMasCheckM;
    @FXML
    private JFXCheckBox tGroupMasCheck;
    @FXML
    private JFXCheckBox groupsMasCheck;
    @FXML
    private JFXButton groupsMasButton;
    @FXML
    private JFXCheckBox atomBaseMasCheck;
    @FXML
    private JFXCheckBox bondBaseMasCheck;
    @FXML
    private JFXCheckBox nCiMasCheck;
    @FXML
    private JFXCheckBox CiMasCheck;
    @FXML
    private JFXCheckBox kaMAScheckBox;
    @FXML
    private JFXCheckBox srwMASCheckBox;
    @FXML
    private JFXCheckBox lpMASCheckBox;
    @FXML
    private JFXCheckBox nosrwMASCheckBox;
    @FXML
    private JFXButton phyChePropMasButton;
    @FXML
    private JFXButton verDegPropMasButton;
    @FXML
    private JFXButton normsAggMasButton;
    @FXML
    private JFXButton meansAggMasButton;
    @FXML
    private JFXButton staAggMasButton;
    @FXML
    private JFXButton choAggMasButton;
    @FXML
    private JFXButton claAggMasButton;
    @FXML
    private JFXButton gowAggMasButton;
    @FXML
    private JFXCheckBox hAtomMasCheck;
    @FXML
    private JFXCheckBox lPairMasCheck;
    @FXML
    private JFXCheckBox linearMasAlgebraicCheck;
    @FXML
    private JFXCheckBox BilinearMasAlgebraicCheck;
    @FXML
    private JFXCheckBox QuadraticMasAlgebraicCheck;
    @FXML
    private GridPane formsGrid;
    @FXML
    private VBox masBox;
    @FXML
    private JFXCheckBox offhAtomMasCheck;
    @FXML
    private JFXCheckBox offLPairMasCheck;
    @FXML
    private GridPane masDiaHGrid;
    @FXML
    private JFXComboBox<Integer> orderMidMatForm;
    @FXML
    private JFXComboBox<Integer> orderMasMaxForm;
    @FXML
    private JFXTextField numDescSubPobTextField;
    @FXML
    private JFXTextField numIterBox;
    @FXML
    private JFXComboBox<GASelectionType> seleOpeBox;
    @FXML
    private JFXTextField numSelTextField;
    @FXML
    private JFXTextField tourSizeTextField;
    @FXML
    private JFXComboBox<GACrossoverType> crossBox;
    @FXML
    private JFXTextField coPTextField;
    @FXML
    private JFXCheckBox incestCheck;
    @FXML
    private JFXComboBox<String> mutBox;
    @FXML
    private JFXTextField muPTextField;
    @FXML
    private JFXComboBox<GAReplaceSubPoblationType> repSubPobComBox;
    @FXML
    private JFXComboBox<GAReplacePoblationType> repBox;
    @FXML
    private JFXTextField repNumIterTextField;
    @FXML
    private JFXComboBox<AttributeQualityType> attBox;
    @FXML
    private VBox reliefBox;
    @FXML
    private JFXComboBox<EvaluationFunctionType> fitnessBox;
    @FXML
    private JFXComboBox<SubSetQualityType> vRetBox;
    @FXML
    private VBox fitBox;
    @FXML
    private JFXCheckBox nanFilterSubPobCheck;
    @FXML
    private JFXCheckBox seFilterPobCheck;
    @FXML
    private JFXTextField sePobTextField;
    @FXML
    private JFXCheckBox r2FilterPobCheck;
    @FXML
    private JFXTextField r2pobTextField;
    @FXML
    private JFXCheckBox corFilterPobCheck;
    @FXML
    private JFXTextField corpobTextField;
    @FXML
    private JFXCheckBox kurPobCheck;
    @FXML
    private JFXTextField kurpobTextField;
    @FXML
    private ProgressBar executingProgress;
    @FXML
    private HBox boxIndicator;
    @FXML
    private Label indTimeLabel;
    @FXML
    private TabPane tabPanelGASearch;
    @FXML
    private JFXTextField numIterSubPobTextField;
    @FXML
    private JFXComboBox<ImputationMissingValuesType> impTypeSub;
    @FXML
    private JFXComboBox<ImputationReplaceValue> impValueSub;
    @FXML
    private JFXCheckBox comSubSetCheckBox;
    @FXML
    private JFXCheckBox coopCheck;
    @FXML
    private JFXComboBox<SearchAggrType> aggSearchCombo;
    @FXML
    private MenuItem builModelMenulItem;
    @FXML
    private MenuItem builModelContextItem;
    @FXML
    private MenuItem showSEExtDBMenulItem;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            // TODO
            conf = new ProjectConf();
        } catch (Exception ex) {
            LOGGER.error("Problem load project conf", ex);
            System.exit(0);
        }

        // tree table
        nameTreeColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("path"));
        numDescTreeColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("numDesc"));
        evaTreeColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("eva"));
        treeTableExecution.setColumnResizePolicy(TreeTableView.CONSTRAINED_RESIZE_POLICY);
        treeTableExecution.setPlaceholder(new Label(""));
        treeTableExecution.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        treeTableExecution.setRoot(new TreeItem<>(new ExecutionInfo("Results", "", "")));

        // midas descriptor order init
        List<Integer> ordersList = new LinkedList<>();
        for (int i = 0; i <= 12; i++) {
            ordersList.add(i);
        }
        ObservableList<Integer> order = FXCollections.observableArrayList();
        order.addAll(ordersList);
        orderMidMatForm.setItems(order);

        // mas descriptor order init
        ordersList = new LinkedList<>();
        for (int i = 0; i <= 15; i++) {
            ordersList.add(i);
        }
        order = FXCollections.observableArrayList();
        order.addAll(ordersList);
        orderMasMaxForm.setItems(order);

        // execution parallel scheme
        ObservableList<ExecutionArch> archData = FXCollections.observableArrayList();
        archData.addAll(Arrays.asList(ExecutionArch.values()));
        parSchComboBox.setItems(archData);

        executingProgress.prefWidthProperty().bind(boxIndicator.widthProperty().subtract(80));
        indTimeLabel.setText("0%");

        initPanelSearch();

        try {
            loadFromConf();
        } catch (Exception ex) {
            new ShowAlerts().showError(String.format("Error loading project, %s", ex.getMessage()));
        }
    }

    private void initPanelSearch() {
        // search 
        // initialize search 
        // filters
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();
            if (change.getControlNewText().length() == 0) {
                return null;
            }
            if (text.matches("\\d*")) {
                return change;
            }
            return null;
        };

        UnaryOperator<TextFormatter.Change> filterfloat = c -> {
            if (c.isContentChange()) {
                if (c.getControlNewText().length() == 0) {
                    return null;
                }
                try {
                    Double c1 = Double.parseDouble(c.getControlNewText());
                    if (c1 >= 0 && c1 <= 1) {
                        if (c.getControlNewText().equals("01")) {
                            return null;
                        }
                        return c;
                    }
                } catch (NumberFormatException e) {
                }
                return null;

            }
            return c;
        };

        // replace subpopulation
        ObservableList<GAReplaceSubPoblationType> repSubData = FXCollections.observableArrayList();
        repSubData.addAll(Arrays.asList(GAReplaceSubPoblationType.values()));
        repSubPobComBox.setItems(repSubData);

        // list of replacement operatios 
        ObservableList<GAReplacePoblationType> listRepData = FXCollections.observableArrayList();
        listRepData.addAll(Arrays.asList(GAReplacePoblationType.values()));
        repBox.setItems(listRepData);
        repNumIterTextField.setTextFormatter(new TextFormatter<>(filter));

        // mutation options
        mutBox.getItems().add("Uniform");
        mutBox.setValue("Uniform");
        muPTextField.setTextFormatter(new TextFormatter<>(filterfloat));

        // crossover
        ObservableList<GACrossoverType> listCroData = FXCollections.observableArrayList();
        listCroData.addAll(Arrays.asList(GACrossoverType.values()));
        crossBox.setItems(listCroData);
        coPTextField.setTextFormatter(new TextFormatter<>(filterfloat));

        //selection
        ObservableList<GASelectionType> listSeleData = FXCollections.observableArrayList();
        listSeleData.addAll(Arrays.asList(GASelectionType.values()));
        seleOpeBox.setItems(listSeleData);
        numSelTextField.setTextFormatter(new TextFormatter<>(filter));
        tourSizeTextField.setTextFormatter(new TextFormatter<>(filter));

        // number of descriptors
        numDescSubPobTextField.setTextFormatter(new TextFormatter<>(filter));
        numIterBox.setTextFormatter(new TextFormatter<>(filter));
        numIterSubPobTextField.setTextFormatter(new TextFormatter<>(filter));

        // quality fnc attributes        
        ObservableList<AttributeQualityType> attData = FXCollections.observableArrayList();
        attData.addAll(Arrays.asList(AttributeQualityType.values()));
        attBox.setItems(attData);

        // search aggregations operators
        ObservableList<SearchAggrType> searchAgg = FXCollections.observableArrayList();
        searchAgg.addAll(Arrays.asList(SearchAggrType.values()));
        aggSearchCombo.setItems(searchAgg);

        // quality subset
        ObservableList<EvaluationFunctionType> evaData = FXCollections.observableArrayList();
        evaData.addAll(Arrays.asList(EvaluationFunctionType.values()));
        fitnessBox.setItems(evaData);
        ObservableList<SubSetQualityType> subsetEvaData = FXCollections.observableArrayList();
        subsetEvaData.addAll(Arrays.asList(SubSetQualityType.values()));
        vRetBox.setItems(subsetEvaData);

        // nan filters
        ObservableList<ImputationMissingValuesType> impTypesData = FXCollections.observableArrayList();
        impTypesData.addAll(Arrays.asList(ImputationMissingValuesType.values()));
        impTypeSub.setItems(impTypesData);

        ObservableList<ImputationReplaceValue> impValuesData = FXCollections.observableArrayList();
        impValuesData.addAll(Arrays.asList(ImputationReplaceValue.values()));
        impValueSub.setItems(impValuesData);
    }

    private void loadFromConf() throws Exception {

        sdfFileTextFiled.setText(conf.getSdfFile());

        if (conf.getSdfFile() != null) {

            try {
                Set targets = SDFFiles.getProperties(conf.getSdfFile());
                ObservableList<String> repSubData = FXCollections.observableArrayList();
                repSubData.addAll(targets);
                targetComboBox.getItems().clear();
                targetComboBox.setItems(repSubData);
                targetComboBox.setValue(conf.getTarget());
            } catch (FileNotFoundException ex) {
                LOGGER.error("Problems loading sdf file");
            }

        }
        csvFileTextFiled.setText(conf.getOutFileFile());

        setMasDescForm();
        setMidasDescForm();
        setGAForm();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private void setMasDescForm() {
        // if is selected
        masSelectCheck.setSelected(conf.getMasConf().isIsSelected());
        masBox.setDisable(!masSelectCheck.isSelected());

        // linears forms
        linearMasAlgebraicCheck.setSelected(conf.getMasConf().getAlgForm().isL());

        // bilinear forms
        BilinearMasAlgebraicCheck.setSelected(conf.getMasConf().getAlgForm().isB());

        // quadratic forms
        QuadraticMasAlgebraicCheck.setSelected(conf.getMasConf().getAlgForm().isQ());

        // set diags conf
        lPairMasCheck.setSelected(conf.getMasConf().getDiags().isLp());
        offLPairMasCheck.setSelected(conf.getMasConf().getDiags().isNone());

        //set HAtom conf
        offhAtomMasCheck.setSelected(conf.getMasConf().getIsH().isIsNotHFilled());
        hAtomMasCheck.setSelected(conf.getMasConf().getIsH().isIsHFilled());

        // matrix forms 
        NSMasCheckM.setSelected(conf.getMasConf().getmForm().isNs());
        SSMasCheckM.setSelected(conf.getMasConf().getmForm().isSs());
        MPMasCheckM.setSelected(conf.getMasConf().getmForm().isMp());
        orderMasMaxForm.setValue(conf.getMasConf().getmForm().getOrden());

        // groups 
        tGroupMasCheck.setSelected(conf.getMasConf().getGroups().isT());
        groupsMasCheck.setSelected(conf.getMasConf().getGroups().isLocal());

        // matrix base
        atomBaseMasCheck.setSelected(conf.getMasConf().getmBase().isAB());
        bondBaseMasCheck.setSelected(conf.getMasConf().getmBase().isBB());

        // chiral
        nCiMasCheck.setSelected(conf.getMasConf().getChiral().isnCi());
        CiMasCheck.setSelected(conf.getMasConf().getChiral().isCi());

        // cutoff 
        kaMAScheckBox.setSelected(conf.getMasConf().getCutoff().isKA());
        lpMASCheckBox.setSelected(conf.getMasConf().getCutoff().isLGP());
        nosrwMASCheckBox.setSelected(conf.getMasConf().getCutoff().isNSRW());
        srwMASCheckBox.setSelected(conf.getMasConf().getCutoff().isSRW());

    }

    private void setMidasDescForm() {
        // if is selected
        midasSelectCheck.setSelected(conf.getMidConf().isIsSelected());
        boxMidas.setDisable(!midasSelectCheck.isSelected());

        // set diags conf
        lPairMidasCheck.setSelected(conf.getMidConf().getDiags().isLp());
        offDiagonalCheck.setSelected(conf.getMidConf().getDiags().isNone());
        dCenterMidasCheck.setSelected(conf.getMidConf().getDiags().isDc());

        //set HAtom conf
        offHAtomMidasCheck.setSelected(conf.getMidConf().getIsH().isIsNotHFilled());
        hAtomMidasCheck.setSelected(conf.getMidConf().getIsH().isIsHFilled());

        // matrix forms 
        NSCheckM.setSelected(conf.getMidConf().getmForm().isNs());
        SSCheckM.setSelected(conf.getMidConf().getmForm().isSs());
        MPCheckM.setSelected(conf.getMidConf().getmForm().isMp());
        orderMidMatForm.setValue(conf.getMidConf().getmForm().getOrden());

        // groups 
        tGroupCheck.setSelected(conf.getMidConf().getGroups().isT());
        groupsMidasCheck.setSelected(conf.getMidConf().getGroups().isLocal());

        // chiral
        nCiMidasCheck.setSelected(conf.getMidConf().getChiral().isnCi());
        CiMidasCheck.setSelected(conf.getMidConf().getChiral().isCi());

        validateMeasures();

    }

    private void setGAForm() {

        //cooperative mode
        coopCheck.setSelected(((GAConf) conf.getSearch()).isCoop());
        //set architecture         
        parSchComboBox.setValue(conf.getArch());

        // replace subpopulation
        repSubPobComBox.setValue(((GAConf) conf.getSearch()).getFamConf().getReplaceSubConf().getType());

        // replace population
        repBox.setValue(((GAConf) conf.getSearch()).getFamConf().getPobConf().getType());
        setrepNum();

        // mutation
        muPTextField.setText(Double.toString(((GAConf) conf.getSearch()).getFamConf().getMutationRate()));

        // crossover        
        crossBox.setValue(((GAConf) conf.getSearch()).getFamConf().getCrosConf().getType());
        coPTextField.setText(Double.toString(((GAConf) conf.getSearch()).getFamConf().getCrosRate()));
        incestCheck.setSelected(((GAConf) conf.getSearch()).getFamConf().isIncest());

        //seleccion
        numSelTextField.setText(Integer.toString(((GAConf) conf.getSearch()).getFamConf().getSelConf().getCant()));
        seleOpeBox.setValue(((GAConf) conf.getSearch()).getFamConf().getSelConf().getType());
        setTourSize();

        // number of descriptors to generate for family
        numDescSubPobTextField.setText(Integer.toString(((GAConf) conf.getSearch()).getFamConf().getNumDesc()));

        // number of iterations for the general scheme
        numIterBox.setText(Integer.toString(((GAConf) conf.getSearch()).getNumIter()));

        // number of iterations for subpoblations 
        numIterSubPobTextField.setText(Integer.toString(((GAConf) conf.getSearch()).getFamConf().getNumIter()));

        // quality fnc attributes
        attBox.setValue(((GAConf) conf.getSearch()).getFamConf().getAttConf().getType());
        setQualityAttBox(true);
        comSubSetCheckBox.setSelected(((GAConf) conf.getSearch()).getFamConf().isCompareSubset());

        // quality subset
        fitnessBox.setValue(((GAConf) conf.getSearch()).getSubsetEva().getType());
        vRetBox.setValue(((GAConf) conf.getSearch()).getSubsetEva().getMethod().getType());
        setFitBox(true);

        // search aggregations operators
        aggSearchCombo.setValue(((GAConf) conf.getSearch()).getFamConf().getsAType());

        initFilters();
    }

    private void initFilters() {
        // set general filters
        nanFilterSubPobCheck.setSelected(false);

        seFilterPobCheck.setSelected(false);
        r2FilterPobCheck.setSelected(false);
        corFilterPobCheck.setSelected(false);
        kurPobCheck.setSelected(false);
        sePobTextField.setDisable(true);
        r2pobTextField.setDisable(true);
        corpobTextField.setDisable(true);
        kurpobTextField.setDisable(true);
        List<FilterConfig> filters = ((GAConf) conf.getSearch()).getFiltersConfig();

        for (FilterConfig fConf : filters) {
            switch (fConf.getType()) {
                case Corr:
                    corFilterPobCheck.setSelected(true);
                    corpobTextField.setDisable(false);
                    corpobTextField.setText(fConf.getOptions()[1]);
                    break;
                case Kur:
                    kurPobCheck.setSelected(true);
                    kurpobTextField.setDisable(false);
                    kurpobTextField.setText(fConf.getOptions()[1]);
                    break;
                case R2:
                    r2FilterPobCheck.setSelected(true);
                    r2pobTextField.setDisable(false);
                    r2pobTextField.setText(fConf.getOptions()[1]);
                    break;
                case SE:
                    seFilterPobCheck.setSelected(true);
                    sePobTextField.setDisable(false);
                    sePobTextField.setText(fConf.getOptions()[1]);
                    break;
            }
        }

        filters = ((GAConf) conf.getSearch()).getFamConf().getFiltersConfig();

        for (FilterConfig fConf : filters) {
            switch (fConf.getType()) {
                case NaN:
                    nanFilterSubPobCheck.setSelected(true);

                    impTypeSub.setDisable(false);
                    impTypeSub.setValue(ImputationMissingValuesType.valueOf(fConf.getOptions()[1]));
                    if (impTypeSub.getValue() == ImputationMissingValuesType.Imputation) {
                        impValueSub.setValue(ImputationReplaceValue.valueOf(fConf.getOptions()[3]));
                    }
                    break;
            }
        }

    }

    private void setFitBox(boolean flag) {
        EvaluationFunctionType type = fitnessBox.getValue();
        fitOptValues = FitBoxFactory.getChield(type, stage);
        fitBox.getChildren().clear();
        if (fitOptValues != null) {
            if (((GAConf) conf.getSearch()).getSubsetEva().getOptions() != null && flag) {
                if (((GAConf) conf.getSearch()).getSubsetEva().getOptions().length > 0) {
                    fitOptValues.setOptions(((GAConf) conf.getSearch()).getSubsetEva().getOptions());
                }
            }
            fitBox.setStyle("-fx-border-color:gray");
            fitBox.getChildren().addAll(fitOptValues.getChilds());
            fitBox.setPadding(new Insets(10, 10, 10, 10));
        } else {
            fitBox.setStyle("");
        }
    }

    private void setQualityAttBox(boolean flag) {
        AttributeQualityType type = attBox.getValue();
        AttFitOptValues = AttFitBoxFactory.getChield(type, stage);
        reliefBox.getChildren().clear();

        if (AttFitOptValues != null) {
            String[] opts = ((GAConf) conf.getSearch()).getFamConf().getAttConf().getOption();
            if (opts != null && flag) {
                AttFitOptValues.setOptions(opts);
            }
            reliefBox.getChildren().addAll(AttFitOptValues.getChilds());
        } else {
            reliefBox.setStyle("");
        }
    }

    private void setrepNum() {
        GAReplacePoblationType type = repBox.getValue();
        if (type == GAReplacePoblationType.Steadystatereset) {
            repNumIterTextField.setDisable(false);
            if (((GAConf) conf.getSearch()).getFamConf().getPobConf().getOptions().length >= 2) {
                repNumIterTextField.setText(((GAConf) conf.getSearch()).getFamConf().getPobConf().getOptions()[1]);
            } else {
                repNumIterTextField.setText("1");
            }
        } else {
            repNumIterTextField.setDisable(true);
        }

    }

    private void setTourSize() {
        GASelectionType type = seleOpeBox.getValue();
        if (type == GASelectionType.Tournament || type == GASelectionType.ProgressiveSelection) {
            tourSizeTextField.setDisable(false);
            String[] opts = ((GAConf) conf.getSearch()).getFamConf().getSelConf().getOptions();
            if (type == GASelectionType.Tournament) {
                if (opts.length >= 2) {
                    tourSizeTextField.setText(opts[1]);
                } else {
                    tourSizeTextField.setText("5");
                }
            } else {
                if (opts.length > 2) {
                    tourSizeTextField.setText(opts[3]);
                } else {
                    tourSizeTextField.setText("5");
                }
            }
        } else {
            tourSizeTextField.setDisable(true);
        }
    }

    @FXML
    private void findSDFFileOnActionMouse(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        File file;
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("SDF Files", "*.sdf"),
                new FileChooser.ExtensionFilter("SDF Files", "*.SDF")
        );
        if (sdfFileTextFiled.getText() != null) {
            if (!sdfFileTextFiled.getText().isEmpty()) {
                file = new File(sdfFileTextFiled.getText());
                if (file.getParentFile().exists()) {
                    fileChooser.setInitialDirectory(file.getParentFile());
                }
            }
        }
        file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            sdfFileTextFiled.setText(file.getAbsolutePath());
            conf.setSdfFile(file.getAbsolutePath());
            try {
                Set targets = SDFFiles.getProperties(file.getAbsolutePath());

                ObservableList<String> repSubData = FXCollections.observableArrayList();
                repSubData.addAll(targets);
                targetComboBox.getItems().clear();
                targetComboBox.setItems(repSubData);
                targetComboBox.setValue(targetComboBox.getItems().get(0));
            } catch (Exception ex) {
                new ShowAlerts().showError(ex.getMessage());
                sdfFileTextFiled.setText("");
                conf.setSdfFile("");
            }
        }
    }

    @FXML
    private void saeCSVFileOnAction(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        File file;
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV Files", "*.csv"),
                new FileChooser.ExtensionFilter("CSV Files", "*.CSV")
        );
        if (csvFileTextFiled.getText() != null) {
            if ((!csvFileTextFiled.getText().isEmpty())) {
                file = new File(csvFileTextFiled.getText());
                if (file.getParentFile().exists()) {
                    fileChooser.setInitialDirectory(file.getParentFile());
                }
            }
        }

        file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            csvFileTextFiled.setText(file.getAbsolutePath());
            conf.setOutFileFile(file.getAbsolutePath());
        }
    }

    private List<String> getPathFromtableExec(ObservableList<TreeItem<ExecutionInfo>> infos) {

        List<String> names = new LinkedList<>();
        for (TreeItem<ExecutionInfo> info : infos) {
            if (info.isLeaf()) {
                names.add(info.getValue().getAbsolutePath());
            } else {
                names.addAll(getPathFromtableExec(info.getChildren()));
            }
        }
        return names;
    }

    @FXML
    private void seGraphShowAction(ActionEvent event) throws IOException {
        ObservableList<TreeItem<ExecutionInfo>> infos = treeTableExecution.getSelectionModel().getSelectedItems();
        if (infos.isEmpty()) {
            new ShowAlerts().showError("Select at least one subset");
            return;
        }

        List<String> names = getPathFromtableExec(infos);

//        SELineShart.showSEShart(names);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/fxml/charts/SEChart.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/gui/styles/Styles.css");
        Stage myStage = new Stage();
        myStage.setScene(scene);
        myStage.setMaximized(true);
        myStage.setResizable(true);
        ((SEChartController) fxmlLoader.getController()).setStage(myStage);
        ((SEChartController) fxmlLoader.getController()).setPaths(names);
        ((SEChartController) fxmlLoader.getController()).
                createLineChart(conf.getTarget());
        myStage.setTitle("QSAR-ToMoCoMD");
        Image ima = new Image(this.getClass().getResource("/gui/icons/molecule.png").toString());
        myStage.show();
    }

    @FXML
    private void loadProjAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("QSAR-ToMoCoMD Project Files", "*.qtproj"));
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            try {
                ObjectInputStream dataread = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
                conf = (ProjectConf) dataread.readObject();
                dataread.close();
                loadFromConf();
            } catch (Exception ex) {
                new ShowAlerts().showError(ex.getMessage());
                return;
            }
            new ShowAlerts().showInfo(String.format("File %s reader successful", file.getName()));
        }
    }

    private GAReplacePoblationConf getReplacePoblationConf() throws Exception {
        GAReplacePoblationType type = repBox.getValue();
        String[] opt = new String[]{};
        if (type == GAReplacePoblationType.Steadystatereset) {
            int num = Integer.parseInt(repNumIterTextField.getText());
            if (num > ((GAConf) conf.getSearch()).getNumIter()) {
                throw new Exception("Number of iterations for reset population must be lowest than the number iterations");
            }
            opt = new String[]{"-c", repNumIterTextField.getText()};
        }
        return new GAReplacePoblationConf(type, opt);
    }

    private List<FilterConfig> getFiltersSubConfig() {
        List<FilterConfig> filters = new LinkedList<>();
        if (nanFilterSubPobCheck.isSelected()) {
            if (impTypeSub.getValue() == ImputationMissingValuesType.Delete) {
                filters.add(new FilterConfig(FilterType.NaN, new String[]{"-t", ImputationMissingValuesType.Delete.toString()}));
            } else {
                filters.add(new FilterConfig(FilterType.NaN, new String[]{"-t", ImputationMissingValuesType.Imputation.toString(),
                    "-v", impValueSub.getValue().toString()}));
            }
        }
        return filters;
    }

    private List<FilterConfig> getFiltersConfig() {
        List<FilterConfig> filters = new LinkedList<>();

        if (seFilterPobCheck.isSelected()) {
            filters.add(new FilterConfig(FilterType.SE, new String[]{"-t", sePobTextField.getText()}));
        }

        if (r2FilterPobCheck.isSelected()) {
            filters.add(new FilterConfig(FilterType.R2, new String[]{"-t", r2pobTextField.getText()}));
        }

        if (corFilterPobCheck.isSelected()) {
            filters.add(new FilterConfig(FilterType.Corr, new String[]{"-t", corpobTextField.getText()}));
        }

        if (kurPobCheck.isSelected()) {
            filters.add(new FilterConfig(FilterType.Kur, new String[]{"-t", kurpobTextField.getText()}));
        }
        return filters;
    }

    private AttributeQualityConf getAttQuaConf() {
        AttributeQualityType attType = attBox.getValue();
        String[] optAtt = new String[]{};
        switch (attType) {
            case ReliefF:
                optAtt = AttFitOptValues.getOptions().split(" ");
                break;
            case SE_ReliefF:
                optAtt = AttFitOptValues.getOptions().split(" ");
                break;
            case Choquet:
                optAtt = AttFitOptValues.getOptions().split(" ");
                break;
        }
        AttributeQualityConf attConf = new AttributeQualityConf(attBox.getValue(), optAtt);
        return attConf;
    }

    private GASelectionConfig getSelectionConfig() throws Exception {
        int num = Integer.parseInt(numSelTextField.getText());
        if (num % 2 != 0) {
            throw new Exception("Number of parents must be even");
        }

        if (num > ((GAConf) conf.getSearch()).getFamConf().getNumDesc()) {
            throw new Exception("Number of parents must be lowest than the number of molecular descriptors");
        }
        GASelectionType type = seleOpeBox.getValue();
        String[] opt = new String[]{};
        if (type == GASelectionType.Tournament) {
            int tourSize = Integer.parseInt(tourSizeTextField.getText());
            if (tourSize > ((GAConf) conf.getSearch()).getFamConf().getNumDesc()) {
                throw new Exception("Number of tournament contestant must be lowest than the number of descriptors");
            }
            opt = new String[]{"-s", tourSizeTextField.getText()};
        } else if (type == GASelectionType.ProgressiveSelection) {
            opt = new String[]{"-i", numIterBox.getText(), "-s", tourSizeTextField.getText()};
        }
        return new GASelectionConfig(num, type, opt);
    }

    private EvaluationFunctionConf getFitConf() throws Exception {
        EvaluationFunctionType typeFit = fitnessBox.getValue();
        String[] options = new String[]{};
        String opts;
        switch (typeFit) {
            case SE_SUM_Relief:
                opts = String.format("%s %s %s %s", AttributeQualityType.SE.toString(), "", AttributeQualityType.ReliefF.toString(), "temp");
                options = opts.split(" ");
                options[options.length - 1] = fitOptValues.getOptions();
//                break;
//            case CfsSubsetReliefSE:
//                opts = fitOptValues.getOptions();
//                String reOpts = opts.substring(opts.indexOf("-M"));
//                options = opts.substring(0, opts.indexOf("-M") + 1).split(" ");
//                options[3] = reOpts;
//                break;
            case ReliefRankMean:
                opts = fitOptValues.getOptions();
                options = opts.split(" ");
        }
        return new EvaluationFunctionConf(typeFit, options, new SubSetQualityConf(vRetBox.getValue()));

    }

    private void writeConf() throws Exception {
        // set number of desc
        ((GAConf) conf.getSearch()).getFamConf().setNumDesc(Integer.parseInt(numDescSubPobTextField.getText()));

        // number of iterations for general search
        ((GAConf) conf.getSearch()).setNumIter(Integer.parseInt(numIterBox.getText()));

        // number of iterations for sub population search
        ((GAConf) conf.getSearch()).getFamConf().setNumIter(Integer.parseInt(numIterSubPobTextField.getText()));

        // selection
        ((GAConf) conf.getSearch()).getFamConf().setSelConf(getSelectionConfig());

        // crossover operator
        ((GAConf) conf.getSearch()).getFamConf().setCrosConf(new GACrossoverConf(crossBox.getValue()));
        ((GAConf) conf.getSearch()).getFamConf().setCrosRate(Double.parseDouble(coPTextField.getText()));
        ((GAConf) conf.getSearch()).getFamConf().setIncest(incestCheck.isSelected());

        // mutation
        ((GAConf) conf.getSearch()).getFamConf().setMutationRate(Double.parseDouble(muPTextField.getText()));

        // replace subpopulation
        ((GAConf) conf.getSearch()).getFamConf().setReplaceSubConf(new GAReplaceSubPoblationConfig(repSubPobComBox.getValue()));

        // replace population
        ((GAConf) conf.getSearch()).getFamConf().setPobConf(getReplacePoblationConf());

        // filter subpopulation
        ((GAConf) conf.getSearch()).getFamConf().setFiltersConfig(getFiltersSubConfig());

        // filter population
        ((GAConf) conf.getSearch()).setFiltersConfig(getFiltersConfig());

        // attribute quality function
        ((GAConf) conf.getSearch()).getFamConf().setAttConf(getAttQuaConf());

        // subset quality fnc
        ((GAConf) conf.getSearch()).setSubsetEva(getFitConf());

        // files and target
        if (csvFileTextFiled.getText() == null) {
            throw new Exception("You have to select a scv file output");
        }
        if (csvFileTextFiled.getText().isEmpty()) {
            throw new Exception("You have to select a scv file output");
        }

        conf.setOutFileFile(csvFileTextFiled.getText());

        if (targetComboBox.getValue() == null) {
            throw new Exception("You have to select a target");
        }
        if (targetComboBox.getValue().isEmpty()) {
            throw new Exception("You have to select a target");
        }
        conf.setTarget(targetComboBox.getValue());
        if (sdfFileTextFiled.getText() == null) {
            throw new Exception("You have to select a sdf file");
        }
        if (sdfFileTextFiled.getText().isEmpty()) {
            throw new Exception("You have to select a sdf file");
        }
        conf.setSdfFile(sdfFileTextFiled.getText());
    }

    @FXML
    private void saveProjAct(ActionEvent event) {
        try {
            writeConf();
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("QSAR-ToMoCoMD Project Files", "*.qtproj"));
            File file = fileChooser.showSaveDialog(stage);
            if (file != null) {
                ObjectOutputStream datafile = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
                datafile.writeObject(conf);
                datafile.close();
                new ShowAlerts().showInfo(String.format("File %s created successful", file.getName()));
            }
        } catch (Exception ex) {
            new ShowAlerts().showError(ex.getMessage());
        }
    }

    @FXML
    private void cancelProjAct(ActionEvent event) {
        if (new ShowAlerts().showConfirmation("Are you want stop the execution?")) {

            if (ga != null) {
                if (!ga.isDone()) {
                    boolean b = ga.cancel(true);
                    if (b) {
                        new ShowAlerts().showInfo("Task cancelled");
                        ended();
                        service.shutdownNow();
                    } else {
                        new ShowAlerts().showInfo("Task cannot be canceled");
                    }
                }
            }
        }
    }

    @FXML
    private void runProjAction(ActionEvent event) {
        try {
            writeConf();
        } catch (Exception ex) {
            new ShowAlerts().showError(ex.getMessage());
            LOGGER.error(ex);
            return;
        }
        try {
            service = Executors.newSingleThreadExecutor();
            final long timeini = System.currentTimeMillis();
            ga = new GAJFXTask(conf, treeTableExecution);
            executingProgress.progressProperty().unbind();
            executingProgress.progressProperty().bind(ga.progressProperty());
//            indTimeLabel.textProperty().bind(ga.progressProperty().asString());
            runProjBut.setDisable(true);
            cancelProjBut.setDisable(false);

            ga.messageProperty().addListener(new ChangeListener<String>() {
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    indTimeLabel.setText(newValue);
                }
            });

            ga.setOnSucceeded(eventFinish -> {
                long finalTime = System.currentTimeMillis() - timeini;
                LOGGER.info(String.format("Execution time:%d", finalTime));
                new ShowAlerts().showInfo("Execution completed");
                indTimeLabel.setText("0%");
                service.shutdown();
                ended();
            });
            ga.setOnFailed(evt -> {
                Throwable exception = evt.getSource().getException();
                if (exception != null) {
                    LOGGER.error(exception.toString(), exception);
                    new ShowAlerts().showError(exception.toString());
                }
                if (!ga.isDone()) {
                    ga.cancel(true);
                    service.shutdown();
                }
                ended();
            });
            service.submit(ga);
        } catch (Exception ex) {
            if (ga != null) {
                ga.cancel(true);
            }
            service.shutdown();
            new ShowAlerts().showError(ex.getMessage());
            LOGGER.error(ex.toString(), ex);
            ended();
        }
    }

    private void ended() {
        executingProgress.progressProperty().unbind();
        executingProgress.setProgress(0);
        cancelProjBut.setDisable(true);
        runProjBut.setDisable(false);
    }

    @FXML
    private void exitApp(ActionEvent event) {
        stopALL(null);
    }

    public void stopALL(WindowEvent event) {
        if (new ShowAlerts().showConfirmation("Are you sure?")) {
            if (ga != null) {
                if (!ga.isDone()) {
                    ga.cancel();
                }
                service.shutdownNow();
            }
            Platform.exit();
            System.exit(0);
        } else {
            if (event != null) {
                event.consume();
            }
        }
    }

    @FXML
    private void setlPairMidasAction(ActionEvent event) {
        try {
            conf.getMidConf().getDiags().setLp(lPairMidasCheck.isSelected());
        } catch (DiagValuesException ex) {
            offDiagonalCheck.setSelected(conf.getMidConf().getDiags().isNone());
            lPairMidasCheck.setSelected(conf.getMidConf().getDiags().isLp());
            dCenterMidasCheck.setSelected(conf.getMidConf().getDiags().isDc());
            new ShowAlerts().showWarning(ex.getMessage());
        }
    }

    @FXML
    private void setdCenterMidasAction(ActionEvent event) {
        try {
            conf.getMidConf().getDiags().setDc(dCenterMidasCheck.isSelected());
        } catch (DiagValuesException ex) {
            offDiagonalCheck.setSelected(conf.getMidConf().getDiags().isNone());
            lPairMidasCheck.setSelected(conf.getMidConf().getDiags().isLp());
            dCenterMidasCheck.setSelected(conf.getMidConf().getDiags().isDc());
            new ShowAlerts().showWarning(ex.getMessage());
        }
    }

    @FXML
    private void offDiagonalAct(ActionEvent event) {
        try {
            conf.getMidConf().getDiags().setNone(offDiagonalCheck.isSelected());
        } catch (DiagValuesException ex) {
            offDiagonalCheck.setSelected(conf.getMidConf().getDiags().isNone());
            lPairMidasCheck.setSelected(conf.getMidConf().getDiags().isLp());
            dCenterMidasCheck.setSelected(conf.getMidConf().getDiags().isDc());
            new ShowAlerts().showWarning(ex.getMessage());
        }
        conf.getMidConf().getDiags().getValues();
    }

    @FXML
    private void sethAtomMidasAction(ActionEvent event) {
        try {
            conf.getMidConf().getIsH().setIsHFilled(hAtomMidasCheck.isSelected());
        } catch (HAtomsException ex) {
            offHAtomMidasCheck.setSelected(conf.getMidConf().getIsH().isIsNotHFilled());
            hAtomMidasCheck.setSelected(conf.getMidConf().getIsH().isIsHFilled());
            new ShowAlerts().showWarning(ex.getMessage());
        }
    }

    @FXML
    private void sethOffHAtomMidasAction(ActionEvent event) {
        try {
            conf.getMidConf().getIsH().setIsNotHFilled(offHAtomMidasCheck.isSelected());
        } catch (HAtomsException ex) {
            offHAtomMidasCheck.setSelected(conf.getMidConf().getIsH().isIsNotHFilled());
            hAtomMidasCheck.setSelected(conf.getMidConf().getIsH().isIsHFilled());
            new ShowAlerts().showWarning(ex.getMessage());
        }
        conf.getMasConf().getIsH().getValues();
    }

    @FXML
    private void setorderMidMatFormAction(ActionEvent event) {
        conf.getMidConf().getmForm().setOrden(orderMidMatForm.getValue());
    }

    @FXML
    private void setNSCheckMAction(ActionEvent event) {
        try {
            conf.getMidConf().getmForm().setNs(NSCheckM.isSelected());
        } catch (MatrixFormException ex) {
            NSCheckM.setSelected(conf.getMidConf().getmForm().isNs());
            SSCheckM.setSelected(conf.getMidConf().getmForm().isSs());
            MPCheckM.setSelected(conf.getMidConf().getmForm().isMp());
            new ShowAlerts().showWarning(ex.getMessage());
        }
    }

    @FXML
    private void setSSCheckMAction(ActionEvent event) {
        try {
            conf.getMidConf().getmForm().setSs(SSCheckM.isSelected());
        } catch (MatrixFormException ex) {
            NSCheckM.setSelected(conf.getMidConf().getmForm().isNs());
            SSCheckM.setSelected(conf.getMidConf().getmForm().isSs());
            MPCheckM.setSelected(conf.getMidConf().getmForm().isMp());
            new ShowAlerts().showWarning(ex.getMessage());
        }
    }

    @FXML
    private void setMPCheckMAction(ActionEvent event) {
        try {
            conf.getMidConf().getmForm().setMp(MPCheckM.isSelected());
        } catch (MatrixFormException ex) {
            NSCheckM.setSelected(conf.getMidConf().getmForm().isNs());
            SSCheckM.setSelected(conf.getMidConf().getmForm().isSs());
            MPCheckM.setSelected(conf.getMidConf().getmForm().isMp());
            new ShowAlerts().showWarning(ex.getMessage());
        }
    }

    @FXML
    private void schowMinkMetricAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/fxml/descriptors/tomocomd/metrics/Minkowskimetrics.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        scene.getStylesheets().add("/gui/styles/descriptorsParams.css");
        stage.setScene(scene);
        stage.setResizable(false);
        ((MinkowskimetricsController) fxmlLoader.getController()).setStage(stage);
        ((MinkowskimetricsController) fxmlLoader.getController()).setMink(conf.getMidConf().getMetrics().getMink());
        stage.setTitle("Minkowsky metrics midas descriptors");
        stage.getIcons().add(new Image(this.getClass().getResource("/gui/icons/molecule.png").toString()));
        stage.setOnCloseRequest(action -> {
            ((MinkowskimetricsController) fxmlLoader.getController()).setMink(conf.getMidConf().getMetrics().getMink());
            stage.close();
        });
        stage.showAndWait();
        conf.getMidConf().getMetrics().setMink(((MinkowskimetricsController) fxmlLoader.getController()).getMink());
    }

    @FXML
    private void schowOtherMetricAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/fxml/descriptors/tomocomd/metrics/Othermetrics.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        scene.getStylesheets().add("/gui/styles/descriptorsParams.css");
        stage.setScene(scene);
        stage.setResizable(false);
        ((OthermetricsController) fxmlLoader.getController()).setStage(stage);
        ((OthermetricsController) fxmlLoader.getController()).setOther(conf.getMidConf().getMetrics().getOther());
        stage.setTitle("Others metrics midas descriptors");
        stage.getIcons().add(new Image(this.getClass().getResource("/gui/icons/molecule.png").toString()));
        stage.setOnCloseRequest(action -> {
            ((OthermetricsController) fxmlLoader.getController()).setOther(conf.getMidConf().getMetrics().getOther());
            stage.close();
        });
        stage.showAndWait();
        conf.getMidConf().getMetrics().setOther(((OthermetricsController) fxmlLoader.getController()).getOther());
    }

    @FXML
    private void measuresButAct(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/fxml/descriptors/tomocomd/metrics/Measures.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        scene.getStylesheets().add("/gui/styles/descriptorsParams.css");
        stage.setScene(scene);
        stage.setResizable(false);
        ((MeasuresController) fxmlLoader.getController()).setStage(stage);
        ((MeasuresController) fxmlLoader.getController()).setConf(conf.getMidConf().getMetrics());
        stage.setTitle("Measures metrics");
        stage.getIcons().add(new Image(this.getClass().getResource("/gui/icons/molecule.png").toString()));
        stage.setOnCloseRequest(action -> {
            ((MeasuresController) fxmlLoader.getController()).setConf(conf.getMidConf().getMetrics());
            stage.close();
        });
        stage.showAndWait();
        conf.getMidConf().getMetrics().setGeo(((MeasuresController) fxmlLoader.getController()).getGeo());
        conf.getMidConf().getMetrics().setClus(((MeasuresController) fxmlLoader.getController()).getClus());
        conf.getMidConf().getMetrics().setSta(((MeasuresController) fxmlLoader.getController()).getSta());
    }

    @FXML
    private void tGroupMasAction(ActionEvent event) {
        try {
            conf.getMasConf().getGroups().setT(tGroupMasCheck.isSelected());
        } catch (GroupsConfException ex) {
            tGroupMasCheck.setSelected(conf.getMasConf().getGroups().isT());
            groupsMasCheck.setSelected(conf.getMasConf().getGroups().isLocal());
            new ShowAlerts().showWarning(ex.getMessage());
        }
        groupsMasButton.setDisable(!groupsMasCheck.isSelected());
    }

    @FXML
    private void actGroupMasAction(ActionEvent event) {
        try {
            conf.getMasConf().getGroups().setLocals(groupsMasCheck.isSelected());
        } catch (GroupsConfException ex) {
            tGroupMasCheck.setSelected(conf.getMasConf().getGroups().isT());
            groupsMasCheck.setSelected(conf.getMasConf().getGroups().isLocal());
            new ShowAlerts().showWarning(ex.getMessage());
        }
        groupsMasButton.setDisable(!groupsMasCheck.isSelected());
    }

    @FXML
    private void showMasGroupsAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/fxml/descriptors/tomocomd/Groups.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/gui/styles/descriptorsParams.css");
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        ((GroupsController) fxmlLoader.getController()).setStage(stage);
        ((GroupsController) fxmlLoader.getController()).setG(conf.getMasConf().getGroups());
        stage.setTitle("Groups mas descriptors");
        stage.getIcons().add(new Image(this.getClass().getResource("/gui/icons/molecule.png").toString()));
        stage.setOnCloseRequest(action -> {
            ((GroupsController) fxmlLoader.getController()).setG(conf.getMasConf().getGroups());
            stage.close();
        });
        stage.showAndWait();
        conf.getMasConf().setGroups(((GroupsController) fxmlLoader.getController()).getG());
        tGroupMasCheck.setSelected(conf.getMasConf().getGroups().isT());
        groupsMasCheck.setSelected(conf.getMasConf().getGroups().isLocal());
        groupsMasButton.setDisable(!groupsMasCheck.isSelected());
    }

    @FXML
    private void tGroupAction(ActionEvent event) {
        try {
            conf.getMidConf().getGroups().setT(tGroupCheck.isSelected());
        } catch (GroupsConfException ex) {
            tGroupCheck.setSelected(conf.getMidConf().getGroups().isT());
            groupsMidasCheck.setSelected(conf.getMidConf().getGroups().isLocal());
            new ShowAlerts().showWarning(ex.getMessage());
        }
        groupsButton.setDisable(!groupsMidasCheck.isSelected());
    }

    @FXML
    private void actGroupMidasAction(ActionEvent event) {
        try {
            conf.getMidConf().getGroups().setLocals(groupsMidasCheck.isSelected());
        } catch (GroupsConfException ex) {
            tGroupCheck.setSelected(conf.getMidConf().getGroups().isT());
            groupsMidasCheck.setSelected(conf.getMidConf().getGroups().isLocal());
            new ShowAlerts().showWarning(ex.getMessage());
        }
        groupsButton.setDisable(!groupsMidasCheck.isSelected());
    }

    @FXML
    private void showMidasGroupsAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/fxml/descriptors/tomocomd/Groups.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/gui/styles/descriptorsParams.css");
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        ((GroupsController) fxmlLoader.getController()).setStage(stage);
        ((GroupsController) fxmlLoader.getController()).setG(conf.getMidConf().getGroups());
        stage.setTitle("Groups midas descriptors");
        stage.getIcons().add(new Image(this.getClass().getResource("/gui/icons/molecule.png").toString()));
        stage.setOnCloseRequest(action -> {
            ((GroupsController) fxmlLoader.getController()).setG(conf.getMidConf().getGroups());
            stage.close();
        });
        stage.showAndWait();
        conf.getMidConf().setGroups(((GroupsController) fxmlLoader.getController()).getG());
        tGroupCheck.setSelected(conf.getMidConf().getGroups().isT());
        groupsMidasCheck.setSelected(conf.getMidConf().getGroups().isLocal());
        groupsButton.setDisable(!groupsMidasCheck.isSelected());
    }

    @FXML
    private void setorderMasMaxFormAction(ActionEvent event) {
        conf.getMasConf().getmForm().setOrden(orderMasMaxForm.getValue());
    }

    @FXML
    private void offSethAtomMasAction(ActionEvent event) {
        try {
            conf.getMasConf().getIsH().setIsNotHFilled(offhAtomMasCheck.isSelected());
        } catch (HAtomsException ex) {
            offhAtomMasCheck.setSelected(conf.getMasConf().getIsH().isIsNotHFilled());
            hAtomMasCheck.setSelected(conf.getMasConf().getIsH().isIsHFilled());
            new ShowAlerts().showWarning(ex.getMessage());
        }
        conf.getMasConf().getIsH().getValues();
    }

    @FXML
    private void sethAtomMasAction(ActionEvent event) {
        try {
            conf.getMasConf().getIsH().setIsHFilled(hAtomMasCheck.isSelected());
        } catch (HAtomsException ex) {
            offhAtomMasCheck.setSelected(conf.getMasConf().getIsH().isIsNotHFilled());
            hAtomMasCheck.setSelected(conf.getMasConf().getIsH().isIsHFilled());
            new ShowAlerts().showWarning(ex.getMessage());
        }
    }

    @FXML
    private void offSetlPairMasAction(ActionEvent event) {
        try {
            conf.getMasConf().getDiags().setNone(offLPairMasCheck.isSelected());
        } catch (DiagValuesException ex) {
            offLPairMasCheck.setSelected(conf.getMasConf().getDiags().isNone());
            lPairMasCheck.setSelected(conf.getMasConf().getDiags().isLp());
            new ShowAlerts().showWarning(ex.getMessage());
        }
    }

    @FXML
    private void setlPairMasAction(ActionEvent event) {
        try {
            conf.getMasConf().getDiags().setLp(lPairMasCheck.isSelected());
        } catch (DiagValuesException ex) {
            offLPairMasCheck.setSelected(conf.getMasConf().getDiags().isNone());
            lPairMasCheck.setSelected(conf.getMasConf().getDiags().isLp());
            new ShowAlerts().showWarning(ex.getMessage());
        }
    }

    @FXML
    private void midasSelectAction(ActionEvent event) {
        boxMidas.setDisable(!midasSelectCheck.isSelected());
        conf.getMidConf().setIsSelected(midasSelectCheck.isSelected());
    }

    @FXML
    private void masSelectAction(ActionEvent event) throws MasInvalidAlgFormException {
        masBox.setDisable(!masSelectCheck.isSelected());
        conf.getMasConf().setIsSelected(masSelectCheck.isSelected());
    }

    @FXML
    private void setlinearMasAlgebraicAction(ActionEvent event) throws MasInvalidAlgFormException {
        boolean flg = conf.getMasConf().setFamiliesDesc(DescriptorFamilyType.MASL, linearMasAlgebraicCheck.isSelected());
    }

    @FXML
    private void setBilinearMasAlgebraicAction(ActionEvent event) throws MasInvalidAlgFormException {
        boolean flg = conf.getMasConf().setFamiliesDesc(DescriptorFamilyType.MASB, BilinearMasAlgebraicCheck.isSelected());
    }

    @FXML
    private void setQuadraticMasAlgebraicAction(ActionEvent event) throws MasInvalidAlgFormException {
        boolean flg = conf.getMasConf().setFamiliesDesc(DescriptorFamilyType.MASQ, QuadraticMasAlgebraicCheck.isSelected());
    }

    @FXML
    private void setNSMasCheckMAction(ActionEvent event) {
        try {
            conf.getMasConf().getmForm().setNs(NSMasCheckM.isSelected());
        } catch (MatrixFormException ex) {
            NSMasCheckM.setSelected(conf.getMasConf().getmForm().isNs());
            SSMasCheckM.setSelected(conf.getMasConf().getmForm().isSs());
            MPMasCheckM.setSelected(conf.getMasConf().getmForm().isMp());
            new ShowAlerts().showWarning(ex.getMessage());
        }
    }

    @FXML
    private void setSSMasCheckMAction(ActionEvent event) {
        try {
            conf.getMasConf().getmForm().setSs(SSMasCheckM.isSelected());
        } catch (MatrixFormException ex) {
            NSMasCheckM.setSelected(conf.getMasConf().getmForm().isNs());
            SSMasCheckM.setSelected(conf.getMasConf().getmForm().isSs());
            MPMasCheckM.setSelected(conf.getMasConf().getmForm().isMp());
            new ShowAlerts().showWarning(ex.getMessage());
        }
    }

    @FXML
    private void setMPMasCheckMAction(ActionEvent event) {
        try {
            conf.getMasConf().getmForm().setMp(MPMasCheckM.isSelected());
        } catch (MatrixFormException ex) {
            NSMasCheckM.setSelected(conf.getMasConf().getmForm().isNs());
            SSMasCheckM.setSelected(conf.getMasConf().getmForm().isSs());
            MPMasCheckM.setSelected(conf.getMasConf().getmForm().isMp());
            new ShowAlerts().showWarning(ex.getMessage());
        }
    }

    @FXML
    private void nCiMidasAction(ActionEvent event) {
        try {
            conf.getMidConf().getChiral().setnCi(nCiMidasCheck.isSelected());
        } catch (ChiralConfExeption ex) {
            nCiMidasCheck.setSelected(conf.getMidConf().getChiral().isnCi());
            CiMidasCheck.setSelected(conf.getMidConf().getChiral().isCi());
            new ShowAlerts().showWarning(ex.getMessage());
        }
    }

    @FXML
    private void CiMidasAction(ActionEvent event) {
        try {
            conf.getMidConf().getChiral().setCi(CiMidasCheck.isSelected());
        } catch (ChiralConfExeption ex) {
            nCiMidasCheck.setSelected(conf.getMidConf().getChiral().isnCi());
            CiMidasCheck.setSelected(conf.getMidConf().getChiral().isCi());
            new ShowAlerts().showWarning(ex.getMessage());
        }
    }

    @FXML
    private void setAtomBaseMasAction(ActionEvent event) {
        try {
            conf.getMasConf().getmBase().setAB(atomBaseMasCheck.isSelected());
        } catch (MatrixBaseException ex) {
            atomBaseMasCheck.setSelected(conf.getMasConf().getmBase().isAB());
            bondBaseMasCheck.setSelected(conf.getMasConf().getmBase().isBB());
            new ShowAlerts().showWarning(ex.getMessage());
        }
    }

    @FXML
    private void setbondBaseMasAction(ActionEvent event) {
        try {
            conf.getMasConf().getmBase().setBB(bondBaseMasCheck.isSelected());
        } catch (MatrixBaseException ex) {
            atomBaseMasCheck.setSelected(conf.getMasConf().getmBase().isAB());
            bondBaseMasCheck.setSelected(conf.getMasConf().getmBase().isBB());
            new ShowAlerts().showWarning(ex.getMessage());
        }
    }

    @FXML
    private void nCiMasAction(ActionEvent event) {
        try {
            conf.getMasConf().getChiral().setnCi(nCiMasCheck.isSelected());
        } catch (ChiralConfExeption ex) {
            nCiMasCheck.setSelected(conf.getMasConf().getChiral().isnCi());
            CiMasCheck.setSelected(conf.getMasConf().getChiral().isCi());
            new ShowAlerts().showWarning(ex.getMessage());
        }
    }

    @FXML
    private void CiMasAction(ActionEvent event) {
        try {
            conf.getMasConf().getChiral().setCi(CiMasCheck.isSelected());
        } catch (ChiralConfExeption ex) {
            nCiMasCheck.setSelected(conf.getMasConf().getChiral().isnCi());
            CiMasCheck.setSelected(conf.getMasConf().getChiral().isCi());
            new ShowAlerts().showWarning(ex.getMessage());
        }
    }

    @FXML
    private void kaMASAction(ActionEvent event) {
        try {
            conf.getMasConf().getCutoff().setKA(kaMAScheckBox.isSelected());
        } catch (CutoffConfException ex) {
            kaMAScheckBox.setSelected(conf.getMasConf().getCutoff().isKA());
            lpMASCheckBox.setSelected(conf.getMasConf().getCutoff().isLGP());
            nosrwMASCheckBox.setSelected(conf.getMasConf().getCutoff().isNSRW());
            srwMASCheckBox.setSelected(conf.getMasConf().getCutoff().isSRW());
            new ShowAlerts().showWarning(ex.getMessage());
        }
    }

    @FXML
    private void srwMASCheckBox(ActionEvent event) {
        try {
            conf.getMasConf().getCutoff().setSRW(srwMASCheckBox.isSelected());
        } catch (CutoffConfException ex) {
            kaMAScheckBox.setSelected(conf.getMasConf().getCutoff().isKA());
            lpMASCheckBox.setSelected(conf.getMasConf().getCutoff().isLGP());
            nosrwMASCheckBox.setSelected(conf.getMasConf().getCutoff().isNSRW());
            srwMASCheckBox.setSelected(conf.getMasConf().getCutoff().isSRW());
            new ShowAlerts().showWarning(ex.getMessage());
        }
    }

    @FXML
    private void lpMASCheckBox(ActionEvent event) {
        try {
            conf.getMasConf().getCutoff().setLGP(lpMASCheckBox.isSelected());
        } catch (CutoffConfException ex) {
            kaMAScheckBox.setSelected(conf.getMasConf().getCutoff().isKA());
            lpMASCheckBox.setSelected(conf.getMasConf().getCutoff().isLGP());
            nosrwMASCheckBox.setSelected(conf.getMasConf().getCutoff().isNSRW());
            srwMASCheckBox.setSelected(conf.getMasConf().getCutoff().isSRW());
            new ShowAlerts().showWarning(ex.getMessage());
        }
    }

    @FXML
    private void nosrwMASAction(ActionEvent event) {
        try {
            conf.getMasConf().getCutoff().setNSRW(nosrwMASCheckBox.isSelected());
        } catch (CutoffConfException ex) {
            kaMAScheckBox.setSelected(conf.getMasConf().getCutoff().isKA());
            lpMASCheckBox.setSelected(conf.getMasConf().getCutoff().isLGP());
            nosrwMASCheckBox.setSelected(conf.getMasConf().getCutoff().isNSRW());
            srwMASCheckBox.setSelected(conf.getMasConf().getCutoff().isSRW());
            new ShowAlerts().showWarning(ex.getMessage());
        }
    }

    @FXML
    private void showPhyChePropMidasAction(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/fxml/descriptors/tomocomd/properties/Physicochemicalprop.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/gui/styles/descriptorsParams.css");
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        ((PhysicochemicalpropController) fxmlLoader.getController()).setStage(stage);
        ((PhysicochemicalpropController) fxmlLoader.getController()).setPhy(conf.getMidConf().getProp().getPhy());
        stage.setTitle("Physicochemical midas descriptors");
        stage.getIcons().add(new Image(this.getClass().getResource("/gui/icons/molecule.png").toString()));
        stage.setOnCloseRequest(action -> {
            ((PhysicochemicalpropController) fxmlLoader.getController()).setPhy(conf.getMidConf().getProp().getPhy());
            stage.close();
        });
        stage.showAndWait();
        try {
            conf.getMidConf().getProp().setPhy(((PhysicochemicalpropController) fxmlLoader.getController()).getPhy());
        } catch (PropertiesConfException ex) {
            new ShowAlerts().showWarning(ex.getMessage());
        }
    }

    @FXML
    private void showVerDegPropMidasAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/fxml/descriptors/tomocomd/properties/VertexDegree.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/gui/styles/descriptorsParams.css");
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        ((VertexDegreeController) fxmlLoader.getController()).setStage(stage);
        ((VertexDegreeController) fxmlLoader.getController()).setVer(conf.getMidConf().getProp().getVer());
        stage.setTitle("Vertex degree midas descriptors");
        stage.getIcons().add(new Image(this.getClass().getResource("/gui/icons/molecule.png").toString()));
        stage.setOnCloseRequest(action -> {
            ((VertexDegreeController) fxmlLoader.getController()).setVer(conf.getMidConf().getProp().getVer());
            stage.close();
        });
        stage.showAndWait();
        try {
            conf.getMidConf().getProp().setVer(((VertexDegreeController) fxmlLoader.getController()).getVer());
        } catch (PropertiesConfException ex) {
            new ShowAlerts().showWarning(ex.getMessage());
        }
    }

    @FXML
    private void showPhyChePropMasAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/fxml/descriptors/tomocomd/properties/Physicochemicalprop.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/gui/styles/descriptorsParams.css");
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        ((PhysicochemicalpropController) fxmlLoader.getController()).setStage(stage);
        ((PhysicochemicalpropController) fxmlLoader.getController()).setPhy(conf.getMasConf().getProp().getPhy());
        stage.setTitle("Physicochemical Mas descriptors");
        stage.getIcons().add(new Image(this.getClass().getResource("/gui/icons/molecule.png").toString()));
        stage.setOnCloseRequest(action -> {
            ((PhysicochemicalpropController) fxmlLoader.getController()).setPhy(conf.getMasConf().getProp().getPhy());
            stage.close();
        });
        stage.showAndWait();
        try {
            conf.getMasConf().getProp().setPhy(((PhysicochemicalpropController) fxmlLoader.getController()).getPhy());
        } catch (PropertiesConfException ex) {
            new ShowAlerts().showWarning(ex.getMessage());
        }
    }

    @FXML
    private void showVerDegPropMasAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/fxml/descriptors/tomocomd/properties/VertexDegree.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/gui/styles/descriptorsParams.css");
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        ((VertexDegreeController) fxmlLoader.getController()).setStage(stage);
        ((VertexDegreeController) fxmlLoader.getController()).setVer(conf.getMasConf().getProp().getVer());
        stage.setTitle("Vertex degree Mas descriptors");
        stage.getIcons().add(new Image(this.getClass().getResource("/gui/icons/molecule.png").toString()));
        stage.setOnCloseRequest(action -> {
            ((VertexDegreeController) fxmlLoader.getController()).setVer(conf.getMasConf().getProp().getVer());
            stage.close();
        });
        stage.showAndWait();
        try {
            conf.getMasConf().getProp().setVer(((VertexDegreeController) fxmlLoader.getController()).getVer());
        } catch (PropertiesConfException ex) {
            new ShowAlerts().showWarning(ex.getMessage());
        }
    }

    @FXML
    private void parSchAction(ActionEvent event) {
        conf.setArch(parSchComboBox.getValue());
    }

    @FXML
    private void thrTuplasAct(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/fxml/descriptors/tomocomd/algForms/3tuples.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        scene.getStylesheets().add("/gui/styles/descriptorsParams.css");
        stage.setScene(scene);
        stage.setResizable(false);
        ((ThreetuplesController) fxmlLoader.getController()).setStage(stage);
        ((ThreetuplesController) fxmlLoader.getController()).setConf(conf.getMidConf().getaForm().getThreelinear());
        stage.setTitle("Algebraic form for 3-linear");
        stage.getIcons().add(new Image(this.getClass().getResource("/gui/icons/molecule.png").toString()));
        stage.setOnCloseRequest(action -> {
            ((ThreetuplesController) fxmlLoader.getController()).setConf(conf.getMidConf().getaForm().getThreelinear());
            stage.close();
        });
        stage.showAndWait();
        conf.getMidConf().getaForm().setThreelinear(((ThreetuplesController) fxmlLoader.getController()).getConf());

        validateMeasures();
    }

    @FXML
    private void quTuplasAct(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/fxml/descriptors/tomocomd/algForms/4tuples.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        scene.getStylesheets().add("/gui/styles/descriptorsParams.css");
        stage.setScene(scene);
        stage.setResizable(false);
        ((FourtuplesController) fxmlLoader.getController()).setStage(stage);
        ((FourtuplesController) fxmlLoader.getController()).setConf(conf.getMidConf().getaForm().getFourlinear());
        stage.setTitle("Algebraic form for 4-linear");
        stage.getIcons().add(new Image(this.getClass().getResource("/gui/icons/molecule.png").toString()));
        stage.setOnCloseRequest(action -> {
            ((FourtuplesController) fxmlLoader.getController()).setConf(conf.getMidConf().getaForm().getFourlinear());
            stage.close();
        });
        stage.showAndWait();
        conf.getMidConf().getaForm().setFourlinear(((FourtuplesController) fxmlLoader.getController()).getConf());
        validateMeasures();

    }

    @FXML
    private void biTuplasAct(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/fxml/descriptors/tomocomd/algForms/2tuples.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        scene.getStylesheets().add("/gui/styles/descriptorsParams.css");
        stage.setScene(scene);
        stage.setResizable(false);
        ((BituplesController) fxmlLoader.getController()).setStage(stage);
        ((BituplesController) fxmlLoader.getController()).setConf(conf.getMidConf().getaForm().getBilinear());
        stage.setTitle("Algebraic form for 2-linear");
        stage.getIcons().add(new Image(this.getClass().getResource("/gui/icons/molecule.png").toString()));
        stage.setOnCloseRequest(action -> {
            ((BituplesController) fxmlLoader.getController()).setConf(conf.getMidConf().getaForm().getBilinear());
            stage.close();
        });
        stage.showAndWait();
        conf.getMidConf().getaForm().setBilinear(((BituplesController) fxmlLoader.getController()).getConf());
    }

    @FXML
    private void showNorAgreMidasAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/fxml/descriptors/tomocomd/aggregations/NormsAgg.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/gui/styles/descriptorsParams.css");
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        ((NormsAggController) fxmlLoader.getController()).setStage(stage);
        ((NormsAggController) fxmlLoader.getController()).setNorm(conf.getMidConf().getAgg().getNorms());
        stage.setTitle("Norms aggregation operators midas descriptors");
        stage.getIcons().add(new Image(this.getClass().getResource("/gui/icons/molecule.png").toString()));
        stage.setOnCloseRequest(action -> {
            ((NormsAggController) fxmlLoader.getController()).setNorm(conf.getMidConf().getAgg().getNorms());
            stage.close();
        });
        stage.showAndWait();
        try {
            conf.getMidConf().getAgg().setNorms(((NormsAggController) fxmlLoader.getController()).getNorm());
        } catch (AggregationsConfException ex) {
            new ShowAlerts().showWarning(ex.getMessage());
        }
    }

    @FXML
    private void showMeansAgreMidasAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/fxml/descriptors/tomocomd/aggregations/MeanAgg.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/gui/styles/descriptorsParams.css");
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        ((MeansAggController) fxmlLoader.getController()).setStage(stage);
        ((MeansAggController) fxmlLoader.getController()).setMeans(conf.getMidConf().getAgg().getMeans());
        stage.setTitle("Means aggregation operators midas descriptors");
        stage.getIcons().add(new Image(this.getClass().getResource("/gui/icons/molecule.png").toString()));
        stage.setOnCloseRequest(action -> {
            ((MeansAggController) fxmlLoader.getController()).setMeans(conf.getMidConf().getAgg().getMeans());
            stage.close();
        });
        stage.showAndWait();
        try {
            conf.getMidConf().getAgg().setMeans(((MeansAggController) fxmlLoader.getController()).getMeans());
        } catch (AggregationsConfException ex) {
            new ShowAlerts().showWarning(ex.getMessage());
        }
    }

    @FXML
    private void showStaAgreMidasAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/fxml/descriptors/tomocomd/aggregations/StatisticsAgg.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/gui/styles/descriptorsParams.css");
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        ((StatisticsAggController) fxmlLoader.getController()).setStage(stage);
        ((StatisticsAggController) fxmlLoader.getController()).setSta(conf.getMidConf().getAgg().getSta());
        stage.setTitle("Statistics aggregation operators midas descriptors");
        stage.getIcons().add(new Image(this.getClass().getResource("/gui/icons/molecule.png").toString()));
        stage.setOnCloseRequest(action -> {
            ((StatisticsAggController) fxmlLoader.getController()).setSta(conf.getMidConf().getAgg().getSta());
            stage.close();
        });
        stage.showAndWait();
        try {
            conf.getMidConf().getAgg().setSta(((StatisticsAggController) fxmlLoader.getController()).getSta());
        } catch (AggregationsConfException ex) {
            new ShowAlerts().showWarning(ex.getMessage());
        }
    }

    @FXML
    private void showShoAgreMidasAction(ActionEvent event) {
    }

    @FXML
    private void showClaAgreMidasAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/fxml/descriptors/tomocomd/aggregations/ClasicalAgre.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/gui/styles/descriptorsParams.css");
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        ((ClasicalAggController) fxmlLoader.getController()).setStage(stage);
        ((ClasicalAggController) fxmlLoader.getController()).setClas(conf.getMidConf().getAgg().getClas());
        stage.setTitle("Classical aggregation operators midas descriptors");
        stage.getIcons().add(new Image(this.getClass().getResource("/gui/icons/molecule.png").toString()));
        stage.setOnCloseRequest(action -> {
            ((ClasicalAggController) fxmlLoader.getController()).setClas(conf.getMidConf().getAgg().getClas());
            stage.close();
        });
        stage.showAndWait();
        try {
            conf.getMidConf().getAgg().setClas(((ClasicalAggController) fxmlLoader.getController()).getClas());
        } catch (ClassicalAggregationConfException ex) {
            new ShowAlerts().showWarning(ex.getMessage());
        }
    }

    @FXML
    private void showGowAgreMidasAction(ActionEvent event) {
    }

    @FXML
    private void showNorAgreMasAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/fxml/descriptors/tomocomd/aggregations/NormsAgg.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/gui/styles/descriptorsParams.css");
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        ((NormsAggController) fxmlLoader.getController()).setStage(stage);
        ((NormsAggController) fxmlLoader.getController()).setNorm(conf.getMasConf().getAgg().getNorms());
        stage.setTitle("Norms aggregation operators mas descriptors");
        stage.getIcons().add(new Image(this.getClass().getResource("/gui/icons/molecule.png").toString()));
        stage.setOnCloseRequest(action -> {
            ((NormsAggController) fxmlLoader.getController()).setNorm(conf.getMasConf().getAgg().getNorms());
            stage.close();
        });
        stage.showAndWait();
        try {
            conf.getMasConf().getAgg().setNorms(((NormsAggController) fxmlLoader.getController()).getNorm());
        } catch (AggregationsConfException ex) {
            new ShowAlerts().showWarning(ex.getMessage());
        }
    }

    @FXML
    private void showMeansAgreMasAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/fxml/descriptors/tomocomd/aggregations/MeanAgg.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/gui/styles/descriptorsParams.css");
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        ((MeansAggController) fxmlLoader.getController()).setStage(stage);
        ((MeansAggController) fxmlLoader.getController()).setMeans(conf.getMasConf().getAgg().getMeans());
        stage.setTitle("Means aggregation operators mas descriptors");
        stage.getIcons().add(new Image(this.getClass().getResource("/gui/icons/molecule.png").toString()));
        stage.setOnCloseRequest(action -> {
            ((MeansAggController) fxmlLoader.getController()).setMeans(conf.getMasConf().getAgg().getMeans());
            stage.close();
        });
        stage.showAndWait();
        try {
            conf.getMasConf().getAgg().setMeans(((MeansAggController) fxmlLoader.getController()).getMeans());
        } catch (AggregationsConfException ex) {
            new ShowAlerts().showWarning(ex.getMessage());
        }
    }

    @FXML
    private void showStaAgreMasAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/fxml/descriptors/tomocomd/aggregations/StatisticsAgg.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/gui/styles/descriptorsParams.css");
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        ((StatisticsAggController) fxmlLoader.getController()).setStage(stage);
        ((StatisticsAggController) fxmlLoader.getController()).setSta(conf.getMasConf().getAgg().getSta());
        stage.setTitle("Statistics aggregation operators mas descriptors");
        stage.getIcons().add(new Image(this.getClass().getResource("/gui/icons/molecule.png").toString()));
        stage.setOnCloseRequest(action -> {
            ((StatisticsAggController) fxmlLoader.getController()).setSta(conf.getMasConf().getAgg().getSta());
            stage.close();
        });
        stage.showAndWait();
        try {
            conf.getMasConf().getAgg().setSta(((StatisticsAggController) fxmlLoader.getController()).getSta());
        } catch (AggregationsConfException ex) {
            new ShowAlerts().showWarning(ex.getMessage());
        }
    }

    @FXML
    private void showShoAgreMasAction(ActionEvent event) {
    }

    @FXML
    private void showClaAgreMasAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/fxml/descriptors/tomocomd/aggregations/ClasicalAgre.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/gui/styles/descriptorsParams.css");
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        ((ClasicalAggController) fxmlLoader.getController()).setStage(stage);
        ((ClasicalAggController) fxmlLoader.getController()).setClas(conf.getMasConf().getAgg().getClas());
        stage.setTitle("Classical aggregation operators midas descriptors");
        stage.getIcons().add(new Image(this.getClass().getResource("/gui/icons/molecule.png").toString()));
        stage.setOnCloseRequest(action -> {
            ((ClasicalAggController) fxmlLoader.getController()).setClas(conf.getMasConf().getAgg().getClas());
            stage.close();
        });
        stage.showAndWait();
        try {
            conf.getMasConf().getAgg().setClas(((ClasicalAggController) fxmlLoader.getController()).getClas());
        } catch (ClassicalAggregationConfException ex) {
            new ShowAlerts().showWarning(ex.getMessage());
        }
    }

    @FXML
    private void showGowAgreMasAction(ActionEvent event) {
    }

    private void validateMeasures() {
        if (conf.getMidConf().getaForm().getThreelinear().isAllFalse() && conf.getMidConf().getaForm().getFourlinear().isAllFalse()) {
            measuresBut.setDisable(true);
        } else {
            measuresBut.setDisable(false);
        }
    }

    @FXML
    private void opeSelOnAction(ActionEvent event) {
        setTourSize();
    }

    @FXML
    private void incestAction(ActionEvent event) {
        ((GAConf) conf.getSearch()).getFamConf().setIncest(incestCheck.isSelected());
    }

    @FXML
    private void opeRepPobOnAction(ActionEvent event) {
        setrepNum();
    }

    @FXML
    private void attBoxOnChange(ActionEvent event) {
        setQualityAttBox(false);
    }

    @FXML
    private void changePanelChildren(ActionEvent event) {
        setFitBox(false);
    }

    @FXML
    private void r2PobAction(ActionEvent event) {
        if (r2FilterPobCheck.isSelected()) {
            r2pobTextField.setDisable(false);
        } else {
            r2pobTextField.setDisable(true);
        }
    }

    @FXML
    private void sePobAction(ActionEvent event) {
        if (seFilterPobCheck.isSelected()) {
            sePobTextField.setDisable(false);
        } else {
            sePobTextField.setDisable(true);
        }
    }

    @FXML
    private void corPobAct(ActionEvent event) {
        if (corFilterPobCheck.isSelected()) {
            corpobTextField.setDisable(false);
        } else {
            corpobTextField.setDisable(true);
        }
    }

    @FXML
    private void kurPobOnAct(ActionEvent event) {
        if (kurPobCheck.isSelected()) {
            kurpobTextField.setDisable(false);
        } else {
            kurpobTextField.setDisable(true);
        }
    }

    @FXML
    private void impTypeSubAct(ActionEvent event) {
        if (impTypeSub.getValue() == ImputationMissingValuesType.Imputation) {
            impValueSub.setDisable(false);
        } else {
            impValueSub.setDisable(true);
        }

    }

    @FXML
    private void nanSubPobAction(ActionEvent event) {
        if (nanFilterSubPobCheck.isSelected()) {
            impTypeSub.setDisable(false);
            impTypeSub.setValue(ImputationMissingValuesType.Delete);
            impValueSub.setDisable(true);
        } else {
            impTypeSub.setDisable(true);
            impValueSub.setDisable(true);
        }
    }

    @FXML
    private void comSubSetAction(ActionEvent event) {
        ((GAConf) conf.getSearch()).getFamConf().setCompareSubset(comSubSetCheckBox.isSelected());
    }

    @FXML
    private void coopAction(ActionEvent event) {
        ((GAConf) conf.getSearch()).setCoop(coopCheck.isSelected());
    }

    @FXML
    private void aggSearchAction(ActionEvent event) {
        ((GAConf) conf.getSearch()).getFamConf().setsAType(aggSearchCombo.getValue());
    }

    @FXML
    private void builModelAction(ActionEvent event) {
    }

    @FXML
    private void showSEExtDBAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/fxml/charts/SEGraph.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/gui/styles/Styles.css");
        Stage myStage = new Stage();
        myStage.setScene(scene);
        myStage.setMaximized(true);
        myStage.setResizable(true);
        ((SEGraphController) fxmlLoader.getController()).setStage(myStage);

        myStage.setTitle("QSAR-ToMoCoMD");
        Image ima = new Image(this.getClass().getResource("/gui/icons/molecule.png").toString());
        myStage.show();
    }
}
