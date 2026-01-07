package combat.actions;

import entities.Entity;
import ui.BattleUIHandler;

public class ItemAction extends BattleAction {

    public ItemAction(Entity user){
        super(user,0,0);
    }

    @Override
    public boolean execute(BattleUIHandler ui){
        //TODO: Add item logic
        ui.printItem(getUser());
        return false;
    }
}
