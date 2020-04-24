package com.visual.scene;

import com.maze.*;
import com.visual.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LevelScene implements Scene {

    MazePanel testMaze;
    JButton mazeGenerationButton;
    Maze maze = new Maze(10, 10);

    public LevelScene()
    {

    }


    public void generateScene(VisualController v) {
        // add the maze panel
        testMaze = new MazePanel(35);
        testMaze.setBounds(100, 100, 240, 240);
        testMaze.setBackground(Color.black);
        v.getFrame().add(testMaze);
        // add a button in the corner
        JButton button = new JButton("Generate");
        button.setBounds(0, 30, 100, 30);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // generate a new maze
                maze = new Maze(10, 10);
                v.generateMaze(maze, testMaze, Color.white);
            }
        });
        v.getFrame().add(button);

    }

    public void clearScene(VisualController v) {

    }

    public void onUpdate() {

    }
}