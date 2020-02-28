package br.com.gabriel.sudokusolver.strategy.api;

import br.com.gabriel.sudokusolver.dataholder.api.DataHolder;

public interface StrategySolver {
    void initStrategySolver(DataHolder dataHolder);

    int[][] applyStrategy();
}
