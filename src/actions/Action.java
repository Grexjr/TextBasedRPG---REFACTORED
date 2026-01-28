package actions;

import data.GameActionType;
import entities.Entity;
import ui.UIHandler;

public abstract class Action implements GameAction{

    private final GameActionType type;
    private final Entity actor;

    public Action(GameActionType type, Entity actor){
        this.type = type;
        this.actor = actor;
    }

    public GameActionType getType(){return this.type;}

    public Entity getActor(){return this.actor;}

    @Override
    public abstract void execute(UIHandler ui);




}
