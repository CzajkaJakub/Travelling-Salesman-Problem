package com.example.combinatorial_optimization.Settings;

import javafx.scene.paint.Color;

public interface Settings {

    // size of window
    int visualizationWindowWidth = 700;
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
    int amountOfNumbers = 150;

    // ant settings
    double vaporization = 0.9;
    double alpha = 1;
    double beta = 4;
    double pheromoneProduction = 1.2;
    double antColony = 1500;

    // test
    int amountOfTry = 1;
}
