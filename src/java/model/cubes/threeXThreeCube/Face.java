package model.cubes.threeXThreeCube;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Face {

    FRONT(1, 5),
    BACK(2, 1),
    RIGHT(3, 4),
    LEFT(4, 3),
    TOP(5, 0),
    BOTTOM(0, 5);

    public final int num;
    final int opposite;

    Face(int num, int opposite) {
        this.num = num;
        this.opposite = opposite;
    }

    public List<Face> getBorderingFaces() {
        return Arrays.stream(Face.values()).filter(face -> face.num != opposite && face.num != num).collect(Collectors.toList());
    }

}
