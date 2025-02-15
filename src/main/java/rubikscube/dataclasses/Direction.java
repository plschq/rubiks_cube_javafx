package rubikscube.dataclasses;


import java.util.Random;


public class Direction {

    public byte id;

    public static final Direction CW = new Direction((byte) 1);
    public static final Direction CCW = new Direction((byte) -1);


    public Direction(byte id) {if (id >= 0) {this.id = 1;} else {this.id = -1;}}


    public boolean isCW() {return this.id >= 0;}
    public boolean isCCW() {return this.id < 0;}

    public static Direction random() {return new Direction((byte) (new Random().nextInt(2) - 1));}

    public Direction invert() {return new Direction((byte) (this.id * -1));}

}
