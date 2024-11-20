package model.cubes.threeXThreeCube.solving.genetic.operators;

import model.cubes.threeXThreeCube.ThreeCube;
import model.cubes.threeXThreeCube.solving.genetic.model.Solution;
import model.cubes.threeXThreeCube.solving.genetic.model.SolvedThreeCube;

import java.util.List;

public class ThreeCubeFitnessEvaluator implements FitnessEvaluator {
    @Override
    public void evaluate(List<Solution> population) {
        for (Solution solution : population) {
            solution.setFitness(fitnessForCube((ThreeCube) solution.getProblem()));
        }
    }

    private double fitnessForCube(ThreeCube threeCube) {
        double fitness = 0;
        int[][][] cube = threeCube.getCube();
        for (int i = 0; i < cube.length; i++) {
            for (int j = 0; j < cube[i].length; j++) {
                for (int k = 0; k < cube[i][j].length; k++) {
                    if (cube[i][j][k] == SolvedThreeCube.SOLVED.getCube()[i][j][k]) {
                        fitness++;
                    }
                }
            }
        }
        return fitness;
    }
}
