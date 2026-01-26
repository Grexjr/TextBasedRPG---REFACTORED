package world;

import constants.CommonConstants;
import constants.WorldConstants;

public class DungeonManager {

    private Room currentRoom;

    // Default constructor

    public void generateDungeon(){
        int floors = CommonConstants.RAND.nextInt(WorldConstants.MIN_FLOORS, WorldConstants.MAX_FLOORS+1);
        // TODO: Generate number of floors equal to number here, then add them to dungeon arraylist

    }

    public void generateFloor(int floorNum){
        int rooms = CommonConstants.RAND.nextInt(WorldConstants.MIN_ROOMS, WorldConstants.MAX_ROOMS + 1);
        // TODO: Generate rooms based on the number of rooms on this floor
        // TODO: THEN, run some algorithm to connect them randomly; for each room, run random boolean on each direction;
        //  If yes, connect to another room and connect that room's opposite to this room (how to do that?)
        // TODO: Here, add rooms to the floor's graph of rooms

    }

    private void addRoomConnections(){

    }

    public Room generateRoom(RoomType type, String id){
        // TODO: Run appearance chance calculation here, then populate the room that is created, then return it
    }

    private void populateRoom(Room room){

    }

    public void movePlayer(){

    }




}
