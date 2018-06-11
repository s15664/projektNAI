package com.projektNAI;

public class Chromosome {
    private int genes[];
    private int fitnessScore;

    public Chromosome(int[] genes) {
        this.genes = genes;
    }

    public int getFitnessScore() {
        return fitnessScore;
    }

    public void setFitnessScore(int fitnessScore) {
        this.fitnessScore = fitnessScore;
    }

    public int[] getGenes() {
        return genes;
    }

    public void setGenes(int[] genes) {
        this.genes = genes;
    }
}
