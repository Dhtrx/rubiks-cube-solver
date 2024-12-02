package model.cubes;

import java.util.List;

public enum Color {
    WHITE(0, List.of(1f, 1f, 1f), 'D'),
    YELLOW(5, List.of(1f, 1f, 0f), 'U'),
    BLUE(4, List.of(0f, 0f, 1f), 'L'),
    RED(1, List.of(1f, 0f, 0f), 'F'),
    GREEN(3, List.of(0f, 1f, 0f), 'R'),
    ORANGE(2, List.of(1f, .5f, 0f), 'B'),
    BLACK(6, List.of(0f, 0f, 0f), 'E');

    public final int num;
    public final List<Float> rgb;
    public char symbol;

    Color(int num, List<Float> rgb, char symbol) {
        this.num = num;
        this.rgb = rgb;
        this.symbol = symbol;
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

    public static Color fromInt(int num) {
        for (Color color: values()) {
            if (color.num == num) {
                return color;
            }
        }

        return BLACK;
    }
}
