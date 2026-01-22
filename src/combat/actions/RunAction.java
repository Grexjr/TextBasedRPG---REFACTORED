package combat.actions;

import combat.BattleResult;
import combat.BattleScene;
import entities.Entity;
import entities.Player;
import ui.BattleUIHandler;

import java.util.ArrayList;

public class RunAction extends BattleAction {

    private final ArrayList<Entity> runFroms;

    public RunAction(BattleScene scene, Entity user, ArrayList<Entity> runFroms){
        super(ActionType.RUN,scene, user);
        this.runFroms = runFroms;
    }

    @Override
    public void execute(BattleUIHandler ui){
        boolean runSuccess = getActor().attemptRun(runFroms);
        ui.printRun(getActor(),runSuccess);
        if(runSuccess){
            if(getActor() instanceof Player){
                getScene().endBattle(BattleResult.ESCAPED);
            } else {
                getScene().endBattle(BattleResult.ENEMY_ESCAPED);
            }
        }
    }

}
