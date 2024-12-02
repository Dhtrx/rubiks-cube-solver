package model.cubes.threeXThreeCube.solving.kociemba;

import model.cubes.Color;
import model.cubes.threeXThreeCube.ThreeCube;
import model.cubes.threeXThreeCube.moves.Move;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

import java.util.List;

public class Solve {

    public static List<Move> solveThreeCube(ThreeCube cube) {
        String stringRep = getStringRepresentation(cube);
        //System.setProperty("python.home", "C:/Users/User/.m2/repository/org/python/jython/2.7.4");
        System.setProperty("python.import.site", "false");
        try (PythonInterpreter interpreter = new PythonInterpreter()) {
            interpreter.exec("""
                    import kociemba
                    def solve(scramble):
                        return kociemba.solve(scramble)
                    """);
            PyObject pyObject = interpreter.eval(STR."solveCube(\{stringRep})");
            String result = pyObject.asString();
            System.out.println(result);
            return null;
        }
    }

    private static String getStringRepresentation(ThreeCube cube) {
        int[][][] arr = cube.getCube();
        StringBuilder builder = new StringBuilder();
        for (int[][] ints : arr) {
            for (int j = 0; j < arr[0].length; j++) {
                for (int l = 0; l < arr[0][0].length; l++) {
                    builder.append(Color.fromInt(ints[j][l]).symbol);
                }
            }
        }
        return builder.toString();
    }

}
