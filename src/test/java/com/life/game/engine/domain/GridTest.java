package com.life.game.engine.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GridTest {
    @Test
    public void nonSquareSeed_throwsIllegalArgumentException() {
        assertThatThrownBy(() -> new Grid(new boolean[10][8]))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Grid data must be a square");
    }

    @Test
    public void nullSeedColumn_throwsIllegalArgumentException() {
        assertThatThrownBy(() -> new Grid(new boolean[][]{null}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Grid data must not contain null columns");
    }

    @Test
    public void nullSeed_throwsIllegalArgumentException() {
        assertThatThrownBy(() -> new Grid(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Grid data must not be null");
    }

    @Test
    public void getNeighbors_forUpperLeftCorner_returnsThreeNeighbors() {
        Grid grid = new Grid(new boolean[3][3]);

        List<Boolean> neighbors = grid.getNeighbors(0, 0);
        assertThat(neighbors).hasSize(3);
    }

    @Test
    public void getNeighbors_forUpperRightCorner_returnsThreeNeighbors() {
        Grid grid = new Grid(new boolean[3][3]);

        List<Boolean> neighbors = grid.getNeighbors(0, 2);
        assertThat(neighbors).hasSize(3);
    }

    @Test
    public void getNeighbors_forLowerLeftCorner_returnsThreeNeighbors() {
        Grid grid = new Grid(new boolean[3][3]);

        List<Boolean> neighbors = grid.getNeighbors(2, 0);
        assertThat(neighbors).hasSize(3);
    }

    @Test
    public void getNeighbors_forLowerRightCorner_returnsThreeNeighbors() {
        Grid grid = new Grid(new boolean[3][3]);

        List<Boolean> neighbors = grid.getNeighbors(2, 2);
        assertThat(neighbors).hasSize(3);
    }

    @Test
    public void getNeighbors_forMiddle_returnsEightNeighbors() {
        Grid grid = new Grid(new boolean[3][3]);

        List<Boolean> neighbors = grid.getNeighbors(1, 1);
        assertThat(neighbors).hasSize(8);
    }

    @Test
    public void eightAliveNeighbors() {
        boolean[][] data = new boolean[3][3];
        data[0][0] = true;
        data[0][1] = true;
        data[0][2] = true;
        data[1][0] = true;
        data[1][2] = true;
        data[2][0] = true;
        data[2][1] = true;
        data[2][2] = true;
        Grid grid = new Grid(data);

        int aliveNeighbors = grid.countAliveNeighbors(1, 1);
        assertThat(aliveNeighbors).isEqualTo(8);
    }

    @Test
    public void zeroAliveNeighbors() {
        boolean[][] data = new boolean[3][3];
        Grid grid = new Grid(data);

        int aliveNeighbors = grid.countAliveNeighbors(1, 1);
        assertThat(aliveNeighbors).isEqualTo(0);
    }

    @Test
    public void threeAliveNeighbors_upperLeftCorner() {
        boolean[][] data = new boolean[3][3];
        data[0][1] = true;
        data[1][0] = true;
        data[1][1] = true;
        Grid grid = new Grid(data);

        int aliveNeighbors = grid.countAliveNeighbors(0, 0);
        assertThat(aliveNeighbors).isEqualTo(3);
    }

    @Test
    public void threeAliveNeighbors_upperRightCorner() {
        boolean[][] data = new boolean[3][3];
        data[0][1] = true;
        data[1][1] = true;
        data[1][2] = true;
        Grid grid = new Grid(data);

        int aliveNeighbors = grid.countAliveNeighbors(0, 2);
        assertThat(aliveNeighbors).isEqualTo(3);
    }

    @Test
    public void threeAliveNeighbors_lowerLeftCorner() {
        boolean[][] data = new boolean[3][3];
        data[1][0] = true;
        data[1][1] = true;
        data[2][1] = true;
        Grid grid = new Grid(data);

        int aliveNeighbors = grid.countAliveNeighbors(2, 0);
        assertThat(aliveNeighbors).isEqualTo(3);
    }

    @Test
    public void threeAliveNeighbors_lowerRightCorner() {
        boolean[][] data = new boolean[3][3];
        data[1][1] = true;
        data[1][2] = true;
        data[2][1] = true;
        Grid grid = new Grid(data);

        int aliveNeighbors = grid.countAliveNeighbors(2, 2);
        assertThat(aliveNeighbors).isEqualTo(3);
    }

    @Test
    public void fiveAliveNeighbors_upperMiddle() {
        boolean[][] data = new boolean[3][3];
        data[0][0] = true;
        data[0][2] = true;
        data[1][0] = true;
        data[1][1] = true;
        data[1][2] = true;
        Grid grid = new Grid(data);

        int aliveNeighbors = grid.countAliveNeighbors(0, 1);
        assertThat(aliveNeighbors).isEqualTo(5);
    }

    @Test
    public void fiveAliveNeighbors_lowerMiddle() {
        boolean[][] data = new boolean[3][3];
        data[1][0] = true;
        data[1][1] = true;
        data[1][2] = true;
        data[2][0] = true;
        data[2][2] = true;
        Grid grid = new Grid(data);

        int aliveNeighbors = grid.countAliveNeighbors(2, 1);
        assertThat(aliveNeighbors).isEqualTo(5);
    }

    @Test
    public void fiveAliveNeighbors_leftMiddle() {
        boolean[][] data = new boolean[3][3];
        data[0][0] = true;
        data[0][1] = true;
        data[1][1] = true;
        data[2][0] = true;
        data[2][1] = true;
        Grid grid = new Grid(data);

        int aliveNeighbors = grid.countAliveNeighbors(1, 0);
        assertThat(aliveNeighbors).isEqualTo(5);
    }

    @Test
    public void fiveAliveNeighbors_rightMiddle() {
        boolean[][] data = new boolean[3][3];
        data[0][1] = true;
        data[0][2] = true;
        data[1][1] = true;
        data[2][1] = true;
        data[2][2] = true;
        Grid grid = new Grid(data);

        int aliveNeighbors = grid.countAliveNeighbors(1, 2);
        assertThat(aliveNeighbors).isEqualTo(5);
    }
}
