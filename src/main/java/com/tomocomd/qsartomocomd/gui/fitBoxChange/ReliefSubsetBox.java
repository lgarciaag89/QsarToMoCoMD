/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomocomd.qsartomocomd.gui.fitBoxChange;

import com.jfoenix.controls.JFXTextField;
import java.util.LinkedList;
import java.util.List;
import java.util.function.UnaryOperator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextFormatter;
import javafx.scene.text.Font;

/**
 *
 * @author Potter
 */
public class ReliefSubsetBox implements IFitBoxChields {

    private final String idInst = "instTextFieldFitValue";
    private final String neiInst = "neiTextFieldFitValue";
    private final String sigInst = "sigTextFieldFitValue";

    private JFXTextField instTextFild, neiTextFild, sigTextFild;

    public ReliefSubsetBox() {
        instTextFild = new JFXTextField();
        neiTextFild = new JFXTextField();
        sigTextFild = new JFXTextField();

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

        //instances textfield
        instTextFild = new JFXTextField();
        instTextFild.setTextFormatter(new TextFormatter<>(filter));
        instTextFild.setLabelFloat(true);
        instTextFild.setPromptText("Instances");
        instTextFild.setPadding(new Insets(20, 10, 10, 10));
        instTextFild.setId(idInst);
        instTextFild.setText("10");
        instTextFild.setAlignment(Pos.CENTER);

        //Neighbours textfield       
        neiTextFild = new JFXTextField();
        neiTextFild.setTextFormatter(new TextFormatter<>(filter));
        neiTextFild.setLabelFloat(true);
        neiTextFild.setPromptText("Neighbours");
        neiTextFild.setPadding(new Insets(20, 10, 10, 10));
        neiTextFild.setId(neiInst);
        neiTextFild.setText("10");
        neiTextFild.setAlignment(Pos.CENTER);

        //Sigma textfield
        sigTextFild = new JFXTextField();
        sigTextFild.setTextFormatter(new TextFormatter<>(filter));
        sigTextFild.setLabelFloat(true);
        sigTextFild.setPromptText("Sigma");
        sigTextFild.setPadding(new Insets(20, 10, 10, 10));
        sigTextFild.setId(sigInst);
        sigTextFild.setText("2");
        sigTextFild.setAlignment(Pos.CENTER);
    }

    @Override
    public List<Node> getChilds() {

        List<Node> chields = new LinkedList<>();
        chields.add(instTextFild);
        chields.add(neiTextFild);
        chields.add(sigTextFild);
        return chields;
    }

    @Override
    public String getOptions() {
        return String.format("-M %s -K %s -A %s", instTextFild.getText(),neiTextFild.getText(), sigTextFild.getText());
    }

    @Override
    public void setOptions(String[] opts) {
        instTextFild.setText(opts[1]);
        neiTextFild.setText(opts[3]);
        sigTextFild.setText(opts[5]);
    }
}
