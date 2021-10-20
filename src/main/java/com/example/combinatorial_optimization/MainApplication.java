package com.example.combinatorial_optimization;

import com.example.combinatorial_optimization.MainPackage.FilesPaths;
import com.example.combinatorial_optimization.MainPackage.MainController;
import com.example.combinatorial_optimization.MainPackage.ScenesTitles;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class MainApplication extends Application implements FilesPaths, ScenesTitles {

    @Override
    public void start(Stage mainStage) throws IOException {
        MainController.setStage(mainStage);
        MainController.changeScene(mainFxmlPath, mainSceneTitle);
    }

    public static void main(String[] args) {
        launch();
    }
}