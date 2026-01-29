package world;

import data.RoomType;

public class RoomFactory {

    public static Room createRoom(String id){
        return switch(id){
            case "R_BASIC" -> new Room(RoomType.BASIC_ROOM);
            case "R_COMPLEX" -> new Room(RoomType.COMPLEX_ROOM);
            default -> null;
        };
    }



}
