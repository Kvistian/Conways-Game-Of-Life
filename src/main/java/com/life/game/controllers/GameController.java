package com.life.game.controllers;

import com.life.game.engine.domain.Grid;
import com.life.game.engine.GameOfLifeEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * GameController responsible for handling rest requests for the game of life.
 */
@RestController
public class GameController {
    private static final double SEED_PROBABILITY = 0.4;
    private final GameOfLifeEngine engine;

    @Autowired
    public GameController(GameOfLifeEngine engine) {
        this.engine = engine;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/seed/{size}")
    public boolean[][] generateRandomSeed(@PathVariable("size") int size) {
        boolean[][] seed = new boolean[size][size];

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                seed[x][y] = Math.random() < SEED_PROBABILITY;
            }
        }

        return seed;
    }

    /**
     * Tick the game of life grid.
     * @param seed (the current state of the grid)
     * @return boolean[][], the next state of the grid
     */
    @CrossOrigin(origins = "*")
    @PostMapping("/tick")
    public boolean[][] tick(@RequestBody boolean[][] seed) {
        try {
            return engine.tick(new Grid(seed));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
