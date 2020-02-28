package br.com.gabriel.sudokusolver.utils;

public class SudokuValidations {

    public static void validadeSudokuMatrix(String[] matrixString) {
        if (matrixString.length != 9) {
            throw new IllegalArgumentException("Matrix has not 9 lines, actual lines: " + matrixString.length);
        }
        for (String lineString : matrixString) {
            if (lineString.length() != 9) {
                throw new IllegalArgumentException("Matrix has not 9 columns, actual columns: " + lineString.length());
            }
        }
    }

    public static void validadeSudokuMatrix(int[][] matrix) {
        if (matrix.length != 9) {
            throw new IllegalArgumentException("Matrix has not 9 lines, actual lines: " + matrix.length);
        }
        if (matrix[0].length != 9) {
            throw new IllegalArgumentException("Matrix has not 9 columns, actual columns: " + matrix[0].length);
        }
    }
}
