package com.example.combinatorial_optimization.DataGeneration;

import com.example.combinatorial_optimization.MainPackage.FilesPaths;

import java.io.*;
import java.util.Random;

public class Generator implements FilesPaths, GeneratorSettings{

    public void generateNumbers() throws IOException {
        FileWriter dataWriter = new FileWriter(dataFilePath);
        StringBuilder data = new StringBuilder(amountOfNumbers + "\n");
        Random random = new Random();

        for(int x = 1; x <= amountOfNumbers; x++){
            int randomX = random.nextInt(1000) + 50;
            int randomY = random.nextInt(800) + 50;
            String dataLine = String.format("%d %d %d\n", x, randomX, randomY);
            data.append(dataLine);
        }

        dataWriter.write(String.valueOf(data));
        dataWriter.close();

    }
}
