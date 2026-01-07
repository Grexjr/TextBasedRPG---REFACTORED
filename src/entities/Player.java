package entities;

public class Player extends Entity {

    public Player(){
        super(
                1,
                20,
                1,
                0,
                1,
                "entities.Player",
                "It's you!"
        );
    }

    @Override
    public int makeBattleChoice(){
        return CommonConstants.SCAN.nextInt();
    }


}
