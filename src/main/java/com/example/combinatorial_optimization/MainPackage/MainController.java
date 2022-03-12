package com.example.combinatorial_optimization.MainPackage;

import com.example.combinatorial_optimization.Algorithms.AntColonySystem.AntAlgorithm;
import com.example.combinatorial_optimization.Algorithms.Greedy.GreedyAlgorithm;
import com.example.combinatorial_optimization.Settings.Settings;
import com.example.combinatorial_optimization.DataGeneration.Generator;
import com.example.combinatorial_optimization.DataReader.DataReader;
import com.example.combinatorial_optimization.DataReader.Point;
import com.example.combinatorial_optimization.DataReader.SetOfPoints;
import com.example.combinatorial_optimization.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainController implements DataPaths,Settings {

    @FXML
    TextField pathField;

    @FXML
    Button greedyAlgorithmButton, antAlgorithmButton, compareButton;

    private static Stage stage;

    private String dataPathToTest;

    private double scale;

    public static void setStage(Stage stage) {
        MainController.stage = stage;
    }

    public static void changeScene(String fxmlPath, String frameTitle) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource(fxmlPath));
        Scene newScene = new Scene(loader.load());
        stage.getIcons().add(applicationIcon);
        stage.resizableProperty().set(false);
        stage.setTitle(frameTitle);
        stage.setScene(newScene);
        stage.show();
    }

    @FXML
    public void generateData() throws IOException {
        Generator generator = new Generator();
        generator.generateNumbers();
        dataPathToTest = dataFilePath;
        pathField.setText(dataFilePath);
        greedyAlgorithmButton.setDisable(false);
        antAlgorithmButton.setDisable(false);
        compareButton.setDisable(false);
    }

    @FXML
    public void showDataDictionary(){
        try {
            Desktop desktop = Desktop.getDesktop();
            desktop.open(new File(dataFileDictionary));
        } catch (IOException e) {
            System.out.println("Błąd ścieżki");
        }
    }

    @FXML
    public void getOwnData(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Set your own data");
        fileChooser.setInitialDirectory(new File("."));
        File selectedFile = fileChooser.showOpenDialog(stage);
        if(selectedFile != null){
            dataPathToTest = selectedFile.toString();
            pathField.setText(selectedFile.toString());
            greedyAlgorithmButton.setDisable(false);
            antAlgorithmButton.setDisable(false);
            compareButton.setDisable(false);
        }
    }

    @FXML
    private void compareAlgorithms(){
        runGreedyAlgorithm();
        runAntAlgorithm();
    }

    @FXML
    public void runGreedyAlgorithm() {
        DataReader dataReader = new DataReader(dataPathToTest);
        dataReader.readDataFromFile();
        SetOfPoints setOfPoints = dataReader.getSetOfPoints();
        GreedyAlgorithm greedyAlgorithm = new GreedyAlgorithm(setOfPoints);
        checkScaleOfPointsDistance(setOfPoints.getMaxX(), setOfPoints.getMaxY());
        greedyAlgorithm.findRoad();
        showVisualization(greedyAlgorithm.getData(), greedyAlgorithm.getRoad(), greedyAlgorithm.getTotalLength(), greedyAlgorithmTitle);
    }

    @FXML
    private void runAntAlgorithm(){
        DataReader dataReader = new DataReader(dataPathToTest);
        dataReader.readDataFromFile();
        SetOfPoints setOfPoints = dataReader.getSetOfPoints();
        AntAlgorithm antAlgorithm = new AntAlgorithm(setOfPoints);
        checkScaleOfPointsDistance(setOfPoints.getMaxX(), setOfPoints.getMaxY());
        antAlgorithm.findRoad();
        showVisualization(antAlgorithm.getData(), antAlgorithm.getRoad(), antAlgorithm.getTotalLength(), antColonyTitle);
    }

    public void showVisualization(HashMap<String, Point> data, ArrayList<String> road, double length, String frameTitle) {
        Group root = new Group();
        Scene scene = new Scene(root, visualizationWindowWidth + 2 * margin, visualizationWindowHeight + 2 * margin, backgroundColor);
        createCircles(root, data);
        createLine(root, road, data);
        createRoadLabel(root, length);
        showStage(scene, frameTitle);
    }

    private void checkScaleOfPointsDistance(double maxX, double maxY) {
        scale = Math.max(maxX/visualizationWindowWidth, maxY/visualizationWindowHeight);
    }

    private void createRoadLabel(Group root, double length) {
        Text lengthText = new Text();
        lengthText.setFont(Font.font(labelType, fontSize));
        lengthText.setFill(textColor);
        lengthText.setTextAlignment(TextAlignment.JUSTIFY);
        lengthText.setText(String.format("Road : %.2f ", length));
        lengthText.setX(xLabelMargin);
        lengthText.setY(yLabelMargin);
        root.getChildren().add(lengthText);
    }


    private void createLine(Group root, ArrayList<String> road, HashMap<String, Point> data){
        String city;
        String nextCity;
        Line line;
        for(int i = 0; i < road.size()-1; i++) {
            line = new Line();
            city = road.get(i);
            nextCity = road.get(i+1);
            line.setStrokeWidth(linesStroke);
            line.setOpacity(1);
            line.setStroke(linesColor);
            line.setStartX(data.get(city).getX()/scale + margin);
            line.setStartY(data.get(city).getY()/scale + margin);
            line.setEndX(data.get(nextCity).getX()/scale + margin);
            line.setEndY(data.get(nextCity).getY()/scale + margin);
            root.getChildren().add(line);
        }
    }

    private void createCircles(Group root, HashMap<String, Point> road){
        double cityX;
        double cityY;
        Circle circle;
        for (String city: road.keySet()) {
            circle = new Circle();
            cityX = road.get(city).getX()/scale + margin;
            cityY = road.get(city).getY()/scale + margin;
            circle.setCenterY(cityY);
            circle.setCenterX(cityX);
            circle.setRadius(circlesRadius);
            circle.setFill(circlesColor);
            root.getChildren().add(circle);
        }
    }

    private void showStage(Scene scene, String frameTitle) {
        Stage stage = new Stage();
        stage.getIcons().add(applicationIcon);
        stage.resizableProperty().set(false);
        stage.setTitle(visualizationTitle + " : " + frameTitle);
        stage.setScene(scene);
        stage.show();
    }


}