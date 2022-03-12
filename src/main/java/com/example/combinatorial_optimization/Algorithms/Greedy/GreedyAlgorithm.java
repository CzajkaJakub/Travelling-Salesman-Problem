package com.example.combinatorial_optimization.Algorithms.Greedy;

import com.example.combinatorial_optimization.Algorithms.Algorithm;
import com.example.combinatorial_optimization.DataReader.Point;
import com.example.combinatorial_optimization.DataReader.SetOfPoints;

import java.util.ArrayList;
import java.util.HashMap;

public class GreedyAlgorithm implements Algorithm {

    private final String startPoint;
    private final ArrayList<String> greedyRoad;
    private final ArrayList<String> citiesToVisit;
    private final HashMap<String, Point> data;
    private final double startX;
    private final double startY;
    private double totalLength;

    public GreedyAlgorithm(SetOfPoints setOfPoints) {
        greedyRoad = new ArrayList<>();
        this.citiesToVisit = setOfPoints.getCities();
        this.data = setOfPoints.getData();
        this.startPoint = citiesToVisit.get(0);
        this.startX = data.get(startPoint).getX();
        this.startY = data.get(startPoint).getY();
    }

    @Override
    public HashMap<String, Point> getData() {
        return data;
    }

    @Override
    public ArrayList<String> getRoad() {
        return greedyRoad;
    }

    @Override
    public double getTotalLength() {
        return totalLength;
    }

    @Override
    public void findRoad() {
        citiesToVisit.remove(startPoint);
        greedyRoad.add(startPoint);
        findTheClosestPoint(startX, startY);
    }

    private void findTheClosestPoint(double xPos, double yPos){
        String nextCity = "";
        double nextCityX = 0;
        double nextCityY = 0;
        double minLengthToTheNextCity = 99999;

        double tempNextCityX;
        double tempNextCityY;
        double lengthToTheNextCity;

        if(citiesToVisit.size()>0){

            for (String city: citiesToVisit) {
                tempNextCityX = data.get(city).getX();
                tempNextCityY = data.get(city).getY();
                lengthToTheNextCity = Math.sqrt(Math.pow(xPos-tempNextCityX, 2)+Math.pow(yPos-tempNextCityY, 2));

                if(lengthToTheNextCity < minLengthToTheNextCity){
                    minLengthToTheNextCity = lengthToTheNextCity;

                    nextCity = city;
                    nextCityX = tempNextCityX;
                    nextCityY = tempNextCityY;
                }
            }

            totalLength += minLengthToTheNextCity;
            citiesToVisit.remove(nextCity);
            greedyRoad.add(nextCity);
            findTheClosestPoint(nextCityX, nextCityY);
        }else{
            greedyRoad.add(startPoint);
            lengthToTheNextCity = Math.sqrt(Math.pow(xPos-startX, 2)+Math.pow(yPos-startY, 2));
            totalLength += lengthToTheNextCity;
        }
    }
}
