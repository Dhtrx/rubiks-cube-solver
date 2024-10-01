package model.cubes.threeXThreeCube.moves;

import model.cubes.Cube;
import model.cubes.threeXThreeCube.ThreeCube;

public enum Move {
    TR,
    TL,
    DR,
    DL,
    FR,
    FL,
    BL,
    BR,
    RU,
    RD,
    LU,
    LD;

    /**
     * Performs the DR move on a {@link ThreeCube}
     * @param threeCube The cube.
     */
    public static void bottomRight(ThreeCube threeCube) {
        int[][][] cube = threeCube.getCube();
    }

    /**
     * Performs the FR move on a {@link ThreeCube}
     * @param threeCube The cube.
     */
    public static void frontRight(ThreeCube threeCube) {
        int[][][] cube = threeCube.getCube();
    }

    /**
     * Performs the BR move on a {@link ThreeCube}
     * @param threeCube The cube.
     */
    public static void backRight(ThreeCube threeCube) {
        int[][][] cube = threeCube.getCube();
    }

    /**
     * Performs the TR move on a {@link ThreeCube}
     * @param threeCube The cube.
     */
    public static void topRight(ThreeCube threeCube) {
        int[][][] cube = threeCube.getCube();
    }
}
