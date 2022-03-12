package com.example.combinatorial_optimization.DataReader;

import java.io.BufferedReader;
import java.io.FileReader;

public class DataReader {

    private final String dataPathToTest;
    private final SetOfPoints setOfPoints;

    public DataReader(String dataPathToTest) {
        this.dataPathToTest = dataPathToTest;
        setOfPoints = new SetOfPoints();
    }

    public SetOfPoints getSetOfPoints() {
        return setOfPoints;
    }

    public void readDataFromFile() {
        try {
            Point point;
            FileReader reader = new FileReader(dataPathToTest);
            BufferedReader bufferedReader = new BufferedReader(reader);
            int amountOfCities = Integer.parseInt(bufferedReader.readLine().strip());
            setOfPoints.setAmountOfCities(amountOfCities);
            for(int i = 1; i <= setOfPoints.getAmountOfCities(); i++){
                String[] line = bufferedReader.readLine().strip()
                        .replace("     ", " ")
                        .replace("    ", " ")
                        .replace("   ", " ")
                        .replace("  ", " ")
                        .split(" ");
                point = new Point();
                point.setCityName(line[0]);
                point.setX(Double.parseDouble(line[1]));
                point.setY(Double.parseDouble(line[2]));
                setOfPoints.addPointToData(point);
            }
            bufferedReader.close();
            reader.close();
        } catch (Exception e) {
            System.out.println("Bład w pliku : " + e.getMessage());
        }
    }
}
