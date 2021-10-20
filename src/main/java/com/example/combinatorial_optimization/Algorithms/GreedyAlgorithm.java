package com.example.combinatorial_optimization.Algorithms;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class GreedyAlgorithm {

    private String dataPathToTest;
    private int amountOfNumbers;
    private HashMap<String, Integer[]> data = new HashMap<>();
    private ArrayList<String> keys = new ArrayList<>();
    private ArrayList<String> roadPoints = new ArrayList<>();

    public GreedyAlgorithm(String dataPathToTest) {
        this.dataPathToTest = dataPathToTest;
    }

    public ArrayList<String> getRoadPoints() {
        return roadPoints;
    }

    public int getAmountOfNumbers() {
        return amountOfNumbers;
    }

    public HashMap<String, Integer[]> getData() {
        return data;
    }

    public ArrayList<String> getKeys() {
        return keys;
    }



    public void readDataFromFile() {
        try {
            FileReader reader = new FileReader(dataPathToTest);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            amountOfNumbers = Integer.parseInt(line);
            for(int i = 1; i <= amountOfNumbers; i++){
                line = bufferedReader.readLine();
                String[] points = line.split(" ");
                Integer[] coordinates = {Integer.parseInt(points[1]), Integer.parseInt(points[2])};
                data.put(points[0], coordinates);
                keys.add(points[0]);
            }
            bufferedReader.close();
            reader.close();
        } catch (IOException e) {
            System.out.println("Bład w pliku");
        }
    }

    public void findRoad() {
        String startPoint = keys.get(0);
        keys.remove(startPoint);
        roadPoints.add(startPoint);
        int xPos = data.get(startPoint)[0];
        int yPos = data.get(startPoint)[1];
        findTheClosestPoint(xPos, yPos);

    }

    private void findTheClosestPoint(int xPos, int yPos){
        if(keys.size()>0){
            double minLength = 99999;
            String nextPoint = "";
            int xNextPos = 0;
            int yNextPos = 0;
            for (String point:keys) {
                int xPos2 = data.get(point)[0];
                int yPos2 = data.get(point)[1];
                double xLength = Math.pow(xPos-xPos2, 2);
                double yLength = Math.pow(yPos-yPos2, 2);
                double length = Math.sqrt(xLength+yLength);
                if(length < minLength){
                    minLength = length;
                    nextPoint = point;
                    xNextPos = xPos2;
                    yNextPos = yPos2;
                }
            }
            keys.remove(nextPoint);
            roadPoints.add(nextPoint);
            findTheClosestPoint(xNextPos, yNextPos);
        }
    }
}
