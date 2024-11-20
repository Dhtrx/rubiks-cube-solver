package model.cubes.threeXThreeCube.solving.genetic.operators;

import model.cubes.threeXThreeCube.solving.genetic.model.Solution;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TournamentSelection implements SelectionOperator{

    @Override
    public Solution selectParent(List<Solution> candidates) {
        Solution candidate1 = candidates.get(ThreadLocalRandom.current().nextInt(candidates.size()));
        Solution candidate2 = candidates.get(ThreadLocalRandom.current().nextInt(candidates.size()));

        return candidate1.getFitness() >= candidate2.getFitness() ? candidate1 : candidate2;
    }
}
