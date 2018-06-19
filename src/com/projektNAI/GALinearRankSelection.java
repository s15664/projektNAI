package com.projektNAI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GALinearRankSelection extends GeneticAlgorithm {

	public GALinearRankSelection(double mutationProb, double crossProb, 
			int iterationNumber) {		
		super(mutationProb, crossProb, iterationNumber);
	}

	
	@Override
	public ArrayList<Chromosome> selection(ArrayList<Chromosome> _population) {
		// LINEAR RANK SELECTION
		// ranks are: worst fitness = rank 1, best fitness = rank = population size
	       
		
    	System.out.println("Selecting. Method: linear rank selection");
    	ArrayList<Chromosome> sortedPopulation = new ArrayList<Chromosome>();
		ArrayList<Chromosome> newPopulation = new ArrayList<Chromosome>();
		sortedPopulation = _population;
		
        int n = _population.size();
        double ndouble = n;
        Collections.sort(sortedPopulation, compareByFitness());
        
        
        double[] selectionProbability = new double[n];
        
        for(int i = 0; i < n; i++) {
        	selectionProbability[i] = (ndouble-i)/(ndouble*(ndouble-1));
        }
        
        double sum = 0.0;
        sum = 1 / (n-2.001);
        
        for(int i = 0; i < n; i++) {
        	
        	 Random r = new Random();
             double	rand = r.nextDouble() * sum;
        	
        	for(int j = 0; j < n; j++) {
        		
        		if(selectionProbability[j] >= rand) {
        			
        			if(newPopulation.size() < n) {
        			newPopulation.add(sortedPopulation.get(j));
        			}
        			
        			
        		}
        	}
        }
        
        return newPopulation;
	}
}
