package entities;

import constants.CommonConstants;

public class Enemy extends Entity {

    public Enemy(){
        super(
                1,
                10,
                1,
                0,
                0,
                "Enemy",
                "It's enemy!",
                10
        );
    }

    /**
     * Gives the enemy a random move choice ranging from 1-4.
     * @return The move choice integer.
     */
    @Override
    public int makeBattleChoice(){
        int choice;
        // While AP is greater than 1, choose random move
        if(getCurrentAP() > 1){
            choice = CommonConstants.RAND.nextInt(1,5);
        } else {
            choice = 5;
        }
        return choice;
    }




}
