package com.visual.scene;

import com.visual.VisualController;

// an interface for the overal outline of a scene
public interface Scene {

    public void generateScene(VisualController v);

    public void clearScene(VisualController v);

    public void onUpdate();
}