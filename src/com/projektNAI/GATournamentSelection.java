package com.projektNAI;

import java.util.ArrayList;
import java.util.Random;

public class GATournamentSelection extends GeneticAlgorithm{



    public GATournamentSelection(double mutationProb, double crossProb, int iterationNumber) {
		super(mutationProb, crossProb, iterationNumber);
	}

	public ArrayList<Chromosome> selection(ArrayList<Chromosome> _population) {
		//TOURNAMENT SELECTION
		
    	System.out.println("Selecting. Method: tournament");
        ArrayList<Chromosome> _oldPopulation = _population;
        ArrayList<Chromosome> _newPopulation = new ArrayList<Chromosome>();

        
        while(_newPopulation.size() < _oldPopulation.size()) {
        	
        	Random r = new Random();

            Chromosome firstFighter = _oldPopulation.get(r.nextInt(_oldPopulation.size()));
            Chromosome secondFighter = _oldPopulation.get(r.nextInt(_oldPopulation.size()));
        	
            if (firstFighter.getFitnessScore() > secondFighter.getFitnessScore()) {
                _newPopulation.add(firstFighter);
            } else {
                _newPopulation.add(secondFighter);
            }
        }
        
        return _newPopulation;
    }
}
