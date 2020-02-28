package br.com.gabriel.sudokusolver.dataholder.api;

import java.util.List;

public interface DataHolder {
    void initDataHolder(int[][] originalMap);

    int[][] getSolution();

    void putPossibilities(String position, Integer... toArray);

    List<Integer> getPossibilities(String position);

    void putSolution(int line, int column, Integer value);
}
