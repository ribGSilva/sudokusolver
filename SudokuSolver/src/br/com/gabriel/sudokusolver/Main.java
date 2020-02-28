package br.com.gabriel.sudokusolver;

import br.com.gabriel.sudokusolver.utils.SudokuMatrixUtils;

public class Main {


    public static final String[] TEST_EASY = new String[]{
            "045017020",
            "020300100",
            "001000374",
            "009003580",
            "102580437",
            "008706900",
            "010472005",
            "007000200",
            "054600001"};

    public static final String[] TEST_MEDIUM = new String[]{
            "400008002",
            "060020805",
            "000060030",
            "396000020",
            "007002040",
            "204000570",
            "072084000",
            "030000009",
            "509610000"};

    public static final String[] TEST_HARD = new String[]{
            "400000701",
            "000705000",
            "030000400",
            "920000000",
            "100020000",
            "047500000",
            "003904276",
            "000050030",
            "000010940"};

    public static final String[] TEST_EXPERT = new String[]{
            "000000009",
            "040830000",
            "820040010",
            "006007000",
            "008000507",
            "053200000",
            "070920005",
            "000000000",
            "105060000"};

    public static void main(String[] args) {
        SudokuSolver sudokuSolver = new SudokuSolver();
        long init = System.currentTimeMillis();
        int[][] solution = sudokuSolver.solveSudoku(TEST_EASY);
        long end = System.currentTimeMillis();
        SudokuMatrixUtils.printSudokuMatrix(solution);
        System.out.printf("Finished in %s ms.\r\n", end - init);
    }
}
