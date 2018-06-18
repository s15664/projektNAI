package com.projektNAI.TSP;

import java.io.IOException;
import java.util.List;

public class mainExample {
    public static void main(String[] args) throws IOException {
        GeneticAlgorithmTSP geneticAlgorithmTSP = new GeneticAlgorithmTSP(0,0,0);

        List<String> lista = geneticAlgorithmTSP.readData("D:\\Programowanie\\IdeaProjects\\GitHub\\data.txt");

        geneticAlgorithmTSP.setListOfAllPoints(geneticAlgorithmTSP.preparingData(lista));

        geneticAlgorithmTSP.calculateDistanceBetweenAllPoints(geneticAlgorithmTSP.getListOfAllPoints());

    }
}
