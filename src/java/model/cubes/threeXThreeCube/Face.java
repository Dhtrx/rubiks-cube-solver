package model.cubes.threeXThreeCube;

public enum Face {

    FRONT(1),
    BACK(2),
    RIGHT(3),
    LEFT(4),
    TOP(5),
    BOTTOM(0);

    final int num;

    Face(int num) {
        this.num = num;
    }

}
