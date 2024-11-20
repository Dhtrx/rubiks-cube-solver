package model.cubes.threeXThreeCube.solving.genetic.operators;

import model.cubes.threeXThreeCube.solving.genetic.model.Solution;

import java.util.List;

public interface SelectionOperator {
    public Solution selectParent(List<Solution> candidates);
}
