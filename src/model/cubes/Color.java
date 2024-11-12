package model.cubes;

import java.util.List;

public enum Color {
    WHITE(0, List.of(1f, 1f, 1f)),
    YELLOW(5, List.of(1f, 1f, 0f)),
    BLUE(4, List.of(0f, 0f, 1f)),
    RED(1, List.of(1f, 0f, 0f)),
    GREEN(3, List.of(0f, 1f, 0f)),
    ORANGE(2, List.of(1f, .5f, 0f)),
    BLACK(6, List.of(0f, 0f, 0f));

    public final int num;
    public final List<Float> rgb;

    Color(int num, List<Float> rgb) {
        this.num = num;
        this.rgb = rgb;
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

        return null;
    }
}
