package com.projektNAI;

import java.util.ArrayList;

public interface IGeneticAlgorithm {

public void fitness(ArrayList<Chromosone> _population);
public void crossOver(Chromosone mother, Chromosone father);
public void mutation(ArrayList<Chromosone> _population, double _mutationProb);
public void selection(ArrayList<Chromosone> _population);
public boolean terminationCondition(int _iterationNumber);

}
