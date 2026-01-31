package actions;

import combat.BattleScene;
import data.BattleActionType;
import entities.Entity;
import ui.BattleUIHandler;

public class DefendAction extends BattleAction {

    public DefendAction(BattleScene scene, Entity user){
        super(BattleActionType.DEFEND, user, scene);
    }

    @Override
    public void executeBattleAction(BattleUIHandler ui){
        getActor().defend();
        ui.printDefense(getActor());
    }


}
