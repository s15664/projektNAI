package com.projektNAI;

import java.util.ArrayList;
import java.util.Comparator;
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
    	
    	int c1 = r.nextInt(_population.size());
    	int c2;
    	
    	do {
    		c2 = r.nextInt(_population.size());
    	} while (c1 == c2);	
    	
		Chromosome first = _population.get(c1);
		Chromosome second = _population.get(c2);
    	
        int length = population.get(0).getGenes().length;
        
        System.out.println("Crossing chromosomes " + c1 + " and " + c2);

        int crossoverPoint = r.nextInt(length-1);

        System.out.println("Crossing point:" + crossoverPoint);
        for(int i = crossoverPoint; i < length; i++){
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
                        System.out.println("Mutating gene " + j + " of chromosome " + i);
                    } else {
                        currentGeneValue = 1;
                        System.out.println("Mutating gene " + j + " of chromosome " + i);
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
    
    public static Comparator<Chromosome> compareByFitness() {
		
   	 Comparator<Chromosome> comp = new Comparator<Chromosome>(){
   	    
   		 @Override
   	     public int compare(Chromosome c1, Chromosome c2)
   	     {
   	         int first = c1.getFitnessScore();
   	         int second = c2.getFitnessScore();
   	         return second - first;
   	     }        
   	 	};
   	 	return comp;
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
