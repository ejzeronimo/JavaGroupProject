package com;

import java.awt.*;
import javax.swing.*;

//the controll for the actual looks of the GUI
public class VisualController 
{
    //the vusal aspects of the window
    JFrame frame;
    //the parameters
    String name;
    int width;
    int height;

    VisualController(String name, int width, int height)
    {
        this.name = name;
        this.width = width;
        this.height = height;
    }

    public void createWindow()
    {
        frame = new JFrame(this.name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(this.width, this.height));
        frame.pack();
        frame.setVisible(true);
    }
    
}