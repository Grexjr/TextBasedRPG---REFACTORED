package combat.actions;

import combat.BattleScene;
import entities.Entity;
import ui.BattleUIHandler;

public class DefendAction extends BattleAction {

    public DefendAction(BattleScene scene, Entity user){
        super(scene, user,1,0);
    }

    @Override
    public boolean execute(BattleUIHandler ui){
        getUser().defend();
        ui.printDefense(getUser());
        return false;
    }


}
