package com.life.game.engine;

import com.life.game.engine.domain.Grid;
import org.springframework.stereotype.Component;

@Component
public class GameOfLifeEngineImpl implements GameOfLifeEngine {
    @Override
    public boolean[][] tick(Grid previousGrid) {
        boolean[][] previousGridData = previousGrid.data();
        int gridSize = previousGridData.length;
        boolean[][] newGrid = new boolean[gridSize][gridSize];

        for (int columnIndex = 0; columnIndex < gridSize; columnIndex++) {
            boolean[] column = previousGridData[columnIndex];

            for (int rowIndex = 0; rowIndex < column.length; rowIndex++) {
                boolean isAlive = previousGridData[columnIndex][rowIndex];
                int aliveNeighbors = previousGrid.countAliveNeighbors(columnIndex, rowIndex);

                if (isAlive) {
                    // Should the cell survive?
                    newGrid[columnIndex][rowIndex] = aliveNeighbors >= 2 && aliveNeighbors <= 3;
                } else {
                    // Should the cell reproduce?
                    newGrid[columnIndex][rowIndex] = aliveNeighbors == 3;
                }
            }
        }

        return newGrid;
    }
}
