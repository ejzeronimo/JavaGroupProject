package com.visual.scene;

import javax.swing.*;
import com.visual.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

//the scene that will first start once the app loads
public class MenuScene implements Scene {

    // scene vars
    int optimalWidth;
    int optimalHeight;
    // actual visual vars
    JButton startButton;
    JLabel backLabel;
    JLabel titleLabel;
    // the images
    BufferedImage backgroundPicture;
    BufferedImage titlePicture;

    public MenuScene() {
        // set the background image
        try {
            backgroundPicture = ImageIO.read(new File("rainbowMaze.png"));
        } catch (IOException e) {
        }
        // set the title image
        try {
            titlePicture = ImageIO.read(new File("finalTitle.png"));
        } catch (IOException e) {
        }
        // set the default width and height
        this.optimalWidth = backgroundPicture.getWidth();
        this.optimalHeight = backgroundPicture.getHeight();
    }

    public void transitionIntoScene() {
        // fade in from black and load the spash screen

    }

    public void clearScene(VisualController v) {
        v.getFrame().remove(this.startButton);
        v.getFrame().remove(this.titleLabel);
        v.getFrame().remove(this.backLabel);
    }

    public void generateScene(VisualController v) {
        // set up the background image
        this.backLabel = new JLabel(new ImageIcon(backgroundPicture));
        backLabel.setBounds(0, 0, backgroundPicture.getWidth(), backgroundPicture.getHeight());
        // set up the title image
        this.titleLabel = new JLabel(new ImageIcon(titlePicture));
        titleLabel.setBounds((this.optimalWidth - titlePicture.getWidth()) / 2,
                (this.optimalHeight - titlePicture.getHeight()) / 3, titlePicture.getWidth(), titlePicture.getHeight());
        titleLabel.setBackground(Color.white);
        // set up the start button
        this.startButton = new JButton("Play");
        startButton.setFocusPainted(false);
        startButton.setForeground(Color.white);
        startButton.setFont(new Font("Impact", Font.PLAIN, 40));
        startButton.setBackground(Color.black);
        startButton.setBounds((this.optimalWidth - 150) / 2, (this.optimalHeight - 50) / 2, 150, 50);
        // render everything
        v.getFrame().add(startButton);
        v.getFrame().add(titleLabel);
        v.getFrame().add(backLabel);
        v.getFrame().setResizable(false);
        v.setDimensions(this.optimalWidth, this.optimalHeight);
        v.refresh();
    }

    public void onUpdate() {

    }

}