package com.maze;

public abstract class MazeTile {
    String type;
    boolean nPath;
    boolean ePath;
    boolean sPath;
    boolean wPath;
    String color;

    public MazeTile() {
        this.color = "\u001B[0m";

    }

    public static String decodePiece(MazeTile tile) {
        if (!tile.nPath && tile.ePath && tile.sPath && !tile.wPath) {
            return "┌";
        } else if (!tile.nPath && tile.ePath && tile.sPath && tile.wPath) {
            return "┬";
        } else if (!tile.nPath && !tile.ePath && tile.wPath && tile.sPath) {
            return "┐";
        } else if (!tile.wPath && tile.nPath && tile.ePath && tile.sPath) {
            return "├";
        } else if (tile.nPath && tile.ePath && tile.sPath && tile.wPath) {
            return "┼";
        } else if (!tile.ePath && tile.nPath && tile.wPath && tile.sPath) {
            return "┤";
        } else if (!tile.wPath && !tile.sPath && tile.nPath && tile.ePath) {
            return "└";
        } else if (!tile.sPath && tile.wPath && tile.nPath && tile.ePath) {
            return "┴";
        } else if (!tile.sPath && !tile.ePath && tile.nPath && tile.wPath) {
            return "┘";
        } else if (!tile.nPath && !tile.ePath && !tile.sPath && !tile.wPath) {
            return "▪";
        } else if (!tile.wPath && !tile.ePath && tile.sPath && tile.nPath) {
            return "|";
        } else if (!tile.nPath && !tile.sPath && tile.wPath && tile.ePath) {
            return "-";
        } else if (!tile.nPath && !tile.ePath && !tile.wPath && tile.sPath) {
            return "↓";
        } else if (!tile.nPath && !tile.ePath && !tile.sPath && tile.wPath) {
            return "←";
        } else if (!tile.ePath && !tile.sPath && !tile.wPath && tile.nPath) {
            return "↑";
        } else if (!tile.nPath && !tile.sPath && !tile.wPath && tile.ePath) {
            return "→";
        }

        return "%";
    }

    public static void printToConsole(Maze maze) {
        System.out.print("\n\n\n     ");
        int headerNum = 0;
        System.out.println("MAZE ANSWER KEY");
        System.out.printf("    ");
        for (int i = 0; i < maze.getWidth(); i++) {
            System.out.printf(" %d", headerNum++);
            if (headerNum == 9) {
                headerNum = 0;
            }
        }
        System.out.println();
        for (int i = 0; i < maze.getHeight(); i++) {
            System.out.printf("  %02d ", i);
            for (int j = 0; j < maze.getWidth(); j++) {
                if (maze.getMazeArray()[i][j] == null) {
                    System.out.print(". ");
                } else {
                    System.out.print(maze.getMazeArray()[i][j].getColor() +
                            MazeTile.decodePiece(maze.getMazeArray()[i][j]) +
                            " \u001B[0m");
                }
            }
            System.out.print("\n");
        }
        System.out.print("\n    ");
        headerNum = 0;
        System.out.println("MAZE");
        System.out.printf("    ");
        for (int i = 0; i < maze.getWidth(); i++) {
            System.out.printf(" %d", headerNum++);
            if (headerNum == 9) {
                headerNum = 0;
            }
        }
        System.out.println();
        for (int i = 0; i < maze.getHeight(); i++) {
            System.out.printf("  %02d ", i);
            for (int j = 0; j < maze.getWidth(); j++) {
                if (maze.getMazeArray()[i][j] == null) {
                    System.out.print(". ");
                } else {
                    System.out.print(MazeTile.decodePiece(maze.getMazeArray()[i][j]) +
                            " ");
                }
            }
            System.out.print("\n");
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isnPath() {
        return nPath;
    }

    public void setnPath(boolean nPath) {
        this.nPath = nPath;
    }

    public boolean isePath() {
        return ePath;
    }

    public void setePath(boolean ePath) {
        this.ePath = ePath;
    }

    public boolean issPath() {
        return sPath;
    }

    public void setsPath(boolean sPath) {
        this.sPath = sPath;
    }

    public boolean iswPath() {
        return wPath;
    }

    public void setwPath(boolean wPath) {
        this.wPath = wPath;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
