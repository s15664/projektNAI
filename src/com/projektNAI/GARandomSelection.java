package com.projektNAI;

import java.util.ArrayList;
import java.util.Random;

public class GARandomSelection extends GeneticAlgorithm {

	public GARandomSelection(double mutationProb, double crossProb,
			int iterationNumber) {
		super(mutationProb, crossProb, iterationNumber);
	}

	@Override
	public ArrayList<Chromosome> selection(ArrayList<Chromosome> _population) {
		// ROULETTE SELECTION
		
    	System.out.println("Selecting. Method: random");
        ArrayList<Chromosome> _oldPopulation = _population;
        ArrayList<Chromosome> _newPopulation = new ArrayList<Chromosome>();
       
        
        while(_newPopulation.size() < _oldPopulation.size()) {
        	
        	Random r = new Random();
            int b = r.nextInt(_population.size()) ;
        	
        	_newPopulation.add(_oldPopulation.get(b));

        }
        
        return _newPopulation;
	}
}
