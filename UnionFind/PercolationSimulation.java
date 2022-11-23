package com.source;

import java.util.Random;

// A class for running the percolation model. The class generates an initial grid which has only blocked cells.
// In accordance with Monte Carlo simulation, random cells in the grid are opened one-by-one. After opening each
// cell, the system is checked for percolation. Based on how many cells were opened on the moment of percolation,
// the percolation threshold is calculated. The experiment is repeated multiple times to estimate an average.
public class PercolationSimulation {
    public boolean debug = false; // flag for outputting debug logs
    private double[] thresholds; // array for storing percolation thresholds of each try
    private double ratio;

    private void printGrid(PercolationModel ps) {
        for (int i = 0; i < ps.getGridSize(); ++i) {
            for (int j = 0; j < ps.getGridSize(); ++j) {
                System.out.printf(ps.getGrid()[i][j] ? "1 " : "0 ");
            }
            System.out.println();
        }
    }

    public PercolationSimulation(int size, int tries) {
        if (size < 1 || tries < 1) {
            throw new IllegalArgumentException("grid size, number of tries < 0");
        }

        thresholds = new double[tries];

        PercolationModel system;
        for (int i = 0; i < tries; i++) {
            system = new PercolationModel(size);
            int count = 0;
            do {
                Random rand = new Random();
                int row = rand.nextInt(1, size + 1);
                int col = rand.nextInt(1, size + 1);
                system.open(row, col);
                if (debug) {
                    System.out.println("opening: row = " + row + ", col = " + col + "\npercolation grid:");
                    printGrid(system);
                    System.out.println("status: " + (system.percolates() ? "PERCOLATING" : "NOT PERCOLATING") + "\n");
                }
            } while (!system.percolates());
            count = system.getOpenCells();
            if (debug)
                System.out.println("open cells: " + system.getOpenCells() + "\n");
            thresholds[i] = (double) count / (double) (size * size);
        }
        double sum = 0.0;
        for (double t : thresholds)
            sum += t;
        ratio = sum / thresholds.length;
    }

    public double getRatio() {
        return ratio;
    }

    public double[] getThresholds() {
        return thresholds;
    }
}
