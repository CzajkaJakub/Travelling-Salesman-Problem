package com.example.combinatorial_optimization.MainPackage;

import javafx.scene.paint.Color;

public interface VisualizationSettings {

    int visualizationWindowWidth = 1000;
    int visualizationWindowHeight = 700;

    Color backgroundColor = Color.BLACK;
    Color circlesColor = Color.WHITESMOKE;
    int circlesRadius = 3;

    Color textColor = Color.YELLOW;
    Color linesColor = Color.RED;
    int linesStroke = 1;

    String labelType = "MV Boli";
    int fontSize = 20;
    int xLabelMargin = 0;
    int yLabelMargin = 20;

    int margin = 30;
}
