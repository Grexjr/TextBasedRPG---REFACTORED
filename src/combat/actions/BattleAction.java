package combat.actions;

import combat.BattleScene;
import entities.Entity;
import ui.BattleUIHandler;

public abstract class BattleAction {

    private final ActionType type;
    private final BattleScene scene;
    private final Entity actor;
    private final int priority;
    private int apCostBase;

    public BattleAction(ActionType type, BattleScene scene, Entity actor){
        this.type = type;
        this.scene = scene;
        this.actor = actor;

        this.priority = type.getPriority();
        this.apCostBase = type.getApCost();
    }

    public ActionType getType(){return type;}

    public BattleScene getScene(){return scene;}

    public Entity getActor() {return actor;}

    public int getPriority(){return priority;}

    public boolean isValid(){
        return !actor.checkDeath();
    }

    // TODO: Add private method to calculate final AP Cost using method in entity that calculates based on items, etc.

    public abstract void execute(BattleUIHandler ui);

}
