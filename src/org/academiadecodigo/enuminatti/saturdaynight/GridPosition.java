package org.academiadecodigo.enuminatti.saturdaynight;

/**
 * Created by codecadet on 09/10/17.
 */
public class GridPosition {

    private Direction currentDirection;
    private int col;
    private int row;
    private Grid gameGrid;

    //Creating a GridPosition  to use if we want a previous defined position
    public GridPosition(int col, int row, Grid gameGrid) {

        //The gameGrid of this position
        this.gameGrid = gameGrid;
        this.col = col;
        this.row = row;

        //Initializing the current Direction to NODIRECTION
        currentDirection = Direction.NODIRECTION;

    }

    // Creating constructor for generating a random position for the various Objects
    public GridPosition(Grid gameGrid) {

        //Generating random col and row
        this.col = (int) Math.ceil(Math.random() * (gameGrid.getCols() - 1));

        this.row = (int) Math.ceil(Math.random() * (gameGrid.getRows() - 1));


        //The gameGrid of this position
        this.gameGrid = gameGrid;


        //Initializing the current Direction to NODIRECTION
        currentDirection = Direction.NODIRECTION;

    }

    public GridPosition(Grid gameGrid, int minY, int maxY) {

        //Generating random col and row
        int rangeX = (gameGrid.getCols() - 1 - 2) + 1;
        this.col = (int) Math.ceil(Math.random() * rangeX) + 1;


        // Generating a y with range , for the first item to appear on the upside of the screen
        int rangeY = (maxY - minY) + 1;
        this.row = (int) Math.ceil(Math.random() * rangeY) + minY;


        //The gameGrid of this position
        this.gameGrid = gameGrid;


        //Initializing the current Direction to NODIRECTION
        currentDirection = Direction.NODIRECTION;

    }


    public void moveUp() {

        if (row - 1 < 0) {

            currentDirection = Direction.NODIRECTION;
            return;
        }

        row -= 1;

    }


    public void moveDown() {

        if (row > gameGrid.getRows() - 2) {

            currentDirection = Direction.NODIRECTION;
            return;
        }
        row += 1;

    }

    public void moveRigth() {

        if (col + 1 > gameGrid.getCols() - 1) {

            currentDirection = Direction.NODIRECTION;
            return;
        }
        col += 1;

    }

    public void moveLeft() {

        if (col < 1) {
            currentDirection = Direction.NODIRECTION;
            return;
        }
        col -= 1;

    }


    //Method to get a random direction in witch the NPC will move
    public void createRandomDirection() {

        //Creating a random number to generate a new direction
        int random = (int) Math.floor(Math.random() * (Direction.values().length - 1));

        Direction newDirection = Direction.values()[random];

        //Using the newDirection to find and give the value of the new direction to this position
        switch (newDirection) {
            case LEFT:
                currentDirection = Direction.LEFT;
                break;
            case DOWN:
                currentDirection = Direction.DOWN;
                break;
            case UP:
                currentDirection = Direction.UP;
                break;
            case RIGTH:
                currentDirection = Direction.RIGTH;
                break;
            case NODIRECTION:
                currentDirection = Direction.NODIRECTION;
        }


    }


    public Grid getGameGrid() {
        return gameGrid;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {

        return col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {

        this.col = col;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }


}
