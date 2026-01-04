import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Player player = new Player();
        Enemy enemy = new Enemy();

        BattleScene battle1 = new BattleScene(player,enemy);
        battle1.runBattle();

    }

}
