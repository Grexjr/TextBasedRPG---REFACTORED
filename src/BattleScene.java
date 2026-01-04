import java.util.ArrayList;
import java.util.List;

public class BattleScene {

    private final ArrayList<Entity> battlers;
    private BattleTurn currentTurn;
    private boolean isBattleOver;

    public BattleScene(Entity... participants){
        battlers = new ArrayList<>();
        battlers.addAll(List.of(participants));
    }

    public ArrayList<Entity> getBattlers(){return battlers;}

    public boolean getBattleOver(){return isBattleOver;}

    public void setBattleOver(boolean battleOver){this.isBattleOver = battleOver;}

    public void runBattle(){
        Printer.printMessage(startBattle());
        while(!isBattleOver){
            runTurns();
        }
        Printer.printMessage(endBattle());
    }

    private String startBattle(){
        String enemyName = null;
        currentTurn = new BattleTurn(this);
        // Get the name of the enemy you are fighting for the battle start string (only works for one)
        for(Entity e : battlers){
            if(e instanceof Enemy){
                enemyName = e.getName();
            }
        }
        return String.format(StringConstants.BATTLE_START,enemyName);
    }

    private void runTurns(){
        while(!currentTurn.isTurnOver()){
            currentTurn.runTurn();
        }
        currentTurn = new BattleTurn(this);
    }

    private String endBattle(){
        return String.format(
                StringConstants.BATTLE_OVER
        );
    }



}
