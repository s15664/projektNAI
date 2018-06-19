package com.projektNAI;


public class Main {


	public static void main(String[] args) {
		
			double mutationProbability = 0.005;
			double crossoverProbability = 0.98;
			int populationSize = 10;
			
			long stochasticSum=0, tournamentSum=0, rouletteSum=0, truncationSum=0, linearSum=0, exponentialSum=0,
					randomSum=0;
			
			for(int i = 1; i < 8; i++) {
				
				for(int j = 0; j < 100; j++) {
				
					Run run = new Run(i);
				
					switch(i) {
				
					case 1: stochasticSum += run.run(mutationProbability, crossoverProbability, populationSize);
					break;
				
					case 2: tournamentSum += run.run(mutationProbability, crossoverProbability, populationSize);
					break;
				
					case 3: rouletteSum += run.run(mutationProbability, crossoverProbability, populationSize);
					break;
				
					case 4: truncationSum += run.run(mutationProbability, crossoverProbability, populationSize);
					break;
				
					case 5: linearSum += run.run(mutationProbability, crossoverProbability, populationSize);
					break;
				
					case 6: exponentialSum += run.run(mutationProbability, crossoverProbability, populationSize);
					break;
					
					case 7: randomSum += run.run(mutationProbability, crossoverProbability, populationSize);
					break;
					}
				}
				
			}
			
			System.out.println("Results: ");
			System.out.println("Stochastic selection: " + stochasticSum);
			System.out.println("Tournament selection: " + tournamentSum);
			System.out.println("Roulette selection: " + rouletteSum);
			System.out.println("Truncation selection: " + truncationSum);
			System.out.println("Linear selection: " + linearSum);
			System.out.println("Exponential selection: " + exponentialSum);
			System.out.println("Random selection: " + randomSum);
	}
}