package com;

import com.visual.*;
import com.visual.scene.*;
import java.awt.event.*;

//The main controller for the entire game
public class GameDriver {

    static int levelComplexity = 5;

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
                if (Integer.parseInt(menuScene.startComplexity.getText()) <= 70) {
                    GameDriver.levelComplexity = Integer.parseInt(menuScene.startComplexity.getText());
                } else {
                    GameDriver.levelComplexity = 5;
                }
                // start the game with the default level complexity
                GameDriver.startGame(vc);
            }
        });
    }

    public static void startGame(VisualController v) {
        LevelGeneratorThread temp = new LevelGeneratorThread(v);
        Thread thread = new Thread(temp);
        // set both the screen and thread to the loading scene
        // start the thread
        thread.start();
    }
}