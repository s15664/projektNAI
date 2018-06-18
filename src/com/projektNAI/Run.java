package com.projektNAI;

public class Run {

	int number;
	
	public Run(int i) {
		 number = i;
	}

	public void run() {
		
		int j = 0, fitnessSum = 0;
		GeneticAlgorithm geneticAlgorithm = null;
		
		switch(number) {
		case 1: 
		
			geneticAlgorithm = new GAStochasticSelection(0.005,0.98,10);
			break;
		
		case 2:
			
			geneticAlgorithm = new GATournamentSelection(0.005,0.98,10);
			break;
			
		case 3:
			
			geneticAlgorithm = new GARouletteSelection(0.005,0.98,10);
			break;
			
		}
			

		geneticAlgorithm.setPopulation(geneticAlgorithm.generateInitialPopulation(10,10));

		geneticAlgorithm.fitness(geneticAlgorithm.getPopulation());
		

		while(j < geneticAlgorithm.getIterationNumber()){
			
			Double probCross = Math.random();

			geneticAlgorithm.setPopulation(geneticAlgorithm.selection(geneticAlgorithm.getPopulation()));
				
			if(probCross < geneticAlgorithm.getCrossProb()){
				
				geneticAlgorithm.crossover(geneticAlgorithm.getPopulation());
			}

			geneticAlgorithm.mutation(geneticAlgorithm.getPopulation(), geneticAlgorithm.getMutationProb());

			geneticAlgorithm.fitness(geneticAlgorithm.getPopulation());

			j++;
			fitnessSum = 0;
			
			System.out.println("Iteracja nr:" + j);
			
			for(Chromosome c : geneticAlgorithm.getPopulation()) {
				
				int g[] = c.getGenes();
				
				for(int o : g) {
					System.out.print(o);
				}
				System.out.println(" Fitness score:" + c.getFitnessScore());
				fitnessSum += c.getFitnessScore();
			}
			System.out.println("Population fitness score:" + fitnessSum);
			System.out.println();
			
			
	}

	}
	
}
