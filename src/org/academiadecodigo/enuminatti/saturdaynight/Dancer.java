package org.academiadecodigo.enuminatti.saturdaynight;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;


/**
 * Created by codecadet on 12/10/2017.
 */
public class Dancer {

    private GridPosition dancerPosition;
    private Rectangle dancerRectangle;
    private Grid dancerGrid;
    private int moves = 4;
    private int square = 1;
    private boolean squaredancing;

    public Dancer(GridPosition dancerPosition) {

        this.dancerPosition = dancerPosition;


        int x = dancerPosition.getGameGrid().colToX(dancerPosition.getCol());
        int y = dancerPosition.getGameGrid().rowToY(dancerPosition.getRow());

        dancerRectangle = new Rectangle(x, y, Grid.CELLSIZE, Grid.CELLSIZE);
        dancerRectangle.setColor(Color.GREEN);
        dancerRectangle.fill();
        dancerPosition.setCurrentDirection(Direction.UP);
        squaredancing = true;

    }







    public void move() {

        Direction dancerdirection = dancerPosition.getCurrentDirection();

        if (squaredancing && moves ==0) {
            System.out.println("Entrie");
            dancerPosition.setCurrentDirection(Direction.angleDirection(dancerdirection));
            moves = 3;
            square++;
            squaredancing= true;
        }

        if(square==4){
            squaredancing = false;
        }

        if (!squaredancing && moves==0) {

            int random = (int) Math.ceil(Math.random() * 100);

            if(random <= 60){
                System.out.println("SquareAgain");
                moves = 4;
                squaredancing = true;
                square = 0;
            }
            getDancerPosition().createRandomDirection();
            moves = 3;

        }

        moves--;
        accelarete();

    }


    public void setDancerGrid(Grid dancerGrid) {
        this.dancerGrid = dancerGrid;
    }

    public void accelarete() {


        switch (dancerPosition.getCurrentDirection()) {

            case UP:
                dancerPosition.moveUp();
                break;
            case DOWN:
                dancerPosition.moveDown();
                break;
            case RIGTH:
                dancerPosition.moveRigth();
                break;
            case LEFT:
                dancerPosition.moveLeft();

        }

        int colDirectionMov = dancerPosition.getCurrentDirection().col * Grid.CELLSIZE;
        int rowDirectionMov = dancerPosition.getCurrentDirection().row * Grid.CELLSIZE;

        dancerRectangle.translate(colDirectionMov, rowDirectionMov);

    }

    public GridPosition getDancerPosition() {
        return dancerPosition;
    }

}

