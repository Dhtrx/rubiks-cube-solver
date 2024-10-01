package model.cubes.threeXThreeCube;

import model.cubes.Color;
import model.cubes.Cube;
import model.cubes.threeXThreeCube.moves.Move;

import java.util.Arrays;

public class ThreeCube implements Cube {

    private final int[][][] cube;

    public ThreeCube(Color[][] bottomFace,
                     Color[][] topFace,
                     Color[][] rightFace,
                     Color[][] leftFace,
                     Color[][] frontFace,
                     Color[][] backFace) {
        this.cube = new int[6][3][3];
        initCubeFaces(bottomFace, topFace, rightFace, leftFace, frontFace, backFace);
    }

    private void initCubeFaces(Color[][] bottomFace,
                               Color[][] topFace,
                               Color[][] rightFace,
                               Color[][] leftFace,
                               Color[][] frontFace,
                               Color[][] backFace) {
        initBottomFace(bottomFace);
        initTopFace(topFace);
        initRightFace(rightFace);
        initLeftFace(leftFace);
        initFrontFace(frontFace);
        initBackFace(backFace);
    }

    private void initBackFace(Color[][] backFace) {
        this.cube[Face.BACK.num] = Color.asInts(backFace);
    }

    private void initFrontFace(Color[][] frontFace) {
        this.cube[Face.FRONT.num] = Color.asInts(frontFace);
    }

    private void initLeftFace(Color[][] leftFace) {
        this.cube[Face.LEFT.num] = Color.asInts(leftFace);
    }

    private void initRightFace(Color[][] rightFace) {
        this.cube[Face.RIGHT.num] = Color.asInts(rightFace);
    }

    private void initTopFace(Color[][] topFace) {
        this.cube[Face.TOP.num] = Color.asInts(topFace);
    }

    private void initBottomFace(Color[][] bottomFace) {
        this.cube[Face.BOTTOM.num] = Color.asInts(bottomFace);
    }

    @Override
    public void rotate(Move move) {
        switch (move) {
            case BL -> rotateLeft(Face.BACK);
            case BR -> rotateRight(Face.BACK);
            case DL -> rotateLeft(Face.BOTTOM);
            case DR -> rotateRight(Face.BOTTOM);
            case FL -> rotateLeft(Face.FRONT);
            case FR -> rotateRight(Face.FRONT);
            case LD -> rotateDown(Face.LEFT);
            case LU -> rotateUp(Face.LEFT);
            case RD -> rotateDown(Face.RIGHT);
            case RU -> rotateUp(Face.RIGHT);
            case TL -> rotateLeft(Face.TOP);
            case TR -> rotateRight(Face.TOP);
            default -> throw new IllegalArgumentException(STR."Cannot process value \{move}");
        }

    }

    @Override
    public void rotateRight(Face face) {
        int faceNum = face.num;

        switch (faceNum) {
            case 0 -> Move.bottomRight(this);
            case 1 -> Move.frontRight(this);
            case 2 -> Move.backRight(this);
            case 5 -> Move.topRight(this);
            default -> throw new IllegalArgumentException(STR."Cannot process value \{faceNum}");
        };


    }

    @Override
    public void rotateLeft(Face face) {

    }

    @Override
    public void rotateUp(Face face) {

    }

    @Override
    public void rotateDown(Face face) {

    }

    public int[][][] getCube() {
        return cube;
    }
}
