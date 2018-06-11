package com.projektNAI;

import java.util.Arrays;
import java.util.Random;

public class main {


	public static void main(String[] args) {

		GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(0.5,0.01,100);

		geneticAlgorithm.setPopulation(geneticAlgorithm.generateInicialPopulation(10,10));


		geneticAlgorithm.fitness(geneticAlgorithm.getPopulation());

		int j = 0;
		while(j < 10){

			geneticAlgorithm.selection(geneticAlgorithm.getPopulation());

			Random r = new Random();
			Double probCross = Math.random();

			if(probCross < geneticAlgorithm.getCrossProb()){
				Chromosone first = geneticAlgorithm.getPopulation().get(r.nextInt(geneticAlgorithm.getPopulation().size()));
				Chromosone second = geneticAlgorithm.getPopulation().get(r.nextInt(geneticAlgorithm.getPopulation().size()));

				geneticAlgorithm.crossOver(first, second);
			}

			Double probMuta = Math.random();

			geneticAlgorithm.mutation(geneticAlgorithm.getPopulation(), probMuta);

			geneticAlgorithm.selection(geneticAlgorithm.getPopulation());

			j++;
		}
	}

}
