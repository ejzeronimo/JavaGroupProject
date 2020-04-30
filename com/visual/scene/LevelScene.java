package com.visual.scene;

import com.maze.*;
import com.visual.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LevelScene implements Scene {

    MazePanel mazePanel;
    JButton mazeGenerationButton;
    Maze maze;
    int unitSizeWidth = 0;
    int unitSizeHeight = 0;
    int panelWidth;
    int panelHeight;

    public LevelScene(int complexity, float aspect) {
        //make the maze
        this.maze = new Maze((int) (complexity * aspect), complexity);
        // once done calc optimal size
        this.scaleMaze();
        // once done here generate scene
        mazePanel = new MazePanel();
        // set the maze panel params
        mazePanel.setSize(panelWidth, panelHeight);
        // generate the maze
        this.generateMaze(this.maze, this.mazePanel, Color.white);
    }

    void scaleMaze() {
        // the max size of the maze (20 less than max)
        int maxWidth = 880;
        int maxHeight = 580;
        // get the amount of channels
        int channelAmountWidth = (this.maze.getWidth() * 3);
        int channelAmountHeight = (this.maze.getHeight() * 3);
        // begin calc
        for (int i = 1; i < 21; i++) {
            if ((channelAmountWidth * i <= maxWidth) && (channelAmountHeight * i <= maxHeight)) {
                // check to see if larger than current unitsize
                if ((i > unitSizeWidth) && (i > unitSizeHeight)) {
                    unitSizeWidth = i;
                    unitSizeHeight = i;
                    panelWidth = channelAmountWidth * i;
                    panelHeight = channelAmountHeight * i;
                }
            }
        }
        System.out.println("Channel width: " + unitSizeWidth);
        System.out.println("Channel height: " + unitSizeHeight);
    }

    public void generateScene(VisualController v) {
        // set the location
        mazePanel.setLocation((v.getFrame().getWidth() - panelWidth) / 2, (v.getFrame().getHeight() - panelHeight) / 2);
        mazePanel.setBackground(Color.gray);
        // add to the scene
        v.getFrame().add(this.mazePanel);
        v.refresh();
    }

    public void clearScene(VisualController v) {
        v.getFrame().remove(this.mazePanel);
    }

    public void onUpdate() {
        
    }

    // make a maze with the given input and preset parameters
    public void generateMaze(Maze maze, MazePanel container, Color pathColor) {
        System.out.println("Generating visual maze");
        container.setVisible(false);
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
                    // vertical.setBackground(Color.blue);
                    // horizontal.setBackground(Color.blue);
                }
                container.add(vertical);
                container.add(horizontal);
            }
        }
        // refresh the container
        // container.setVisible(false);
        container.setVisible(true);
    }
}