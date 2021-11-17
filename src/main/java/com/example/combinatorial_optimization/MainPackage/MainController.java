package com.example.combinatorial_optimization.MainPackage;

import com.example.combinatorial_optimization.Algorithms.GreedyAlgorithm;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainController implements FilesPaths, ScenesTitles, VisualizationSettings {

    @FXML
    TextField pathField;

    @FXML
    Button greedyAlgorithmButton;

    private static Stage stage;

    private String dataPathToTest;

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
        fileChooser.setInitialDirectory(new File("C://"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        if(selectedFile != null){
            dataPathToTest = selectedFile.toString();
            pathField.setText(selectedFile.toString());
            greedyAlgorithmButton.setDisable(false);
        }
    }

    @FXML
    public void runGreedyAlgorithm() {
        DataReader dataReader = new DataReader(dataPathToTest);
        dataReader.readDataFromFile();
        SetOfPoints setOfPoints = dataReader.getSetOfPoints();
        GreedyAlgorithm greedyAlgorithm = new GreedyAlgorithm(setOfPoints);
        greedyAlgorithm.findRoad();
        showVisualization(greedyAlgorithm);
    }

    private void showVisualization(GreedyAlgorithm greedyAlgorithm) {
        Group root = new Group();
        Scene scene = new Scene(root, visualizationWindowWidth + 2 * margin, visualizationWindowHeight + 2 * margin, backgroundColor);
        stage.setTitle(visualizationTitle);
        createCircles(root, greedyAlgorithm.getData());
        createLine(root, greedyAlgorithm.getGreedyRoad(), greedyAlgorithm.getData());
        stage.setScene(scene);
        stage.show();

        System.out.println(greedyAlgorithm.getTotalLength());
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
            line.setStartX(data.get(city).getX() + margin);
            line.setStartY(data.get(city).getY() + margin);
            line.setEndX(data.get(nextCity).getX() + margin);
            line.setEndY(data.get(nextCity).getY() + margin);
            root.getChildren().add(line);
        }
    }

    private void createCircles(Group root, HashMap<String, Point> road){
        double cityX;
        double cityY;
        Circle circle;
        for (String city: road.keySet()) {
            circle = new Circle();
            cityX = road.get(city).getX() + margin;
            cityY = road.get(city).getY() + margin;
            circle.setCenterY(cityY);
            circle.setCenterX(cityX);
            circle.setRadius(circlesRadius);
            circle.setFill(circlesColor);
            root.getChildren().add(circle);
        }
    }
}