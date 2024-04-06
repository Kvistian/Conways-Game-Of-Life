package com.life.game.engine.domain;

import com.google.common.annotations.VisibleForTesting;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a grid of cells in the game of life.
 * The grid is a square and each cell can be either alive or dead.
 * The grid can be "ticked" to generate a new grid based on the rules of the game of life via GameEngine.tick(Grid).
 */
public record Grid(boolean[][] data) {
    public Grid {
        if (data == null) {
            throw new IllegalArgumentException("Grid data must not be null");
        }

        for (boolean[] column : data) {
            if (column == null) {
                throw new IllegalArgumentException("Grid data must not contain null columns");
            } else if (column.length != data.length) {
                throw new IllegalArgumentException("Grid data must be a square");
            }
        }
    }

    /**
     * Count the number of alive neighbors of a cell.
     *
     * @param x The x-coordinate of the cell
     * @param y The y-coordinate of the cell
     * @return The number of alive neighbors
     */
    public int countAliveNeighbors(int x, int y) {
        return (int) getNeighbors(x, y).stream()
                .filter(Boolean::booleanValue)
                .count();
    }

    /**
     * Get the neighbors of a cell.
     *
     * @param x The x-coordinate of the cell
     * @param y The y-coordinate of the cell
     * @return List<Boolean> representing the state of the neighbors
     */
    @VisibleForTesting
    List<Boolean> getNeighbors(int x, int y) {
        List<Boolean> neighbors = new ArrayList<>();

        for (int xi = x - 1; xi <= x + 1; xi++) {
            if (xi < 0 || xi >= data.length) {
                // Column is out of bounds
                continue;
            }
            for (int yi = y - 1; yi <= y + 1; yi++) {
                if (yi < 0 || yi >= data[xi].length) {
                    // Row is out of bounds
                    continue;
                }
                if (xi == x && yi == y) {
                    // Skip the cell itself
                    continue;
                }
                neighbors.add(data[xi][yi]);
            }
        }

        return neighbors;
    }
}
