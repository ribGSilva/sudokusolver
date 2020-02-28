package br.com.gabriel.sudokusolver;

import br.com.gabriel.sudokusolver.dataholder.api.DataHolder;
import br.com.gabriel.sudokusolver.dataholder.impl.SudokuDataHolder;
import br.com.gabriel.sudokusolver.strategy.api.StrategySolver;
import br.com.gabriel.sudokusolver.strategy.impl.ExclusionStrategySolver;
import br.com.gabriel.sudokusolver.utils.SudokuMatrixUtils;
import br.com.gabriel.sudokusolver.utils.SudokuValidations;

public class SudokuSolver {

    public StrategySolver strategySolver = new ExclusionStrategySolver();

    public int[][] solveSudoku(String[] stringMap) {
        SudokuValidations.validadeSudokuMatrix(stringMap);
        int[][] map = SudokuMatrixUtils.transformStringArrayToSudokuMatrix(stringMap);
        return solveSudoku(map);
    }

    public int[][] solveSudoku(int[][] map) {
        SudokuValidations.validadeSudokuMatrix(map);

        DataHolder dataHolder = new SudokuDataHolder();
        dataHolder.initDataHolder(map);

        strategySolver.initStrategySolver(dataHolder);
        return strategySolver.applyStrategy();
    }
}
