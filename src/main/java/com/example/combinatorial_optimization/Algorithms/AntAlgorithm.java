package com.example.combinatorial_optimization.Algorithms;

import com.example.combinatorial_optimization.DataReader.Point;
import com.example.combinatorial_optimization.DataReader.SetOfPoints;

import java.util.ArrayList;
import java.util.HashMap;

public class AntAlgorithm implements Algorithm{

    private final ArrayList<String> antRoad;
    private final HashMap<String, Point> data;
    private final ArrayList<String> citiesToVisit;

    private  String startPoint;
    private double totalLength;
    private  double startX;
    private  double startY;


    public AntAlgorithm(SetOfPoints setOfPoints) {
        antRoad = new ArrayList<>();
        this.citiesToVisit = setOfPoints.getCities();
        this.data = setOfPoints.getData();
    }

    @Override
    public HashMap<String, Point> getData() {
        return data;
    }

    @Override
    public ArrayList<String> getRoad() {
        return antRoad;
    }

    @Override
    public double getTotalLength() {
        return totalLength;
    }

    @Override
    public void findRoad() {

    }
}
