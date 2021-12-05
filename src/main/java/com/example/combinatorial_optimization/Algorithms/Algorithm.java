
package com.example.combinatorial_optimization.Algorithms;

import com.example.combinatorial_optimization.DataReader.Point;

import java.util.ArrayList;
import java.util.HashMap;

public interface Algorithm {
    HashMap<String, Point> getData();
    ArrayList<String> getRoad();
    double getTotalLength();
    void findRoad();
}