package com.example.combinatorial_optimization.DataReader;

import java.util.ArrayList;
import java.util.HashMap;

public class SetOfPoints {

    private int amountOfCities;
    private final HashMap<String, Point> data;
    private final ArrayList<String> cities;
    private double maxX;
    private double maxY;

    public SetOfPoints() {
        data = new HashMap<>();
        cities = new ArrayList<>();
    }

    public void setAmountOfCities(int amountOfCities) {
        this.amountOfCities = amountOfCities;
    }

    public int getAmountOfCities() {
        return amountOfCities;
    }

    public HashMap<String, Point> getData() {
        return data;
    }

    public ArrayList<String> getCities() {
        return cities;
    }


    public double getMaxX() {
        return maxX;
    }

    public double getMaxY() {
        return maxY;
    }

    public void addPointToData(Point point){
        String cityName = point.getCityName();
        cities.add(cityName);
        data.put(cityName, point);
        checkMax(point.getX(), point.getY());
    }

    private void checkMax(double x, double y) {
        if(x > maxX){maxX = x;}
        if(y > maxY){maxY = y;}
    }


}
