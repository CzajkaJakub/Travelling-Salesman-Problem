package com.example.combinatorial_optimization.Algorithms.AntColonySystem;

import com.example.combinatorial_optimization.Algorithms.Algorithm;
import com.example.combinatorial_optimization.DataReader.Point;
import com.example.combinatorial_optimization.DataReader.SetOfPoints;
import com.example.combinatorial_optimization.Settings.Settings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class AntAlgorithm implements Algorithm, Settings {

    private ArrayList<String> finalRoad;
    private final ArrayList<String> cities;
    private final HashMap<String, Point> data;
    private int amountOfCities;

    private double totalLength;
    private Point startPoint;

    private HashMap<String, HashMap<String, Double>> graphCost;
    private HashMap<String, HashMap<String, Double>> pheromoneLevel;


    public AntAlgorithm(SetOfPoints setOfPoints) {
        this.finalRoad = new ArrayList<>();
        this.amountOfCities = setOfPoints.getAmountOfCities();
        this.cities = setOfPoints.getCities();
        this.data = setOfPoints.getData();
        this.startPoint = data.get(cities.get(0));
        this.graphCost = new HashMap<>();
        this.pheromoneLevel = new HashMap<>();
        createPheromoneMatrix();
        createGraphCost();
    }

    @Override
    public HashMap<String, Point> getData() {
        return data;
    }

    @Override
    public ArrayList<String> getRoad() {
        return finalRoad;
    }

    @Override
    public double getTotalLength() {
        return totalLength;
    }

    @Override
    public void findRoad() {

// wypuszczenie mrowek

        double min = 99999999999.0;
        Random random = new Random();

        for(int i = 1; i <= antColony; i++){
            System.out.println(i);
            Ant ant = new Ant(cities, data, graphCost, pheromoneLevel);
            ant.placeAnt(random.nextInt(amountOfCities));
            znikajPheromon();


            if(ant.getTotalLength() < min){
                totalLength = ant.getTotalLength();
                finalRoad = ant.getAntRoad();
                min = ant.getTotalLength();
            }
        }
    }

    private void znikajPheromon() {

        for (String fromCity: cities) {
            HashMap<String, Double> len = new HashMap<>();
            for (String toCity: cities) {
                if(!fromCity.equals(toCity)){
                    len.put(toCity, pheromoneLevel.get(fromCity).get(toCity)* vaporization);
                }else{
                    len.put(toCity, 0.0);
                }
            }
            pheromoneLevel.put(fromCity, len);
        }
    }

    private void createPheromoneMatrix() {
        for (String fromCity: cities) {
            HashMap<String, Double> len = new HashMap<>();
            for (String toCity: cities) {
                if(!fromCity.equals(toCity)){
                    len.put(toCity, 1.0);
                }else{
                    len.put(toCity, 0.0);
                }
            }
            pheromoneLevel.put(fromCity, len);
        }
    }

    private void createGraphCost() {
        for (String fromCity: cities) {
            HashMap<String, Double> x = new HashMap<>();
            double xPosFromCity = data.get(fromCity).getX();
            double yPosFromCity = data.get(fromCity).getY();
            for (String toCity: cities) {
                double xPosToCity = data.get(toCity).getX();
                double yPosToCity = data.get(toCity).getY();
                x.put(toCity, Math.sqrt(Math.pow(xPosFromCity-xPosToCity, 2) + Math.pow(yPosFromCity-yPosToCity, 2)));
            }
            graphCost.put(fromCity, x);
        }
    }
}
