package model.cubes.threeXThreeCube.solving.genetic.operators;

import model.cubes.threeXThreeCube.ThreeCube;
import model.cubes.threeXThreeCube.moves.Move;
import model.cubes.threeXThreeCube.solving.genetic.model.Problem;
import model.cubes.threeXThreeCube.solving.genetic.model.Solution;
import model.cubes.threeXThreeCube.solving.genetic.model.SolvedThreeCube;

import java.util.concurrent.ThreadLocalRandom;

public class ThreeCubeMutation implements EvolutionaryOperator{

    @Override
    public Solution evolve(Solution solution) throws EvolutionException {
        Solution ret = new SolvedThreeCube(solution);

        for (int i = 0; i < 10; i++) {
            Move move = Move.values()[ThreadLocalRandom.current().nextInt(Move.values().length)];
            ((ThreeCube) ret.getProblem()).rotate(move);
            ((SolvedThreeCube) solution).getMoves().add(move);
            if (ret.getProblem().equals(SolvedThreeCube.SOLVED)) {
                return ret;
            }
        }

        return ret;
    }
}
