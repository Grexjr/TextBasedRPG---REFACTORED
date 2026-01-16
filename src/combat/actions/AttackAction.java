package combat.actions;

import combat.BattleScene;
import entities.Entity;
import ui.BattleUIHandler;

public class AttackAction extends BattleAction{

    private final Entity target;

    public AttackAction(BattleScene scene, Entity user, Entity target){
        super(ActionType.ATTACK,scene, user);
        this.target = target;
    }

    public Entity getTarget(){return target;}

    @Override
    public boolean isValid(){
        return !getActor().checkDeath() && !target.checkDeath();
    }

    @Override
    public void execute(BattleUIHandler ui){
        super.execute(ui);
        int damage = target.takeDamage(getActor().getAttack());
        ui.printAttack(getActor(),target,damage);
    }


}
