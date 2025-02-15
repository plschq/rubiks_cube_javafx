package rubikscube.cube;


import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

import rubikscube.dataclasses.Axis;
import rubikscube.dataclasses.XYZ;


public class Sticker {

    public static double relativeSize = 90;
    public static double relativeDepth = 1.5;
    public static double size = Sticker.relativeSize * Piece.size * 0.01;
    public static double depth = Sticker.relativeDepth * Piece.size * 0.01;

    public XYZ position;
    public Axis axis;
    public Color color;

    public final Box box = new Box(Sticker.size, Sticker.size, Sticker.size);


    public Sticker(XYZ position, Axis axis, Color color) {
        this.position = position;
        this.axis = axis;
        this.color = color;

        this.box.setTranslateX((this.position.x - 1) * Piece.size);
        this.box.setTranslateY((this.position.y - 1) * Piece.size);
        this.box.setTranslateZ((this.position.z - 1) * Piece.size);

        switch (this.axis.id) {
            case 0 -> {
                this.box.setWidth(Sticker.depth);
                this.box.setTranslateX(this.box.getTranslateX() + (Piece.size + Sticker.depth) * 0.5 * (this.position.x - 1));
            }
            case 1 -> {
                this.box.setHeight(Sticker.depth);
                this.box.setTranslateY(this.box.getTranslateY() + (Piece.size + Sticker.depth) * 0.5 * (this.position.y - 1));
            }
            case 2 -> {
                this.box.setDepth(Sticker.depth);
                this.box.setTranslateZ(this.box.getTranslateZ() + (Piece.size + Sticker.depth) * 0.5 * (this.position.z - 1));
            }
        }

        this.setColor(this.color);
    }


    public boolean isBlue() {return this.color.equals(Color.web("#00f"));}
    public boolean isGreen() {return this.color.equals(Color.web("#0f0"));}
    public boolean isWhite() {return this.color.equals(Color.web("#fff"));}
    public boolean isYellow() {return this.color.equals(Color.web("#ff0")) || this.color.equals(Color.web("#000"));}
    public boolean isOrange() {return this.color.equals(Color.web("#f80"));}
    public boolean isRed() {return this.color.equals(Color.web("#f00"));}

    public void setColor(Color color) {
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(color);
        material.setSpecularColor(color);
        // material.setSpecularPower(1);

        this.color = color;
        this.box.setMaterial(material);
    }

    public int getColorId() {
        if (this.isBlue()) {return 1;}
        if (this.isGreen()) {return 2;}
        if (this.isWhite()) {return 3;}
        if (this.isYellow()) {return 4;}
        if (this.isOrange()) {return 5;}
        if (this.isRed()) {return 6;}
        return 1;
    }

    public static void setRelativeSize(double relativeSize) {
        Sticker.relativeSize = relativeSize;
        Sticker.size = Sticker.relativeSize * Piece.size * 0.01;
    }

    public static void setRelativeDepth(double relativeDepth) {
        Sticker.relativeDepth = relativeDepth;
        Sticker.depth = Sticker.relativeDepth * Piece.size * 0.01;
    }

}
