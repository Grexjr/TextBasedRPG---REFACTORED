package combat.actions;

import entities.Entity;
import ui.BattleUIHandler;

public abstract class BattleAction {

    private final int priority, apCost;
    private Entity user;

    public BattleAction(Entity user, int priority, int apCost){
        this.priority = priority;
        this.apCost = apCost;
        this.user = user;
    }

    public int getPriority() {
        return priority;
    }

    public int getApCost() {
        return apCost;
    }

    public Entity getUser() {
        return user;
    }

    public void setUser(Entity user) {
        this.user = user;
    }

    public boolean isValid(){
        return !user.checkDeath();
    }


    public abstract boolean execute(BattleUIHandler ui);

}
