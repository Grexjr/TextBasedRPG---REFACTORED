package actions;

import combat.BattleScene;
import data.BattleActionType;
import entities.Entity;
import ui.BattleUIHandler;

public class ItemAction extends BattleAction {

    public ItemAction(BattleScene scene, Entity user){
        super(BattleActionType.ITEM, user, scene);
    }

    @Override
    public void executeBattleAction(BattleUIHandler ui){
        //TODO: Add item logic
        ui.printItem(getActor());
    }
}
