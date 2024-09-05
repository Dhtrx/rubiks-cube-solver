package model.cubes;

import model.cubes.threeXThreeCube.Face;
import model.cubes.threeXThreeCube.moves.Move;

public interface Cube {

    void rotate(Move move, Face face);

    void rotateRight(Face face);

    void rotateLeft(Face face);

    void rotateBack(Face face);

    void rotateFront(Face face);


}
