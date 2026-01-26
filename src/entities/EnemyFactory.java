package entities;

import data.EnemySpecies;

public class EnemyFactory {

    // Create enemy method of the factory
    public static Entity createEnemy(String id, int level){
        return switch(id) {
            case "SLIME" -> new Enemy(EnemySpecies.SLIME,level);
            case "SKELETON" -> new Enemy(EnemySpecies.SKELETON,level);
            case "CYBERNETIC_ROCK" -> new Enemy(EnemySpecies.CYBERNETIC_ROCK,level);
            default -> null;
        };
    }


}
