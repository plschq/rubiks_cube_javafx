package rubikscube.cube;


import javafx.scene.layout.AnchorPane;

import javafx.scene.paint.Color;
import rubikscube.dataclasses.*;

import java.util.ArrayList;


public class Cube {

    public static final AnchorPane rotateAnchorPane = new AnchorPane();
    public static final AnchorPane mainAnchorPane = new AnchorPane(Cube.rotateAnchorPane);

    public static final ArrayList<Piece> allPieces = new ArrayList<>();
    public static final ArrayList<Sticker> allStickers = new ArrayList<>();


    public static void init() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    XYZ position = new XYZ(i, j, k);

                    if (!(position.x == 1 && position.y == 1 && position.z == 1)) {Cube.allPieces.add(new Piece(position));}

                    if (position.x == 0) {Cube.allStickers.add(new Sticker(position, Axis.X, Side.lColor));}
                    if (position.x == 2) {Cube.allStickers.add(new Sticker(position, Axis.X, Side.rColor));}
                    if (position.y == 0) {Cube.allStickers.add(new Sticker(position, Axis.Y, Side.uColor));}
                    if (position.y == 2) {Cube.allStickers.add(new Sticker(position, Axis.Y, Side.dColor));}
                    if (position.z == 0) {Cube.allStickers.add(new Sticker(position, Axis.Z, Side.fColor));}
                    if (position.z == 2) {Cube.allStickers.add(new Sticker(position, Axis.Z, Side.bColor));}
                }
            }
        }

        for (Piece piece : Cube.allPieces) {Cube.mainAnchorPane.getChildren().add(piece.box);}
        for (Sticker sticker : Cube.allStickers) {Cube.mainAnchorPane.getChildren().add(sticker.box);}
    }

    public static int[][][] getData() {
        int[][][] data = new int[6][3][3];
        int[][][] fillValues = new int[][][] {
                {
                        {5, 3, 0},
                        {11, 10, 8},
                        {18, 16, 13}
                }, {
                        {33, 36, 38},
                        {41, 43, 44},
                        {46, 49, 51}
                }, {
                        {6, 24, 39},
                        {4, 23, 37},
                        {1, 21, 34}
                }, {
                        {14, 28, 47},
                        {17, 30, 50},
                        {19, 31, 52}
                }, {
                        {2, 22, 35},
                        {9, 26, 42},
                        {15, 29, 48}
                }, {
                        {40, 25, 7},
                        {45, 27, 12},
                        {53, 32, 20}
                }
        };

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                for (int k = 0; k < data[i][j].length; k++) {
                    data[i][j][k] = Cube.allStickers.get(fillValues[i][j][k]).getColorId();
                }
            }
        }

        return data;
    }

    public static void setData(int[][][] data) {
        int[][][] fillValues = new int[][][] {
                {
                        {5, 3, 0},
                        {11, 10, 8},
                        {18, 16, 13}
                }, {
                        {33, 36, 38},
                        {41, 43, 44},
                        {46, 49, 51}
                }, {
                        {6, 24, 39},
                        {4, 23, 37},
                        {1, 21, 34}
                }, {
                        {14, 28, 47},
                        {17, 30, 50},
                        {19, 31, 52}
                }, {
                        {2, 22, 35},
                        {9, 26, 42},
                        {15, 29, 48}
                }, {
                        {40, 25, 7},
                        {45, 27, 12},
                        {53, 32, 20}
                }
        };

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                for (int k = 0; k < data[i][j].length; k++) {
                    Cube.allStickers.get(fillValues[i][j][k]).setColor(new Color[] {
                            Color.web("#00f"),
                            Color.web("#0f0"),
                            Color.web("#fff"),
                            Color.web("#ff0"),
                            Color.web("#f80"),
                            Color.web("#f00")
                    }[data[i][j][k]-1]);
                }
            }
        }
    }

    public static void scramble(int steps) {
        Axis lastAxisRotated = Axis.X;

        ArrayList<Turn> sequence = new ArrayList<>();

        for (int i = 0; i < steps; i++) {
            Side side = Side.random();
            while (Axis.bySide(side).equals(lastAxisRotated)) {side = Side.random();}
            lastAxisRotated = Axis.bySide(side);
            sequence.add(new Turn(side, Direction.random()));
        }

        Turn.turn(sequence);
    }

}
