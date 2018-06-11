package com.projektNAI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GeneticAlgorithm implements IGeneticAlgorithm {
    private ArrayList<Chromosone> population;
    private double mutationProb;
    private double crossProb;
    private int iterationNumber;

    public GeneticAlgorithm(double mutationProb, double crossProb, int iterationNumber) {
        this.mutationProb = mutationProb;
        this.crossProb = crossProb;
        this.iterationNumber = iterationNumber;
    }

    @Override
    public void fitness(ArrayList<Chromosone> _population) {
        int score = 0;
        for (Chromosone c : _population) {
            for(int i =0; i < c.getGenes().length ; i++){
                if(c.getGenes()[i] == 1){
                    score += 10;
                }
            }
            c.setFitnessScore(score);
            score = 0;
        }
    }

    @Override
    public void crossOver(Chromosone first, Chromosone second) {
        int length = population.get(0).getGenes().length;

        Random r = new Random();
        int crossOverPoint = r.nextInt(length-1);

        for(int i = crossOverPoint; i < length; i++){
            int geneFromFirst = first.getGenes()[i];
            int geneFromSecond = second.getGenes()[i];

            first.getGenes()[i] = geneFromSecond;
            second.getGenes()[i] = geneFromFirst;
        }
    }

    @Override
    public void mutation(ArrayList<Chromosone> _population, double _mutationProb) {
        for(int i= 0; i < _population.size(); i++){
            for(int j=0; j < _population.get(0).getGenes().length; j++){
                Random r = new Random();
                double b = r.nextDouble();

                int currentGeneValue = _population.get(i).getGenes()[j];

                if(b > _mutationProb){
                    if(currentGeneValue == 1){
                        currentGeneValue = 0;
                    } else {
                        currentGeneValue = 1;
                    }
                }

                _population.get(i).getGenes()[j] = currentGeneValue;
            }
        }
    }

    @Override
    public void selection(ArrayList<Chromosone> _population) {
        ArrayList<Chromosone> _oldPopulation = _population;
        ArrayList<Chromosone> _newPopulation = new ArrayList<>();

        Random r = new Random();
        Random r2 = new Random();

        Chromosone firstFighter = _oldPopulation.get(r.nextInt(_oldPopulation.size()));
        Chromosone secondFighter = _oldPopulation.get(r.nextInt(_oldPopulation.size()));

        while(_newPopulation.size() < _oldPopulation.size()) {
            if (firstFighter.getFitnessScore() > secondFighter.getFitnessScore()) {
                _newPopulation.add(firstFighter);
            } else {
                _newPopulation.add(secondFighter);
            }
        }
    }

    @Override
    public boolean terminationCondition(int _iterationNumber) {
        return _iterationNumber > this.iterationNumber;
    }

    public ArrayList<Chromosone> generateInicialPopulation(int amount, int length){
        ArrayList<Chromosone> currentPop = new ArrayList<>();

        for(int i= 0; i < amount; i++){
            int genes[] = new int[length];

            for(int j=0; j < length; j++){
                Random r = new Random();
                boolean b = r.nextBoolean();

                if(b){
                    genes[j] = 1;
                } else {
                    genes[j] = 0;
                }
            }

            currentPop.add(new Chromosone(genes));
        }

        return currentPop;
    }

    public ArrayList<Chromosone> getPopulation() {
        return population;
    }

    public void setPopulation(ArrayList<Chromosone> population) {
        this.population = population;
    }

    public double getMutationProb() {
        return mutationProb;
    }

    public void setMutationProb(double mutationProb) {
        this.mutationProb = mutationProb;
    }

    public double getCrossProb() {
        return crossProb;
    }

    public void setCrossProb(double crossProb) {
        this.crossProb = crossProb;
    }

    public int getIterationNumber() {
        return iterationNumber;
    }

    public void setIterationNumber(int iterationNumber) {
        this.iterationNumber = iterationNumber;
    }
}
