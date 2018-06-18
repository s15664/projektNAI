package com.projektNAI.TSP;

import com.projektNAI.Chromosome;

import java.util.ArrayList;

public class ChromosomeTSP {
    int fitnessScore;
    ArrayList<Point> listOfPoint = new ArrayList<>();

    public ChromosomeTSP(ArrayList<Point> listOfPoint) {
        this.listOfPoint = listOfPoint;
    }

    public int getFitnessScore() {
        return fitnessScore;
    }

    public void setFitnessScore(int fitnessScore) {
        this.fitnessScore = fitnessScore;
    }

    public ArrayList<Point> getListOfPoint() {
        return listOfPoint;
    }

    public void setListOfPoint(ArrayList<Point> listOfPoint) {
        this.listOfPoint = listOfPoint;
    }
}
