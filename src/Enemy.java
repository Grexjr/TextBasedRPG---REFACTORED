import java.util.UUID;

public class Enemy extends Entity{

    public Enemy(){
        super(
                1,
                10,
                1,
                0,
                0,
                "Enemy",
                "It's enemy!"
        );
    }

    /**
     * Gives the enemy a random move choice ranging from 1-4.
     * @return The move choice integer.
     */
    @Override
    public int makeBattleChoice(){
        return CommonConstants.RAND.nextInt(1,5);
    }




}
