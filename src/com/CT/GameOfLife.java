// File:         GameOfLife.java
// Created:      02.01.2020
// Last Changed: 02.01.2020
// Author:       Lukas Huth

package com.CT;

import java.lang.Math;

public class GameOfLife {
    // length of one edge of the square shaped map
    private int map_width;
    private int map_height;
    //holds the current game state
    private boolean[][] map;

    /* Constructor of GameOfLife object, creates the game state with an initial population

        @param int _map_width   the width of the map
        @param int _map_height  the height of the map
     */
    public GameOfLife(int _map_width, int _map_height){
        this.map_width = _map_width;
        this.map_height = _map_height;
        this.map = new boolean[this.map_width][this.map_height];

        this.start_population();
    }

    /* Returns the current game state as an 2D array

        @param None

        @return boolean[mapsize][mapsize]   current game state

     */
    public boolean[][] get_game_state(){
        return this.map;
    }

    /* Calculates the next game state, by the rules of Conways game of life

        @param None

        @return None
     */
    public void next_generation(){
        //creates a new game state to prevent interaction with previous game state
        boolean[][] next_generation = new boolean[this.map_width][this.map_height];

        // iterates over all cells and update their state
        for(int x_it = 0; x_it < this.map_width; x_it++){
            for(int y_it = 0; y_it < this.map_height; y_it++){
                // get count of neighbor cells that are  labeled as alive
                int alive_neighbors = this.alive_neighbors(x_it, y_it);

                if(this.map[x_it][y_it]){

                    // cells with under two living neighbors die due to loneliness
                    if(alive_neighbors < 2)
                        next_generation[x_it][y_it] = false;

                   // cells with two or three living neighbors stay alive
                    if(alive_neighbors == 2 || alive_neighbors == 3)
                        next_generation[x_it][y_it] = true;

                    // cells with more than three living neighbors due to by overpopulation
                    if(3 < alive_neighbors)
                        next_generation[x_it][y_it] = false;

                }else if(!this.map[x_it][y_it]){
                    // dead cells with three living neighbors get reborn
                    if(alive_neighbors == 3)
                        next_generation[x_it][y_it] = true;
                }else{
                    next_generation[x_it][y_it] = this.map[x_it][y_it];
                }
            }
        }

        this.map = next_generation;
    }

    /* Counts neighbor cells that are alive and return the amount of neighbors

        @param int x_pos    x position of the cell to check
        @param int y_pos    y position of the cell to check

        @return int         amount of living neighbor cells
     */
    private int alive_neighbors(int x_pos, int y_pos){
        int alive_neighbors = 0;

        // Left edge check //////////////////////////////////////////////////////////////////////////////////

        // check top left cell only if cell is not on the top left corner of the screen
        if(0 < x_pos && 0 < y_pos)
            alive_neighbors += this.map[x_pos - 1][y_pos - 1] ? 1 : 0;

        // check left center cell only if the cell is not on the left edge of the screen
        if(0 < x_pos)
            alive_neighbors += this.map[x_pos - 1][y_pos] ? 1 : 0;

        // check bottom left cell only if cell is not in the bottom left corner of the screen
        if(0 < x_pos && this.map_height - 1 > y_pos)
            alive_neighbors += this.map[x_pos - 1][y_pos + 1] ? 1 : 0;

        /////////////////////////////////////////////////////////////////////////////////////////////////////


        // top and bottom center check //////////////////////////////////////////////////////////////////////

        // check top center cell only if cell is not on the upper edge of the screen
        if(0 < y_pos)
            alive_neighbors += this.map[x_pos][y_pos - 1] ? 1 : 0;

        // check bottom center cell only if cell is not on the lower edge of the screen
        if(this.map_height - 1 > y_pos)
            alive_neighbors += this.map[x_pos][y_pos + 1] ? 1 : 0;

        /////////////////////////////////////////////////////////////////////////////////////////////////////


        // right edge check /////////////////////////////////////////////////////////////////////////////////

        // check top right cell only if cell is not on the top right corner of the screen
        if(0 < y_pos && this.map_width - 1 > x_pos)
            alive_neighbors += this.map[x_pos + 1][y_pos - 1] ? 1 : 0;

        // check center right cell only if cell is not on the right edge of the screen
        if(this.map_width -1 > x_pos)
            alive_neighbors += this.map[x_pos + 1][y_pos] ? 1 : 0;

        // check the bottom right cell only if cell is not on the bottom right of the screen
        if(this.map_width - 1 > x_pos && this.map_height - 1 > y_pos)
            alive_neighbors += this.map[x_pos + 1][y_pos + 1] ? 1 : 0;

        /////////////////////////////////////////////////////////////////////////////////////////////////////

        return alive_neighbors;
    }

    /* Populates the map member variable with random states of booleans (dead or alive cells)

        @param None

        @return None
     */
    private void start_population(){
        // iterates over the 40% percent in the middle of the screen in both axis
        for(int x_it = (int)(this.map_width * 0.3); x_it < this.map_width * 0.7; x_it++){
            for(int y_it = (int)(this.map_height * 0.3); y_it < this.map_height * 0.7; y_it++){
                // creates an living cell randomly with a chance of 50%
                this.map[x_it][y_it] = Math.random() < 0.5;
            }
        }
    }


}
