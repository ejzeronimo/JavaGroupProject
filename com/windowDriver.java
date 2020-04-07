package com;

import java.awt.*;
import java.awt.event.*;  
import java.awt.event.WindowEvent;  
import java.awt.event.WindowListener;

class windowDriver extends WindowAdapter {

    static Frame mainWindow;
    static String windowTitle = "Testing Testing...";

    //constructor is used to populate the parameters of the frame
    windowDriver() {
        // make the window and add this class as a listener
        mainWindow = new Frame();
        mainWindow.addWindowListener(this);
        //set the layout to null so we can place things customly
        mainWindow.setLayout(null);
        //set the size of the window
        mainWindow.setSize(600, 600);
        //set the title
        mainWindow.setTitle(windowTitle);
        //set the background color
        mainWindow.setBackground(Color.BLUE);
        //make the window visible
        mainWindow.setVisible(true);
    }

    //methods that are not needed yet
    public void windowActivated(WindowEvent e) {}  
    public void windowClosed(WindowEvent e) {}  

    //an AWT method that allows the window to close
    public void windowClosing(WindowEvent event) {
        mainWindow.dispose();
    }

    public static void main(String args[]) {
        // make this class because it houses the main system for the frame
        new windowDriver();
        // make a button to test
        Button button = new Button("Pump Up those Kicks");
        button.setBounds(30, 100, 80, 30);
        // add the button
        mainWindow.add(button);

        //the main loop
        while(true)
        {
            
        }
    }
}