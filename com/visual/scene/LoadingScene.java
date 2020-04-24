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
    BufferedImage loadingGif;

    public LoadingScene() {
        // set the loading gif
        try {
            loadingGif = ImageIO.read(new File("loadingGif.png"));
        } catch (IOException e) {
        }
    }

    public void generateScene(VisualController v) {
        //set the fram color to black bc why not
        v.getFrame().setBackground(Color.black);
        // set up the loading image
        this.loadingAnimation = new JLabel(new ImageIcon(loadingGif));
        loadingAnimation.setBounds(v.getFrame().getWidth() - loadingGif.getWidth() - 5, v.getFrame().getHeight() - loadingGif.getHeight() - 5, loadingGif.getWidth(), loadingGif.getHeight());
        //add and refresh the frame
        v.getFrame().add(loadingAnimation);
        v.refresh();
    }

    public void clearScene(VisualController v) {
        v.getFrame().remove(this.loadingAnimation);

    }

    public void onUpdate() {
        //we will increment through this as the scene loads
    }

}