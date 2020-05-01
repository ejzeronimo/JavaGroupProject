package com.visual.scene;

import com.maze.*;
import com.visual.*;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class LevelScene implements Scene {

    // visual comps
    MazePanel mazePanel;
    MazeCube endGoal;
    JButton mazeGenerationButton;
    JPanel mazeRunner;
    Maze maze;
    // maze gen params
    int unitSizeWidth = 0;
    int unitSizeHeight = 0;
    int panelWidth;
    int panelHeight;
    // the colors
    Color background = Color.gray;
    Color path = Color.lightGray;
    Color cursor = Color.GREEN;
    // public var
    AtomicBoolean isBeaten = new AtomicBoolean(false);

    public LevelScene(VisualController v, int complexity, float aspect, Color path, Color cursor, Color background) {
        // set the params
        this.background = background;
        this.path = path;
        this.cursor = cursor;
        // make the maze
        maze = new Maze((int) (complexity * aspect), complexity);
        // once done calc optimal size
        this.scaleMaze(v.getFrame().getWidth(), v.getFrame().getHeight());
        // set the cursor params
        mazeRunner = new JPanel();
        mazeRunner.setBackground(cursor);
        mazeRunner.setSize((int) (unitSizeWidth / 2), (int) (unitSizeHeight / 2));
        // set the maze panel params
        mazePanel = new MazePanel();
        mazePanel.setSize(panelWidth, panelHeight);
        // add the cursor
        mazePanel.add(mazeRunner);
        mazeRunner.setLocation(mazePanel.getWidth() - (int) (unitSizeWidth * .75),
                mazePanel.getHeight() - (int) (unitSizeWidth * 1.75));
        // add the end goal
        this.endGoal = new MazeCube(cursor);
        endGoal.setBounds(0, unitSizeHeight, unitSizeWidth, unitSizeHeight);
        mazePanel.add(endGoal);
        // generate the maze
        this.generateMaze(this.maze, this.mazePanel, path);
    }

    private void scaleMaze(int w, int h) {
        // the max size of the maze (20 less than max)
        int maxWidth = w - 20;
        int maxHeight = h - 40;
        // get the amount of channels
        int channelAmountWidth = (this.maze.getWidth() * 3);
        int channelAmountHeight = (this.maze.getHeight() * 3);
        // begin calc for best size
        for (int i = 1; i < 21; i++) {
            if ((channelAmountWidth * i <= maxWidth) && (channelAmountHeight * i <= maxHeight)) {
                // check to see if larger than current unitsize
                if ((i > unitSizeWidth) && (i > unitSizeHeight)) {
                    unitSizeWidth = i;
                    unitSizeHeight = i;
                    panelWidth = channelAmountWidth * i;
                    panelHeight = channelAmountHeight * i;
                }
            }
        }
    }

    private boolean withinMargin(int p, int c, int m) {
        if (p >= c - m && p <= c + m) {
            return true;
        }
        return false;
    }

    public void generateScene(VisualController v) {
        // set the location
        mazePanel.setLocation((v.getFrame().getWidth() - panelWidth) / 2,
                ((v.getFrame().getHeight() - panelHeight) / 2) + 10);
        mazePanel.setBackground(background);
        // add a mouse listener
        mazePanel.addMouseMotionListener(new MouseInputAdapter() {
            public void mouseMoved(MouseEvent e) {
                runnerController(e);
            }

            int lastX = -1;
            int lastY = -1;

            public void runnerController(MouseEvent e) {
                if (lastX == -1 && lastY == -1 && mazePanel.getComponentAt(e.getPoint()).equals(mazeRunner)) {
                    lastX = e.getX();
                    lastY = e.getY();
                } else if (mazePanel.getComponentAt(e.getPoint()) instanceof MazeCube) {
                    if (withinMargin(e.getX(), lastX, unitSizeWidth / 2)
                            && withinMargin(e.getY(), lastY, unitSizeHeight / 2)) {
                        lastX = e.getX();
                        lastY = e.getY();
                        mazeRunner.setLocation(e.getX() - (mazeRunner.getWidth() / 2),
                                e.getY() - (mazeRunner.getHeight() / 2));
                    }
                }
                if (endGoal.getBounds().intersects(mazeRunner.getBounds())) {
                    setBeaten(true);
                }
            }
        });
        // add to the scene
        v.getFrame().add(this.mazePanel);
        v.getFrame().setBackground(cursor);
        v.setDimensions(v.getFrame().getWidth(), v.getFrame().getHeight());
        v.refresh();
    }

    public void clearScene(VisualController v) {
        v.getFrame().remove(this.mazePanel);
    }

    public void onUpdate() {
        try {
            if (this.endGoal.getBounds().intersects(this.mazeRunner.getBounds())) {
                setBeaten(true);
            }
            Thread.sleep(10);
        } catch (InterruptedException e) {
        }
    }

    public boolean getBeaten() {
        return this.isBeaten.get();
    }

    public void setBeaten(boolean b) {
        this.isBeaten.set(b);
    }

    // make a maze with the given input and preset parameters
    public void generateMaze(Maze maze, MazePanel container, Color pathColor) {
        container.setVisible(false);
        // the maze itself
        MazeTile[][] template = maze.getMazeArray();
        // start the iteration!
        for (int i = 0; i < template.length; i++) {
            for (int j = 0; j < template[0].length; j++) {
                // make offsets
                int horizontalOffset = (j * 3 * unitSizeWidth);
                int verticalOffset = (i * 3 * unitSizeHeight);
                // check the vertical direction
                MazeCube vertical = new MazeCube(pathColor);
                if (template[i][j].isnPath() && template[i][j].issPath()) {
                    vertical.setBounds(horizontalOffset + unitSizeWidth, verticalOffset, unitSizeWidth,
                            unitSizeHeight * 3);
                } else if (template[i][j].isnPath()) {
                    vertical.setBounds(horizontalOffset + unitSizeWidth, verticalOffset, unitSizeWidth,
                            unitSizeHeight * 2);
                } else if (template[i][j].issPath()) {
                    vertical.setBounds(horizontalOffset + unitSizeWidth, verticalOffset + unitSizeHeight, unitSizeWidth,
                            unitSizeHeight * 2);
                }
                // check the horizontal direction
                MazeCube horizontal = new MazeCube(pathColor);
                if (template[i][j].isePath() && template[i][j].iswPath()) {
                    horizontal.setBounds(horizontalOffset, verticalOffset + unitSizeHeight, unitSizeWidth * 3,
                            unitSizeHeight);
                } else if (template[i][j].isePath()) {
                    horizontal.setBounds(horizontalOffset + unitSizeWidth, verticalOffset + unitSizeHeight,
                            unitSizeWidth * 2, unitSizeHeight);
                } else if (template[i][j].iswPath()) {
                    horizontal.setBounds(horizontalOffset, verticalOffset + unitSizeHeight, unitSizeWidth * 2,
                            unitSizeHeight);
                }
                if (template[i][j].getColor().equals("\u001B[34m")) {
                    // vertical.setBackground(Color.blue);
                    // horizontal.setBackground(Color.blue);
                }
                container.add(vertical);
                container.add(horizontal);
            }
        }
        // refresh the container
        container.setVisible(true);
    }
}