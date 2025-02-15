package rubikscube.dataclasses;


import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import rubikscube.Math;
import rubikscube.cube.Cube;
import rubikscube.cube.Piece;
import rubikscube.cube.Sticker;

import java.util.ArrayList;
import java.util.Random;


public class Side {

    public static final Color lColor = Color.web("#00f");
    public static final Color rColor = Color.web("#0f0");
    public static final Color uColor = Color.web("#fff");
    public static final Color dColor = Color.web("#ff0");
    public static final Color fColor = Color.web("#f80");
    public static final Color bColor = Color.web("#f00");

    public final byte id;

    public static final Side L = new Side((byte) 0);
    public static final Side R = new Side((byte) 1);
    public static final Side U = new Side((byte) 2);
    public static final Side D = new Side((byte) 3);
    public static final Side F = new Side((byte) 4);
    public static final Side B = new Side((byte) 5);


    public Side(byte id) {this.id = id;}


    public boolean isL() {return this.id == 0;}
    public boolean isR() {return this.id == 1;}
    public boolean isU() {return this.id == 2;}
    public boolean isD() {return this.id == 3;}
    public boolean isF() {return this.id == 4;}
    public boolean isB() {return this.id == 5;}

    public static Side random() {return new Side((byte) new Random().nextInt(6));}

    public ArrayList<Piece> getPieces() {
        ArrayList<Piece> slicePieces = new ArrayList<>();

        if (this.isL()) {for (Piece piece : Cube.allPieces) {if (piece.position.x == 0) {slicePieces.add(piece);}}}
        if (this.isR()) {for (Piece piece : Cube.allPieces) {if (piece.position.x == 2) {slicePieces.add(piece);}}}
        if (this.isU()) {for (Piece piece : Cube.allPieces) {if (piece.position.y == 0) {slicePieces.add(piece);}}}
        if (this.isD()) {for (Piece piece : Cube.allPieces) {if (piece.position.y == 2) {slicePieces.add(piece);}}}
        if (this.isF()) {for (Piece piece : Cube.allPieces) {if (piece.position.z == 0) {slicePieces.add(piece);}}}
        if (this.isB()) {for (Piece piece : Cube.allPieces) {if (piece.position.z == 2) {slicePieces.add(piece);}}}

        return slicePieces;
    }

    public ArrayList<Sticker> getStickers() {
        ArrayList<Sticker> sliceStickers = new ArrayList<>();

        if (this.isL()) {for (Sticker sticker : Cube.allStickers) {if (sticker.position.x == 0) {sliceStickers.add(sticker);}}}
        if (this.isR()) {for (Sticker sticker : Cube.allStickers) {if (sticker.position.x == 2) {sliceStickers.add(sticker);}}}
        if (this.isU()) {for (Sticker sticker : Cube.allStickers) {if (sticker.position.y == 0) {sliceStickers.add(sticker);}}}
        if (this.isD()) {for (Sticker sticker : Cube.allStickers) {if (sticker.position.y == 2) {sliceStickers.add(sticker);}}}
        if (this.isF()) {for (Sticker sticker : Cube.allStickers) {if (sticker.position.z == 0) {sliceStickers.add(sticker);}}}
        if (this.isB()) {for (Sticker sticker : Cube.allStickers) {if (sticker.position.z == 2) {sliceStickers.add(sticker);}}}

        return sliceStickers;
    }

