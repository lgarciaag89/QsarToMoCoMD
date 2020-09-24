/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomocomd.qsartomocomd.gui.alerts;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author Potter
 */
public class ShowAlerts {

    public boolean showConfirmation(String msg) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
                getClass().getResource("/gui/styles/Styles.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");

        Stage stageDia = (Stage) dialogPane.getScene().getWindow();
        stageDia.getIcons().clear();
        stageDia.getIcons().add(new Image(this.getClass().getResource("/gui/icons/molecule.png").toString()));
        ImageView icon = new ImageView(new Image(this.getClass().getResource("/gui/icons/warning.png").toString()));
        icon.setFitHeight(48);
        icon.setFitWidth(48);
        alert.setGraphic(icon);
        alert.setHeaderText(null);
        alert.setTitle("Information");
        alert.setContentText(msg);
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            return true;
        }
        return false;
    }

    public void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
                getClass().getResource("/gui/styles/Styles.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");

        Stage stageDia = (Stage) dialogPane.getScene().getWindow();
        stageDia.getIcons().clear();
        stageDia.getIcons().add(new Image(this.getClass().getResource("/gui/icons/molecule.png").toString()));
        ImageView icon = new ImageView(new Image(this.getClass().getResource("/gui/icons/error.png").toString()));
        icon.setFitHeight(48);
        icon.setFitWidth(48);
        alert.setGraphic(icon);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public void showInfo(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
                getClass().getResource("/gui/styles/Styles.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");

        Stage stageDia = (Stage) dialogPane.getScene().getWindow();
        stageDia.getIcons().clear();
        stageDia.getIcons().add(new Image(this.getClass().getResource("/gui/icons/molecule.png").toString()));
        ImageView icon = new ImageView(new Image(this.getClass().getResource("/gui/icons/succeful.png").toString()));
        icon.setFitHeight(48);
        icon.setFitWidth(48);
        alert.setGraphic(icon);
        alert.setHeaderText(null);
        alert.setTitle("Information");
        alert.setContentText(msg);
        alert.showAndWait();
    }
    
    public void showWarning(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
                getClass().getResource("/gui/styles/Styles.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");

        Stage stageDia = (Stage) dialogPane.getScene().getWindow();
        stageDia.getIcons().clear();
        stageDia.getIcons().add(new Image(this.getClass().getResource("/gui/icons/molecule.png").toString()));
        ImageView icon = new ImageView(new Image(this.getClass().getResource("/gui/icons/warning.png").toString()));
        icon.setFitHeight(48);
        icon.setFitWidth(48);
        alert.setGraphic(icon);
        alert.setHeaderText(null);
        alert.setTitle("Warning");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
