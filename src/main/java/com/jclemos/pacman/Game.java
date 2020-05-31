package com.jclemos.pacman;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.graphics.TextGraphicsWriter;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.input.KeyStroke;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

import static java.awt.Color.white;
import static java.lang.Thread.sleep;

public class Game {
    private Terminal terminal;
    private Screen screen;

    private TextGraphics graphics;
    private InputHandler inputHandler;
    private Player pacman;
    private Wall wall;
    private Maze maze;
    private NPC ghost;
    private int ghostNumber = 4;



    public Game() throws IOException {
        terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(59, 17)).createTerminal();
        screen = new TerminalScreen(terminal);
        pacman = new Player(1, 1, DynamicEntity.Direction.Down, 1, 3);
        wall = new Wall(1, 1);
        maze = new Maze();

        ghost = new NPC(1, 1, DynamicEntity.Direction.Up, 2, 200);
        inputHandler = new InputHandler(pacman, maze);

        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary
        graphics = screen.newTextGraphics();

        inputHandler.initInputHandler();
    }

    public void run() throws IOException, InterruptedException {
        System.out.println(maze.generateEmptyPoint().getX());
        System.out.println(maze.generateEmptyPoint().getY());



        while(true){
            sleep(60);
            draw();
        }

    }
        
    private void draw() throws IOException {
        screen.clear();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#ffe118"));
        graphics.fillRectangle(new TerminalPosition(maze.generateEmptyPoint().getX(),maze.generateEmptyPoint().getY()), new TerminalSize(1, 1), ' ');
        maze.draw(graphics);
        //pacman.draw(graphics);
        ghost.draw(graphics);
        screen.refresh();
    }
}
