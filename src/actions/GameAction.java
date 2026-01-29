package actions;

import data.GameActionType;
import entities.Entity;
import ui.UIHandler;

public interface GameAction {

    GameActionType getType();
    Entity getActor();
    int getAPCostFinal();
    void useAP();
    boolean isActorAlive();

    void execute(UIHandler ui);

}
