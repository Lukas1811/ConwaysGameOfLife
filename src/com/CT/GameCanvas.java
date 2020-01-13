// File:         GameCanvas.java
// Created:      02.01.2020
// Last Changed: 02.01.2020
// Author:       Lukas Huth

package com.CT;

import java.awt.*;

/*
    The GameCanvas class draws a game state as an AWT canvas
 */
public class GameCanvas extends Canvas{
    private boolean[][] gamestate;
    private int game_width;
    private int game_height;
    private int window_width;
    private int window_height;


    private int cell_width;
    private int cell_height;

    /* Constructor of the GameCanvas object, by initiating the canvas and calculating the cell size

        @param int _window_size     size of the square shaped window
        @param int  _game_size      the amount of cells in the square shaped game
     */
    public GameCanvas (int _window_width, int _window_height, int _game_width, int _game_height) {
        this.window_width = _window_width;
        this.window_height = _window_height;
        this.game_width = _game_width;
        this.game_height = _game_height;

        this.cell_width = this.window_width / this.game_width;
        this.cell_height = this.window_height / this.game_height;

        this.setBackground (Color.BLACK);
        this.setSize(this.window_width, this.window_height);
    }

    /* Sets the game state that should be drawn

        @param boolean[][] _gamestate   new game state

        @return None
     */
    public void set_game_state(boolean[][] _gamestate) {
        this.gamestate = _gamestate;
    }

    public void update(Graphics graphics){
        paint(graphics);
    }

    /* Paints the current game state onto the canvas

        @param Graphics graphics    the graphic frame to draw on

        @return None
     */
    public void paint (Graphics graphics) {
        // iterates over all cells and update their state
        for(int x_it = 0; x_it < this.game_width; x_it++){
            for(int y_it = 0; y_it < this.game_height; y_it++) {
                // draws a rectangle, white if it's alive and black  if it's dead
                graphics.setColor(this.gamestate[x_it][y_it] ? Color.WHITE : Color.BLACK);
                graphics.fillRect(x_it * this.cell_width, y_it * this.cell_height, this.cell_width, this.cell_height);
            }
        }
    }
}

