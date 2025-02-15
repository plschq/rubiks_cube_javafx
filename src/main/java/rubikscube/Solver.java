// 16.10.2022 - 23.10.2022  Rubik's cube solver by Vladimir Polischuk
package rubikscube;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;
import java.util.StringJoiner;


public final class Solver {

    private static void turnFaceByCubeData(int faceId, int directionId, int[][][] cubeData1, int[][][] cubeData2) {
        if (directionId >= 0) {

            cubeData1[faceId][0][0] = cubeData2[faceId][2][0];
            cubeData1[faceId][0][1] = cubeData2[faceId][1][0];
            cubeData1[faceId][0][2] = cubeData2[faceId][0][0];

            cubeData1[faceId][1][0] = cubeData2[faceId][2][1];
            cubeData1[faceId][1][1] = cubeData2[faceId][1][1];
            cubeData1[faceId][1][2] = cubeData2[faceId][0][1];

            cubeData1[faceId][2][0] = cubeData2[faceId][2][2];
            cubeData1[faceId][2][1] = cubeData2[faceId][1][2];
            cubeData1[faceId][2][2] = cubeData2[faceId][0][2];

        } else {

            cubeData1[faceId][0][0] = cubeData2[faceId][0][2];
            cubeData1[faceId][0][1] = cubeData2[faceId][1][2];
            cubeData1[faceId][0][2] = cubeData2[faceId][2][2];

            cubeData1[faceId][1][0] = cubeData2[faceId][0][1];
            cubeData1[faceId][1][1] = cubeData2[faceId][1][1];
            cubeData1[faceId][1][2] = cubeData2[faceId][2][1];

            cubeData1[faceId][2][0] = cubeData2[faceId][0][0];
            cubeData1[faceId][2][1] = cubeData2[faceId][1][0];
            cubeData1[faceId][2][2] = cubeData2[faceId][2][0];

        }
    }

    private static void fillZeros(int[][][] cubeData1, int[][][] cubeData2) {
        for (int i = 0; i < cubeData2.length; i++) {
            for (int j = 0; j < cubeData2[i].length; j++) {
                for (int k = 0; k < cubeData2[i][j].length; k++) {
                    if (cubeData1[i][j][k] == 0) {
                        cubeData1[i][j][k] = cubeData2[i][j][k];
                    }
                }
            }
        }
    }

