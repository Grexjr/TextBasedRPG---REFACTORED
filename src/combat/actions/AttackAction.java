package combat.actions;

import entities.Entity;
import ui.BattleUIHandler;

public class AttackAction extends BattleAction{

    private final Entity target;

    public AttackAction(Entity user, Entity target){
        super(user,0,0);
        this.target = target;
    }

    @Override
    public boolean execute(BattleUIHandler ui){
        int damage = target.takeDamage(getUser().getAttack());
        ui.printAttack(getUser(),target,damage);
        return false;
    }


}
