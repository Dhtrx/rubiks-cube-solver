package model.cubes.threeXThreeCube.moves;

import model.cubes.Cube;
import model.cubes.threeXThreeCube.Face;
import model.cubes.threeXThreeCube.ThreeCube;

import java.util.List;
import java.util.stream.Collectors;

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
        int face = Face.BOTTOM.num;

        rotateFaceRight(face, cube);
        rotateByBottomAffectedFacesRight(cube);

    }

    private static void rotateByBottomAffectedFacesRight(int[][][] cube) {
        //Save one of the affected Faces last row
        var tmp = cube[Face.FRONT.num][2];

        //Rotate the affected Faces last rows clockwise
        cube[Face.FRONT.num][2] = cube[Face.LEFT.num][2];
        cube[Face.LEFT.num][2] = cube[Face.BACK.num][2];
        cube[Face.BACK.num][2] = cube[Face.RIGHT.num][2];
        cube[Face.RIGHT.num][2] = tmp;

    }

    /**
     * Rotates only the focussed {@link Face} of a move. Ignoring the other faces that change thereby.
     * @param face The face to be rotated
     * @param cube The cube to perform the rotation on
     */
    private static void rotateFaceRight(int face, int[][][] cube) {
        //Safe top row of face except for the most left stone.
        var tmp1 = cube[face][0][1];
        var tmp2 = cube[face][0][2];

        //Most left stone of first row becomes most right stone of first row
        cube[face][0][2] = cube[face][0][0];
        //rest of left column becomes first row
        cube[face][0][0] = cube[face][2][0];
        cube[face][0][1] = cube[face][1][0];

        //last row becomes left column
        cube[face][2][0] = cube[face][2][2];
        cube[face][1][0] = cube[face][2][1];

        //right column becomes last row
        cube[face][2][1] = cube[face][1][2];
        cube[face][2][2] = tmp2;

        //first row becomes right column
        cube[face][1][2] = tmp1;
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
