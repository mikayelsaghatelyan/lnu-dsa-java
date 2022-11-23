package com.source;


// Class for a percolation research. The class constructor creates a grid with initially blocked cells, which can be then opened.
// After a cell is opened, it is connected to the previously opened cells and the result is mapped to the union.

public class PercolationModel {
    private int grid_size;
    private boolean[][] grid;
    private WQUF union;
    private int open_cells;
    private int bottom_index;

    PercolationModel(int n) {
        this.grid_size = n;
        this.bottom_index = grid_size * grid_size + 1;
        grid = new boolean[grid_size][grid_size];
        union = new WQUF(grid_size * grid_size + 2); // all grid cells + imaginary top and bottom cells
    }

    int getGridSize() {
        return this.grid_size;
    }

    boolean[][] getGrid() {
        return grid;
    }

    int getOpenCells() {
        return open_cells;
    }

    // method for opening the cells of the system
    void open(int row, int col) {
        if (isOpen(row, col))
            return;
        // open cell (the cell is set as true in the grid)
        grid[row - 1][col - 1] = true;
        open_cells++;
        // new-opened cell is connected to the adjacent open cells (maximum is 4).
        int cell = convert2Dto1D(row, col);
        connectAdjacentCell(row - 1, col, cell); // upper-adjacent cell
        connectAdjacentCell(row + 1, col, cell); // lower-adjacent cell
        connectAdjacentCell(row, col + 1, cell); // right-adjacent cell
        connectAdjacentCell(row, col - 1, cell); // left-adjacent cell
        // connect to virtual top (or bottom)
        if (row == 1)
            union.union(cell, 0);
        if (row == grid_size) {
            union.union(bottom_index, cell);
        }
    }

    boolean isOpen(int row, int col) {
        checkBoundaries(row - 1, col - 1);
        return grid[row - 1][col - 1];
    }

    boolean percolates() {
        return (grid_size == 1 ? grid[0][0] : union.connected(0, bottom_index));
    }

    void checkBoundaries(int i, int j) {
        if (i >= grid_size || j >= grid_size || i < 0 || j < 0)
            throw new java.lang.IndexOutOfBoundsException(
                    "index out of range.");
    }

    // method for converting 2-dimensional coordinates (X,Y) to 1-dimensional ones for the union.
    int convert2Dto1D(int i, int j) {
        return grid_size * (i - 1) + j;
    }

    // method for connecting a new-opened cell to its adjacent open cells
    void connectAdjacentCell(int row, int col, int cell) {
        try {
            if (isOpen(row, col)) {
                union.union(cell, convert2Dto1D(row, col));
            }
        } catch (IndexOutOfBoundsException ignored) {}
    }
}