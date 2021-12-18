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
    private double totalLength = 99999999999.0;

    private final ArrayList<String> citiesToVisit;
    private final HashMap<String, Point> data;
    private final int amountOfCities;

    private final HashMap<String, HashMap<String, Double>> graphCost;
    private final HashMap<String, HashMap<String, Double>> pheromoneLevel;

    private final Random random;
    private Ant ant;

    public AntAlgorithm(SetOfPoints setOfPoints) {
        this.random = new Random();
        this.finalRoad = new ArrayList<>();
        this.pheromoneLevel = new HashMap<>();
        this.amountOfCities = setOfPoints.getAmountOfCities();
        this.citiesToVisit = setOfPoints.getCities();
        this.data = setOfPoints.getData();
        this.graphCost = new HashMap<>();
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
        for(int i = 1; i <= antColony; i++){

            //progress
            System.out.printf("Ant Colony algorithm progress : %.1f %c\n", i/antColony * 100, '%');
            //

            createAnt();
            pheromoneVaporization();
            if(ant.getTotalLength() < totalLength){
                totalLength = ant.getTotalLength();
                finalRoad = ant.getAntRoad();
            }
        }
    }

    private void createAnt() {
        ant = new Ant();
        ant.setCitiesToVisit(citiesToVisit);
        ant.setData(data);
        ant.setGraphCost(graphCost);
        ant.setPheromoneLevel(pheromoneLevel);
        ant.placeAntIntoCity(random.nextInt(amountOfCities));
        ant.generateSolution();
    }

    // updating pheromone level after each iteration
    private void pheromoneVaporization() {
        for (String fromCity: citiesToVisit) {
            for (String toCity: citiesToVisit) {
                if(!fromCity.equals(toCity)){
                    double oldPheromoneLevel = pheromoneLevel.get(fromCity).get(toCity);
                    pheromoneLevel.get(fromCity).replace(toCity, oldPheromoneLevel * vaporization);
                }
            }
        }
    }


    // create a matrix with default pheromone level on all roads = 1
    private void createPheromoneMatrix() {
        for (String fromCity: citiesToVisit) {
            HashMap<String, Double> x = new HashMap<>();
            for (String toCity: citiesToVisit) {
                if(!fromCity.equals(toCity)){
                    x.put(toCity, 1.0);
                }else{
                    x.put(toCity, 0.0);
                }
            }
            pheromoneLevel.put(fromCity, x);
        }
    }

    // create z matrix with lengths from city to city
    private void createGraphCost() {
        for (String fromCity: citiesToVisit) {
            HashMap<String, Double> x = new HashMap<>();
            double xPosFromCity = data.get(fromCity).getX();
            double yPosFromCity = data.get(fromCity).getY();
            for (String toCity: citiesToVisit) {
                double xPosToCity = data.get(toCity).getX();
                double yPosToCity = data.get(toCity).getY();
                x.put(toCity, Math.sqrt(Math.pow(xPosFromCity-xPosToCity, 2) + Math.pow(yPosFromCity-yPosToCity, 2)));
            }
            graphCost.put(fromCity, x);
        }
    }
}
