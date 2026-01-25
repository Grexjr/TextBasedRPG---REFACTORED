package core;

import combat.BattleScene;
import constants.CommonConstants;
import data.EnemySpecies;
import entities.Enemy;
import entities.Player;
import ui.BattleUIHandler;

public class Main {

    public static void main(String[] args) {
        BattleUIHandler ui = new BattleUIHandler();

        Enemy enemy = new Enemy(EnemySpecies.SLIME,1);
        Enemy enemy1 = new Enemy(EnemySpecies.SKELETON,1);
        Enemy enemy2 = new Enemy(EnemySpecies.CYBERNETIC_ROCK,1);


        Player player = new Player();

        BattleScene battle = new BattleScene(ui,player,enemy);

        int rand = CommonConstants.RAND.nextInt(0,4);
        if(rand == 1) {
            battle = new BattleScene(ui, player, enemy);
        }
        else if (rand == 2){
            battle = new BattleScene(ui,player,enemy1);
        }
        else if (rand == 3){
            battle = new BattleScene(ui,player,enemy2);
        }

        battle.startBattle();

    }

}
