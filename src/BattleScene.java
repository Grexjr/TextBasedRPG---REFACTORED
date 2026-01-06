import java.util.ArrayList;
import java.util.List;

public class BattleScene {

    private final BattleUIHandler ui;

    private final ArrayList<Entity> battlers;
    private BattleTurn currentTurn;
    private boolean isBattleOver;

    public BattleScene(BattleUIHandler ui, Entity... participants){
        this.ui = ui;

        battlers = new ArrayList<>();
        battlers.addAll(List.of(participants));
    }

    public ArrayList<Entity> getBattlers(){return battlers;}

    public boolean getBattleOver(){return isBattleOver;}

    public void setBattleOver(boolean battleOver){this.isBattleOver = battleOver;}

    public void startBattle(){
        // Tell the UI to announce the fight
        ui.printBattleStart(battlers);

        // Create a new turn for the battle to
        runBattle();
    }

    private void runBattle(){
        currentTurn = new BattleTurn(ui,this);
        while(!isBattleOver){
            runTurns();
        }
        ui.printBattleEnd();
    }

    private void runTurns(){
        while(!currentTurn.isTurnOver()){
            currentTurn.runTurn();
        }
        currentTurn = new BattleTurn(ui,this);
    }



}
