package ui;

import constants.StringConstants;
import data.ActionTypeRegistry;
import entities.Entity;

public class DungeonUIHandler implements UIHandler {

    public void printEnterRoom(){
        String message = StringConstants.PAGE_BREAK + "\n"
                + StringConstants.ENTER_ROOM  + "\n" + StringConstants.PAGE_BREAK;
        render(message);
    }

    public void printRoomActions(Entity actor){
        printActions(ActionTypeRegistry.getRoomActionTypes(),actor);
    }





}
