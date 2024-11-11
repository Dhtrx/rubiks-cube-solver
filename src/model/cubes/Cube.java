package model.cubes;

import model.cubes.threeXThreeCube.Face;
import model.cubes.threeXThreeCube.moves.Move;
import view.Renderer;

public interface Cube {

    /**
     * Rotates one {@link Face} of the cube with a given {@link Move}
     * @param move The move to be performed
     */
    void rotate(Move move);

    /**
     * Rotates one of the following {@link Face} to the right:
     * <ul>
     *     <li>TOP (5)</li>
     *     <li>BOTTOM(0)</li>
     *     <li>FRONT(1)</li>
     *     <li>BACK(2)</li>
     * </ul>
     *
     * @param face The {@link Face} to be rotated
     */
    void rotateRight(Face face);

    /**
     * Rotates one of the following {@link Face} to the left:
     * <ul>
     *     <li>TOP(5)</li>
     *     <li>BOTTOM(0)</li>
     *     <li>FRONT(1)</li>
     *     <li>BACK(2)</li>
     * </ul>
     *
     * @param face The {@link Face} to be rotated
     */
    void rotateLeft(Face face);

    /**
     * Rotates one of the following {@link Face} up:
     * <ul>
     *     <li>RIGHT(3)</li>
     *     <li>LEFT(4)</li>
     * </ul>
     *
     * @param face The {@link Face} to be rotated
     */
    void rotateUp(Face face);

    /**
     * Rotates one of the following {@link Face} up:
     * <ul>
     *     <li>RIGHT(3)</li>
     *     <li>LEFT(4)</li>
     * </ul>
     *
     * @param face The {@link Face} to be rotated
     */
    void rotateDown(Face face);

    /**
     * Translates a {@link Cube} Object from its inner multidimensional Array representation to a 27x6 Array containing
     * color information for every side of every cube for visualization purposes {@link Renderer}
     * @return
     */
    Color[][] toArrayForAnimation();


}
