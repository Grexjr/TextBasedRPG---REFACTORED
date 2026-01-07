package core;

import combat.BattleScene;
import entities.Enemy;
import entities.Player;
import ui.BattleUIHandler;

public class Main {

    public static void main(String[] args) {
        BattleUIHandler ui = new BattleUIHandler();

        Player player = new Player();
        Enemy enemy = new Enemy();

        BattleScene battle = new BattleScene(ui,player,enemy);
        battle.startBattle();

    }

}
