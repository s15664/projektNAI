package com.projektNAI;

import java.util.ArrayList;
import java.util.Random;

public class GAStochasticSelection extends GeneticAlgorithm {

	public GAStochasticSelection(double mutationProb, double crossProb,
			int iterationNumber) {
		super(mutationProb, crossProb, iterationNumber);
	}

	@Override
	public ArrayList<Chromosome> selection(ArrayList<Chromosome> _population) {
		
		//STOCHASTIC UNIVERSAL SAMPLING
		
		
    	System.out.println("selecting stochastic");
        ArrayList<Chromosome> _newPopulation = new ArrayList<Chromosome>();
        int fitnessSum = 0;
        int averageFitness = 0;
        double delta = 0;
       
        for(Chromosome c : this.getPopulation()) {
			
			fitnessSum += c.getFitnessScore();
		}
        
        
        
        averageFitness = fitnessSum / _population.size();
        
        Random r = new Random();
        double offset = r.nextDouble();
        
        delta = 0;
        int j = 0, i = 0;
        
        for(i=0; i < _population.size(); i++) {
        	
        	delta = this.getPopulation().get(i).getFitnessScore() / averageFitness * _population.size();
        	
     
        	while(delta > offset + j){
        	
        			System.out.println("j = " + j);
        			_newPopulation.add(this.getPopulation().get(j));
        			j++;
        			
        		} 
        }
            
     
        return _newPopulation;
	}

	
	

}
