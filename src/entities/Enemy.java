package entities;

import constants.CommonConstants;
import data.EnemySpecies;

public class Enemy extends Entity {

    private EnemySpecies species;

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

    public Enemy(EnemySpecies species, int level){
        super(
                level,
                species.calculateMaxHP(level),
                species.calculateAttack(level),
                species.calculateDefense(level),
                species.calculateSpeed(level),
                species.getName(),
                species.getDescription(),
                species.calculateMaxAP(level)
        );
    }

    private Enemy(int level, int maxHealth, int attack, int defense, int speed, String name, String description,
                 int maxAP){
        super(level,maxHealth,attack,defense,speed,name,description,maxAP);
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
