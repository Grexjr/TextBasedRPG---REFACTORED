package actions;

import combat.BattleScene;
import data.BattleActionType;
import entities.Entity;
import ui.BattleUIHandler;

public abstract class BattleAction {

    private final BattleActionType type;
    private final BattleScene scene;
    private final Entity actor;
    private final int priority;
    private int apCostFinal; // Maybe make final?

    public BattleAction(BattleActionType type, BattleScene scene, Entity actor){
        this.type = type;
        this.scene = scene;
        this.actor = actor;

        this.priority = type.getPriority();
        // Calculates the ap cost based on user stats using the base ap cost; if no modifiers, returns the base cost
        this.apCostFinal = actor.calculateAPCost(type);
    }

    public BattleActionType getType(){return type;}

    public BattleScene getScene(){return scene;}

    public Entity getActor() {return actor;}

    public int getPriority(){return priority;}

    public int getApCostFinal(){return apCostFinal;}

    public boolean isValid(){
        return !actor.checkDeath();
    }

    public void useAP(Entity actor){
        actor.consumeAP(apCostFinal);
    }

    public abstract void execute(BattleUIHandler ui);

}
