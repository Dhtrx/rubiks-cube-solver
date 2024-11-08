package model.cubes;

public enum Color {
    WHITE(0),
    YELLOW(5),
    BLUE(4),
    RED(1),
    GREEN(3),
    ORANGE(2);

    public final int num;

    Color(int num) {
        this.num = num;
    }

    public static int[][] asInts(Color[][] face) {
        int[][] ret = new int[face.length][face[0].length];

        for (int i = 0; i < ret.length; i++) {
            for (int j = 0; j < ret[i].length; j++) {
                ret[i][j] = face[i][j].num;
            }
        }

        return ret;
    }
}
