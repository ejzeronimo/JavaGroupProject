package com;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

import com.maze.*;
import com.visual.*;

//The main controller for the entire game
public class GameDriver {
    static VisualController visualController;
    static Maze maze = new Maze(10, 10);

    public static void main(String[] args) {

        // populate the visual controller and make the window
        visualController = new VisualController("PathFinder", 500, 500);

        //add the maze panel
        MazePanel mazePanel = new MazePanel(35);
        mazePanel.setBounds(100, 100, 960, 960);
        mazePanel.setBackground(Color.gray);
        visualController.getFrame().add(mazePanel);

        // add a button in the corner
        JButton button = new JButton("Generate");
        button.setBounds(0, 30, 100, 30);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // generate a new maze
                maze = new Maze(80, 80);
                //generate visual maze
                visualController.generateMaze(maze, mazePanel,Color.lightGray);
            }
        });
        visualController.getFrame().add(button);
    
        //refresh the pane
        visualController.refresh();
    }
}