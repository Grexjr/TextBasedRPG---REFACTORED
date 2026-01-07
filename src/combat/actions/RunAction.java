package combat.actions;

import entities.Entity;
import ui.BattleUIHandler;

import java.util.ArrayList;

public class RunAction extends BattleAction {

    private final ArrayList<Entity> runFroms;

    public RunAction(Entity user, ArrayList<Entity> runFroms){
        super(user,0,0);
        this.runFroms = runFroms;
    }

    @Override
    public boolean execute(BattleUIHandler ui){
        boolean runSuccess = getUser().attemptRun(runFroms);
        ui.printRun(getUser(),runSuccess);
        return true;
    }

}
