package combat.actions;

import combat.BattleScene;
import entities.Entity;
import ui.BattleUIHandler;

public abstract class BattleAction {

    private final BattleScene scene;
    private final int priority, apCost;
    private Entity user;

    public BattleAction(BattleScene scene, Entity user, int priority, int apCost){
        this.scene = scene;
        this.priority = priority;
        this.apCost = apCost;
        this.user = user;
    }

    public BattleScene getScene(){return scene;}

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

    public boolean canAfford(){
        return user.getCurrentAP() >= apCost;
    }


    public abstract boolean execute(BattleUIHandler ui);

}
