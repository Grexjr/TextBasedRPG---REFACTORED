import java.util.UUID;

public class Player extends Entity{

    public Player(){
        super(
                1,
                20,
                1,
                0,
                1,
                "Player",
                "It's you!"
        );
    }

    @Override
    public int makeBattleChoice(){
        //Display method but for now
        System.out.println("What is your move? (1-4)");
        return CommonConstants.SCAN.nextInt();
    }


}
