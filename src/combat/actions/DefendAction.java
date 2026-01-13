package combat.actions;

import combat.BattleScene;
import entities.Entity;
import ui.BattleUIHandler;

public class DefendAction extends BattleAction {

    public DefendAction(BattleScene scene, Entity user){
        super(ActionType.DEFEND,scene, user);
    }

    @Override
    public void execute(BattleUIHandler ui){
        getActor().defend();
        ui.printDefense(getActor());
    }


}
