package rubikscube.dataclasses;


public class Axis {

    public final byte id;

    public static final Axis X = new Axis((byte) 0);
    public static final Axis Y = new Axis((byte) 1);
    public static final Axis Z = new Axis((byte) 2);


    public Axis(byte id) {this.id = id;}


    public boolean isX() {return this.id == 0;}
    public boolean isY() {return this.id == 1;}
    public boolean isZ() {return this.id == 2;}

    public static Axis bySide(Side side) {
        if (side.isL() || side.isR()) {return Axis.X;}
        else if (side.isU() || side.isD()) {return Axis.Y;}
        else if (side.isF() || side.isB()) {return Axis.Z;}

        return Axis.X;
    }

}
