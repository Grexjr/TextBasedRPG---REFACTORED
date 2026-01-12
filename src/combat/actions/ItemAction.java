package combat.actions;

import combat.BattleScene;
import entities.Entity;
import ui.BattleUIHandler;

public class ItemAction extends BattleAction {

    public ItemAction(BattleScene scene, Entity user){
        super(scene, user,0,0);
    }

    @Override
    public boolean execute(BattleUIHandler ui){
        //TODO: Add item logic
        ui.printItem(getUser());
        return false;
    }
}
