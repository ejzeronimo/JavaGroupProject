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
    public JButton startButton;// public so the driver can add a listener
    public JTextField startComplexity;
    JLabel backLabel;
    JLabel titleLabel;
    // the images
    BufferedImage backgroundPicture;
    BufferedImage titlePicture;

    public MenuScene() {
        // set the background image
        try {
            backgroundPicture = ImageIO.read(this.getClass().getResource("/images/rainbowMaze.png"));
            titlePicture = ImageIO.read(this.getClass().getResource("/images/finalTitle.png"));
        } catch (IOException e) {
        }
        // set the default width and height
        optimalWidth = backgroundPicture.getWidth();
        optimalHeight = backgroundPicture.getHeight();
    }

    public void generateScene(VisualController v) {
        // set up the background image
        this.backLabel = new JLabel(new ImageIcon(backgroundPicture));
        backLabel.setBounds(0, 0, backgroundPicture.getWidth(), backgroundPicture.getHeight());
        // set up the title image
        this.titleLabel = new JLabel(new ImageIcon(titlePicture));
        titleLabel.setBounds((optimalWidth - titlePicture.getWidth()) / 2,
                (optimalHeight - titlePicture.getHeight()) / 3, titlePicture.getWidth(), titlePicture.getHeight());
        titleLabel.setBackground(Color.white);
        // set up the start button
        this.startButton = new JButton("Play");
        startButton.setFocusPainted(false);
        startButton.setForeground(Color.white);
        startButton.setFont(new Font("Impact", Font.PLAIN, 40));
        startButton.setBackground(Color.black);
        startButton.setBounds((optimalWidth - 150) / 2, (optimalHeight - 50) / 2, 150, 50);
        // make the complexity button
        this.startComplexity = new JTextField("5");
        startComplexity.setHorizontalAlignment(JTextField.CENTER);
        startComplexity.setForeground(Color.white);
        startComplexity.setFont(new Font("Impact", Font.PLAIN, 40));
        startComplexity.setBackground(Color.black);
        startComplexity.setBounds((optimalWidth - 150) / 2, (optimalHeight - 50) / 2 + 70, 150, 50);
        // render everything
        v.getFrame().add(startComplexity);
        v.getFrame().add(startButton);
        v.getFrame().add(titleLabel);
        v.getFrame().add(backLabel);
        v.getFrame().setResizable(false);
        v.setDimensions(optimalWidth, optimalHeight);
        v.refresh();
    }

    public void clearScene(VisualController v) {
        v.getFrame().remove(this.startComplexity);
        v.getFrame().remove(this.startButton);
        v.getFrame().remove(this.titleLabel);
        v.getFrame().remove(this.backLabel);
    }

    public void onUpdate() {

    }

}