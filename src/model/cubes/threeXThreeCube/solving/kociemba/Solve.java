package model.cubes.threeXThreeCube.solving.kociemba;

import model.cubes.Color;
import model.cubes.threeXThreeCube.Face;
import model.cubes.threeXThreeCube.ThreeCube;
import model.cubes.threeXThreeCube.moves.Move;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solve {

    public static List<Move> solveThreeCube(ThreeCube cube) {
        String stringRep = getStringRepresentation(cube);
        try {
            ProcessBuilder builder = new ProcessBuilder("python", "python/solve_cube.py", stringRep);
            builder.directory(new File("."));
            Process process = builder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String result = reader.readLine();
            return getMyMoves(result);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getStringRepresentation(ThreeCube cube) {
        int[][][] arr = cube.getCube();
        StringBuilder builder = new StringBuilder();

        builder.append(getFace(arr, Face.TOP))
                .append(getFace(arr, Face.RIGHT))
                .append(getFace(arr, Face.FRONT))
                .append(getFace(arr, Face.BOTTOM))
                .append(getFace(arr, Face.LEFT))
                .append(getFace(arr, Face.BACK));

        return builder.toString();
    }

    private static StringBuilder getFace(int[][][] arr, Face face) {
        int[][] faceArr = arr[face.num];
        StringBuilder builder = new StringBuilder();
        for (int[] ints : faceArr) {
            for (int anInt : ints) {
                builder.append(Color.fromInt(anInt).symbol);
            }
        }
        return builder;
    }

    private static List<Move> getMyMoves(String moves) {
        String[] kociembaMoveArray = moves.split(" ");
        List<Move> myMoves = new ArrayList<>();

        for (String move: kociembaMoveArray) {
            if (move.contains("2")) {
                String moveChar = String.valueOf(move.charAt(0));
                myMoves.add(Move.fromKociemba(moveChar));
                myMoves.add(Move.fromKociemba(moveChar));
            } else {
                myMoves.add(Move.fromKociemba(move));
            }
        }

        return myMoves;
    }

}
