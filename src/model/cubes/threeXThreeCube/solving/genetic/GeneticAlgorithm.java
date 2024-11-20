package model.cubes.threeXThreeCube.solving.genetic;

import model.cubes.threeXThreeCube.solving.genetic.model.NoSolutionException;
import model.cubes.threeXThreeCube.solving.genetic.model.Problem;
import model.cubes.threeXThreeCube.solving.genetic.model.Solution;
import model.cubes.threeXThreeCube.solving.genetic.operators.EvolutionaryOperator;
import model.cubes.threeXThreeCube.solving.genetic.operators.FitnessEvaluator;
import model.cubes.threeXThreeCube.solving.genetic.operators.SurvivalOperator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GeneticAlgorithm {

    private final Problem problem;
    private int populationSize;
    private final List<EvolutionaryOperator> operators;
    private FitnessEvaluator fitnessEvaluator;
    private SurvivalOperator survivalOperator;
    private int numGenerations;

    public GeneticAlgorithm(Problem problem, int populationSize, int numGenerations) {
        this.problem = problem;
        this.populationSize = populationSize;
        this.operators = new ArrayList<>();
        this.numGenerations = numGenerations;
    }

    public void setFitnessEvaluator(FitnessEvaluator fitnessEvaluator) {
        this.fitnessEvaluator = fitnessEvaluator;
    }

    public void setSurvivalOperator(SurvivalOperator survivalOperator) {
        this.survivalOperator = survivalOperator;
    }

    public void addEvolutionaryOperator(EvolutionaryOperator operator) {
        operators.add(operator);
    }

    public List<Solution> run() {
        List<Solution> population = new ArrayList<>();
        try {
            for (int i = 0; i < populationSize; i++) {
                population.add(problem.createNewSolution());
            }
        } catch (NoSolutionException e) {
            e.printStackTrace();
        }

        fitnessEvaluator.evaluate(population);
        for (int i = 0; i < numGenerations; i++) {
            EvolutionaryOperator operator = operators.get(ThreadLocalRandom.current().nextInt(operators.size()));
            try {
                List<Solution> newPopulation = new ArrayList<>();
                for (int j = 0; j < populationSize; j++) {
                    newPopulation.add(operator.evolve(population.get(j)));
                }
                fitnessEvaluator.evaluate(newPopulation);
                population.addAll(newPopulation);
                population = survivalOperator.selectPopulation(newPopulation, populationSize);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return population;
    }

}
