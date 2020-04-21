package com.visual;

import java.awt.*;
import javax.swing.*;
import com.maze.*;
import java.awt.event.*;

//the controll for the actual looks of the GUI
public class VisualController {
    // the visual aspects of the window
    Frame frame;
    String name;
    // the parameters
    int width;
    int height;

    public VisualController(String name, int width, int height) {
        //params of the window
        this.name = name;
        this.width = width;
        this.height = height;

        // make the window
        this.frame = new Frame(this.name);
        this.frame.setSize(this.width, this.height);
        this.frame.setLayout(null);
        // frame.setResizable(false);
        this.frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        this.frame.setBackground(Color.darkGray);
        this.frame.setVisible(true);
    }

    // make a maze with the given input and preset parameters
    public void generateMaze(Maze maze, JPanel container, Color pathColor) {
        // empty the container
        container.removeAll();
        // start by getting the container and making a unit size
        int unitSizeWidth = container.getWidth() / (maze.getWidth() * 3);
        int unitSizeHeight = container.getHeight() / (maze.getHeight() * 3);
        System.out.println(unitSizeWidth);
        System.out.println(unitSizeHeight);
        // the maze itself
        MazeTile[][] template = maze.getMazeArray();
        // start the iteration!
        for (int i = 0; i < template.length; i++) {
            for (int j = 0; j < template[0].length; j++) {
                // make offsets
                int horizontalOffset = (j * 3 * unitSizeWidth);
                int verticalOffset = (i * 3 * unitSizeHeight);
                // check the vertical direction
                MazeCube vertical = new MazeCube(pathColor);
                if (template[i][j].isnPath() && template[i][j].issPath()) {
                    vertical.setBounds(horizontalOffset + unitSizeWidth, verticalOffset, unitSizeWidth,
                            unitSizeHeight * 3);
                } else if (template[i][j].isnPath()) {
                    vertical.setBounds(horizontalOffset + unitSizeWidth, verticalOffset, unitSizeWidth,
                            unitSizeHeight * 2);
                } else if (template[i][j].issPath()) {
                    vertical.setBounds(horizontalOffset + unitSizeWidth, verticalOffset + unitSizeHeight, unitSizeWidth,
                            unitSizeHeight * 2);
                }
                // check the horizontal direction
                MazeCube horizontal = new MazeCube(pathColor);
                if (template[i][j].isePath() && template[i][j].iswPath()) {
                    horizontal.setBounds(horizontalOffset, verticalOffset + unitSizeHeight, unitSizeWidth * 3,
                            unitSizeHeight);
                } else if (template[i][j].isePath()) {
                    horizontal.setBounds(horizontalOffset + unitSizeWidth, verticalOffset + unitSizeHeight,
                            unitSizeWidth * 2, unitSizeHeight);
                } else if (template[i][j].iswPath()) {
                    horizontal.setBounds(horizontalOffset, verticalOffset + unitSizeHeight, unitSizeWidth * 2,
                            unitSizeHeight);
                }
                if (template[i][j].getColor().equals("\u001B[34m")) {
                    vertical.setBackground(Color.blue);
                    horizontal.setBackground(Color.blue);
                }
                container.add(vertical);
                container.add(horizontal);
            }
        }

        // refresh the container
        container.setVisible(false);
        container.setVisible(true);
    }

    public Frame getFrame() {
        return this.frame;
    }

    public void refresh() {
        this.frame.pack();
        this.frame.setSize(this.width, this.height);
    }
}