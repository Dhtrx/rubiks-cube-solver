package model.cubes.threeXThreeCube.solving.genetic.operators;

import model.cubes.threeXThreeCube.solving.genetic.model.Solution;

import java.util.List;

public interface SurvivalOperator {
	public List<Solution> selectPopulation(List<Solution> candidates, int populationSize)
			throws SurvivalException;
}
