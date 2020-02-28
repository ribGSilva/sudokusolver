package br.com.gabriel.sudokusolver.utils;

public class SudokuMatrixUtils {
    public static boolean hasZero(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 0)
                    return true;
            }
        }
        return false;
    }

    public static int[][] copyMatrix(int[][] original) {
        int[][] copy = new int[original.length][original[0].length];

        for (int i = 0; i < original.length; i++) {
            for (int j = 0; j < original[0].length; j++) {
                copy[i][j] = original[i][j];
            }
        }

        return copy;
    }

    public static void printSudokuMatrix(int[][] matrix) {
        for (int i = 0; i < 9; i++) {
            if (i == 3 || i == 6)
                System.out.println(" -------------------");
            for (int j = 0; j < 9; j++) {
                if (j == 3 || j == 6)
                    System.out.print(" |");
                System.out.print(" " + matrix[i][j]);
            }
            System.out.println("");
        }
    }

    public static int[][] transformStringArrayToSudokuMatrix(String[] matrixString) {
        int[][] map = new int[9][9];
        final String controlChars = "123456789";

        String lineString;
        String charString;
        try {
            for (int i = 0; i < map.length; i++) {
                lineString = matrixString[i];
                for (int j = 0; j < map[0].length; j++) {
                    charString = String.valueOf(lineString.charAt(j));
                    if (controlChars.contains(charString))
                        map[i][j] = Integer.parseInt(charString);
                }
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Matrix wrongly formatted");
        }

        return map;
    }
    }
