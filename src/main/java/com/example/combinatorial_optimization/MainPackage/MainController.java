package com.example.combinatorial_optimization.MainPackage;

import com.example.combinatorial_optimization.Algorithms.GreedyAlgorithm;
import com.example.combinatorial_optimization.DataGeneration.Generator;
import com.example.combinatorial_optimization.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.awt.*;
import java.io.File;
import java.io.IOException;

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
        GreedyAlgorithm greedyAlgorithm = new GreedyAlgorithm(dataPathToTest);
        greedyAlgorithm.readDataFromFile();
        greedyAlgorithm.findRoad();
        showVisualization(greedyAlgorithm);
    }

    private void showVisualization(GreedyAlgorithm greedyAlgorithm) {
        Group root = new Group();
        Scene scene = new Scene(root, visualizationWindowWidth, visualizationWindowHeight, backgroundColor);
        stage.setTitle(visualizationTitle);



        //draw circles
        for (String key: greedyAlgorithm.getRoadPoints()) {
            int xPos = greedyAlgorithm.getData().get(key)[0];
            int yPos = greedyAlgorithm.getData().get(key)[1];
            createCircles(root, xPos, yPos);
        }

        //draw lines
        for(int i = 0; i < greedyAlgorithm.getAmountOfNumbers()-1; i++) {
            int xPos1 = greedyAlgorithm.getData().get(greedyAlgorithm.getRoadPoints().get(i))[0];
            int yPos1 = greedyAlgorithm.getData().get(greedyAlgorithm.getRoadPoints().get(i))[1];
            int xPos2 = greedyAlgorithm.getData().get(greedyAlgorithm.getRoadPoints().get(i + 1))[0];
            int yPos2 = greedyAlgorithm.getData().get(greedyAlgorithm.getRoadPoints().get(i + 1))[1];
            createLine(root, xPos1, xPos2, yPos1, yPos2);
        }

        int xPos1 = greedyAlgorithm.getData().get(greedyAlgorithm.getRoadPoints().get(greedyAlgorithm.getAmountOfNumbers()-1))[0];
        int yPos1 = greedyAlgorithm.getData().get(greedyAlgorithm.getRoadPoints().get(greedyAlgorithm.getAmountOfNumbers()-1))[1];
        int xPos2 = greedyAlgorithm.getData().get(greedyAlgorithm.getRoadPoints().get(0))[0];
        int yPos2 = greedyAlgorithm.getData().get(greedyAlgorithm.getRoadPoints().get(0))[1];
        createLine(root, xPos1, xPos2, yPos1, yPos2);

        // draw length
        Text length = new Text();
        length.setText("Road's length : " + greedyAlgorithm.getRoadLength() + "px");
        length.setX(10);
        length.setY(40);
        length.setFont(new Font("MV Boli", 40));
        length.setFill(Color.WHITE);
        root.getChildren().add(length);

        stage.setScene(scene);
        stage.show();
    }

    private void createLine(Group root, int xPos1, int xPos2, int yPos1, int yPos2){
        Line line = new Line();
        line.setStrokeWidth(linesStroke);
        line.setOpacity(1);
        line.setStroke(linesColor);
        line.setStartX(xPos1);
        line.setStartY(yPos1);
        line.setEndX(xPos2);
        line.setEndY(yPos2);
        root.getChildren().add(line);
    }

    private void createCircles(Group root, int xPos, int yPos){
        Circle circle = new Circle();
        circle.setCenterY(yPos);
        circle.setCenterX(xPos);
        circle.setRadius(circlesRadius);
        circle.setFill(circlesColor);
        root.getChildren().add(circle);
    }
}