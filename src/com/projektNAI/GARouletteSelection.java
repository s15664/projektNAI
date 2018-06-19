package com.projektNAI;

import java.util.ArrayList;
import java.util.Random;

public class GARouletteSelection extends GeneticAlgorithm {

	


	public GARouletteSelection(double mutationProb, double crossProb,
			int iterationNumber) {
		super(mutationProb, crossProb, iterationNumber);
	}



	@Override
	public ArrayList<Chromosome> selection(ArrayList<Chromosome> _population) {
		// ROULETTE SELECTION
		
    	System.out.println("Selecting. Method: roulette");
        ArrayList<Chromosome> _oldPopulation = _population;
        ArrayList<Chromosome> _newPopulation = new ArrayList<Chromosome>();
        double fitnessSum = 0;
        int rouletteSum = 0;
       
        for(Chromosome c : this.getPopulation()) {
			
			fitnessSum += c.getFitnessScore();
		}
        
        
        while(_newPopulation.size() < _oldPopulation.size()) {
        	
        	rouletteSum = 0;
        	Random r = new Random();
            double b = r.nextDouble()*fitnessSum;
        	int i = 0;
        	
        	while(rouletteSum <= b && i < 9) {
        			
        			rouletteSum += this.getPopulation().get(i).getFitnessScore();
        			i++;
        		
        	}
        	
        	_newPopulation.add(_oldPopulation.get(i));

        }
        
        return _newPopulation;
	}

}
