package model.cubes.threeXThreeCube.solving.genetic.model;

import model.cubes.Color;
import model.cubes.threeXThreeCube.ThreeCube;
import model.cubes.threeXThreeCube.moves.Move;

import java.util.ArrayList;
import java.util.List;

public class SolvedThreeCube extends Solution {

    public static final ThreeCube SOLVED = new ThreeCube(
            new Color[][]{
                    {Color.WHITE, Color.WHITE, Color.WHITE},
                    {Color.WHITE, Color.WHITE, Color.WHITE},
                    {Color.WHITE, Color.WHITE, Color.WHITE}
            },
            new Color[][]{
                    {Color.YELLOW, Color.YELLOW, Color.YELLOW},
                    {Color.YELLOW, Color.YELLOW, Color.YELLOW},
                    {Color.YELLOW, Color.YELLOW, Color.YELLOW}
            },
            new Color[][]{
                    {Color.GREEN, Color.GREEN, Color.GREEN},
                    {Color.GREEN, Color.GREEN, Color.GREEN},
                    {Color.GREEN, Color.GREEN, Color.GREEN}
            },
            new Color[][]{
                    {Color.BLUE, Color.BLUE, Color.BLUE},
                    {Color.BLUE, Color.BLUE, Color.BLUE},
                    {Color.BLUE, Color.BLUE, Color.BLUE}
            },
            new Color[][]{
                    {Color.RED, Color.RED, Color.RED},
                    {Color.RED, Color.RED, Color.RED},
                    {Color.RED, Color.RED, Color.RED}
            },
            new Color[][]{
                    {Color.ORANGE, Color.ORANGE, Color.ORANGE},
                    {Color.ORANGE, Color.ORANGE, Color.ORANGE},
                    {Color.ORANGE, Color.ORANGE, Color.ORANGE}
            }
    );

    private final List<Move> moves;

    public SolvedThreeCube(Problem problem) {
        super(new ThreeCube((ThreeCube) problem));
        moves = new ArrayList<>();
    }

    public SolvedThreeCube(Solution solution) {
        super(solution);
        moves = new ArrayList<>(((SolvedThreeCube) solution).moves);
    }

    public List<Move> getMoves() {
        return moves;
    }
}
