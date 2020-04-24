package com;

import java.awt.*;
import javax.swing.*;
import com.maze.*;
import com.visual.*;
import com.visual.scene.*;

//The main controller for the entire game
public class GameDriver {
    public static void main(String[] args) {
        //populate the essential variable
        VisualController vc = new VisualController("Mazerunner", 500, 500);
        TestScene t = new TestScene();
        MenuScene m = new MenuScene();
        //this will
        vc.setScene(m);
        //vc.setScene(t);
    }
}