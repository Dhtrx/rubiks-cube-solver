package controller;

import model.cubes.Color;
import model.cubes.Cube;
import model.cubes.threeXThreeCube.ThreeCube;
import model.cubes.threeXThreeCube.solving.genetic.GeneticAlgorithm;
import model.cubes.threeXThreeCube.solving.genetic.model.Solution;
import model.cubes.threeXThreeCube.solving.genetic.model.SolvedThreeCube;
import model.cubes.threeXThreeCube.solving.genetic.operators.ThreeCubeFitnessEvaluator;
import model.cubes.threeXThreeCube.solving.genetic.operators.ThreeCubeMutation;
import model.cubes.threeXThreeCube.solving.genetic.operators.TopKSurvival;
import model.cubes.threeXThreeCube.solving.kociemba.Solve;

import java.util.Comparator;
import java.util.List;

public class MainController {

    public static void main(String[] args) {
        ThreeCube cube = new ThreeCube(
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
        );
        /*
        algorithm.addEvolutionaryOperator(new ThreeCubeMutation());
        algorithm.setFitnessEvaluator(new ThreeCubeFitnessEvaluator());
        algorithm.setSurvivalOperator(new TopKSurvival(20));

        List<Solution> solutions = algorithm.run();

        solutions.sort(Comparator.comparing(Solution::getFitness).reversed());
        System.out.println(STR."\{solutions.get(0).getProblem()} \{solutions.get(0).getFitness()}");

         */
        Solve.solveThreeCube(cube);
    }

}
