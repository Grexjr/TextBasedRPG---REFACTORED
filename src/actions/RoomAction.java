package actions;

import data.RoomActionType;
import entities.Entity;

public abstract class RoomAction extends Action {

    public RoomAction(RoomActionType type, Entity actor){
        super(type,actor);
    }




}