    private static int[][][] turn(int sideId, int directionId, int[][][] cubeData) {
        int[][][] result = new int[6][3][3];

        if (sideId == 0) {
            if (directionId >= 0) {
                result[4][0][0] = cubeData[2][0][0]; result[4][1][0] = cubeData[2][1][0]; result[4][2][0] = cubeData[2][2][0];
                result[2][0][0] = cubeData[5][2][2]; result[2][1][0] = cubeData[5][1][2]; result[2][2][0] = cubeData[5][0][2];
                result[5][2][2] = cubeData[3][0][0]; result[5][1][2] = cubeData[3][1][0]; result[5][0][2] = cubeData[3][2][0];
                result[3][2][0] = cubeData[4][2][0]; result[3][1][0] = cubeData[4][1][0]; result[3][0][0] = cubeData[4][0][0];
            } else {
                result[4][0][0] = cubeData[3][0][0]; result[4][1][0] = cubeData[3][1][0]; result[4][2][0] = cubeData[3][2][0];
                result[2][0][0] = cubeData[4][0][0]; result[2][1][0] = cubeData[4][1][0]; result[2][2][0] = cubeData[4][2][0];
                result[5][2][2] = cubeData[2][0][0]; result[5][1][2] = cubeData[2][1][0]; result[5][0][2] = cubeData[2][2][0];
                result[3][2][0] = cubeData[5][0][2]; result[3][1][0] = cubeData[5][1][2]; result[3][0][0] = cubeData[5][2][2];
            } Solver.turnFaceByCubeData(0, directionId, result, cubeData);
        } else if (sideId == 1) {
            if (directionId >= 0) {
                result[4][2][2] = cubeData[3][2][2]; result[4][1][2] = cubeData[3][1][2]; result[4][0][2] = cubeData[3][0][2];
                result[2][2][2] = cubeData[4][2][2]; result[2][1][2] = cubeData[4][1][2]; result[2][0][2] = cubeData[4][0][2];
                result[5][0][0] = cubeData[2][2][2]; result[5][1][0] = cubeData[2][1][2]; result[5][2][0] = cubeData[2][0][2];
                result[3][0][2] = cubeData[5][2][0]; result[3][1][2] = cubeData[5][1][0]; result[3][2][2] = cubeData[5][0][0];
            } else {
                result[4][2][2] = cubeData[2][2][2]; result[4][1][2] = cubeData[2][1][2]; result[4][0][2] = cubeData[2][0][2];
                result[2][2][2] = cubeData[5][0][0]; result[2][1][2] = cubeData[5][1][0]; result[2][0][2] = cubeData[5][2][0];
                result[5][0][0] = cubeData[3][2][2]; result[5][1][0] = cubeData[3][1][2]; result[5][2][0] = cubeData[3][0][2];
                result[3][0][2] = cubeData[4][0][2]; result[3][1][2] = cubeData[4][1][2]; result[3][2][2] = cubeData[4][2][2];
            } Solver.turnFaceByCubeData(1, directionId, result, cubeData);
        } else if (sideId == 2) {
            if (directionId >= 0) {
                result[4][0][0] = cubeData[1][0][0]; result[4][0][1] = cubeData[1][0][1]; result[4][0][2] = cubeData[1][0][2];
                result[1][0][0] = cubeData[5][0][0]; result[1][0][1] = cubeData[5][0][1]; result[1][0][2] = cubeData[5][0][2];
                result[5][0][0] = cubeData[0][0][0]; result[5][0][1] = cubeData[0][0][1]; result[5][0][2] = cubeData[0][0][2];
                result[0][0][0] = cubeData[4][0][0]; result[0][0][1] = cubeData[4][0][1]; result[0][0][2] = cubeData[4][0][2];
            } else {
                result[4][0][0] = cubeData[0][0][0]; result[4][0][1] = cubeData[0][0][1]; result[4][0][2] = cubeData[0][0][2];
                result[1][0][0] = cubeData[4][0][0]; result[1][0][1] = cubeData[4][0][1]; result[1][0][2] = cubeData[4][0][2];
                result[5][0][0] = cubeData[1][0][0]; result[5][0][1] = cubeData[1][0][1]; result[5][0][2] = cubeData[1][0][2];
                result[0][0][0] = cubeData[5][0][0]; result[0][0][1] = cubeData[5][0][1]; result[0][0][2] = cubeData[5][0][2];
            } Solver.turnFaceByCubeData(2, directionId, result, cubeData);
        } else if (sideId == 3) {
            if (directionId >= 0) {
                result[4][2][0] = cubeData[0][2][0]; result[4][2][1] = cubeData[0][2][1]; result[4][2][2] = cubeData[0][2][2];
                result[1][2][0] = cubeData[4][2][0]; result[1][2][1] = cubeData[4][2][1]; result[1][2][2] = cubeData[4][2][2];
                result[5][2][0] = cubeData[1][2][0]; result[5][2][1] = cubeData[1][2][1]; result[5][2][2] = cubeData[1][2][2];
                result[0][2][0] = cubeData[5][2][0]; result[0][2][1] = cubeData[5][2][1]; result[0][2][2] = cubeData[5][2][2];
            } else {
                result[4][2][0] = cubeData[1][2][0]; result[4][2][1] = cubeData[1][2][1]; result[4][2][2] = cubeData[1][2][2];
                result[1][2][0] = cubeData[5][2][0]; result[1][2][1] = cubeData[5][2][1]; result[1][2][2] = cubeData[5][2][2];
                result[5][2][0] = cubeData[0][2][0]; result[5][2][1] = cubeData[0][2][1]; result[5][2][2] = cubeData[0][2][2];
                result[0][2][0] = cubeData[4][2][0]; result[0][2][1] = cubeData[4][2][1]; result[0][2][2] = cubeData[4][2][2];
            } Solver.turnFaceByCubeData(3, directionId, result, cubeData);
        } else if (sideId == 4) {
            if (directionId >= 0) {
                result[2][2][0] = cubeData[0][2][2]; result[2][2][1] = cubeData[0][1][2]; result[2][2][2] = cubeData[0][0][2];
                result[0][2][2] = cubeData[3][0][2]; result[0][1][2] = cubeData[3][0][1]; result[0][0][2] = cubeData[3][0][0];
                result[3][0][0] = cubeData[1][2][0]; result[3][0][1] = cubeData[1][1][0]; result[3][0][2] = cubeData[1][0][0];
                result[1][2][0] = cubeData[2][2][2]; result[1][1][0] = cubeData[2][2][1]; result[1][0][0] = cubeData[2][2][0];
            } else {
                result[2][2][0] = cubeData[1][0][0]; result[2][2][1] = cubeData[1][1][0]; result[2][2][2] = cubeData[1][2][0];
                result[0][2][2] = cubeData[2][2][0]; result[0][1][2] = cubeData[2][2][1]; result[0][0][2] = cubeData[2][2][2];
                result[3][0][0] = cubeData[0][0][2]; result[3][0][1] = cubeData[0][1][2]; result[3][0][2] = cubeData[0][2][2];
                result[1][2][0] = cubeData[3][0][0]; result[1][1][0] = cubeData[3][0][1]; result[1][0][0] = cubeData[3][0][2];
            } Solver.turnFaceByCubeData(4, directionId, result, cubeData);
        } else if (sideId == 5) {
            if (directionId >= 0) {
                result[2][0][2] = cubeData[1][2][2]; result[2][0][1] = cubeData[1][1][2]; result[2][0][0] = cubeData[1][0][2];
                result[0][0][0] = cubeData[2][0][2]; result[0][1][0] = cubeData[2][0][1]; result[0][2][0] = cubeData[2][0][0];
                result[3][2][2] = cubeData[0][2][0]; result[3][2][1] = cubeData[0][1][0]; result[3][2][0] = cubeData[0][0][0];
                result[1][0][2] = cubeData[3][2][2]; result[1][1][2] = cubeData[3][2][1]; result[1][2][2] = cubeData[3][2][0];
            } else {
                result[2][0][2] = cubeData[0][0][0]; result[2][0][1] = cubeData[0][1][0]; result[2][0][0] = cubeData[0][2][0];
                result[0][0][0] = cubeData[3][2][0]; result[0][1][0] = cubeData[3][2][1]; result[0][2][0] = cubeData[3][2][2];
                result[3][2][2] = cubeData[1][0][2]; result[3][2][1] = cubeData[1][1][2]; result[3][2][0] = cubeData[1][2][2];
                result[1][0][2] = cubeData[2][0][0]; result[1][1][2] = cubeData[2][0][1]; result[1][2][2] = cubeData[2][0][2];
            } Solver.turnFaceByCubeData(5, directionId, result, cubeData);
        }

        Solver.fillZeros(result, cubeData);
        return result;
    }

