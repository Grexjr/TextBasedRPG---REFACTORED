package actions;

import combat.BattleScene;
import data.BattleActionType;
import entities.Entity;
import ui.BattleUIHandler;

public class DefendAction extends BattleAction {

    public DefendAction(BattleScene scene, Entity user){
        super(BattleActionType.DEFEND,scene, user);
    }

    @Override
    public void executeBattleAction(BattleUIHandler ui){
        getActor().defend();
        ui.printDefense(getActor());
    }


}
