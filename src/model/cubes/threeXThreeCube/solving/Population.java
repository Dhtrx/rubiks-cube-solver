package model.cubes.threeXThreeCube.solving;

import model.cubes.Cube;

import java.util.ArrayList;
import java.util.List;

public class Population<E extends Cube> {

    private List<E> population;

    public Population() {
        population = new ArrayList<>();
    }

    public void add(E cube) {
        population.add(cube);
    }

}
