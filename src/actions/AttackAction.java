package actions;

import combat.BattleScene;
import data.BattleActionType;
import entities.Entity;
import ui.BattleUIHandler;

public class AttackAction extends BattleAction{

    private final Entity target;

    public AttackAction(BattleScene scene, Entity user, Entity target){
        super(BattleActionType.ATTACK,scene, user);
        this.target = target;
    }

    public Entity getTarget(){return target;}

    @Override
    public boolean isValid(){
        return !getActor().checkDeath() && !target.checkDeath();
    }

    @Override
    public void execute(BattleUIHandler ui){
        int damage = target.takeDamage(getActor().getAttack());
        ui.printAttack(getActor(),target,damage);
    }


}
