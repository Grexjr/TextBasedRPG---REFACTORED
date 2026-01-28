package actions;

import combat.BattleScene;
import data.BattleActionType;
import entities.Entity;
import ui.BattleUIHandler;
import ui.UIHandler;

public abstract class BattleAction extends Action{

    private final BattleScene scene;
    private final Entity actor;
    private final int priority;
    private int apCostFinal; // Maybe make final?

    public BattleAction(BattleActionType type, BattleScene scene, Entity actor){
        super(type, actor);
        this.scene = scene;
        this.actor = actor;

        this.priority = type.getPriority();
        // Calculates the ap cost based on user stats using the base ap cost; if no modifiers, returns the base cost
        this.apCostFinal = actor.calculateAPCost(type);
    }

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

    @Override
    public void execute(UIHandler ui){
        if(ui instanceof BattleUIHandler battleUI){
            executeBattleAction(battleUI);
        }
    }

    public abstract void executeBattleAction(BattleUIHandler ui);

}
