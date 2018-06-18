package com.projektNAI.comivojager;

import com.projektNAI.Chromosome;
import com.projektNAI.GeneticAlgorithm;
import com.projektNAI.IGeneticAlgorithm;

import java.util.ArrayList;

public class GeneticAlgoritmComivojager extends GeneticAlgorithm {
    private 

    public GeneticAlgoritmComivojager(double mutationProb, double crossProb, int iterationNumber) {
        super(mutationProb, crossProb, iterationNumber);
    }

    @Override
    public void fitness(ArrayList<Chromosome> _population) {

    }

    @Override
    public void crossOver(Chromosome mother, Chromosome father) {

    }

    @Override
    public void mutation(ArrayList<Chromosome> _population, double _mutationProb) {

    }

    @Override
    public ArrayList<Chromosome> selection(ArrayList<Chromosome> _population) {
        return null;
    }

    @Override
    public boolean terminationCondition(int _iterationNumber) {
        return false;
    }



    @Override
    public ArrayList<Chromosome> generateInitialPopulation(int amount, int length) {
        return super.generateInitialPopulation(amount, length);
    }

    @Override
    public ArrayList<Chromosome> getPopulation() {
        return super.getPopulation();
    }

    @Override
    public void setPopulation(ArrayList<Chromosome> population) {
        super.setPopulation(population);
    }

    @Override
    public double getMutationProb() {
        return super.getMutationProb();
    }

    @Override
    public void setMutationProb(double mutationProb) {
        super.setMutationProb(mutationProb);
    }

    @Override
    public double getCrossProb() {
        return super.getCrossProb();
    }

    @Override
    public void setCrossProb(double crossProb) {
        super.setCrossProb(crossProb);
    }

    @Override
    public int getIterationNumber() {
        return super.getIterationNumber();
    }

    @Override
    public void setIterationNumber(int iterationNumber) {
        super.setIterationNumber(iterationNumber);
    }
}
