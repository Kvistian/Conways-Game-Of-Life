package com.life.game.engine;

import com.life.game.engine.domain.Grid;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameOfLifeEngineImplTest {
    private final GameOfLifeEngine engine = new GameOfLifeEngineImpl();

    @Test
    public void allDead_returnsAllDead() {
        boolean[][] data = new boolean[3][3];
        Grid grid = new Grid(data);

        boolean[][] newData = engine.tick(grid);

        for (boolean[] column : newData) {
            for (boolean cell : column) {
                assertThat(cell).isFalse();
            }
        }
    }

    @Test
    public void allAlive_returnsOnlyAliveCorners() {
        boolean[][] data = new boolean[3][3];
        for (int x = 0; x < data.length; x++) {
            for (int y = 0; y < data.length; y++) {
                data[x][y] = true;
            }
        }
        Grid grid = new Grid(data);

        boolean[][] newData = engine.tick(grid);

        for (int x = 0; x < data.length; x++) {
            for (int y = 0; y < data.length; y++) {
                if ((x == 0 || x == 2) && (y == 0 || y == 2)) {
                    assertThat(newData[x][y]).isTrue();
                } else {
                    assertThat(newData[x][y]).isFalse();
                }
            }
        }
    }

    @Test
    public void underpopulatedCell_dies() {
        boolean[][] data = new boolean[3][3];
        data[1][1] = true; // Underpopulated cell
        data[1][2] = true; // Underpopulated cell
        Grid grid = new Grid(data);

        boolean[][] newData = engine.tick(grid);

        for (boolean[] column : newData) {
            for (boolean cell : column) {
                assertThat(cell).isFalse();
            }
        }
    }

    @Test
    public void overpopulatedCell_dies() {
        boolean[][] data = new boolean[3][3];
        data[0][0] = true;
        data[0][2] = true;
        data[1][1] = true; // Overpopulated cell
        data[2][0] = true;
        data[2][2] = true;
        Grid grid = new Grid(data);

        boolean[][] newData = engine.tick(grid);
        assertThat(newData[1][1]).isFalse();
    }

    @Test
    public void reproduceDeadCell() {
        boolean[][] data = new boolean[3][3];
        data[0][0] = true;
        data[0][1] = true;
        data[0][2] = true;
        Grid grid = new Grid(data);

        boolean[][] newData = engine.tick(grid);
        assertThat(newData[1][1]).isTrue();
    }

    @Test
    public void cellIsNotReproduced_whenFourLiveNeighbors() {
        boolean[][] data = new boolean[3][3];
        data[0][0] = true;
        data[0][2] = true;
        data[1][1] = false; // Cell not to reproduce
        data[2][0] = true;
        data[2][2] = true;
        Grid grid = new Grid(data);

        boolean[][] newData = engine.tick(grid);
        assertThat(newData[1][1]).isFalse();
    }

    @Test
    public void cellIsNotReproduced_whenTwoLiveNeighbors() {
        boolean[][] data = new boolean[3][3];
        data[0][0] = true;
        data[1][1] = false; // Cell not to reproduce
        data[2][2] = true;
        Grid grid = new Grid(data);

        boolean[][] newData = engine.tick(grid);
        assertThat(newData[1][1]).isFalse();
    }

    @Test
    public void cellSurvives_withTwoLiveNeighbors() {
        boolean[][] data = new boolean[3][3];
        data[0][0] = true;
        data[1][1] = true; // Cell to survive
        data[2][2] = true;
        Grid grid = new Grid(data);

        boolean[][] newData = engine.tick(grid);
        assertThat(newData[1][1]).isTrue();
    }

    @Test
    public void cellSurvives_withThreeLiveNeighbors() {
        boolean[][] data = new boolean[3][3];
        data[0][0] = true;
        data[0][2] = true;
        data[1][1] = true; // Cell to survive
        data[2][2] = true;
        Grid grid = new Grid(data);

        boolean[][] newData = engine.tick(grid);
        assertThat(newData[1][1]).isTrue();
    }
}
