package br.com.gabriel.sudokusolver.strategy.impl;

import br.com.gabriel.sudokusolver.dataholder.api.DataHolder;
import br.com.gabriel.sudokusolver.strategy.api.StrategySolver;
import br.com.gabriel.sudokusolver.utils.SudokuMatrixUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExclusionStrategySolver implements StrategySolver {

    private DataHolder dataHolder;

    @Override
    public void initStrategySolver(DataHolder dataHolder) {
        this.dataHolder = dataHolder;
    }

    @Override
    public int[][] applyStrategy() {
        if (dataHolder == null) {
            throw new IllegalStateException("DataHolder not setted");
        }

        populateInitialPossibilities();
        boolean changed;
        boolean continueSolving = true;
        while (continueSolving) {
            changed = analisePositions();
            changed = changed || updateSolution();

            continueSolving = changed && SudokuMatrixUtils.hasZero(dataHolder.getSolution());
        }

        if (SudokuMatrixUtils.hasZero(dataHolder.getSolution())) {
            System.out.println("Could not solve problem");
        } else {
            System.out.println("Solved");
        }

        return dataHolder.getSolution();
    }

    private boolean updateSolution() {
        boolean changed = false;
        List<Integer> possibilities;
        String position;
        for (int line = 0; line < 9; line++) {
            for (int column = 0; column < 9; column++) {
                if (dataHolder.getSolution()[line][column] != 0) {
                    continue;
                }
                position = String.valueOf(line).concat(String.valueOf(column));
                possibilities = new ArrayList<>(dataHolder.getPossibilities(position));
                if (possibilities.size() == 1) {
                    dataHolder.putSolution(line, column, possibilities.get(0));
                    dataHolder.putPossibilities(position);
                    changed = true;
                }
            }
        }
        return changed;
    }

    private boolean analisePositions() {
        boolean changed = false;
        String position;
        List<Integer> possibilities;
        for (int line = 0; line < 9; line++) {
            for (int column = 0; column < 9; column++) {
                if (dataHolder.getSolution()[line][column] == 0) {
                    position = String.valueOf(line).concat(String.valueOf(column));
                    possibilities = new ArrayList<>(dataHolder.getPossibilities(position));
                    changed = changed || analiseVertical(possibilities, line, column);
                    possibilities = new ArrayList<>(dataHolder.getPossibilities(position));
                    changed = changed || analiseHorizontal(possibilities, line, column);
                    possibilities = new ArrayList<>(dataHolder.getPossibilities(position));
                    changed = changed || analiseQuartersForPosition(possibilities, line, column);
                }
            }
        }
        return changed;
    }

    private boolean analiseHorizontal(List<Integer> possibilities, int line, int column) {
        boolean changed = false;
        int positionValue;
        for (int columnIndex = 0; columnIndex < 9; columnIndex++) {
            positionValue = dataHolder.getSolution()[line][columnIndex];
            if (positionValue != 0) {
                changed = changed || possibilities.remove(Integer.valueOf(positionValue));
            }
        }
        dataHolder.putPossibilities(String.valueOf(line).concat(String.valueOf(column)), possibilities.toArray(new Integer[0]));
        return changed;
    }

    private boolean analiseVertical(List<Integer> possibilities, int line, int column) {
        boolean changed = false;
        int positionValue;
        for (int lineIndex = 0; lineIndex < 9; lineIndex++) {
            positionValue = dataHolder.getSolution()[lineIndex][column];
            if (positionValue != 0) {
                changed = changed || possibilities.remove(Integer.valueOf(positionValue));
            }
        }
        dataHolder.putPossibilities(String.valueOf(line).concat(String.valueOf(column)), possibilities.toArray(new Integer[0]));
        return changed;
    }

    private boolean analiseQuartersForPosition(List<Integer> possibilities, int line, int column) {
        int lineIndex = 0;
        int columnIndex = 0;

        if (line < 3) lineIndex = 1;
        else if (line < 6) lineIndex = 4;
        else lineIndex = 7;

        if (column < 3) columnIndex = 1;
        else if (column < 6) columnIndex = 4;
        else columnIndex = 7;

        return analiseQuarter(possibilities, lineIndex, columnIndex, line, column);
    }

    private boolean analiseQuarter(List<Integer> possibilities, int centerLine, int centerColumn, int line, int column) {
        boolean changed = false;
        String position = String.valueOf(line).concat(String.valueOf(column));

        int[][] map = dataHolder.getSolution();
        for (int lineIndex = -1; lineIndex < 2; lineIndex++) {
            for (int columnIndex = -1; columnIndex < 2; columnIndex++) {
                changed = changed || possibilities.remove(Integer.valueOf(map[centerLine + lineIndex][centerColumn + columnIndex]));
            }
        }

        dataHolder.putPossibilities(position, possibilities.toArray(new Integer[0]));
        return changed;
    }

    private void populateInitialPossibilities() {
        for (int line = 1; line < 10; line += 3) {
            for (int column = 1; column < 10; column += 3) {
                populateInitialPossibilitiesFromQuarter(line, column);
            }
        }
    }

    private void populateInitialPossibilitiesFromQuarter(int line, int column) {
        List<Integer> missing = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        int[][] map = dataHolder.getSolution();
        for (int lineIndex = -1; lineIndex < 2; lineIndex++) {
            for (int columnIndex = -1; columnIndex < 2; columnIndex++) {
                missing.remove(Integer.valueOf(map[line + lineIndex][column + columnIndex]));
            }
        }

        for (int lineIndex = -1; lineIndex < 2; lineIndex++) {
            for (int columnIndex = -1; columnIndex < 2; columnIndex++) {
                if (map[line+lineIndex][column+columnIndex] == 0) {
                    dataHolder.putPossibilities(String.valueOf(line + lineIndex).concat(String.valueOf(column + columnIndex)), missing.toArray(new Integer[0]));
                }
            }
        }
    }
}
