// File:         Main.java
// Created:      02.01.2020
// Last Change:  02.01.2020
// Author:       Lukas Huth

package com.CT;

import java.awt.*;
import java.awt.event.*;

import com.CT.GameOfLife;
import com.CT.GameCanvas;

public class Main {
    // The size of the Window in which the Game of Life is rendered
    public static final int WINDOW_WIDTH = 1500;
    public static final int WINDOW_HEIGHT = 1000;

    // Ratio between window and game size
    public static final int SCALING_FACTOR = 1;

    // The dimensions of the game
    public static final int GAME_WIDTH = WINDOW_WIDTH / SCALING_FACTOR;
    public static final int GAME_HEIGHT = WINDOW_HEIGHT / SCALING_FACTOR;

    //
    private static GameOfLife game;
    private static GameCanvas canvas;

    public static void main(String[] args) {
        // init game of life
        Main.game = new GameOfLife(GAME_WIDTH, GAME_HEIGHT);
        Main.canvas = new GameCanvas(WINDOW_WIDTH,WINDOW_HEIGHT,GAME_WIDTH,GAME_HEIGHT);

        Main.init_gui();

        // iterates infinitely, gets the game state and draw it
        while(true){
            canvas.set_game_state(game.get_game_state());
            Main.game.next_generation();

            // sleeps for 10 milliseconds to limit the framerate to 100 frames per second
            try {
                Thread.sleep(10);
            } catch (InterruptedException exc) {
                exc.printStackTrace();
            }

            // draws the current game state
            canvas.repaint();

        }
    }

    public static void init_gui(){
        // init canvas window which displays the game
        Frame canvas_frame = new Frame("Game of life");
        canvas_frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        canvas_frame.setResizable(false);
        // adds event listener which stops execution if the window is closed
        canvas_frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        // adds the GameCanvas instance to the window
        canvas_frame.add(Main.canvas);
        canvas_frame.setVisible(true);
    }
}
