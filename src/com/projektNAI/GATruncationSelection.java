package com.projektNAI;

import java.util.ArrayList;
import java.util.Collections;

public class GATruncationSelection extends GeneticAlgorithm {

	public GATruncationSelection(double mutationProb, double crossProb,
			int iterationNumber) {
		super(mutationProb, crossProb, iterationNumber);
	}
	
	
	@Override
	public ArrayList<Chromosome> selection(ArrayList<Chromosome> _population) {
		// TRUNCATION SELECTION
		
    	System.out.println("Selecting. Method: truncation");
		ArrayList<Chromosome> sortedPopulation = new ArrayList<Chromosome>();
		ArrayList<Chromosome> newPopulation = new ArrayList<Chromosome>();
		sortedPopulation = _population;
		Collections.sort(sortedPopulation, compareByFitness());
		
		for(Chromosome c : sortedPopulation) {
			
			int g[] = c.getGenes();
			
			for(int o : g) {
				System.out.print(o);
			}
			System.out.println();
        }

		double p = 0.5;
		
		while(newPopulation.size() < _population.size()) {
			
			int j = 0;
			while (j < (_population.size() * p)) {
				
				if(newPopulation.size() < _population.size()){
				newPopulation.add(sortedPopulation.get(j));
				}
				j++;
			}
		}
		return newPopulation;
	}
}