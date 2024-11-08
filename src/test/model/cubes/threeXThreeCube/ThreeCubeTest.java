package model.cubes.threeXThreeCube;

import model.cubes.Color;
import model.cubes.threeXThreeCube.moves.Move;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ThreeCubeTest {

    ThreeCube threeCube;

    @BeforeEach
    void setUp() {
        threeCube = new ThreeCube(
                new Color[][]{
                        {Color.RED, Color.YELLOW, Color.ORANGE},
                        {Color.ORANGE, Color.WHITE, Color.GREEN},
                        {Color.BLUE, Color.YELLOW, Color.YELLOW}
                },
                new Color[][]{
                        {Color.BLUE, Color.ORANGE, Color.ORANGE},
                        {Color.GREEN, Color.YELLOW, Color.BLUE},
                        {Color.WHITE, Color.ORANGE, Color.BLUE}
                },
                new Color[][]{
                        {Color.RED, Color.RED, Color.BLUE},
                        {Color.BLUE, Color.GREEN, Color.YELLOW},
                        {Color.YELLOW, Color.WHITE, Color.GREEN}
                },
                new Color[][]{
                        {Color.YELLOW, Color.RED, Color.ORANGE},
                        {Color.RED, Color.BLUE, Color.YELLOW},
                        {Color.ORANGE, Color.GREEN, Color.GREEN}
                },
                new Color[][]{
                        {Color.GREEN, Color.WHITE, Color.WHITE},
                        {Color.RED, Color.RED, Color.WHITE},
                        {Color.WHITE, Color.ORANGE, Color.GREEN}
                },
                new Color[][]{
                        {Color.YELLOW, Color.BLUE, Color.RED},
                        {Color.BLUE, Color.ORANGE, Color.WHITE},
                        {Color.RED, Color.GREEN, Color.WHITE}
                }
        );
    }

    @Test
    void rotateRightDR() {
        threeCube.rotate(Move.DR);

        assertArrayEquals(new int[][]{
                {Color.BLUE.num, Color.ORANGE.num, Color.RED.num},
                {Color.YELLOW.num, Color.WHITE.num, Color.YELLOW.num},
                {Color.YELLOW.num, Color.GREEN.num, Color.ORANGE.num}
        }, threeCube.getCube()[Face.BOTTOM.num]);

        //assert new Front Face
        assertArrayEquals(new int[]{Color.ORANGE.num, Color.GREEN.num, Color.GREEN.num}, threeCube.getCube()[Face.FRONT.num][2]);
        //assert new Left Face
        assertArrayEquals(new int[]{Color.RED.num, Color.GREEN.num, Color.WHITE.num}, threeCube.getCube()[Face.LEFT.num][2]);
        //assert new Back Face
        assertArrayEquals(new int[]{Color.YELLOW.num, Color.WHITE.num, Color.GREEN.num}, threeCube.getCube()[Face.BACK.num][2]);
        //assert new Right Face
        assertArrayEquals(new int[]{Color.WHITE.num, Color.ORANGE.num, Color.GREEN.num}, threeCube.getCube()[Face.RIGHT.num][2]);
    }

    @Test
    void rotateRightFR() {
        var cube = threeCube.getCube();
        threeCube.rotate(Move.FR);
        assertArrayEquals(new int[][]{
                {Color.WHITE.num, Color.RED.num, Color.GREEN.num},
                {Color.ORANGE.num, Color.RED.num, Color.WHITE.num},
                {Color.GREEN.num, Color.WHITE.num, Color.WHITE.num}
        }, threeCube.getCube()[Face.FRONT.num]);

        //assert new Top Face
        assertArrayEquals(new int[]{Color.GREEN.num, Color.YELLOW.num, Color.ORANGE.num}, cube[Face.TOP.num][2]);
        //assert new Bottom Face
        assertArrayEquals(new int[]{Color.YELLOW.num, Color.BLUE.num, Color.RED.num}, cube[Face.BOTTOM.num][0]);
        //assert new Right Face
        var newRight = new int[3];
        for (int i = 0; i < newRight.length; i++) {
            newRight[i] = cube[Face.RIGHT.num][i][0];
        }
        assertArrayEquals(new int[]{Color.WHITE.num, Color.ORANGE.num, Color.BLUE.num}, newRight);
        //assert new Left Face
        var newLeft = new int[3];
        for (int i = 0; i < newLeft.length; i++) {
            newLeft[i] = cube[Face.LEFT.num][i][2];
        }
        assertArrayEquals(new int[]{Color.RED.num, Color.YELLOW.num, Color.ORANGE.num}, newLeft);

    }

    @Test
    void rotateLeft() {
    }

    @Test
    void rotateUp() {
    }

    @Test
    void rotateDown() {
    }
}