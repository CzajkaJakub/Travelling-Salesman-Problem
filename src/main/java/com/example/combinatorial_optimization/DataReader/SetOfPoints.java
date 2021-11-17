package com.example.combinatorial_optimization.DataReader;

import java.util.ArrayList;
import java.util.HashMap;

public class SetOfPoints {

    private int amountOfCities;
    private final HashMap<String, Point> data;
    private final ArrayList<String> cities;

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


    public void addPointToData(Point point){
        String cityName = point.getCityName();
        cities.add(cityName);
        data.put(cityName, point);
    }
}
