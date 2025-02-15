package rubikscube.dataclasses;


import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import rubikscube.Math;
import rubikscube.cube.Cube;
import rubikscube.cube.Piece;
import rubikscube.cube.Sticker;

import java.util.ArrayList;
import java.util.Arrays;


public class Turn {

    public static int time = 250;
    public static Interpolator interpolator = Interpolator.LINEAR;

    public static boolean isTurning = false;

    public final Side side;
    public final Direction direction;


    public Turn(Side side, Direction direction) {
        this.side = side;
        this.direction = direction;
    }


    public void turn() {this.side.turn(this.direction);}
    public static void turn(Lang lang) {Turn.turn(lang.turnArrayList);}
    public static void turn(Side side, Direction direction) {new Turn(side, direction).turn();}
    public static void turn(Turn[] sequence) {Turn.turn(new ArrayList<>(Arrays.asList(sequence)));}

    public static void turn(ArrayList<Turn> sequence) {
        if (Turn.isTurning) {return;}
        Turn.isTurning = true;

        Side side = sequence.get(0).side;
        Direction direction = sequence.get(0).direction;

        if (Math.isOdd(side.id)) {direction = direction.invert();}

        ArrayList<Piece> layerPieces = side.getPieces();
        ArrayList<Sticker> layerStickers = side.getStickers();

        for (Piece piece : layerPieces) {
            Cube.rotateAnchorPane.getChildren().add(piece.box);}
        for (Sticker sticker : layerStickers) {Cube.rotateAnchorPane.getChildren().add(sticker.box);}

        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(Turn.time));
        rotateTransition.setNode(Cube.rotateAnchorPane);
        rotateTransition.setInterpolator(Turn.interpolator);

        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(Turn.time));
        translateTransition.setNode(Cube.rotateAnchorPane);
        translateTransition.setInterpolator(Turn.interpolator);

        if (Axis.bySide(side).isX()) {
            rotateTransition.setAxis(Rotate.X_AXIS);
            translateTransition.setToY(Piece.size * -0.25);
            translateTransition.setToZ(Piece.size * 0.25 * direction.id);
        } else if (Axis.bySide(side).isY()) {
            rotateTransition.setAxis(Rotate.Y_AXIS);
            translateTransition.setToX(Piece.size *- 0.25);
            translateTransition.setToZ(Piece.size * -0.25 * direction.id);
        } else if (Axis.bySide(side).isZ()) {
            rotateTransition.setAxis(Rotate.Z_AXIS);
            translateTransition.setToX(Piece.size*(-0.25 - direction.id * 0.25));
            translateTransition.setToY(Piece.size*(-0.25 + direction.id * 0.25));
        } rotateTransition.setToAngle(90 * direction.id);

        Direction finalDirection = direction;
        rotateTransition.setOnFinished(event -> {
            Cube.rotateAnchorPane.setRotate(0);
            side.changeStickersColors(finalDirection);
            for (Piece piece : layerPieces) {Cube.mainAnchorPane.getChildren().add(piece.box);}
            for (Sticker sticker : layerStickers) {Cube.mainAnchorPane.getChildren().add(sticker.box);}
        });
        translateTransition.setOnFinished(event -> {
            Cube.rotateAnchorPane.setTranslateX(0);
            Cube.rotateAnchorPane.setTranslateY(0);
            Cube.rotateAnchorPane.setTranslateZ(0);
            Turn.isTurning = false;
            if (sequence.toArray().length > 1) {
                sequence.remove(0);
                Turn.turn(sequence);
            }
        });

        rotateTransition.play();
        translateTransition.play();
    }

    public static Turn random() {return new Turn(Side.random(), Direction.random());}

    public static void setTurnTime(int turnTime) {Turn.time = turnTime;}
    public static void setTurnInterpolator(Interpolator turnInterpolator) {Turn.interpolator = turnInterpolator;}

}
