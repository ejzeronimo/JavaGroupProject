package com.visual;

import javax.swing.*;
import java.awt.*;

public class MazeCube extends JPanel 
{
    public MazeCube(Color background) {
        super();
        setLayout(null);
        setBackground(background);
        //backgroundColor = bgColor;
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // do your superclass's painting routine first, and then paint on top of it.
        g.setColor(getBackground());
        g.fillRect(0,0,getWidth(),getHeight());
    }
}