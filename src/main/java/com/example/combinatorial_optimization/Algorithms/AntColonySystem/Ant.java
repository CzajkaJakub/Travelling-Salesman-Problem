package com.example.combinatorial_optimization.Algorithms.AntColonySystem;

import com.example.combinatorial_optimization.DataReader.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Ant {

    private final ArrayList<String> antRoad;
    private ArrayList<String> citiesToVisit;
    private final HashMap<String, Point> data;
    private Point startPoint;

    private HashMap<String, HashMap<String, Double>> graphCost;
    private HashMap<String, HashMap<String, Double>> pheromoneLevel;
    private double totalLength;

    public Ant(ArrayList<String> cities, HashMap<String, Point> data, HashMap<String, HashMap<String, Double>> graphCost, HashMap<String, HashMap<String, Double>> pheromoneLevel) {
        antRoad = new ArrayList<>();
        this.data = data;
        this.graphCost = graphCost;
        this.pheromoneLevel = pheromoneLevel;
        citiesToVisit = new ArrayList<>();
        citiesToVisit.addAll(cities);
    }


    public void placeAnts(int x){
        this.startPoint = data.get(citiesToVisit.get(x));
        citiesToVisit.remove(startPoint.getCityName());
        antRoad.add(startPoint.getCityName());
        move(startPoint.getCityName());
    }

    public void move(String cityName) {
        if(citiesToVisit.size() == 0){
            antRoad.add(startPoint.getCityName());
            totalLength += graphCost.get(cityName).get(startPoint.getCityName());
            for(int x = 0; x < antRoad.size() - 1; x++){
                double srajPheromonem = 10000/totalLength + pheromoneLevel.get(antRoad.get(x)).get(antRoad.get(x+1));
                pheromoneLevel.get(antRoad.get(x)).put(antRoad.get(x+1), srajPheromonem);
                pheromoneLevel.get(antRoad.get(x+1)).put(antRoad.get(x), srajPheromonem);
            }
        }else{
            Random random = new Random();
            double x = random.nextDouble();



            double full = 0;
            for (String next: citiesToVisit) {
                full += pheromoneLevel.get(cityName).get(next);
         //       full += graphCost.get(cityName).get(next)/100;
            }

            ArrayList<Double> chances = new ArrayList<>();


            for (String next: citiesToVisit) {

                double level = pheromoneLevel.get(cityName).get(next)/full;

                if(chances.size() != 0){
                    level += chances.get(chances.size()-1);
                }

                if(x <= level){
                    antRoad.add(next);
                    citiesToVisit.remove(next);


                    totalLength += graphCost.get(cityName).get(next);
                    chances.add(level);

                    move(next);
                    break;
                }else{
                    chances.add(level);

                }
            }
        }
    }

    public ArrayList<String> getAntRoad() {
        return antRoad;
    }

    public double getTotalLength() {
        return totalLength;
    }
}
