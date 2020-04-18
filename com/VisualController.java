package com;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

//the controll for the actual looks of the GUI
public class VisualController 
{
    //the vusal aspects of the window
    Frame frame;
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
        frame = new Frame(this.name);
        frame.setSize(this.width,this.height);
        frame.setLayout(null);
        frame.addWindowListener(new WindowAdapter()
         {
            public void windowClosing(WindowEvent windowEvent)
            {
               System.exit(0);
            }        
         });  

        frame.setVisible(true);
    }
    
}