package actions;

import data.GameActionType;
import entities.Entity;
import ui.UIHandler;

public abstract class Action implements GameAction{

    private final GameActionType type;
    private final Entity actor;
    private int apCostFinal;

    public Action(GameActionType type, Entity actor){
        this.type = type;
        this.actor = actor;
        // Calculates the ap cost based on user stats using the base ap cost; if no modifiers, returns the base cost
        this.apCostFinal = actor.calculateAPCost(type);
    }

    @Override
    public GameActionType getType(){return this.type;}

    @Override
    public Entity getActor(){return this.actor;}

    @Override
    public int getAPCostFinal(){return this.apCostFinal;}

    @Override
    public void useAP(){actor.consumeAP(apCostFinal);}

    @Override
    public boolean isActorAlive(){return !actor.checkDeath();}

    @Override
    public abstract void execute(UIHandler ui);




}
