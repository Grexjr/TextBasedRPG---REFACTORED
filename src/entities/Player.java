package entities;

import constants.CommonConstants;

public class Player extends Entity {

    public Player(){
        super(
                1,
                20,
                1,
                0,
                1,
                "Player",
                "It's you!",
                10
        );
    }

    @Override
    public int makeBattleChoice(){
        return CommonConstants.SCAN.nextInt();
    }


}
