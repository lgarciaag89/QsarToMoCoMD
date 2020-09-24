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

/**
 *
 * @author Potter
 */
public class SESimilarBox implements IFitBoxChields {

    private final String idsethes = "idThresTextFieldFitValue";

    private JFXTextField thresTextFild;

    public SESimilarBox() {
        thresTextFild = new JFXTextField();
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

        //instances textfield
        thresTextFild = new JFXTextField();
        thresTextFild.setTextFormatter(new TextFormatter<>(filterfloat));
        thresTextFild.setLabelFloat(true);
        thresTextFild.setPromptText("Threshold");
        thresTextFild.setPadding(new Insets(20, 10, 10, 10));
        thresTextFild.setId(idsethes);
        thresTextFild.setText("0.5");
        thresTextFild.setAlignment(Pos.CENTER);
    }

    @Override
    public List<Node> getChilds() {

        List<Node> chields = new LinkedList<>();
        chields.add(thresTextFild);
        return chields;
    }

    @Override
    public String getOptions() {
        return String.format("-t %s", thresTextFild.getText());
    }

    @Override
    public void setOptions(String[] opts) {
        thresTextFild.setText(opts[1]);
    }

}
