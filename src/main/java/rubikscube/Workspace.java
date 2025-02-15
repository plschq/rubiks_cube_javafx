package rubikscube;


import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import rubikscube.cube.*;
import rubikscube.dataclasses.*;


public final class Workspace {

    public static void init() {
        App.getStage().getScene().addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.S)) {
                Turn.setTurnTime(50);
                Cube.scramble(30);
            } else if (keyEvent.getCode().equals(KeyCode.SPACE)) {
                Turn.setTurnTime(100);
                String solveAlgorithm = Solver.solve(Cube.getData());
                Turn.turn(new Lang(solveAlgorithm));
            } else if (keyEvent.getCode().equals(KeyCode.D)) {
                String solveAlgorithm = Solver.solve(Cube.getData());
                System.out.println(solveAlgorithm);
            }
        });
    }

}
