package org.academiadecodigo.enuminatti.saturdaynight;


import org.academiadecodigo.enuminatti.saturdaynight.utils.Sound;

import java.util.LinkedList;

/**
 * Created by codecadet on 12/10/17.
 */
public class CollisionDetector {

    private LinkedList<Collidable> collidebels;
    private LinkedList<Item> myitems;
    Sound beerSound = new Sound("/beerSound.wav");



    public CollisionDetector(LinkedList<Collidable> collidables, LinkedList<Item> myitems) {
        this.collidebels = collidables;
        this.myitems = myitems;
    }

    public void checkObjectColliding(Collidable objChecking) {

        CheckItemCollision(objChecking);

        switch (objChecking.getType()) {


            case CHICK:
                Chick chick = (Chick) objChecking;
                checkChickCollision(chick);
                break;
            case PLAYER:
                Player player = (Player) objChecking;
                checkPlayerCollison(player);
                break;
            case DANCER:
                Dancer dancer = (Dancer) objChecking;
                checkDancerCollision(dancer);
                break;
            default:
                return;

        }


    }


    public void checkPlayerCollison(Player player) {
        for (Collidable c : collidebels) {

            if (player == c) {
                continue;
            }

            int col = player.getPosition().getCol();
            int row = player.getPosition().getRow();

            if (col == c.getPosition().getCol() && row == c.getPosition().getRow()) {


                switch (c.getType()) {
                    case CHICK:
                        Chick chick = (Chick) c;
                        if (player.getItems() == 3) {
                            chick.whenCollisionHappens();
                        }
                        player.resetItems();
                        break;
                    case ITEM:
                        Item item = (Item) c;
                        item.itemRespawn();
                        player.addItemToPlayer();
                        break;

                    case DANCER:
                        Dancer dancer = (Dancer) c;
                        player.beingPushed(dancer.getPosition().getCurrentDirection());
                        break;

                }


            }
        }

    }


    public void checkChickCollision(Chick chick) {

        for (Collidable c : collidebels) {

            int col = chick.getPosition().getCol();
            int row = chick.getPosition().getRow();

            if (col == c.getPosition().getCol() && row == c.getPosition().getRow()) {

                if (c.getType() == TypeOfGameObjects.PLAYER) {

                    Player player = (Player) c;
                    if (player.getItems() == 5) {
                        chick.whenCollisionHappens();
                        continue;
                    }
                    //if(random > 20 + player.baditem*5 + chick)
                    player.resetItems();
                    break;

                }

            }
        }
    }


    public void checkDancerCollision(Dancer dancer) {

        for (Collidable c : collidebels) {

            int col = dancer.getPosition().getCol();
            int row = dancer.getPosition().getRow();

            if (col == c.getPosition().getCol() && row == c.getPosition().getRow()) {

                if (c.getType() == TypeOfGameObjects.PLAYER) {

                    Player player = (Player) c;
                    player.beingPushed(dancer.getPosition().getCurrentDirection());

                }
            }


        }


    }


    public void CheckItemCollision(Collidable myCollideble) {

        int col = myCollideble.getPosition().getCol();
        int row = myCollideble.getPosition().getRow();


        for (Item item : myitems) {

            if (col == item.getPosition().getCol() && row == item.getPosition().getRow()) {

                if (myCollideble.getType() == TypeOfGameObjects.PLAYER) {
                    if (item.isItemStatus() == true) {
                        beerSound.play(true);
                        System.out.println("estou a interagir");
                        Player myplayer = (Player) myCollideble;
                        myplayer.addItemToPlayer();
                        item.itemRespawn();
                        continue;
                    }
                    beerSound.play(true);
                    System.out.println("Boa cerveja");
                    Player myplayer = (Player)  myCollideble;
                    myplayer.addConfidenceToPlayer();
                    item.itemRespawn();

                }


            }


        }
    }
}
