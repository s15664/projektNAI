package com.projektNAI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;
import java.lang.Math;

public class GAExponentialRankSelection extends GeneticAlgorithm {

	public GAExponentialRankSelection(double mutationProb, double crossProb,
			int iterationNumber) {
		super(mutationProb, crossProb, iterationNumber);
	}

	@Override
	public ArrayList<Chromosome> selection(ArrayList<Chromosome> _population) {
		// EXPONENTIAL RANK SELECTION
		
		
    	System.out.println("Selecting. Method: exponential rank selection");
   
		
    	ArrayList<Chromosome> sortedPopulation = new ArrayList<Chromosome>();
		ArrayList<Chromosome> newPopulation = new ArrayList<Chromosome>();
		sortedPopulation = _population;
		
        int n = _population.size();
        double ndouble = n;
        Collections.sort(sortedPopulation, compareByFitness());
        
        
        double[] selectionProbability = new double[n];
        double c = (2*ndouble*(n-1)) / ((6*(ndouble-1)+ndouble));
        
        for(int i = 0; i < n; i++) {
        	
        	
        	selectionProbability[i] = Math.exp((-(n-i)/c));
        
        }
        
        
        for(int i = 0; i < n; i++) {
        	
        	 double rand = ThreadLocalRandom.current().nextDouble((1/9*c), (2/c));
        	
        	for(int j = 0; j < n; j++) {
        		
        		if(selectionProbability[j] <= rand) {
        			
        			if(newPopulation.size() < n) {
        			newPopulation.add(sortedPopulation.get(j));
        			}       			
        		}
        	}
        }
        
        return newPopulation;
	}
}