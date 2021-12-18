package com.example.combinatorial_optimization;

import com.example.combinatorial_optimization.MainPackage.DataPaths;
import com.example.combinatorial_optimization.MainPackage.MainController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application implements DataPaths {

    @Override
    public void start(Stage mainStage) throws IOException {
        MainController.setStage(mainStage);
        MainController.changeScene(mainFxmlPath, mainSceneTitle);
    }

    public static void main(String[] args) {
        launch();
    }
}