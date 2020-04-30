package com.visual.scene;

import java.awt.*;
import javax.swing.*;
import com.visual.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class LoadingScene implements Scene {
    //the visual objects
    JLabel loadingAnimation;
    //the loading gif
    ImageIcon loadingGif;

    public LoadingScene() {

        loadingGif = new ImageIcon(this.getClass().getResource("./../../../loadingGif.gif"));
        // set the loading gif
    }

    public void generateScene(VisualController v) {
        //set the fram color to black bc why not
        v.getFrame().setBackground(Color.black);
        // set up the loading image
        this.loadingAnimation = new JLabel();
        //this.loadingAnimation.setDoubleBuffered(true);
        this.loadingAnimation.setIcon(loadingGif);
        this.loadingAnimation.setBounds((v.getFrame().getWidth() - loadingGif.getIconWidth())/2, (v.getFrame().getHeight() - loadingGif.getIconHeight()) /2, loadingGif.getIconWidth(), loadingGif.getIconHeight());
        //add and refresh the frame
        v.getFrame().add(loadingAnimation);
        v.refresh();
    }

    public void clearScene(VisualController v) {
        v.getFrame().remove(this.loadingAnimation);
    }

    public void onUpdate() {
        this.loadingAnimation.paint(this.loadingAnimation.getGraphics());
    }

}