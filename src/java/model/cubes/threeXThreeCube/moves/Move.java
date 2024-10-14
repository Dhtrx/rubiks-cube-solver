package model.cubes.threeXThreeCube.moves;

import model.Utils;
import model.cubes.Cube;
import model.cubes.threeXThreeCube.Face;
import model.cubes.threeXThreeCube.ThreeCube;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
     * Performs the FR move on a {@link ThreeCube}
     * @param threeCube The cube.
     */
    public static void frontRight(ThreeCube threeCube) {
        int[][][] cube = threeCube.getCube();
        int face = Face.FRONT.num;

        rotateFaceRight(face, cube);
        rotateByFrontOrBackAffectedFacesRight(cube, true);
    }

    private static void rotateByFrontOrBackAffectedFacesRight(int[][][] cube, boolean front) {
        int n = front? 2 : 0;
        int m = front? 0 : 2;
        //Save last or first row of top face
        var tmp = cube[Face.TOP.num][n];

        //Rotate front- or back-face bordering layers clockwise
        //New Top Face
        rotateTopOrBottomAffectedByFrontOrBackRight(cube, true, front);

        //New Left Face
        for (int i = 0; i < 3; i++) {
            cube[Face.LEFT.num][i][n] = cube[Face.BOTTOM.num][m][i];
        }

        //New Bottom Face
        rotateTopOrBottomAffectedByFrontOrBackRight(cube, false, front);

        //New Right Face
        for (int i = 0; i < tmp.length; i++) {
            cube[Face.RIGHT.num][i][m] = tmp[i];
        }
    }

    private static void rotateTopOrBottomAffectedByFrontOrBackRight(int[][][] cube, boolean top, boolean front) {
        int n = top ^ front? 0 : 2;
        int face = top? Face.LEFT.num : Face.RIGHT.num;

        var newFace = new int[3];
        for (int i = 0; i < newFace.length; i++) {
            newFace[i] = cube[face][i][n];
        }
        cube[top? Face.TOP.num : Face.BOTTOM.num][n] = Utils.reverseIntArray(newFace);
    }

    /**
     * Performs the BR move on a {@link ThreeCube}
     * @param threeCube The cube.
     */
    public static void backRight(ThreeCube threeCube) {
        int[][][] cube = threeCube.getCube();
        int face = Face.BACK.num;

        rotateFaceRight(face, cube);
        rotateByFrontOrBackAffectedFacesRight(cube, false);
    }

    /**
     * Performs the TR move on a {@link ThreeCube}
     * @param threeCube The cube.
     */
    public static void topRight(ThreeCube threeCube) {
        int[][][] cube = threeCube.getCube();
    }
}
