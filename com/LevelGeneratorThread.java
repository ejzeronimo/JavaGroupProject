package com;

import java.awt.Color;
import com.visual.*;
import com.visual.scene.*;

public class LevelGeneratorThread implements Runnable {

    VisualController vc;
    LevelScene currentLevel;
    Thread thread;
    SceneUpdateThread sceneThread;
    Color[] rainbow = { Color.red, Color.orange, Color.yellow, Color.green, Color.blue, Color.pink, Color.magenta,
            Color.cyan };

    public LevelGeneratorThread(VisualController v) {
        this.vc = v;
        // the scene update thread stuff
        sceneThread = new SceneUpdateThread();
        thread = new Thread(sceneThread);
    }

    public void run() {
        // just for kicks
        vc.getFrame().setResizable(true);
        // set both the screen and thread to the loading scene
        vc.setScene(vc.loadingScene);
        sceneThread.setScene(vc.loadingScene);
        // start the thread
        this.thread.start();
        // start generation after the thread has been made
        currentLevel = new LevelScene(vc, 5, 1.5f, Color.white, Color.green, Color.black);
        // set the game to current level
        thread.interrupt();
        sceneThread.setScene(currentLevel);
        vc.setScene(currentLevel);
        // gameloop
        while (true) {
            if (currentLevel.getBeaten()) {
                // move to loading scene
                thread.interrupt();
                sceneThread.setScene(vc.loadingScene);
                vc.setScene(vc.loadingScene);
                // up the complexity
                if (GameDriver.levelComplexity < 70) {
                    GameDriver.levelComplexity++;
                }
                // choose a ratio
                double ratio = Math.random() * 2.5;
                // start generation after the thread has been made
                currentLevel = new LevelScene(vc, GameDriver.levelComplexity, (float) Math.max(1, ratio), Color.white,
                        rainbow[(int) (Math.random() * 7)], Color.black);
                // move to game scene
                thread.interrupt();
                sceneThread.setScene(currentLevel);
                vc.setScene(currentLevel);
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }

        }
    }

}