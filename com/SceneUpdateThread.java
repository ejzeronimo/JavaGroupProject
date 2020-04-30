package com;

import com.maze.*;
import com.visual.scene.*;

public class SceneUpdateThread implements Runnable {

    Scene s;

    public SceneUpdateThread() {
    }

    public void run() {
        while (true) {
            if (s != null) {
                s.onUpdate();
            }
        }
    }

    public void setScene(Scene s) {
        this.s = s;
    }

}