package com.visual.scene;

import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.*;
import com.visual.*;

public class LoadingScene implements Scene {
    // the visual objects
    JLabel loadingAnimation;
    // the loading gif
    ImageIcon loadingGif;
    // boolean flag
    // public var
    AtomicBoolean isToast = new AtomicBoolean(false);

    public LoadingScene() {

        loadingGif = new ImageIcon(this.getClass().getResource("/images/loadingGif.gif"));
        // set the loading gif
    }

    public void generateScene(VisualController v) {
        setToast(true);
        // set the fram color to black bc why not
        v.getFrame().setBackground(Color.black);
        // set up the loading image
        loadingAnimation = new JLabel();
        loadingAnimation.setIcon(loadingGif);
        loadingAnimation.setBounds((v.getFrame().getWidth() - loadingGif.getIconWidth()) / 2,
                (v.getFrame().getHeight() - loadingGif.getIconHeight()) / 2, loadingGif.getIconWidth(),
                loadingGif.getIconHeight());
        // add and refresh the frame
        v.getFrame().add(this.loadingAnimation);
        v.setDimensions(v.getFrame().getWidth(), v.getFrame().getHeight());
        v.refresh();
    }

    public void clearScene(VisualController v) {
        this.setToast(true);
        v.getFrame().remove(this.loadingAnimation);
    }

    public boolean getToast() {
        return this.isToast.get();
    }

    public void setToast(boolean b) {
        this.isToast.set(b);
    }

    public void onUpdate() {
        try {
            this.loadingAnimation.paint(this.loadingAnimation.getGraphics());
        } catch (NullPointerException e) {

        }
    }

}