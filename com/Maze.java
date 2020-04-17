package com;

import java.util.ArrayList;
import java.util.Random;

public class Maze {
    private int width;
    private int height;
    private MazeTile[][] mazeArray;

    public Maze(int width, int height) {
        this.width = width;
        this.height = height;
        mazeArray = new MazeTile[height][width];
        this.buildMaze();
    }

    private void buildMaze() {
        // Build solution route one piece at a time
        // Start at the xLocal and yLocal
        int xLocal = 0;
        int yLocal = 0;

        boolean notAtFinishPoint = true;

        while (notAtFinishPoint) { // Place pieces until the maze is built, or makes a circle and has to start over
            // Clear mazeArray of all objects other than the starting tile and start the placement local at 0,0
            for (int i = 0; i < mazeArray[0].length; i++) {
                for (int j = 0; j < mazeArray.length; j++) {
                    mazeArray[j][i] = null;
                }
            }
            yLocal = 0;
            xLocal = 0;
            int previousXLocal = 0;
            int previousYLocal = 0;
            mazeArray[yLocal][xLocal] = new NWCornerTile();

            while (yLocal != height && xLocal != width) {
                // Verify that the maze is not circled in
                boolean nSpaceAvailable = false;
                boolean eSpaceAvailable = false;
                boolean sSpaceAvailable = false;
                boolean wSpaceAvailable = false;

                // Validate that north is possible
                if (yLocal != 0 && xLocal != 0 && xLocal != width - 1) {
                    // Verify that there is not a tile to the north already
                    if (mazeArray[yLocal - 1][xLocal] == null) {
                        nSpaceAvailable = true;
                    }
                }
                // Validate that east is available
                // Validate that the placement local is not all the way east already
                if (xLocal != width - 1) {
                    // Verify that there is not a tile to the east already
                    if (mazeArray[yLocal][xLocal + 1] == null) {
                        eSpaceAvailable = true;
                    }
                }
                // Validate that south is available
                // Validate that the placement local is not all the way south already
                if (yLocal != height - 1) {
                    // Verify that there is not a tile to the south already
                    if (mazeArray[yLocal + 1][xLocal] == null) {
                        sSpaceAvailable = true;
                    }
                }
                // Validate that west is available
                // Validate that the placement local is not all the way west, south, or north
                if (xLocal != 0 && yLocal != 0 && yLocal != height - 1) {
                    // Verify that there is not a tile to the east already
                    if (mazeArray[yLocal][xLocal - 1] == null) {
                        wSpaceAvailable = true;
                    }
                }
                // If a circle has been made with the pieces, break this while loop
                if ((!nSpaceAvailable) && (!eSpaceAvailable) && (!sSpaceAvailable) && (!wSpaceAvailable)) {
                    break;
                }

                // Randomly chooses next direction to head 1,2,3,4 = N,S,E,W
                previousXLocal = xLocal;
                previousYLocal = yLocal;
                Random rnd = new Random();
                boolean nextLocalChosen = false;
                int nextDirection;
                while (!nextLocalChosen) {
                    nextDirection = rnd.nextInt(4) + 1;

                    // Check if that direction is a valid place to place the next tile

                    // Validate north is available if 1 is chosen randomly
                    if (nextDirection == 1) {
                        // Validate that your placement local is not all the way north, west, or east
                        if (yLocal != 0 && xLocal != 0 && xLocal != width - 1) {
                            // Verify that there is not a tile to the north already
                            if (mazeArray[yLocal - 1][xLocal] == null) {
                                yLocal--;
                                nextLocalChosen = true;
                            }
                        }
                        // Validate that east is available if 2 is chosen
                    } else if (nextDirection == 2) {
                        // Validate that the placement local is not all the way east already
                        if (xLocal != width - 1) {
                            // Verify that there is not a tile to the east already
                            if (mazeArray[yLocal][xLocal + 1] == null) {
                                xLocal++;
                                nextLocalChosen = true;
                            }
                        }
                        // Validate that south is available if 3 is chosen
                    } else if (nextDirection == 3) {
                        // Validate that the placement local is not all the way south already
                        if (yLocal != height - 1) {
                            // Verify that there is not a tile to the south already
                            if (mazeArray[yLocal + 1][xLocal] == null) {
                                yLocal++;
                                nextLocalChosen = true;
                            }
                        }
                        // Validate that west is available if 4 is chosen
                    } else if (nextDirection == 4) {
                        // Validate that the placement local is not all the way west, south, or north
                        if (xLocal != 0 && yLocal != 0 && yLocal != height - 1) {
                            // Verify that there is not a tile to the east already
                            if (mazeArray[yLocal][xLocal - 1] == null) {
                                xLocal--;
                                nextLocalChosen = true;
                            }
                        }
                    }
                } // Next placement local has been chosen

                // Place new tile and disconnect the tile object from paths it can't connect to
                // Place north corner or side tile
                if (yLocal == 0) {
                    if (xLocal == width - 1) {
                        mazeArray[yLocal][xLocal] = new NECornerTile();
                    } else {
                        mazeArray[yLocal][xLocal] = new NSideTile();
                        // Disconnect from all adjacent tiles but one
                        // Disconnect from south tile
                        if ((mazeArray[yLocal + 1][xLocal] != null) &&
                                (yLocal + 1 != previousYLocal)) {
                            mazeArray[yLocal][xLocal].setsPath(false);
                        }
                        // Disconnect from west tile
                        if (xLocal >= 1) {
                            if (mazeArray[yLocal][xLocal - 1] != null &&
                                    xLocal - 1 != previousXLocal) {
                                mazeArray[yLocal][xLocal].setwPath(false);
                            }
                        }
                    }
                }
                // Place east side tile
                if (xLocal == width - 1 && yLocal != 0) {
                    mazeArray[yLocal][xLocal] = new ESideTile();
                    // Disconnect from tile to the west unless it just came from the west
                    if (mazeArray[yLocal][xLocal - 1] != null &&
                            xLocal - 1 != previousXLocal) {
                        mazeArray[yLocal][xLocal].setwPath(false);
                    }
                    if (yLocal > height) {
                        if (mazeArray[yLocal + 1][xLocal] != null) {
                            mazeArray[yLocal][xLocal].setsPath(false);
                        }
                    }
                }
                // Place west side and corner tile
                if (xLocal == 0) {
                    if (yLocal == height - 1) {
                        mazeArray[yLocal][xLocal] = new SWCornerTile();
                    } else {
                        mazeArray[yLocal][xLocal] = new WSideTile();
                        // Disconnect from east tile unless it just came from there
                        if (mazeArray[yLocal][xLocal + 1] != null &&
                                xLocal + 1 != previousXLocal) {
                            mazeArray[yLocal][xLocal].setePath(false);
                        }
                        // Disconnect from north tile unless that's where it just came from
                        if (mazeArray[yLocal - 1][xLocal] != null &&
                                yLocal - 1 != previousYLocal) {
                            mazeArray[yLocal][xLocal].setnPath(false);
                        }
                    }
                }
                // Place south side tile
                if (yLocal == height - 1 && xLocal != 0) {
                    mazeArray[yLocal][xLocal] = new SSideTile();
                    // Disconnect from north tile unless thats where we just came from
                    if (mazeArray[yLocal - 1][xLocal] != null &&
                            yLocal - 1 != previousYLocal) {
                        mazeArray[yLocal][xLocal].setnPath(false);
                    }
                    // Disconnect from west tile unless thats where we just came from
                    if (mazeArray[yLocal][xLocal - 1] != null &&
                            xLocal - 1 != previousXLocal) {
                        mazeArray[yLocal][xLocal].setwPath(false);
                    }
                }
                // Place center tile
                if (yLocal > 0 && yLocal < height - 1 &&
                        xLocal > 0 && xLocal < width - 1) {
                    mazeArray[yLocal][xLocal] = new CenterTile();
                    // Check for tiles to the north to disconnect from
                    if (mazeArray[yLocal - 1][xLocal] != null &&
                            yLocal - 1 != previousYLocal) {
                        mazeArray[yLocal][xLocal].setnPath(false);
                    }
                    // Check for tiles to the east to disconnect from
                    if (mazeArray[yLocal][xLocal + 1] != null &&
                            xLocal + 1 != previousXLocal) {
                        mazeArray[yLocal][xLocal].setePath(false);
                    }
                    // Check for tiles to the south to disconnect from
                    if (mazeArray[yLocal + 1][xLocal] != null &&
                            yLocal + 1 != previousYLocal) {
                        mazeArray[yLocal][xLocal].setsPath(false);
                    }
                    // Check for tiles to the west to disconnect from
                    if (mazeArray[yLocal][xLocal - 1] != null &&
                            xLocal - 1 != previousXLocal) {
                        mazeArray[yLocal][xLocal].setwPath(false);
                    }
                }
            }
            if (xLocal == width - 1 && yLocal == height - 1) {
                notAtFinishPoint = false;
            }
        }

        // TODO: COLORING TILES FOR DEBUGGING
        // Give a color to each of the correct path tiles
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (mazeArray[i][j] != null) {
                    mazeArray[i][j].setColor("\u001B[34m");
                }

            }

        }

        // ::Fill in tiles that are part of the incorrect route::
        // Loop through all empty maze spaces
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Find null part of matrix (empty spot in maze)
                if (mazeArray[y][x] == null) {
                    // Place appropriate tile
                    mazeArray[y][x] = getAppropriateTile(y, x);
                    // find how many adjoining tiles there are and where they are
                    ArrayList<String> adjoiningTileDirection = new ArrayList<>();
                    if (y > 0) {    // Check for tiles to the north
                        if (mazeArray[y - 1][x] != null) {
                            adjoiningTileDirection.add("north");
                        }
                    }
                    if (y < height - 1) {   // Check for tiles to the south
                        if (mazeArray[y + 1][x] != null) {
                            adjoiningTileDirection.add("south");
                        }
                    }
                    if (x > 0) {    // Check for tiles to the west
                        if (mazeArray[y][x - 1] != null) {
                            adjoiningTileDirection.add("west");
                        }
                    }
                    if (x < width - 1) {  // Check for tiles to the east
                        if (mazeArray[y][x + 1] != null) {
                            adjoiningTileDirection.add("east");
                        }
                    }
                    // Connect tile randomly to ONLY one other tile
                    Random rnd = new Random();
                    String directionToGo = adjoiningTileDirection.get(rnd.nextInt(adjoiningTileDirection.size()));
                    switch (directionToGo) {
                        case "north":
                            mazeArray[y][x].setePath(false);
                            mazeArray[y][x].setsPath(false);
                            mazeArray[y][x].setwPath(false);
                            mazeArray[y - 1][x].setsPath(true);
                            break;
                        case "south":
                            mazeArray[y][x].setePath(false);
                            mazeArray[y][x].setnPath(false);
                            mazeArray[y][x].setwPath(false);
                            mazeArray[y + 1][x].setnPath(true);
                            break;
                        case "east":
                            mazeArray[y][x].setnPath(false);
                            mazeArray[y][x].setsPath(false);
                            mazeArray[y][x].setwPath(false);
                            mazeArray[y][x + 1].setwPath(true);
                            break;
                        case "west":
                            mazeArray[y][x].setePath(false);
                            mazeArray[y][x].setsPath(false);
                            mazeArray[y][x].setnPath(false);
                            mazeArray[y][x - 1].setePath(true);
                            break;
                    }
                }
            }
        }


        // Remove paths that are disconnected from adjoining tiles
        for (int yVar = 0; yVar < height; yVar++) { // For each column
            for (int xVar = 0; xVar < width; xVar++) { // For each row
                // If there is a tile to the N w/o a S path, remove N path from this tile
                if (yVar > 0 && mazeArray[yVar - 1][xVar] != null) {
                    if (!mazeArray[yVar - 1][xVar].issPath()) {
                        mazeArray[yVar][xVar].setnPath(false);
                    }
                }
                // If there is a tile to the S w/o a N path, remove s path from this tile
                if (yVar < height - 1 && mazeArray[yVar + 1][xVar] != null) {
                    if (!mazeArray[yVar + 1][xVar].isnPath()) {
                        mazeArray[yVar][xVar].setsPath(false);
                    }
                }
                // If there is a tile to the W w/o an E path, remove the W path from this tile
                if (xVar > 0 && mazeArray[yVar][xVar - 1] != null) {
                    if (!mazeArray[yVar][xVar - 1].isePath()) {
                        mazeArray[yVar][xVar].setwPath(false);
                    }
                }
                // If there is a tile to the E w/o a W path, remove the E path from this tile
                if (xVar < width - 1 && mazeArray[yVar][xVar + 1] != null) {
                    if (!mazeArray[yVar][xVar + 1].iswPath()) {
                        mazeArray[yVar][xVar].setePath(false);
                    }
                }
            }
        }
    }

    private MazeTile getAppropriateTile(int y, int x) {
        if (y == 0 && x == 0) {
            return new NWCornerTile();
        } else if (y == 0 && x == width - 1) {
            return new NECornerTile();
        } else if (y == 0) {
            return new NSideTile();
        } else if (x == 0 && y == height - 1) {
            return new SWCornerTile();
        } else if (x == 0) {
            return new WSideTile();
        } else if (y == height - 1) {
            return new SSideTile();
        } else if (x == width - 1) {
            return new ESideTile();
        } else {
            return new CenterTile();
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public MazeTile[][] getMazeArray() {
        return mazeArray;
    }

    public void setMazeArray(MazeTile[][] mazeArray) {
        this.mazeArray = mazeArray;
    }
}
