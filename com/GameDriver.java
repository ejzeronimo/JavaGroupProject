package com;

import java.awt.*;
import javax.swing.*;
import com.visual.*;
import com.visual.scene.*;

//The main controller for the entire game
public class GameDriver {
    public static void main(String[] args) {
        //populate the essential variable
        VisualController vc = new VisualController("Mazerunner", 500, 500);
        //the global scenes
        MenuScene menuScene = new MenuScene();
        LoadingScene levelScene = new LoadingScene();
        LevelScene gameScene = new LevelScene();
        //this will
        vc.setScene(menuScene);
    }
}