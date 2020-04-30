package com;

import java.awt.*;
import javax.swing.*;
import com.visual.*;
import com.visual.scene.*;
import java.awt.event.*;

//The main controller for the entire game
public class GameDriver {

    static boolean gameLoop = false;
    static int levelComplexity = 60;

    public static void main(String[] args) {
        // populate the essential variable
        VisualController vc = new VisualController("Mazerunner", 500, 500);
        // the global scenes
        LoadingScene loadingScene = new LoadingScene();
        MenuScene menuScene = new MenuScene();
        // this will set the defaults
        vc.setMenuScene(menuScene);
        vc.setLoadingScene(loadingScene);
        // set the scene to the menu
        vc.setScene(menuScene);
        menuScene.startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // start level
                GameDriver.startGame(vc);
            }
        });
    }

    public static void startGame(VisualController v) {
        SceneUpdateThread temp = new SceneUpdateThread();

        // set to loading scene
        v.setScene(v.loadingScene);
        temp.setScene(v.loadingScene);
        //make a thread so that the scenes can run their update methods
        Thread thread = new Thread(temp);
        thread.start();

        LevelScene currentLevel = new LevelScene(levelComplexity,1.5f);

        temp.setScene(null);

        v.setScene(currentLevel);
    }
}