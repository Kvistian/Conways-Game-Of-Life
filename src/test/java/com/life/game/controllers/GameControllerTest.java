package com.life.game.controllers;

import com.life.game.engine.GameOfLifeEngineImpl;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

class GameControllerTest {
    private final GameController gameController = new GameController(new GameOfLifeEngineImpl());

    @Test
    public void generateSeed_10x10() {
        final int size = 10;
        boolean[][] grid = gameController.generateRandomSeed(size);

        assertThat(grid).isNotNull();
        assertThat(grid.length).isEqualTo(size);
        for (boolean[] column : grid) {
            assertThat(column.length).isEqualTo(size);
        }
    }

    @Test
    public void tick_checkeredPattern() {
        boolean[][] grid = new boolean[][]{
            {true, false, true},
            {false, true, false},
            {true, false, true}
        };

        boolean[][] newGrid = gameController.tick(grid);
        assertThat(newGrid).isNotNull();
        assertThat(newGrid[0][0]).isFalse();
        assertThat(newGrid[0][1]).isTrue();
        assertThat(newGrid[0][2]).isFalse();
        assertThat(newGrid[1][0]).isTrue();
        assertThat(newGrid[1][1]).isFalse();
        assertThat(newGrid[1][2]).isTrue();
        assertThat(newGrid[2][0]).isFalse();
        assertThat(newGrid[2][1]).isTrue();
        assertThat(newGrid[2][2]).isFalse();
    }

    @Test
    public void responseStatusException_isThrown_whenGridConstructor_throwsIllegalArgumentException() {
        try {
            gameController.tick(null);
            fail("Expected ResponseStatusException");
        } catch (ResponseStatusException e) {
            assertThat(e).isInstanceOf(org.springframework.web.server.ResponseStatusException.class);
            assertThat(e.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }
    }
}
