// File:         Main.java
// Created:      02.01.2020
// Last Changed: 02.01.2020
// Author:       Lukas Huth

package com.CT;

import java.awt.*;
import java.awt.event.*;

import com.CT.GameOfLife;
import com.CT.GameCanvas;

public class Main {
    public static final int WINDOW_WIDTH = 1500;
    public static final int WINDOW_HEIGHT = 1000;

    public static final int GAME_WIDTH = 1500;
    public static final int GAME_HEIGHT = 1000;

    private static GameOfLife game;
    private static GameCanvas canvas;

    public static void main(String[] args) {
        // init game of
        Main.game = new GameOfLife(GAME_WIDTH, GAME_HEIGHT);
        Main.canvas = new GameCanvas(WINDOW_WIDTH,WINDOW_HEIGHT,GAME_WIDTH,GAME_HEIGHT);

        Main.init_gui();

        // iterates infinitely, gets the game state and draw it
        while(true){
            canvas.set_game_state(game.get_game_state());
            Main.game.next_generation();

            try {
                Thread.sleep(50);
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
        canvas_frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        canvas_frame.add(Main.canvas);
        canvas_frame.setVisible(true);
    }
}
