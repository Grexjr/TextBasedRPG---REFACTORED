package combat.actions;

import entities.Entity;
import ui.BattleUIHandler;

public class DefendAction extends BattleAction {

    public DefendAction(Entity user){
        super(user,0,0);
    }

    @Override
    public boolean execute(BattleUIHandler ui){
        getUser().defend();
        ui.printDefense(getUser());
        return false;
    }


}
