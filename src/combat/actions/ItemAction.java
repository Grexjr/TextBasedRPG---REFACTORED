package combat.actions;

import combat.BattleScene;
import entities.Entity;
import ui.BattleUIHandler;

public class ItemAction extends BattleAction {

    public ItemAction(BattleScene scene, Entity user){
        super(ActionType.ITEM,scene, user);
    }

    @Override
    public void execute(BattleUIHandler ui){
        super.execute(ui);
        //TODO: Add item logic
        ui.printItem(getActor());
    }
}
