package br.com.gabriel.sudokusolver.dataholder.impl;

import br.com.gabriel.sudokusolver.dataholder.api.DataHolder;
import br.com.gabriel.sudokusolver.utils.SudokuMatrixUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SudokuDataHolder implements DataHolder {
    private int[][] map;
    private Map<String, List<Integer>> possibilities;

    @Override
    public void initDataHolder(int[][] originalMap) {
        this.map = SudokuMatrixUtils.copyMatrix(originalMap);
        this.possibilities = new HashMap<>();
    }

    @Override
    public int[][] getSolution() {
        return map;
    }

    @Override
    public void putPossibilities(String position, Integer... possibilities) {
        this.possibilities.put(position, Arrays.asList(possibilities));
    }

    @Override
    public List<Integer> getPossibilities(String position) {
        return possibilities.get(position);
    }

    @Override
    public void putSolution(int line, int column, Integer value) {
        map[line][column] = value;
    }
}
