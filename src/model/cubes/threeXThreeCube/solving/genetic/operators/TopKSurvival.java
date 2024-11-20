package model.cubes.threeXThreeCube.solving.genetic.operators;

import model.cubes.threeXThreeCube.solving.genetic.model.Solution;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TopKSurvival implements SurvivalOperator {
    private int k;

    public TopKSurvival(int k) {
        this.k = k;
    }

    @Override
    public List<Solution> selectPopulation(List<Solution> candidates, int populationSize) throws SurvivalException {
        List<Solution> newPopulation = new ArrayList<>();
        if (k > populationSize) {
            throw new SurvivalException("Population size exceeded");
        }
        candidates.sort(Comparator.comparing(Solution::getFitness).reversed());

        for (int i = 0; i < k; i++) {
            newPopulation.add(candidates.removeFirst());
        }
        if (k < populationSize) {
            while (newPopulation.size() < populationSize) {
                newPopulation.add(candidates.remove(ThreadLocalRandom.current().nextInt(candidates.size())));
            }
        }
        return newPopulation;
    }
}
