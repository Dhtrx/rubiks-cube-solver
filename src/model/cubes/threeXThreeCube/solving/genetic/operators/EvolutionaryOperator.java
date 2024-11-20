package model.cubes.threeXThreeCube.solving.genetic.operators;

import model.cubes.threeXThreeCube.solving.genetic.model.Solution;

public interface EvolutionaryOperator {
	public Solution evolve(Solution solution)
			throws EvolutionException;
}
