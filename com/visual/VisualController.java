package com.visual;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import com.maze.*;
import com.visual.scene.*;

import java.awt.event.*;

//the controll for the actual looks of the GUI
public class VisualController {
    // the visual aspects of the window
    ImageIcon logo;
    Frame frame;
    String name;
    // the parameters
    int width;
    int height;
    //the static scenes
    public LoadingScene loadingScene;
    public MenuScene menuScene;
    // the scenes
    Scene currentScene;

    public VisualController(String name, int width, int height) {
        // set params of the window
        this.name = name;
        this.width = width;
        this.height = height;
        // get the logo
        this.logo = new ImageIcon("logo.png");

        // make the window
        this.frame = new Frame(this.name);
        this.frame.setSize(this.width, this.height);
        this.frame.setLayout(null);
        this.frame.setIconImage(this.logo.getImage());
        this.frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        this.frame.addMouseMotionListener(new MouseInputAdapter() {

        });
        this.frame.setBackground(Color.darkGray);
        this.frame.setVisible(true);
    }

    public Frame getFrame() {
        return this.frame;
    }

    public void setDimensions(int w, int h)
    {
        this.width = w;
        this.height = h;
    }

    public void setScene(Scene s) {
        if(this.currentScene != null)
        {   
            this.currentScene.clearScene(this);
        }
        this.currentScene = s;
        s.generateScene(this);
    }

    public void setLoadingScene(LoadingScene s)
    {
        this.loadingScene = s;
    }
    
    public void setMenuScene(MenuScene s)
    {
        this.menuScene = s;
    } 

    public void refresh() {
        this.frame.pack();
        this.frame.setSize(this.width, this.height);
    }
}