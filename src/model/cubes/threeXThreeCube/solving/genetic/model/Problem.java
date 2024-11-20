package model.cubes.threeXThreeCube.solving.genetic.model;

public interface Problem {
	Solution createNewSolution() throws NoSolutionException;
}
