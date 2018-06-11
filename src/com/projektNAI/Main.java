package com.projektNAI;

import java.util.Random;

public class Main {


	public static void main(String[] args) {

		GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(0.01,0.98,1000);

		geneticAlgorithm.setPopulation(geneticAlgorithm.generateInitialPopulation(10,10));


		geneticAlgorithm.fitness(geneticAlgorithm.getPopulation());

		int j = 0;
		while(j < geneticAlgorithm.getIterationNumber()){

			geneticAlgorithm.selection(geneticAlgorithm.getPopulation());

			Random r = new Random();
			Double probCross = Math.random();

			if(probCross < geneticAlgorithm.getCrossProb()){
				Chromosome first = geneticAlgorithm.getPopulation().get(r.nextInt(geneticAlgorithm.getPopulation().size()));
				Chromosome second = geneticAlgorithm.getPopulation().get(r.nextInt(geneticAlgorithm.getPopulation().size()));

				geneticAlgorithm.crossOver(first, second);
			}

			Double probMuta = Math.random();

			geneticAlgorithm.mutation(geneticAlgorithm.getPopulation(), probMuta);

			geneticAlgorithm.selection(geneticAlgorithm.getPopulation());

			j++;
			
			System.out.println("Iteracja nr:" + j);
			for(Chromosome c : geneticAlgorithm.getPopulation()) {
				int g[] = c.getGenes();
				for(int o : g) {
					System.out.print(o);
				}
				System.out.println();
			}
		}
	}

}