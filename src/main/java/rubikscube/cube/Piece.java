package rubikscube.cube;


import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

import rubikscube.dataclasses.XYZ;

import java.util.ArrayList;


public class Piece {

    public static double size = 80;

    public XYZ position;

    public final Box box = new Box(Piece.size, Piece.size, Piece.size);


    public Piece(XYZ position) {
        this.position = position;

        this.box.setTranslateX((this.position.x - 1) * Piece.size);
        this.box.setTranslateY((this.position.y - 1) * Piece.size);
        this.box.setTranslateZ((this.position.z - 1) * Piece.size);

        Color color = Color.web("#080808");
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(color);
        material.setSpecularColor(color);

        this.box.setMaterial(material);
    }


    public ArrayList<Sticker> getStickers() {
        ArrayList<Sticker> pieceStickers = new ArrayList<>();
        for (Sticker sticker : Cube.allStickers) {
            if (
                    sticker.position.x == this.position.x &&
                    sticker.position.y == this.position.y &&
                    sticker.position.z == this.position.z
            ) {pieceStickers.add(sticker);}
        }
        return pieceStickers;
    }

    public boolean isCenter() {return this.getStickers().toArray().length == 1;}
    public boolean isEdge() {return this.getStickers().toArray().length == 2;}
    public boolean isCorner() {return this.getStickers().toArray().length == 3;}

    public static void setSize(double size) {Piece.size = size;}

}
