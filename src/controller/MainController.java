package controller;

import model.cubes.Color;
import model.cubes.threeXThreeCube.ThreeCube;
import model.cubes.threeXThreeCube.solving.genetic.GeneticAlgorithm;
import model.cubes.threeXThreeCube.solving.genetic.model.Solution;
import model.cubes.threeXThreeCube.solving.genetic.operators.ThreeCubeFitnessEvaluator;
import model.cubes.threeXThreeCube.solving.genetic.operators.ThreeCubeMutation;
import model.cubes.threeXThreeCube.solving.genetic.operators.TopKSurvival;

public class MainController {

    public static void main(String[] args) {
        GeneticAlgorithm algorithm = new GeneticAlgorithm(new ThreeCube(
                new Color[][]{
                        {Color.RED, Color.YELLOW, Color.ORANGE},
                        {Color.ORANGE, Color.WHITE, Color.GREEN},
                        {Color.BLUE, Color.YELLOW, Color.YELLOW}
                },
                new Color[][]{
                        {Color.BLUE, Color.ORANGE, Color.ORANGE},
                        {Color.GREEN, Color.YELLOW, Color.BLUE},
                        {Color.WHITE, Color.ORANGE, Color.BLUE}
                },
                new Color[][]{
                        {Color.RED, Color.RED, Color.BLUE},
                        {Color.BLUE, Color.GREEN, Color.YELLOW},
                        {Color.YELLOW, Color.WHITE, Color.GREEN}
                },
                new Color[][]{
                        {Color.YELLOW, Color.RED, Color.ORANGE},
                        {Color.RED, Color.BLUE, Color.YELLOW},
                        {Color.ORANGE, Color.GREEN, Color.GREEN}
                },
                new Color[][]{
                        {Color.GREEN, Color.WHITE, Color.WHITE},
                        {Color.RED, Color.RED, Color.WHITE},
                        {Color.WHITE, Color.ORANGE, Color.GREEN}
                },
                new Color[][]{
                        {Color.YELLOW, Color.BLUE, Color.RED},
                        {Color.BLUE, Color.ORANGE, Color.WHITE},
                        {Color.RED, Color.GREEN, Color.WHITE}
                }
        ), 100, 1000);

        algorithm.addEvolutionaryOperator(new ThreeCubeMutation());
        algorithm.setFitnessEvaluator(new ThreeCubeFitnessEvaluator());
        algorithm.setSurvivalOperator(new TopKSurvival(20));

        for (Solution solution: algorithm.run()) {
            System.out.println(solution.getProblem().toString());
        }
    }

}
