package com.example.combinatorial_optimization.Settings;

import javafx.scene.paint.Color;

public interface Settings {

    // size of window
    int visualizationWindowWidth = 1000;
    int visualizationWindowHeight = 700;

    // visualization settings
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

    // data generator settings
    int amountOfNumbers = 127;

    // ant settings
    double vaporization = 0.5;
    double alpha = 1;
    double beta = 5;
    double pheromoneProduction = 0.8;
    double antColony = 3000;
}
