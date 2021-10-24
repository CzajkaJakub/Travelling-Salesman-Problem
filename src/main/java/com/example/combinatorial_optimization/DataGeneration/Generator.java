package com.example.combinatorial_optimization.DataGeneration;

import com.example.combinatorial_optimization.MainPackage.FilesPaths;
import com.example.combinatorial_optimization.MainPackage.VisualizationSettings;

import java.io.*;
import java.util.Random;

public class Generator implements FilesPaths, GeneratorSettings, VisualizationSettings {

    public void generateNumbers() throws IOException {
        FileWriter dataWriter = new FileWriter(dataFilePath);
        StringBuilder data = new StringBuilder(amountOfNumbers + "\n");
        Random random = new Random();
        for(int x = 1; x <= amountOfNumbers; x++){
            int randomX = random.nextInt(visualizationWindowWidth - 2*margin) + margin;
            int randomY = random.nextInt(visualizationWindowHeight - 2*margin) + margin;
            String dataLine = String.format("%d %d %d\n", x, randomX, randomY);
            data.append(dataLine);
        }
        dataWriter.write(String.valueOf(data));
        dataWriter.close();
    }
}