    private static int[][][] turn(String lang, int[][][] cubeData) {
        for (int i = 0; i < lang.split(" ").length; i++) {
            switch (lang.split(" ")[i]) {
                case "L" -> cubeData = Solver.turn(0, 1, cubeData);
                case "L'" -> cubeData = Solver.turn(0, -1, cubeData);
                case "L2" -> {cubeData = Solver.turn(0, 1, cubeData); cubeData = Solver.turn(0, 1, cubeData);}
                case "L2'" -> {cubeData = Solver.turn(0, -1, cubeData); cubeData = Solver.turn(0, -1, cubeData);}

                case "R" -> cubeData = Solver.turn(1, 1, cubeData);
                case "R'" -> cubeData = Solver.turn(1, -1, cubeData);
                case "R2" -> {cubeData = Solver.turn(1, 1, cubeData); cubeData = Solver.turn(1, 1, cubeData);}
                case "R2'" -> {cubeData = Solver.turn(1, -1, cubeData); cubeData = Solver.turn(1, -1, cubeData);}

                case "U" -> cubeData = Solver.turn(2, 1, cubeData);
                case "U'" -> cubeData = Solver.turn(2, -1, cubeData);
                case "U2" -> {cubeData = Solver.turn(2, 1, cubeData); cubeData = Solver.turn(2, 1, cubeData);}
                case "U2'" -> {cubeData = Solver.turn(2, -1, cubeData); cubeData = Solver.turn(2, -1, cubeData);}

                case "D" -> cubeData = Solver.turn(3, 1, cubeData);
                case "D'" -> cubeData = Solver.turn(3, -1, cubeData);
                case "D2" -> {cubeData = Solver.turn(3, 1, cubeData); cubeData = Solver.turn(3, 1, cubeData);}
                case "D2'" -> {cubeData = Solver.turn(3, -1, cubeData); cubeData = Solver.turn(3, -1, cubeData);}

                case "F" -> cubeData = Solver.turn(4, 1, cubeData);
                case "F'" -> cubeData = Solver.turn(4, -1, cubeData);
                case "F2" -> {cubeData = Solver.turn(4, 1, cubeData); cubeData = Solver.turn(4, 1, cubeData);}
                case "F2'" -> {cubeData = Solver.turn(4, -1, cubeData); cubeData = Solver.turn(4, -1, cubeData);}

                case "B" -> cubeData = Solver.turn(5, 1, cubeData);
                case "B'" -> cubeData = Solver.turn(5, -1, cubeData);
                case "B2" -> {cubeData = Solver.turn(5, 1, cubeData); cubeData = Solver.turn(5, 1, cubeData);}
                case "B2'" -> {cubeData = Solver.turn(5, -1, cubeData); cubeData = Solver.turn(5, -1, cubeData);}

                default -> {
                    if (!Objects.equals(lang.split(" ")[i], "")) {
                        System.out.println("Unknown step " + lang.split(" ")[i]);
                    }
                }
            }
        }

        return cubeData;
    }

    private static String simplifyAlgorithm(String algorithm) {
        algorithm += " ";
        String oldAlgorithm;

        String[] nth = new String[] {
                "X X'",
                "X' X",
                "X2 X2",
                "X2 X2'",
                "X2' X2",
                "X2' X2'"
        };
        String[] cws = new String[] {
                "X' X2",
                "X' X2'",
                "X2 X'",
                "X2' X'"
        };
        String[] ccw = new String[] {
                "X X2",
                "X X2'",
                "X2 X",
                "X2' X"
        };
        String[] dbl = new String[] {
                "X X",
                "X' X'"
        };

        do {
            oldAlgorithm = algorithm;
            for (String i : new String[] {"L", "R", "U", "D", "F", "B"}) {
                for (String j : nth) {algorithm = algorithm.replace(j.replace("X", i) + " ", " ");}
                for (String j : cws) {algorithm = algorithm.replace(j.replace("X", i) + " ", i + " ");}
                for (String j : ccw) {algorithm = algorithm.replace(j.replace("X", i) + " ", i + "' ");}
                for (String j : dbl) {algorithm = algorithm.replace(j.replace("X", i) + " ", i + "2 ");}
            }
        } while (!Objects.equals(oldAlgorithm, algorithm));

        return algorithm;
    }

    private static String removeSpaces(String algorithm) {
        StringJoiner algorithmJoiner = new StringJoiner(" ");
        for (String ch : algorithm.split(" ")) {
            if (!Objects.equals(ch, "")) {
                algorithmJoiner.add(ch);
            }
        } return algorithmJoiner.toString();
    }

