/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomocomd.qsartomocomd.gui.attributeFitBox;

import com.jfoenix.controls.JFXTextField;
import java.util.LinkedList;
import java.util.List;
import java.util.function.UnaryOperator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextFormatter;

/**
 *
 * @author potter
 */
public class ChoquetAttBox implements IAttFitBoxChields {

    private final String idRF = "rfTextFieldFitValue";
    private final String idRE = "reTextFieldFitValue";
    private final String idSE = "segTextFieldFitValue";
    private final String idCo = "cogTextFieldFitValue";
    private final String idLa = "laTextFieldFitValue";

    private JFXTextField rfTextFild, reTextFild, seTextFild, coTextFild, laTextFild;

    public ChoquetAttBox() {
        rfTextFild = new JFXTextField();
        reTextFild = new JFXTextField();
        seTextFild = new JFXTextField();
        coTextFild = new JFXTextField();
        laTextFild = new JFXTextField();

        UnaryOperator<TextFormatter.Change> filter = c -> {
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

        rfTextFild = new JFXTextField();
        rfTextFild.setTextFormatter(new TextFormatter<>(filter));
        rfTextFild.setLabelFloat(true);
        rfTextFild.setPromptText("Random Forest weight");
        rfTextFild.setPadding(new Insets(20, 10, 10, 10));
        rfTextFild.setId(idRF);
        rfTextFild.setText("0.75");
        rfTextFild.setAlignment(Pos.CENTER);

        reTextFild = new JFXTextField();
        reTextFild.setTextFormatter(new TextFormatter<>(filter));
        reTextFild.setLabelFloat(true);
        reTextFild.setPromptText("ReliefF weigth");
        reTextFild.setPadding(new Insets(20, 10, 10, 10));
        reTextFild.setId(idRE);
        reTextFild.setText("0.75");
        reTextFild.setAlignment(Pos.CENTER);

        seTextFild = new JFXTextField();
        seTextFild.setTextFormatter(new TextFormatter<>(filter));
        seTextFild.setLabelFloat(true);
        seTextFild.setPromptText("Shannon entropy weigth");
        seTextFild.setPadding(new Insets(20, 10, 10, 10));
        seTextFild.setId(idSE);
        seTextFild.setText("0.5");
        seTextFild.setAlignment(Pos.CENTER);

        coTextFild = new JFXTextField();
        coTextFild.setTextFormatter(new TextFormatter<>(filter));
        coTextFild.setLabelFloat(true);
        coTextFild.setPromptText("Correlation weigth");
        coTextFild.setPadding(new Insets(20, 10, 10, 10));
        coTextFild.setId(idCo);
        coTextFild.setText("0.3");
        coTextFild.setAlignment(Pos.CENTER);

        laTextFild = new JFXTextField();
        laTextFild.setTextFormatter(new TextFormatter<>(filter));
        laTextFild.setLabelFloat(true);
        laTextFild.setPromptText("Lambda value");
        laTextFild.setPadding(new Insets(20, 10, 10, 10));
        laTextFild.setId(idLa);
        laTextFild.setText("0.5");
        laTextFild.setAlignment(Pos.CENTER);
    }

    @Override
    public List<Node> getChilds() {

        List<Node> chields = new LinkedList<>();
        chields.add(rfTextFild);
        chields.add(reTextFild);
        chields.add(seTextFild);
        chields.add(coTextFild);
        chields.add(laTextFild);
        return chields;
    }

    @Override
    public String getOptions() {
        return String.format("-rf %s -re %s -se %s -co %s -l %s",
                rfTextFild.getText(), reTextFild.getText(),
                seTextFild.getText(), coTextFild.getText(), laTextFild.getText());
    }

    @Override
    public void setOptions(String[] opts) {
        rfTextFild.setText(opts[1]);
        reTextFild.setText(opts[3]);
        seTextFild.setText(opts[5]);
        coTextFild.setText(opts[7]);
        laTextFild.setText(opts[9]);
    }
}
