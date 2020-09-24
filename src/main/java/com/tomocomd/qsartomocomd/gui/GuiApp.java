package com.tomocomd.qsartomocomd.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GuiApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/fxml/MainScene.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/gui/styles/Styles.css");

        stage.setMaximized(true);
        stage.setResizable(true);
        stage.setTitle("QSAR-ToMoCoMD");

        Image ima = new Image(this.getClass().getResource("/gui/icons/molecule.png").toString());
        stage.getIcons().add(ima);
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> {
            ((MainScene) fxmlLoader.getController()).stopALL(event);
        });
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
}
