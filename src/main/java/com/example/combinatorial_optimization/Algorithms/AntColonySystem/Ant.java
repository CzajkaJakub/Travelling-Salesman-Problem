package com.example.combinatorial_optimization.Algorithms.AntColonySystem;

import com.example.combinatorial_optimization.DataReader.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Ant implements AntSettings {

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


    public void placeAnt(int x){
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
                double pheromoneLev = pheromoneLevel.get(antRoad.get(x)).get(antRoad.get(x+1)) + wydalaniePheromonu;
                pheromoneLevel.get(antRoad.get(x)).put(antRoad.get(x+1), pheromoneLev);
                pheromoneLevel.get(antRoad.get(x+1)).put(antRoad.get(x), pheromoneLev);
            }

        }else{

            Random random = new Random();
            double x = random.nextDouble();


            double suma = 0;
            for (String next: citiesToVisit) {

                double iloscFeromonu = Math.pow(pheromoneLevel.get(cityName).get(next), a);
                double atrakcyjnosc = Math.pow(1/graphCost.get(cityName).get(next), b);

                suma += iloscFeromonu * atrakcyjnosc;
            }


            ArrayList<Double> chances = new ArrayList<>();

            for (String next: citiesToVisit) {
                double iloscFeromonu = Math.pow(pheromoneLevel.get(cityName).get(next), a);
                double atrakcyjnosc = Math.pow(1/graphCost.get(cityName).get(next), b);
                double praw = iloscFeromonu*atrakcyjnosc/suma;


                if(chances.size() == 0){
                    chances.add(praw);
                }else{
                    praw += chances.get(chances.size() - 1);
                    chances.add(praw);
                }
            }

            double redukcja = 1 - chances.get(chances.size()-1);
            x -= redukcja;


            for (int i = 0; i < citiesToVisit.size(); i++) {
                if(x <= chances.get(i)){
                    String nextCity = citiesToVisit.get(i);
                    antRoad.add(nextCity);
                    citiesToVisit.remove(nextCity);
                    totalLength += graphCost.get(cityName).get(nextCity);
                    move(nextCity);
                    return;
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
