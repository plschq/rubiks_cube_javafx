package rubikscube;


import rubikscube.dataclasses.Lang;


public final class Patterns {

    // Patterns from https://ruwix.com/the-rubiks-cube/rubiks-cube-patterns-algorithms/
    // There is not all patterns from this source
    // Some titles are changed
    // Not finished
    public static final Lang SUPERFLIP = new Lang("U R2 F B R B2 R U2 L B2 R U' D' R2 F R' L B2 U2 F2");
    public static final Lang CHECKERBOARD = new Lang("L2 R2 U2 D2 F2 B2");
    public static final Lang WIRE = new Lang("R L F B R L F B R L F B R2 B2 L2 R2 B2 L2");
    public static final Lang CHECKERBOARD_IN_THE_CUBE = new Lang("B D F' B' D L2 U L U' B D' R B R D' R L' F U2 D");
    public static final Lang PERPENDICULAR_LINES = new Lang("R2 U2 L2 R2 U2 L2 U2 D2");
    public static final Lang FLIPPED_CORNERS = new Lang("U B D' F2 D B' U' R2 D F2 D' R2 D F2 D' R2");
    public static final Lang PLUS_MINUS = new Lang("U2 R2 L2 U2 R2 L2");
    public static final Lang SPIRAL = new Lang("L' B' D U R U' R' D2 R2 D L D' L' R' F U");
    public static final Lang VERTICAL_STRIPES = new Lang("F U F R L2 B D' R D2 L D' B R2 L F U F");
    public static final Lang GIFT_BOX = new Lang("U B2 R2 B2 L2 F2 R2 D' F2 L2 B F' L F2 D U' R2 F' L' R'");
    public static final Lang OPPOSITE_CORNERS = new Lang("R L U2 F2 D2 F2 R L F2 D2 B2 D2");
    public static final Lang CROSSES = new Lang("R2 L' D F2 R' D' R' L U' D R D B2 R' U D2");
    public static final Lang UNION_JACK = new Lang("U F B' L2 U2 L2 F' B U2 L2 U");
    public static final Lang CUBE_IN_A_CUBE = new Lang("F L F U' R U F2 L2 U' L' B D' B' L2 U");
    public static final Lang CUBE_IN_A_CUBE_IN_A_CUBE = new Lang("U' L' U' F' R2 B' R F U B2 U B' L U' F U R F'");
    public static final Lang SNAKE = new Lang("L U B' U' R L' B R' F B' D R D' F'");
    public static final Lang DOTS = new Lang("U D' R L' F B' U D'");
    public static final Lang TWISTER = new Lang("F R' U L F' L' F U' R U L' U' L F'");
    public static final Lang KLIT = new Lang("U' R2 L2 F2 B2 U' R L F B' U F2 D2 R2 L2 F2 U2 F2 U' F2");
    public static final Lang HI_AROUND = new Lang("U2 R2 F2 U2 D2 F2 L2 U2");
    public static final Lang HI_AGAIN = new Lang("U2 D2 L2 U2 D2 R2 F2 B2 L2 F2 B2 R2 U2 D2 F2 U2 D2 B2");
    public static final Lang DISPLACED_MOTIF = new Lang("L2 B2 D' B2 D L2 U R2 D R2 B U R' F2 R U' B' U'");
    public static final Lang C_U_AROUND = new Lang("U' B2 U L2 D L2 R2 D' B' R D' L R' B2 U2 F' L' U'");
    public static final Lang OPPOSITE_PILLARS = new Lang("R2 F2 L2 R2 F2 L2");
    public static final Lang VIADUCT = new Lang("R2 U2 L2 D B2 L2 B2 R2 D' U L' D F' U' R2 F' U B2 U2 R'");
    public static final Lang SOLVED_IN_SCRAMBLED = new Lang("U2 L' U2 L' U2 F2 D L F' L F R2 D' B2 D' L2 D' L2 F2 R2");
    public static final Lang STAIRCASE = new Lang("L2 F2 D' L2 B2 D' U' R2 B2 U' L' B2 L D L B' D L' U");
    public static final Lang WRAPPED_2X2 = new Lang("D' B2 F2 L2 U' F2 R2 D F2 U2 L' B R' U' L' F D' F L D2");
    public static final Lang FLOWER_FIELD = new Lang("L R' D U' B F' L R L2 U2 D2 F2 B2");
    public static final Lang QUOTE = new Lang("U2 F2 D2 B2 R2 U2 B2 R2 U2 R2");

    // Other patterns
    public static final Lang REVERSE = new Lang("U R U2 R F2 L U2 R F' B' R2 D R' L U2 F2 D2 F R2 D");

}
