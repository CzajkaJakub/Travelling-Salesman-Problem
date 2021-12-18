package com.example.combinatorial_optimization.MainPackage;

import com.example.combinatorial_optimization.MainApplication;
import javafx.scene.image.Image;

import java.util.Objects;

public interface DataPaths {
    String mainFxmlPath = "FxmlFiles/mainApplication.fxml";
    String dataFilePath = "src/main/resources/com/example/Combinatorial_Optimization/data/data.txt";
    String dataFileDictionary = "src/main/resources/com/example/Combinatorial_Optimization/data";
    String iconPath = "/com/example/Combinatorial_Optimization/Images/kvIcon.png";

    String mainSceneTitle = "Travelling Salesman Problem";
    String visualizationTitle = "Visualization";
    String greedyAlgorithmTitle = "Greedy algorithm";
    String antColonyTitle = "Ant Colony algorithm";

    Image applicationIcon = new Image(Objects.requireNonNull(MainApplication.class.getResourceAsStream(iconPath)));
}
