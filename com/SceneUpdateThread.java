package com;

import com.visual.scene.*;

public class SceneUpdateThread implements Runnable {
    private Scene s;

    public SceneUpdateThread() {
    }

    public void run() {
        while (true) {
            try {
                s.onUpdate();
            } catch (NullPointerException e) {

            }
        }
    }

    public void setScene(Scene s) {
        this.s = s;
    }
}