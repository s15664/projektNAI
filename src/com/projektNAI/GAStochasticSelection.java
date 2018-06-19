package com.projektNAI;

import java.util.ArrayList;

public class GAStochasticSelection extends GeneticAlgorithm {

	public GAStochasticSelection(double mutationProb, double crossProb,
			int iterationNumber) {
		super(mutationProb, crossProb, iterationNumber);
	}

	@Override
	public ArrayList<Chromosome> selection(ArrayList<Chromosome> _population) {
		// STOCHASTIC UNIVERSAL SAMPLING
		
    	System.out.println("Selecting. Method: stochastic universal sampling");
		ArrayList<Chromosome> _newPopulation = new ArrayList<Chromosome>();
		int n = _population.size();
		double fitnessSum = 0.0;
		
        for (Chromosome c: _population) {
            fitnessSum += c.getFitnessScore();
        }
 
        double averageFitness = fitnessSum / n;
        
        double start = Math.random() * averageFitness;
        
        int[] pointerArray = new int[n];
        int index = 0;
        double sum = _population.get(index).getFitnessScore();
        
        for (int i = 0; i < n; i++) {
            
            double pointer = start + i * averageFitness;
            
            if (sum >= pointer) {
                pointerArray[i] = index;
            } else {
                for (++index; index < n; index++) {
                    sum += _population.get(index).getFitnessScore();
                    if (sum >= pointer) {
                        pointerArray[i] = index;
                        break;
                    }
                }
            }
        }
        
        for(int i: pointerArray) {
        
        	_newPopulation.add(_population.get(i));
       
        }
        
        return _newPopulation;
	}
}
