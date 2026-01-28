package actions;

import combat.BattleScene;
import data.BattleActionType;
import entities.Entity;
import ui.BattleUIHandler;
import ui.UIHandler;

public abstract class BattleAction extends Action{

    private final BattleScene scene;
    private final int priority;

    public BattleAction(BattleActionType type, Entity actor, BattleScene scene){
        super(type, actor);
        this.scene = scene;

        this.priority = type.getPriority();
    }

    public BattleScene getScene(){return scene;}

    public int getPriority(){return priority;}

    @Override
    public void execute(UIHandler ui){
        if(ui instanceof BattleUIHandler battleUI){
            executeBattleAction(battleUI);
        }
    }

    public abstract void executeBattleAction(BattleUIHandler ui);

}
