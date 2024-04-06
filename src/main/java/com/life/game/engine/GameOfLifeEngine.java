package com.life.game.engine;

import com.life.game.engine.domain.Grid;

/**
 * Interface for the game of life game engine.
 * The engine is responsible for generating new grid data based on the rules of the game of life.
 * @see <a href="https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life#Rules">Rules of the game of life</a>
 * @see <a href="https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life#Examples_of_patterns">Examples of patterns</a>
 */
public interface GameOfLifeEngine {
    /**
     * Generate new grid data based on the rules of the game of life.
     * @return New grid data
     */
    boolean[][] tick(Grid grid);
}
