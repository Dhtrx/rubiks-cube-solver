package model.cubes.threeXThreeCube;

import model.cubes.Color;
import model.cubes.Cube;
import model.cubes.threeXThreeCube.moves.Move;

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
        }


    }

    @Override
    public void rotateLeft(Face face) {
        int faceNum = face.num;

        switch (faceNum) {
            case 0 -> Move.bottomLeft(this);
            case 1 -> Move.frontLeft(this);
            case 2 -> Move.backLeft(this);
            case 5 -> Move.topLeft(this);
            default -> throw new IllegalArgumentException(STR."Cannot process value \{faceNum}");
        }
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

    @Override
    public Color[][] toArrayForAnimation() {
        Color[][] ret = new Color[27][6];

        //Left side
        ret[0] = new Color[]{Color.BLACK, Color.fromInt(cube[Face.BACK.num][2][2]), Color.fromInt(cube[Face.LEFT.num][2][0]), Color.BLACK, Color.BLACK, Color.fromInt(cube[Face.BOTTOM.num][2][0])};
        ret[1] = new Color[]{Color.BLACK, Color.BLACK, Color.fromInt(cube[Face.LEFT.num][2][1]), Color.BLACK, Color.BLACK, Color.fromInt(cube[Face.BOTTOM.num][1][0])};
        ret[2] = new Color[]{Color.fromInt(cube[Face.FRONT.num][2][0]), Color.BLACK, Color.fromInt(cube[Face.LEFT.num][2][2]), Color.BLACK, Color.BLACK, Color.fromInt(cube[Face.BOTTOM.num][0][0])};

        ret[3] = new Color[]{Color.BLACK, Color.fromInt(cube[Face.BACK.num][1][2]), Color.fromInt(cube[Face.LEFT.num][1][0]), Color.BLACK, Color.BLACK, Color.BLACK};
        ret[4] = new Color[]{Color.BLACK, Color.BLACK, Color.fromInt(cube[Face.LEFT.num][1][1]), Color.BLACK, Color.BLACK, Color.BLACK};
        ret[5] = new Color[]{Color.fromInt(cube[Face.FRONT.num][1][0]), Color.BLACK, Color.fromInt(cube[Face.LEFT.num][1][2]), Color.BLACK, Color.BLACK, Color.BLACK};

        ret[6] = new Color[]{Color.BLACK, Color.fromInt(cube[Face.BACK.num][0][2]), Color.fromInt(cube[Face.LEFT.num][0][0]), Color.BLACK, Color.fromInt(cube[Face.TOP.num][0][0]), Color.BLACK};
        ret[7] = new Color[]{Color.BLACK, Color.BLACK, Color.fromInt(cube[Face.LEFT.num][0][1]), Color.BLACK, Color.fromInt(cube[Face.TOP.num][1][0]), Color.BLACK};
        ret[8] = new Color[]{Color.fromInt(cube[Face.FRONT.num][0][0]), Color.BLACK, Color.fromInt(cube[Face.LEFT.num][0][2]), Color.BLACK, Color.fromInt(cube[Face.TOP.num][2][0]), Color.BLACK};

        //Middle
        ret[9] = new Color[]{Color.BLACK, Color.fromInt(cube[Face.BACK.num][2][1]), Color.BLACK, Color.BLACK, Color.BLACK, Color.fromInt(cube[Face.BOTTOM.num][2][1])};
        ret[10] =new Color[]{Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.fromInt(cube[Face.BOTTOM.num][1][1])};
        ret[11] =new Color[]{Color.fromInt(cube[Face.FRONT.num][2][1]), Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.fromInt(cube[Face.BOTTOM.num][0][1])};

        ret[12] =new Color[]{Color.BLACK, Color.fromInt(cube[Face.BACK.num][1][1]), Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK};
        ret[13] =new Color[]{Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK};
        ret[14] =new Color[]{Color.fromInt(cube[Face.FRONT.num][1][1]), Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK};

        ret[15] =new Color[]{Color.BLACK, Color.fromInt(cube[Face.BACK.num][0][1]), Color.BLACK, Color.BLACK, Color.fromInt(cube[Face.TOP.num][0][1]), Color.BLACK};
        ret[16] =new Color[]{Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.fromInt(cube[Face.TOP.num][1][1]), Color.BLACK};
        ret[17] =new Color[]{Color.fromInt(cube[Face.FRONT.num][0][1]), Color.BLACK, Color.BLACK, Color.BLACK, Color.fromInt(cube[Face.TOP.num][2][1]), Color.BLACK};

        //Right side
        ret[18] =new Color[]{Color.BLACK, Color.fromInt(cube[Face.BACK.num][2][0]), Color.BLACK, Color.fromInt(cube[Face.RIGHT.num][2][2]), Color.BLACK, Color.fromInt(cube[Face.BOTTOM.num][2][2])};
        ret[19] =new Color[]{Color.BLACK, Color.BLACK, Color.BLACK, Color.fromInt(cube[Face.RIGHT.num][2][1]), Color.BLACK, Color.fromInt(cube[Face.BOTTOM.num][1][2])};
        ret[20] =new Color[]{Color.fromInt(cube[Face.FRONT.num][2][2]), Color.BLACK, Color.BLACK, Color.fromInt(cube[Face.RIGHT.num][2][0]), Color.BLACK, Color.fromInt(cube[Face.BOTTOM.num][0][2])};

        ret[21] =new Color[]{Color.BLACK, Color.fromInt(cube[Face.BACK.num][1][0]), Color.BLACK, Color.fromInt(cube[Face.RIGHT.num][1][2]), Color.BLACK, Color.BLACK};
        ret[22] =new Color[]{Color.BLACK, Color.BLACK, Color.BLACK, Color.fromInt(cube[Face.RIGHT.num][1][1]), Color.BLACK, Color.BLACK};
        ret[23] =new Color[]{Color.fromInt(cube[Face.FRONT.num][1][2]), Color.BLACK, Color.BLACK, Color.fromInt(cube[Face.RIGHT.num][1][0]), Color.BLACK, Color.BLACK};

        ret[24] =new Color[]{Color.BLACK, Color.fromInt(cube[Face.BACK.num][0][0]), Color.BLACK, Color.fromInt(cube[Face.RIGHT.num][0][2]), Color.fromInt(cube[Face.TOP.num][0][2]), Color.BLACK};
        ret[25] =new Color[]{Color.BLACK, Color.BLACK, Color.BLACK, Color.fromInt(cube[Face.RIGHT.num][0][1]), Color.fromInt(cube[Face.TOP.num][1][2]), Color.BLACK};
        ret[26] =new Color[]{Color.fromInt(cube[Face.FRONT.num][0][2]), Color.BLACK, Color.BLACK, Color.fromInt(cube[Face.RIGHT.num][0][0]), Color.fromInt(cube[Face.TOP.num][2][2]), Color.BLACK};

        return ret;
    }


}
