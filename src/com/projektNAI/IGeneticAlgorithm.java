package com.projektNAI;

import java.util.ArrayList;

public interface IGeneticAlgorithm {

public void fitness(ArrayList<Chromosome> _population);
public void crossOver(Chromosome mother, Chromosome father);
public void mutation(ArrayList<Chromosome> _population, double _mutationProb);
public void selection(ArrayList<Chromosome> _population);
public boolean terminationCondition(int _iterationNumber);

}