    public static String solve(int[][][] cubeData) {
        long startTime = System.currentTimeMillis();

        StringBuilder result = new StringBuilder();

        // Yellow-orange edge
        if (!(cubeData[3][0][1] == 4 && cubeData[4][2][1] == 5)) {
            String algorithm = "";

            if (cubeData[3][0][1] == 5 && cubeData[4][2][1] == 4) {algorithm = "F L D";}
            else if (cubeData[0][1][2] == 4 && cubeData[4][1][0] == 5) {algorithm = "F'";}
            else if (cubeData[0][1][2] == 5 && cubeData[4][1][0] == 4) {algorithm = "L D";}
            else if (cubeData[2][2][1] == 4 && cubeData[4][0][1] == 5) {algorithm = "F2";}
            else if (cubeData[2][2][1] == 5 && cubeData[4][0][1] == 4) {algorithm = "U' R' F";}
            else if (cubeData[1][1][0] == 4 && cubeData[4][1][2] == 5) {algorithm = "F";}
            else if (cubeData[1][1][0] == 5 && cubeData[4][1][2] == 4) {algorithm = "R' D'";}
            else if (cubeData[2][1][0] == 4 && cubeData[0][0][1] == 5) {algorithm = "U' F2";}
            else if (cubeData[2][1][0] == 5 && cubeData[0][0][1] == 4) {algorithm = "L F'";}
            else if (cubeData[3][1][0] == 4 && cubeData[0][2][1] == 5) {algorithm = "D";}
            else if (cubeData[3][1][0] == 5 && cubeData[0][2][1] == 4) {algorithm = "L' F'";}
            else if (cubeData[3][1][2] == 4 && cubeData[1][2][1] == 5) {algorithm = "D'";}
            else if (cubeData[3][1][2] == 5 && cubeData[1][2][1] == 4) {algorithm = "R F";}
            else if (cubeData[2][1][2] == 4 && cubeData[1][0][1] == 5) {algorithm = "U F2";}
            else if (cubeData[2][1][2] == 5 && cubeData[1][0][1] == 4) {algorithm = "R' F";}
            else if (cubeData[2][0][1] == 4 && cubeData[5][0][1] == 5) {algorithm = "B2 D2";}
            else if (cubeData[2][0][1] == 5 && cubeData[5][0][1] == 4) {algorithm = "U' L F'";}
            else if (cubeData[0][1][0] == 4 && cubeData[5][1][2] == 5) {algorithm = "B D2";}
            else if (cubeData[0][1][0] == 5 && cubeData[5][1][2] == 4) {algorithm = "L' D";}
            else if (cubeData[3][2][1] == 4 && cubeData[5][2][1] == 5) {algorithm = "D2";}
            else if (cubeData[3][2][1] == 5 && cubeData[5][2][1] == 4) {algorithm = "B' L' D";}
            else if (cubeData[1][1][2] == 4 && cubeData[5][1][0] == 5) {algorithm = "B' D2";}
            else if (cubeData[1][1][2] == 5 && cubeData[5][1][0] == 4) {algorithm = "R D'";}

            result.append(algorithm);
            cubeData = Solver.turn(algorithm, cubeData);
        }

        // Yellow-green edge
        if (!(cubeData[3][1][2] == 4 && cubeData[1][2][1] == 2)) {
            String algorithm = "";

            if (cubeData[0][1][2] == 4 && cubeData[4][1][0] == 2) {algorithm = "D' F' D";}
            else if (cubeData[0][1][2] == 2 && cubeData[4][1][0] == 4) {algorithm = "D2' L D2";}
            else if (cubeData[2][2][1] == 4 && cubeData[4][0][1] == 2) {algorithm = "U' R2'";}
            else if (cubeData[2][2][1] == 2 && cubeData[4][0][1] == 4) {algorithm = "F R' F'";}
            else if (cubeData[1][1][0] == 4 && cubeData[4][1][2] == 2) {algorithm = "D' F D";}
            else if (cubeData[1][1][0] == 2 && cubeData[4][1][2] == 4) {algorithm = "R'";}
            else if (cubeData[2][1][0] == 4 && cubeData[0][0][1] == 2) {algorithm = "U2' R2'";}
            else if (cubeData[2][1][0] == 2 && cubeData[0][0][1] == 4) {algorithm = "U' F R' F'";}
            else if (cubeData[3][1][0] == 4 && cubeData[0][2][1] == 2) {algorithm = "L2' U2' R2'";}
            else if (cubeData[3][1][0] == 2 && cubeData[0][2][1] == 4) {algorithm = "L' D' F' D";}
            else if (cubeData[3][1][2] == 2 && cubeData[1][2][1] == 4) {algorithm = "R' D B' D'";}
            else if (cubeData[2][1][2] == 4 && cubeData[1][0][1] == 2) {algorithm = "R2";}
            else if (cubeData[2][1][2] == 2 && cubeData[1][0][1] == 4) {algorithm = "R' D' F D";}
            else if (cubeData[2][0][1] == 4 && cubeData[5][0][1] == 2) {algorithm = "U R2'";}
            else if (cubeData[2][0][1] == 2 && cubeData[5][0][1] == 4) {algorithm = "B' R";}
            else if (cubeData[0][1][0] == 4 && cubeData[5][1][2] == 2) {algorithm = "D B D'";}
            else if (cubeData[0][1][0] == 2 && cubeData[5][1][2] == 4) {algorithm = "D2 L' D2";}
            else if (cubeData[3][2][1] == 4 && cubeData[5][2][1] == 2) {algorithm = "B2 U R2";}
            else if (cubeData[3][2][1] == 2 && cubeData[5][2][1] == 4) {algorithm = "B R";}
            else if (cubeData[1][1][2] == 4 && cubeData[5][1][0] == 2) {algorithm = "D B' D'";}
            else if (cubeData[1][1][2] == 2 && cubeData[5][1][0] == 4) {algorithm = "R";}

            result.append(" ").append(algorithm);
            cubeData = Solver.turn(algorithm, cubeData);
        }

        // Yellow-red edge
        if (!(cubeData[3][2][1] == 4 && cubeData[5][2][1] == 6)) {
            String algorithm = "";

            if (cubeData[0][1][2] == 4 && cubeData[4][1][0] == 6) {algorithm = "L2' B";}
            else if (cubeData[0][1][2] == 6 && cubeData[4][1][0] == 4) {algorithm = "L' U B2";}
            else if (cubeData[2][2][1] == 4 && cubeData[4][0][1] == 6) {algorithm = "U2 B2";}
            else if (cubeData[2][2][1] == 6 && cubeData[4][0][1] == 4) {algorithm = "U L' B";}
            else if (cubeData[1][1][0] == 4 && cubeData[4][1][2] == 6) {algorithm = "F' U2 F B2";}
            else if (cubeData[1][1][0] == 6 && cubeData[4][1][2] == 4) {algorithm = "R U' R' B2";}
            else if (cubeData[2][1][0] == 4 && cubeData[0][0][1] == 6) {algorithm = "U B2";}
            else if (cubeData[2][1][0] == 6 && cubeData[0][0][1] == 4) {algorithm = "L' B";}
            else if (cubeData[3][1][0] == 4 && cubeData[0][2][1] == 6) {algorithm = "L2 U B2";}
            else if (cubeData[3][1][0] == 6 && cubeData[0][2][1] == 4) {algorithm = "L B";}
            else if (cubeData[2][1][2] == 4 && cubeData[1][0][1] == 6) {algorithm = "U' B2";}
            else if (cubeData[2][1][2] == 6 && cubeData[1][0][1] == 4) {algorithm = "R B' R'";}
            else if (cubeData[2][0][1] == 4 && cubeData[5][0][1] == 6) {algorithm = "B2";}
            else if (cubeData[2][0][1] == 6 && cubeData[5][0][1] == 4) {algorithm = "U' L' B";}
            else if (cubeData[0][1][0] == 4 && cubeData[5][1][2] == 6) {algorithm = "B";}
            else if (cubeData[0][1][0] == 6 && cubeData[5][1][2] == 4) {algorithm = "D L' D'";}
            else if (cubeData[3][2][1] == 6 && cubeData[5][2][1] == 4) {algorithm = "B' D L' D'";}
            else if (cubeData[1][1][2] == 4 && cubeData[5][1][0] == 6) {algorithm = "B'";}
            else if (cubeData[1][1][2] == 6 && cubeData[5][1][0] == 4) {algorithm = "D' R D";}

            result.append(" ").append(algorithm);
            cubeData = Solver.turn(algorithm, cubeData);
        }

        // Yellow-blue edge
        if (!(cubeData[3][1][0] == 4 && cubeData[0][2][1] == 1)) {
            String algorithm = "";

            if (cubeData[0][1][2] == 4 && cubeData[4][1][0] == 1) {algorithm = "D F' D'";}
            else if (cubeData[0][1][2] == 1 && cubeData[4][1][0] == 4) {algorithm = "L";}
            else if (cubeData[2][2][1] == 4 && cubeData[4][0][1] == 1) {algorithm = "U L2";}
            else if (cubeData[2][2][1] == 1 && cubeData[4][0][1] == 4) {algorithm = "F' L F";}
            else if (cubeData[1][1][0] == 4 && cubeData[4][1][2] == 1) {algorithm = "F' U F L2";}
            else if (cubeData[1][1][0] == 1 && cubeData[4][1][2] == 4) {algorithm = "R U2 R' L2";}
            else if (cubeData[2][1][0] == 4 && cubeData[0][0][1] == 1) {algorithm = "L2";}
            else if (cubeData[2][1][0] == 1 && cubeData[0][0][1] == 4) {algorithm = "L D F' D'";}
            else if (cubeData[3][1][0] == 1 && cubeData[0][2][1] == 4) {algorithm = "L D' B D";}
            else if (cubeData[2][1][2] == 4 && cubeData[1][0][1] == 1) {algorithm = "U2 L2";}
            else if (cubeData[2][1][2] == 1 && cubeData[1][0][1] == 4) {algorithm = "U F' L F";}
            else if (cubeData[2][0][1] == 4 && cubeData[5][0][1] == 1) {algorithm = "U' L2";}
            else if (cubeData[2][0][1] == 1 && cubeData[5][0][1] == 4) {algorithm = "B L' B'";}
            else if (cubeData[0][1][0] == 4 && cubeData[5][1][2] == 1) {algorithm = "D' B D";}
            else if (cubeData[0][1][0] == 1 && cubeData[5][1][2] == 4) {algorithm = "L'";}
            else if (cubeData[1][1][2] == 4 && cubeData[5][1][0] == 1) {algorithm = "B U' B' L2";}
            else if (cubeData[1][1][2] == 1 && cubeData[5][1][0] == 4) {algorithm = "B2 L' B2'";}

            result.append(" ").append(algorithm);
            cubeData = Solver.turn(algorithm, cubeData);
        }

        // First layer corners (finishing first layer)
        for (int i : new int[] {52, 26, 61, 15, 52}) {
            String algorithm = "";

            int lColor = (int) ((i - (i % 10)) * 0.1);
            int rColor = i % 10;

            if (!(cubeData[4][2][2] == lColor && cubeData[3][0][2] == 4 && cubeData[1][2][0] == rColor)) {
                if (cubeData[4][2][2] == rColor && cubeData[3][0][2] == lColor && cubeData[1][2][0] == 4) {algorithm = "R U R' U' R U R'";}
                else if (cubeData[4][2][2] == 4 && cubeData[3][0][2] == rColor && cubeData[1][2][0] == lColor) {algorithm = "R U' R' U R U' R'";}
                else if (cubeData[4][0][2] == lColor && cubeData[1][0][0] == 4 && cubeData[2][2][2] == rColor) {algorithm = "R U R'";}
                else if (cubeData[4][0][2] == rColor && cubeData[1][0][0] == lColor && cubeData[2][2][2] == 4) {algorithm = "R U R' U' R U R' U' R U R'";}
                else if (cubeData[4][0][2] == 4 && cubeData[1][0][0] == rColor && cubeData[2][2][2] == lColor) {algorithm = "U R U' R'";}
                else if (cubeData[4][0][0] == lColor && cubeData[2][2][0] == 4 && cubeData[0][0][2] == rColor) {algorithm = "U' R U R' U' R U R' U' R U R'";}
                else if (cubeData[4][0][0] == rColor && cubeData[2][2][0] == lColor && cubeData[0][0][2] == 4) {algorithm = "R U' R'";}
                else if (cubeData[4][0][0] == 4 && cubeData[2][2][0] == rColor && cubeData[0][0][2] == lColor) {algorithm = "U' R U R'";}
                else if (cubeData[5][0][2] == lColor && cubeData[0][0][0] == 4 && cubeData[2][0][0] == rColor) {algorithm = "U2' R U R'";}
                else if (cubeData[5][0][2] == rColor && cubeData[0][0][0] == lColor && cubeData[2][0][0] == 4) {algorithm = "U2' R U R' U' R U R' U' R U R'";}
                else if (cubeData[5][0][2] == 4 && cubeData[0][0][0] == rColor && cubeData[2][0][0] == lColor) {algorithm = "R U2' R'";}
                else if (cubeData[1][0][2] == lColor && cubeData[5][0][0] == 4 && cubeData[2][0][2] == rColor) {algorithm = "U R U R'";}
                else if (cubeData[1][0][2] == rColor && cubeData[5][0][0] == lColor && cubeData[2][0][2] == 4) {algorithm = "U R U R' U' R U R' U' R U R'";}
                else if (cubeData[1][0][2] == 4 && cubeData[5][0][0] == rColor && cubeData[2][0][2] == lColor) {algorithm = "U2 R U' R'";}
                else if (cubeData[1][2][2] == lColor && cubeData[3][2][2] == 4 && cubeData[5][2][0] == rColor) {algorithm = "B U2 B' R U' R'";}
                else if (cubeData[1][2][2] == rColor && cubeData[3][2][2] == lColor && cubeData[5][2][0] == 4) {algorithm = "B U B' R U R'";}
                else if (cubeData[1][2][2] == 4 && cubeData[3][2][2] == rColor && cubeData[5][2][0] == lColor) {algorithm = "B U' B' U2 R U' R'";}
                else if (cubeData[5][2][2] == lColor && cubeData[3][2][0] == 4 && cubeData[0][2][0] == rColor) {algorithm = "B' F' U2' B F";}
                else if (cubeData[5][2][2] == rColor && cubeData[3][2][0] == lColor && cubeData[0][2][0] == 4) {algorithm = "L U2 L' R U R'";}
                else if (cubeData[5][2][2] == 4 && cubeData[3][2][0] == rColor && cubeData[0][2][0] == lColor) {algorithm = "B' U' B R U' R'";}
                else if (cubeData[0][2][2] == lColor && cubeData[3][2][0] == 4 && cubeData[4][2][0] == rColor) {algorithm = "F U F' U' R U' R'";}
                else if (cubeData[0][2][2] == rColor && cubeData[3][2][0] == lColor && cubeData[4][2][0] == 4) {algorithm = "F U F' U2' R U R'";}
                else if (cubeData[0][2][2] == 4 && cubeData[3][2][0] == rColor && cubeData[4][2][0] == lColor) {algorithm = "F U' F' R U' R'";}

                result.append(" ").append(algorithm);
                cubeData = Solver.turn(algorithm, cubeData);
            }

            result.append(" D'");
            cubeData = Solver.turn("D'", cubeData);
        } result.append(" D"); cubeData = Solver.turn("D", cubeData);

        // Orange-green edge
        if (!(cubeData[4][1][2] == 5 && cubeData[1][1][0] == 2)) {
            String algorithm = "";
            String L = "F' U' F U R U R' U'", R = "R U R' U' F' U' F U";

            if (cubeData[0][1][2] == 5 && cubeData[4][1][0] == 2) {algorithm = "F U F' U' L' U' L U' " + L;}
            else if (cubeData[0][1][2] == 2 && cubeData[4][1][0] == 5) {algorithm = "F U F' U' L' U' L U2 " + R;}
            else if (cubeData[2][2][1] == 5 && cubeData[4][0][1] == 2) {algorithm = "U2' " + L;}
            else if (cubeData[2][2][1] == 2 && cubeData[4][0][1] == 5) {algorithm = "U " + R;}
            else if (cubeData[1][1][0] == 5 && cubeData[4][1][2] == 2) {algorithm = R + " U2 " + R;}
            else if (cubeData[2][1][0] == 5 && cubeData[0][0][1] == 2) {algorithm = "U " + L;}
            else if (cubeData[2][1][0] == 2 && cubeData[0][0][1] == 5) {algorithm = R;}
            else if (cubeData[2][1][2] == 5 && cubeData[1][0][1] == 2) {algorithm = "U' " + L;}
            else if (cubeData[2][1][2] == 2 && cubeData[1][0][1] == 5) {algorithm = "U2 " + R;}
            else if (cubeData[2][0][1] == 5 && cubeData[5][0][1] == 2) {algorithm = L;}
            else if (cubeData[2][0][1] == 2 && cubeData[5][0][1] == 5) {algorithm = "U' " + R;}
            else if (cubeData[0][1][0] == 5 && cubeData[5][1][2] == 2) {algorithm = "L U L' U' B' U' B U " + R;}
            else if (cubeData[0][1][0] == 2 && cubeData[5][1][2] == 5) {algorithm = "L U L' U' B' U' B U2' " + L;}
            else if (cubeData[1][1][2] == 5 && cubeData[5][1][0] == 2) {algorithm = "B U B' U' R' U' R U " + L;}
            else if (cubeData[1][1][2] == 2 && cubeData[5][1][0] == 5) {algorithm = "B U B' U' R' U' R " + R;}

            result.append(" ").append(algorithm);
            cubeData = Solver.turn(algorithm, cubeData);
        }

        // Green-red edge
        if (!(cubeData[1][1][2] == 2 && cubeData[5][1][0] == 6)) {
            String algorithm = "";
            String L = "R' U' R U B U B' U'", R = "B U B' U' R' U' R U";

            if (cubeData[0][1][2] == 2 && cubeData[4][1][0] == 6) {algorithm = "F U F' U' L' U' L U2 " + L;}
            else if (cubeData[0][1][2] == 6 && cubeData[4][1][0] == 2) {algorithm = "F U F' U' L' U' L U " + R;}
            else if (cubeData[2][2][1] == 2 && cubeData[4][0][1] == 6) {algorithm = "U " + L;}
            else if (cubeData[2][2][1] == 6 && cubeData[4][0][1] == 2) {algorithm = R;}
            else if (cubeData[2][1][0] == 2 && cubeData[0][0][1] == 6) {algorithm = L;}
            else if (cubeData[2][1][0] == 6 && cubeData[0][0][1] == 2) {algorithm = "U' " + R;}
            else if (cubeData[2][1][2] == 2 && cubeData[1][0][1] == 6) {algorithm = "U2' " + L;}
            else if (cubeData[2][1][2] == 6 && cubeData[1][0][1] == 2) {algorithm = "U " + R;}
            else if (cubeData[2][0][1] == 2 && cubeData[5][0][1] == 6) {algorithm = "U' " + L;}
            else if (cubeData[2][0][1] == 6 && cubeData[5][0][1] == 2) {algorithm = "U2 " + R;}
            else if (cubeData[0][1][0] == 2 && cubeData[5][1][2] == 6) {algorithm = "L U L' U' B' U' B " + R;}
            else if (cubeData[0][1][0] == 6 && cubeData[5][1][2] == 2) {algorithm = "L U L' U' B' U' B U " + L;}
            else if (cubeData[1][1][2] == 6 && cubeData[5][1][0] == 2) {algorithm = R + " U2 " + R;}

            result.append(" ").append(algorithm);
            cubeData = Solver.turn(algorithm, cubeData);
        }

        // Red-blue edge
        if (!(cubeData[5][1][2] == 6 && cubeData[0][1][0] == 1)) {
            String algorithm = "";
            String L = "B' U' B U L U L' U'", R = "L U L' U' B' U' B U";

            if (cubeData[0][1][2] == 6 && cubeData[4][1][0] == 1) {algorithm = "F U F' U' L' U' L U " + L;}
            else if (cubeData[0][1][2] == 1 && cubeData[4][1][0] == 6) {algorithm = "F U F' U' L' U' L U' " + R;}
            else if (cubeData[2][2][1] == 6 && cubeData[4][0][1] == 1) {algorithm = L;}
            else if (cubeData[2][2][1] == 1 && cubeData[4][0][1] == 6) {algorithm = "U' " + R;}
            else if (cubeData[2][1][0] == 6 && cubeData[0][0][1] == 1) {algorithm = "U' " + L;}
            else if (cubeData[2][1][0] == 1 && cubeData[0][0][1] == 6) {algorithm = "U2' " + R;}
            else if (cubeData[2][1][2] == 6 && cubeData[1][0][1] == 1) {algorithm = "U " + L;}
            else if (cubeData[2][1][2] == 1 && cubeData[1][0][1] == 6) {algorithm = R;}
            else if (cubeData[2][0][1] == 6 && cubeData[5][0][1] == 1) {algorithm = "U2 " + L;}
            else if (cubeData[2][0][1] == 1 && cubeData[5][0][1] == 6) {algorithm = "U " + R;}
            else if (cubeData[0][1][0] == 6 && cubeData[5][1][2] == 1) {algorithm = R + " U2 " + R;}

            result.append(" ").append(algorithm);
            cubeData = Solver.turn(algorithm, cubeData);
        }

        // Blue-orange edge
        if (!(cubeData[0][1][2] == 1 && cubeData[4][1][0] == 5)) {
            String algorithm = "";
            String L = "L' U' L U F U F' U'", R = "F U F' U' L' U' L U";

            if (cubeData[0][1][2] == 5 && cubeData[4][1][0] == 1) {algorithm = R + " U2 " + R;}
            else if (cubeData[2][2][1] == 1 && cubeData[4][0][1] == 5) {algorithm = "U' " + L;}
            else if (cubeData[2][2][1] == 5 && cubeData[4][0][1] == 1) {algorithm = "U2' " + R;}
            else if (cubeData[2][1][0] == 1 && cubeData[0][0][1] == 5) {algorithm = "U2' " + L;}
            else if (cubeData[2][1][0] == 5 && cubeData[0][0][1] == 1) {algorithm = "U " + R;}
            else if (cubeData[2][1][2] == 1 && cubeData[1][0][1] == 5) {algorithm = L;}
            else if (cubeData[2][1][2] == 5 && cubeData[1][0][1] == 1) {algorithm = "U' " + R;}
            else if (cubeData[2][0][1] == 1 && cubeData[5][0][1] == 5) {algorithm = "U " + L;}
            else if (cubeData[2][0][1] == 5 && cubeData[5][0][1] == 1) {algorithm = R;}

            result.append(" ").append(algorithm);
            cubeData = Solver.turn(algorithm, cubeData);
        }

        // White cross
        if (!(cubeData[2][1][0] == 3 && cubeData[2][1][2] == 3 && cubeData[2][0][1] == 3 && cubeData[2][2][1] == 3)) {
            String algorithm;

            if (cubeData[2][1][0] == 3 && cubeData[2][1][2] != 3 && cubeData[2][0][1] == 3 && cubeData[2][2][1] != 3) {
                algorithm = "F R U R' U' R U R' U' F'";
            } else if (cubeData[2][1][0] == 3 && cubeData[2][1][2] != 3 && cubeData[2][0][1] != 3 && cubeData[2][2][1] == 3) {
                algorithm = "R B U B' U' B U B' U' R'";
            } else if (cubeData[2][1][0] != 3 && cubeData[2][1][2] == 3 && cubeData[2][0][1] != 3 && cubeData[2][2][1] == 3) {
                algorithm = "B L U L' U' L U L' U' B'";
            } else if (cubeData[2][1][0] != 3 && cubeData[2][1][2] == 3 && cubeData[2][0][1] == 3 && cubeData[2][2][1] != 3) {
                algorithm = "L F U F' U' F U F' U' L'";
            } else if (cubeData[2][1][0] == 3 && cubeData[2][1][2] == 3 && cubeData[2][0][1] != 3 && cubeData[2][2][1] != 3) {
                algorithm = "F R U R' U' F'";
            } else if (cubeData[2][1][0] != 3 && cubeData[2][1][2] != 3 && cubeData[2][0][1] == 3 && cubeData[2][2][1] == 3) {
                algorithm = "L F U F' U' L'";
            } else {algorithm = "F R U R' U' F' B L U L' U' L U L' U' B'";}

            result.append(" ").append(algorithm);
            cubeData = Solver.turn(algorithm, cubeData);
        }

        // White side
        for (int i = 0; i < 4; i++) {
            if (!(cubeData[2][2][2] == 3)) {
                String algorithm = "";

                if (cubeData[4][0][2] == 3) {algorithm = "F D F' D' F D F' D'";}
                else if (cubeData[1][0][0] == 3) {algorithm = "D F D' F' D F D' F'";}

                result.append(" ").append(algorithm);
                cubeData = Solver.turn(algorithm, cubeData);
            }

            result.append(" U");
            cubeData = Solver.turn("U", cubeData);
        }

        // White corners orientation
        if (!(cubeData[4][0][0] == cubeData[4][0][2] && cubeData[5][0][0] == cubeData[5][0][2])) {
            String algorithm;

            if (cubeData[4][0][0] == cubeData[4][0][2] && cubeData[5][0][0] != cubeData[5][0][2]) {
                algorithm = "B U B' R' B U B' U' B' R B2 U' B' U'";
            } else if (cubeData[4][0][0] != cubeData[4][0][2] && cubeData[5][0][0] == cubeData[5][0][2]) {
                algorithm = "F U F' L' F U F' U' F' L F2 U' F' U'";
            } else if (cubeData[0][0][0] == cubeData[0][0][2] && cubeData[1][0][0] != cubeData[1][0][2]) {
                algorithm = "R U R' F' R U R' U' R' F R2 U' R' U'";
            } else if (cubeData[0][0][0] != cubeData[0][0][2] && cubeData[1][0][0] == cubeData[1][0][2]) {
                algorithm = "L U L' B' L U L' U' L' B L2 U' L' U'";
            } else {algorithm = "R U R' F' R U R' U' R' F R2 U' R' U R U R' F' R U R' U' R' F R2 U' R'";}

            result.append(" ").append(algorithm);
            cubeData = Solver.turn(algorithm, cubeData);
        }

        // White corners position
        if (!(cubeData[4][0][0] == 5)) {
            String algorithm = "";

            if (cubeData[4][0][0] == 1) {algorithm = "U";}
            else if (cubeData[4][0][0] == 6) {algorithm = "U2";}
            else if (cubeData[4][0][0] == 2) {algorithm = "U'";}

            result.append(" ").append(algorithm);
            cubeData = Solver.turn(algorithm, cubeData);
        }

        // Top edges (finishing cube solving)
        if (!(cubeData[4][0][1] == 5 && cubeData[0][0][1] == 1 && cubeData[5][0][1] == 6 && cubeData[1][0][1] == 2)) {
            String algorithm = "";

            if (cubeData[4][0][1] == 6 && cubeData[0][0][1] == 2 && cubeData[5][0][1] == 5 && cubeData[1][0][1] == 1) {
                algorithm = "L2 R2 D L2 R2 U2 L2 R2 D L2 R2";
            } else if (cubeData[4][0][1] == 5 && cubeData[0][0][1] != 1 && cubeData[5][0][1] != 6 && cubeData[1][0][1] != 2) {
                if (cubeData[5][0][1] == 2) {
                    algorithm = "R' U R' U' R' U' R' U R U R2'";
                } else if (cubeData[5][0][1] == 1) {
                    algorithm = "L U' L U L U L U' L' U' L2";
                }
            } else if (cubeData[4][0][1] != 5 && cubeData[0][0][1] == 1 && cubeData[5][0][1] != 6 && cubeData[1][0][1] != 2) {
                if (cubeData[1][0][1] == 5) {
                    algorithm = "U' R' U R' U' R' U' R' U R U R2' U";
                } else if (cubeData[1][0][1] == 6) {
                    algorithm = "U' L U' L U L U L U' L' U' L2 U";
                }
            } else if (cubeData[4][0][1] != 5 && cubeData[0][0][1] != 1 && cubeData[5][0][1] == 6 && cubeData[1][0][1] != 2) {
                if (cubeData[4][0][1] == 1) {
                    algorithm = "U2 R' U R' U' R' U' R' U R U R2' U2";
                } else if (cubeData[4][0][1] == 2) {
                    algorithm = "U2 L U' L U L U L U' L' U' L2 U2";
                }
            } else if (cubeData[4][0][1] != 5 && cubeData[0][0][1] != 1 && cubeData[5][0][1] != 6 && cubeData[1][0][1] == 2) {
                if (cubeData[0][0][1] == 6) {
                    algorithm = "U R' U R' U' R' U' R' U R U R2' U'";
                } else if (cubeData[0][0][1] == 5) {
                    algorithm = "U L U' L U L U L U' L' U' L2 U'";
                }
            } else if (cubeData[4][0][1] == 2 && cubeData[0][0][1] == 6 && cubeData[5][0][1] == 1 && cubeData[1][0][1] == 5) {
                algorithm = "R' U R' U' R' U' R' U R U R2' U2 L U' L U L U L U' L' U' L2 U2";
            } else if (cubeData[4][0][1] == 1 && cubeData[0][0][1] == 5 && cubeData[5][0][1] == 2 && cubeData[1][0][1] == 6) {
                algorithm = "R' U R' U' R' U' R' U R U R2' U R' U R' U' R' U' R' U R U R2' U'";
            }

            result.append(" ").append(algorithm);
        }

        String solution = removeSpaces(simplifyAlgorithm(result.toString()));

        System.out.println("Solution: " + solution);
        System.out.println("Steps: " + solution.split(" ").length);
        System.out.println("Time: " + (System.currentTimeMillis() - startTime) + "ms");

        return solution;
    }

}
