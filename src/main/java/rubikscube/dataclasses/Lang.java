package rubikscube.dataclasses;


import java.util.ArrayList;
import java.util.Objects;


public class Lang {

    public String string;
    public String[] stringArray;
    public ArrayList<Turn> turnArrayList;
    public int length;


    public Lang(String lang) {this.init(lang);}


    private void init(String lang) {
        this.string = lang;
        this.stringArray = this.string.split(" ");
        this.length = this.stringArray.length;

        this.turnArrayList = new ArrayList<>();
        for (int i = 0; i < this.length; i++) {
            switch (stringArray[i]) {
                case "L" -> this.turnArrayList.add(new Turn(Side.L, Direction.CW));
                case "L'" -> this.turnArrayList.add(new Turn(Side.L, Direction.CCW));
                case "L2" -> {this.turnArrayList.add(new Turn(Side.L, Direction.CW)); this.turnArrayList.add(new Turn(Side.L, Direction.CW));}
                case "L2'" -> {this.turnArrayList.add(new Turn(Side.L, Direction.CCW)); this.turnArrayList.add(new Turn(Side.L, Direction.CCW));}

                case "R" -> this.turnArrayList.add(new Turn(Side.R, Direction.CW));
                case "R'" -> this.turnArrayList.add(new Turn(Side.R, Direction.CCW));
                case "R2" -> {this.turnArrayList.add(new Turn(Side.R, Direction.CW)); this.turnArrayList.add(new Turn(Side.R, Direction.CW));}
                case "R2'" -> {this.turnArrayList.add(new Turn(Side.R, Direction.CCW)); this.turnArrayList.add(new Turn(Side.R, Direction.CCW));}

                case "U" -> this.turnArrayList.add(new Turn(Side.U, Direction.CW));
                case "U'" -> this.turnArrayList.add(new Turn(Side.U, Direction.CCW));
                case "U2" -> {this.turnArrayList.add(new Turn(Side.U, Direction.CW)); this.turnArrayList.add(new Turn(Side.U, Direction.CW));}
                case "U2'" -> {this.turnArrayList.add(new Turn(Side.U, Direction.CCW)); this.turnArrayList.add(new Turn(Side.U, Direction.CCW));}

                case "D" -> this.turnArrayList.add(new Turn(Side.D, Direction.CW));
                case "D'" -> this.turnArrayList.add(new Turn(Side.D, Direction.CCW));
                case "D2" -> {this.turnArrayList.add(new Turn(Side.D, Direction.CW)); this.turnArrayList.add(new Turn(Side.D, Direction.CW));}
                case "D2'" -> {this.turnArrayList.add(new Turn(Side.D, Direction.CCW)); this.turnArrayList.add(new Turn(Side.D, Direction.CCW));}

                case "F" -> this.turnArrayList.add(new Turn(Side.F, Direction.CW));
                case "F'" -> this.turnArrayList.add(new Turn(Side.F, Direction.CCW));
                case "F2" -> {this.turnArrayList.add(new Turn(Side.F, Direction.CW)); this.turnArrayList.add(new Turn(Side.F, Direction.CW));}
                case "F2'" -> {this.turnArrayList.add(new Turn(Side.F, Direction.CCW)); this.turnArrayList.add(new Turn(Side.F, Direction.CCW));}

                case "B" -> this.turnArrayList.add(new Turn(Side.B, Direction.CW));
                case "B'" -> this.turnArrayList.add(new Turn(Side.B, Direction.CCW));
                case "B2" -> {this.turnArrayList.add(new Turn(Side.B, Direction.CW)); this.turnArrayList.add(new Turn(Side.B, Direction.CW));}
                case "B2'" -> {this.turnArrayList.add(new Turn(Side.B, Direction.CCW)); this.turnArrayList.add(new Turn(Side.B, Direction.CCW));}

                default -> {
                    if (!Objects.equals(stringArray[i], "")) {
                        System.out.println("Unknown step " + stringArray[i]);
                    }
                }
            }
        }
    }

    public void add(Lang lang) {this.init(this.string + " " + lang.string);}

    public void d0() {Turn.turn(this);}
    public static void d0(Lang lang) {Turn.turn(lang);}

}