    public void changeStickersColors(Direction direction) {
        int[] xCW  = new int[] {5, 7, 6, 11, 12, 18, 20, 19, 3, 4, 10, 16, 17, 0, 2, 1, 8, 9, 13, 15, 14};
        int[] xCCW = new int[] {13, 15, 14, 8, 9, 0, 2, 1, 16, 17, 10, 3, 4, 18, 20, 19, 11, 12, 5, 7, 6};
        int[] yCW  = new int[] {15, 14, 13, 9, 8, 2, 1, 0, 17, 16, 10, 4, 3, 20, 19, 18, 12, 11, 7, 6, 5};
        int[] yCCW = new int[] {7, 6, 5, 12, 11, 20, 19, 18, 4, 3, 10, 17, 16, 2, 1, 0, 9, 8, 15, 14, 13};
        int[] zCW  = new int[] {6, 5, 7, 11, 12, 19, 18, 20, 3, 4, 10, 16, 17, 1, 0, 2, 8, 9, 14, 13, 15};
        int[] zCCW = new int[] {14, 13, 15, 8, 9, 1, 0, 2, 16, 17, 10, 3, 4, 19, 18, 20, 11, 12, 6, 5, 7};

        ArrayList<Sticker> stickers = this.getStickers();
        ArrayList<Color> colors = new ArrayList<>();
        for (Sticker sticker : this.getStickers()) {
            colors.add(sticker.color);
        }

        if (Axis.bySide(this).isX() && direction.isCW()) {
            for (int i = 0; i < stickers.toArray().length; i++) {
                stickers.get(i).setColor(colors.get(xCW[i]));
            }
        } else if (Axis.bySide(this).isX() && direction.isCCW()) {
            for (int i = 0; i < stickers.toArray().length; i++) {
                stickers.get(i).setColor(colors.get(xCCW[i]));
            }
        } else if (Axis.bySide(this).isY() && direction.isCW()) {
            for (int i = 0; i < stickers.toArray().length; i++) {
                stickers.get(i).setColor(colors.get(yCW[i]));
            }
        } else if (Axis.bySide(this).isY() && direction.isCCW()) {
            for (int i = 0; i < stickers.toArray().length; i++) {
                stickers.get(i).setColor(colors.get(yCCW[i]));
            }
        } else if (Axis.bySide(this).isZ() && direction.isCW()) {
            for (int i = 0; i < stickers.toArray().length; i++) {
                stickers.get(i).setColor(colors.get(zCW[i]));
            }
        } else if (Axis.bySide(this).isZ() && direction.isCCW()) {
            for (int i = 0; i < stickers.toArray().length; i++) {
                stickers.get(i).setColor(colors.get(zCCW[i]));
            }
        }
    }

    public void turn(Direction direction) {
        if (Turn.isTurning) {return;}
        Turn.isTurning = true;

        if (Math.isOdd(this.id)) {direction = direction.invert();}

        ArrayList<Piece> layerPieces = this.getPieces();
        ArrayList<Sticker> layerStickers = this.getStickers();

        for (Piece piece : layerPieces) {Cube.rotateAnchorPane.getChildren().add(piece.box);}
        for (Sticker sticker : layerStickers) {Cube.rotateAnchorPane.getChildren().add(sticker.box);}

        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(Turn.time));
        rotateTransition.setNode(Cube.rotateAnchorPane);
        rotateTransition.setInterpolator(Turn.interpolator);

        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(Turn.time));
        translateTransition.setNode(Cube.rotateAnchorPane);
        translateTransition.setInterpolator(Turn.interpolator);

        if (Axis.bySide(this).isX()) {
            rotateTransition.setAxis(Rotate.X_AXIS);
            translateTransition.setToY(Piece.size * -0.25);
            translateTransition.setToZ(Piece.size * 0.25 * direction.id);
        } else if (Axis.bySide(this).isY()) {
            rotateTransition.setAxis(Rotate.Y_AXIS);
            translateTransition.setToX(Piece.size *- 0.25);
            translateTransition.setToZ(Piece.size * -0.25 * direction.id);
        } else if (Axis.bySide(this).isZ()) {
            rotateTransition.setAxis(Rotate.Z_AXIS);
            translateTransition.setToX(Piece.size*(-0.25 - direction.id * 0.25));
            translateTransition.setToY(Piece.size*(-0.25 + direction.id * 0.25));
        } rotateTransition.setToAngle(90 * direction.id);

        Direction finalDirection = direction;
        rotateTransition.setOnFinished(event -> {
            Cube.rotateAnchorPane.setRotate(0);
            this.changeStickersColors(finalDirection);
            for (Piece piece : layerPieces) {Cube.mainAnchorPane.getChildren().add(piece.box);}
            for (Sticker sticker : layerStickers) {Cube.mainAnchorPane.getChildren().add(sticker.box);}
        });
        translateTransition.setOnFinished(event -> {
            Cube.rotateAnchorPane.setTranslateX(0);
            Cube.rotateAnchorPane.setTranslateY(0);
            Cube.rotateAnchorPane.setTranslateZ(0);
            Turn.isTurning = false;
        });

        rotateTransition.play();
        translateTransition.play();
    }

}
