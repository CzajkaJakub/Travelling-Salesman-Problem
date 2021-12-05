package com.example.combinatorial_optimization.Algorithms.AntColonySystem;

import com.example.combinatorial_optimization.Algorithms.Algorithm;
import com.example.combinatorial_optimization.DataReader.Point;
import com.example.combinatorial_optimization.DataReader.SetOfPoints;

import java.lang.ref.PhantomReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class AntAlgorithm implements Algorithm {

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

        double min = 99999999999.0;
        Random random = new Random();

        for(int i = 1; i <= 4; i++){
            Ant ant = new Ant(cities, data, graphCost, pheromoneLevel);
            ant.placeAnts(random.nextInt(amountOfCities));

            if(ant.getTotalLength() < min){
                totalLength = ant.getTotalLength();
                finalRoad = ant.getAntRoad();
                min = ant.getTotalLength();
            }
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
            HashMap<String, Double> len = new HashMap<>();
            double x1 = data.get(fromCity).getX();
            double y1 = data.get(fromCity).getY();
            for (String toCity: cities) {
                double x2 = data.get(toCity).getX();
                double y2 = data.get(toCity).getY();
                len.put(toCity, Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2)));
            }
            graphCost.put(fromCity, len);
        }
    }
}
