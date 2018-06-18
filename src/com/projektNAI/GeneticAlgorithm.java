package com.projektNAI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public abstract class GeneticAlgorithm{
    private ArrayList<Chromosome> population;
    private double mutationProb;
    private double crossProb;
    private int iterationNumber;

    public GeneticAlgorithm(double mutationProb, double crossProb, int iterationNumber) {
        this.mutationProb = mutationProb;
        this.crossProb = crossProb;
        this.iterationNumber = iterationNumber;
    }

    public void fitness(ArrayList<Chromosome> _population) {
        int score = 0;
        for (Chromosome c : _population) {
            for(int i =0; i < c.getGenes().length ; i++){
                if(c.getGenes()[i] == 1){
                    score += 10;
                }
            }
            c.setFitnessScore(score);
            score = 0;
        }
    }

    public void crossover(ArrayList<Chromosome> _population) {
    	
    	Random r = new Random();
			
			Chromosome first = _population.get(r.nextInt(_population.size()));
			Chromosome second = _population.get(r.nextInt(_population.size()));
    	
        int length = population.get(0).getGenes().length;
        
        System.out.println("First chromosome:");
        System.out.println(Arrays.toString(first.getGenes()));
		System.out.println();

        System.out.println("Second chromosome:");
        System.out.println(Arrays.toString(second.getGenes()));
		System.out.println();
        

        int crossOverPoint = r.nextInt(length-1);

        System.out.println("crossing point:" + crossOverPoint);
        for(int i = crossOverPoint; i < length; i++){
        	int geneFromFirst = first.getGenes()[i];
            int geneFromSecond = second.getGenes()[i];

            first.getGenes()[i] = geneFromSecond;
            second.getGenes()[i] = geneFromFirst;
        }
        
    }

    public void mutation(ArrayList<Chromosome> _population, double _mutationProb) {
    	
        for(int i= 0; i < _population.size(); i++){
            for(int j=0; j < _population.get(0).getGenes().length; j++){
                Random r = new Random();
                double b = r.nextDouble();

                int currentGeneValue = _population.get(i).getGenes()[j];

                if(b < _mutationProb){
                    if(currentGeneValue == 1){
                        currentGeneValue = 0;
                        System.out.println("mutating gene " + j + " of chromosome " + i);
                    } else {
                        currentGeneValue = 1;
                        System.out.println("mutating gene " + j + " of chromosome " + i);
                    }
                }

                _population.get(i).getGenes()[j] = currentGeneValue;
            }
        }
    }


    public ArrayList<Chromosome> selection(ArrayList<Chromosome> _population) {
    	
		return _population;
		 
    }


    public boolean terminationCondition(int _iterationNumber) {
        return _iterationNumber > this.iterationNumber;
    }

    public ArrayList<Chromosome> generateInitialPopulation(int amount, int length){
        ArrayList<Chromosome> currentPop = new ArrayList<>();

        for(int i= 0; i < amount; i++){
            int genes[] = new int[length];

            for(int j=0; j < length; j++){
                Random r = new Random();
                boolean b = r.nextBoolean();

                if(b){
                    genes[j] = 1;
                } else {
                    genes[j] = 0;
                }
            }

            currentPop.add(new Chromosome(genes));
        }

        return currentPop;
    }
    
   

    public ArrayList<Chromosome> getPopulation() {
        return population;
    }

    public void setPopulation(ArrayList<Chromosome> population) {
        this.population = population;
    }

    public double getMutationProb() {
        return mutationProb;
    }

    public void setMutationProb(double mutationProb) {
        this.mutationProb = mutationProb;
    }

    public double getCrossProb() {
        return crossProb;
    }

    public void setCrossProb(double crossProb) {
        this.crossProb = crossProb;
    }

    public int getIterationNumber() {
        return iterationNumber;
    }

    public void setIterationNumber(int iterationNumber) {
        this.iterationNumber = iterationNumber;
    }
}
