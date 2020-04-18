package com;

import com.maze.Maze;

//The main controller for the entire game
public class GameDriver 
{
    static VisualController visualController;

    public static void main(String[] args) 
    {
        //populate the visual controller and make the window 
        visualController = new VisualController("WindowName",500,500);
        visualController.createWindow();

        // Build maze
        Maze maze = new Maze(10, 10);
        //MazeTile.printToConsole(maze);

    }
}