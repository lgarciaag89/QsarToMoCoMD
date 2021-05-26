package com.tomocomd.qsartomocomd.gui;

import com.tomocomd.qsartomocomd.gui.buildmodels.BuildModelController;
import java.io.File;
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
//        testModel(stage);
    }
    
    private void testModel(Stage stage) throws Exception {
        String path = "/media/DATA/Doctorado/WORKSPACE/QsarToMoCoMDProject/QsarToMoCoMD/salida/salida.csv_best3.csv";
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/fxml/buildmodels/BuildModel.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/gui/styles/Styles.css");
        Stage myStage = new Stage();
        myStage.setTitle("QSAR-ToMoCoMD models for "+new File(path).getName());
        myStage.setScene(scene);
        myStage.setMaximized(true);
        myStage.setResizable(true);
        ((BuildModelController) fxmlLoader.getController()).setStage(myStage);
        ((BuildModelController) fxmlLoader.getController()).
                setPath(path);
        ((BuildModelController) fxmlLoader.getController()).setAct("Activity");
        Image ima = new Image(this.getClass().getResource("/gui/icons/molecule.png").toString());
        stage.getIcons().add(ima);
        myStage.show();
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
