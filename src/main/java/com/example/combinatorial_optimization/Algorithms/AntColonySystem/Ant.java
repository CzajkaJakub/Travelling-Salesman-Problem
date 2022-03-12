package com.example.combinatorial_optimization.Algorithms.AntColonySystem;

import com.example.combinatorial_optimization.DataReader.Point;
import com.example.combinatorial_optimization.Settings.Settings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Ant implements Settings{ // should implement settings

    private final ArrayList<String> antRoad;
    private ArrayList<String> citiesToVisit;
    private HashMap<String, Point> data;
    private Point startPoint;

    private HashMap<String, HashMap<String, Double>> graphCost;
    private HashMap<String, HashMap<String, Double>> pheromoneLevel;
    private double totalLength;
    private final Random random;

    public Ant() {
        antRoad = new ArrayList<>();
        random = new Random();
    }

    public ArrayList<String> getAntRoad() {
        return antRoad;
    }

    public double getTotalLength() {
        return totalLength;
    }

    public void setCitiesToVisit(ArrayList<String> citiesToVisit) {
        this.citiesToVisit = new ArrayList<>();
        this.citiesToVisit.addAll(citiesToVisit);
    }

    public void setData(HashMap<String, Point> data) {
        this.data = data;
    }

    public void setGraphCost(HashMap<String, HashMap<String, Double>> graphCost) {
        this.graphCost = graphCost;
    }

    public void setPheromoneLevel(HashMap<String, HashMap<String, Double>> pheromoneLevel) {
        this.pheromoneLevel = pheromoneLevel;
    }

    public void placeAntIntoCity(int startCity) {
        this.startPoint = data.get(citiesToVisit.get(startCity));
        citiesToVisit.remove(startPoint.getCityName());
        antRoad.add(startPoint.getCityName());
    }

    public void generateSolution() {
        findNextCity(startPoint.getCityName());
    }

    private void findNextCity(String fromCity) {
        if (citiesToVisit.size() != 0) {
            ArrayList<Double> probabilities = createProbabilitiesOfEachRoad(fromCity);
            double randomNumber = random.nextDouble();

            // choose next city with probabilities
            for (int i = 0; i < citiesToVisit.size(); i++) {
                if (randomNumber <= probabilities.get(i)) {
                    String nextCity = citiesToVisit.get(i);
                    antRoad.add(nextCity);
                    citiesToVisit.remove(nextCity);
                    totalLength += graphCost.get(fromCity).get(nextCity);
                    findNextCity(nextCity);
                    return;
                }
            }
        }else{
            antRoad.add(startPoint.getCityName());
            totalLength += graphCost.get(fromCity).get(startPoint.getCityName());
            updatePheromoneLevelOnAllRoads();
        }
    }

    private ArrayList<Double> createProbabilitiesOfEachRoad(String fromCity) {
        ArrayList<Double> chances = new ArrayList<>();

        double denominator = 0;
        double amountOfPheromoneOnRoad;
        double valueOfRoad;
        double probability;
        double numerator;

        // count denominator of probability
        for (String next : citiesToVisit) {
            amountOfPheromoneOnRoad = Math.pow(pheromoneLevel.get(fromCity).get(next), alpha);
            valueOfRoad = Math.pow(1/graphCost.get(fromCity).get(next), beta);
            denominator += amountOfPheromoneOnRoad * valueOfRoad;
        }

        // count probabilities to visit each city
        for (String next : citiesToVisit) {
            amountOfPheromoneOnRoad = Math.pow(pheromoneLevel.get(fromCity).get(next), alpha);
            valueOfRoad = Math.pow(1/graphCost.get(fromCity).get(next), beta);
            numerator = amountOfPheromoneOnRoad * valueOfRoad;
            probability = numerator / denominator;

            if (chances.size() != 0) {
                probability += chances.get(chances.size() - 1);
            }
            chances.add(probability);
        }
        chances.set(chances.size()-1, 1.1);
        return chances;
    }

    private void updatePheromoneLevelOnAllRoads() {
        double newPheromoneLevel;
        for(int x = 0; x < antRoad.size() - 1; x++){
            newPheromoneLevel = pheromoneLevel.get(antRoad.get(x)).get(antRoad.get(x+1)) + pheromoneProduction;
            pheromoneLevel.get(antRoad.get(x)).put(antRoad.get(x+1), newPheromoneLevel);
            pheromoneLevel.get(antRoad.get(x+1)).put(antRoad.get(x), newPheromoneLevel);
        }
    }
}


