/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomocomd.qsartomocomd.gui.fitBoxChange;

import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Potter
 */
public class ModelBox implements IFitBoxChields {

    private JFXTextField testSDF;

    public ModelBox(Stage stage) {
        testSDF = new JFXTextField();
        testSDF.setEditable(false);
        testSDF.promptTextProperty().setValue("Path Test SDF");
        
        testSDF.setFont(Font.font("Family", FontPosture.ITALIC, 14));
        testSDF.setAlignment(Pos.TOP_LEFT);
        testSDF.labelFloatProperty().setValue(Boolean.TRUE);
        testSDF.setPadding(new Insets(20, 10, 10, 10));
        testSDF.setOnMouseClicked(event -> {
            FileChooser fileChooser = new FileChooser();
            File file;
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("SDF Files", "*.sdf"),
                    new FileChooser.ExtensionFilter("SDF Files", "*.SDF")
            );
            if (!testSDF.getText().isEmpty()) {
                file = new File(testSDF.getText());
                if (file.getParentFile().exists()) {
                    fileChooser.setInitialDirectory(file.getParentFile());
                }
            }
            file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                testSDF.setText(file.getAbsolutePath());
            }
        });
    }

    @Override
    public List<Node> getChilds() {
        List<Node> chields = new LinkedList<>();
        chields.add(testSDF);
        return chields;
    }

    @Override
    public String getOptions() {
        return String.format("-p,%s", testSDF.getText());
    }

    @Override
    public void setOptions(String[] opts) {
        testSDF.setText(opts[1]);
    }

}
