package com.projektNAI.TSP;

import com.projektNAI.Chromosome;
import com.projektNAI.GeneticAlgorithm;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeneticAlgorithmTSP {
    private double MutationProb;
    private double CrossProb;
    private double IterationNumber;

    private ArrayList<Point> listOfAllPoints = new ArrayList<>();
    private ArrayList<ChromosomeTSP> population;
    private int [][] distancesBetweenAllPoints;

    public GeneticAlgorithmTSP(double mutationProb, double crossProb, double iterationNumber) {
        MutationProb = mutationProb;
        CrossProb = crossProb;
        IterationNumber = iterationNumber;
    }

    public List<String> readData(String pathname) throws IOException {
        return java.nio.file.Files.readAllLines(new File(pathname).toPath(), StandardCharsets.UTF_8);
    }

    public ArrayList<Point> preparingData(List<String> _doc) {
        ArrayList<Point> points = new ArrayList<>();

        System.out.println(_doc);
        Pattern number = Pattern.compile("\\d+\\,\\d+\\,\\d+");

        for (String a_doc : _doc) {
            Matcher mnumber = number.matcher(a_doc);
            Matcher mnumber2 = number.matcher(a_doc);
            Matcher mnumber3 = number.matcher(a_doc);

            int id = 0;
            int x = 0;
            int y = 0;

            if (mnumber.find()){
                id = Integer.parseInt(mnumber.group().substring(0,1));
                x = Integer.parseInt(mnumber2.group().substring(1,2));
                y = Integer.parseInt(mnumber3.group().substring(4,5));
            }

            Point point = new Point(id, x, y);

            points.add(point);
        }

        return points;
    }

    public void calculateDistanceBetweenAllPoints(ArrayList<Point> _points){
        distancesBetweenAllPoints = new int[_points.size()-1][_points.size()-1];

        for(int i = 0; i < _points.size(); i++){
            for(int j =0; j < _points.size(); j++){

                int point1x = _points.get(i).getX();
                int point1y = _points.get(i).getY();

                int point2x = _points.get(j).getX();
                int point2y = _points.get(j).getY();

                System.out.println(i + " " + j);
                System.out.println((int)Math.sqrt(Math.pow((point1x-point2x), 2) + Math.pow((point1y-point2y), 2)));

                distancesBetweenAllPoints[i][j] = (int)Math.sqrt(Math.pow((point1x-point2x), 2) + Math.pow((point1y-point2y), 2));
            }
        }
    }

    public void fitness(ArrayList<ChromosomeTSP> _population) {
        for(int i=0; i < _population.size(); i++){
            int score = 0;
            for(int j =0; j < _population.get(i).getListOfPoint().size()-1; j++){ //dodatkowo -1 bo ostatni niema dalszej odległości do punktu
                score =+ distancesBetweenAllPoints[_population.get(i).getListOfPoint().get(j).id][_population.get(i).getListOfPoint().get(j+1).id];
            }

            _population.get(i).setFitnessScore(score);
        }

    }

    public void crossOver(ChromosomeTSP first, ChromosomeTSP second) {
        int length = population.get(0).getListOfPoint().size();

        ArrayList<Point> fromFirstChromosome = new ArrayList<>();
        ArrayList<Point> fromSecondChromosome = new ArrayList<>();

        Random r = new Random();

        int crossOverPoint = r.nextInt(length-1);

        for(int a=0; a < length; a++){
            fromFirstChromosome.add(population.get(population.indexOf(first)).getListOfPoint().get(a));
            fromSecondChromosome.add(population.get(population.indexOf(second)).getListOfPoint().get(a));
        }

        for(int b=0; b < length; b++){
            fromFirstChromosome.remove(population.get(population.indexOf(first)).getListOfPoint().get(b));
            fromSecondChromosome.remove(population.get(population.indexOf(second)).getListOfPoint().get(b));
        }

        first.getListOfPoint().addAll(fromFirstChromosome);
        second.getListOfPoint().addAll(fromSecondChromosome);

    }

    public void mutation(ArrayList<Chromosome> _population, double _mutationProb) {

    }

    public ArrayList<ChromosomeTSP> selection(ArrayList<ChromosomeTSP> _population) {
        ArrayList<ChromosomeTSP> _newPopulation = new ArrayList<>();

        while(_newPopulation.size() < _population.size()) {

            Random r = new Random();

            ChromosomeTSP firstFighter = _population.get(r.nextInt(_population.size()));
            ChromosomeTSP secondFighter = _population.get(r.nextInt(_population.size()));

            if (firstFighter.getFitnessScore() > secondFighter.getFitnessScore()) {
                _newPopulation.add(firstFighter);
            } else {
                _newPopulation.add(secondFighter);
            }
        }

        return _newPopulation;
    }


    public boolean terminationCondition(int _iterationNumber) {
        return false;
    }


    public ArrayList<ChromosomeTSP> generateInitialPopulation(int amount) {
        ArrayList<ChromosomeTSP> listOfChromosomes = new ArrayList<>();

        for(int i = 0; i <= amount; i++) {
            ArrayList<Point> oldPointsList = listOfAllPoints;
            ArrayList<Point> newPoints = new ArrayList<>();

            while (!oldPointsList.isEmpty()) {

                Random random = new Random();

                int r = random.nextInt(oldPointsList.size() - 1);

                newPoints.add(oldPointsList.get(r));

                oldPointsList.remove(r);
            }
            listOfChromosomes.add(new ChromosomeTSP(newPoints));
        }
        return listOfChromosomes;
    }

    public double getMutationProb() {
        return MutationProb;
    }

    public void setMutationProb(double mutationProb) {
        MutationProb = mutationProb;
    }

    public double getCrossProb() {
        return CrossProb;
    }

    public void setCrossProb(double crossProb) {
        CrossProb = crossProb;
    }

    public double getIterationNumber() {
        return IterationNumber;
    }

    public void setIterationNumber(double iterationNumber) {
        IterationNumber = iterationNumber;
    }

    public ArrayList<Point> getListOfAllPoints() {
        return listOfAllPoints;
    }

    public void setListOfAllPoints(ArrayList<Point> listOfAllPoints) {
        this.listOfAllPoints = listOfAllPoints;
    }

    public int[][] getDistancesBetweenAllPoints() {
        return distancesBetweenAllPoints;
    }

    public void setDistancesBetweenAllPoints(int[][] distancesBetweenAllPoints) {
        this.distancesBetweenAllPoints = distancesBetweenAllPoints;
    }
}
