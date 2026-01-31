package world;

import constants.CommonConstants;
import constants.WorldConstants;
import data.RoomType;
import entities.Entity;
import entities.Player;
import ui.DungeonUIHandler;

public class DungeonManager {

    private final DungeonUIHandler ui;
    private Room currentRoom;
    private Player player;

    public DungeonManager(DungeonUIHandler ui){
        this.ui = ui;
    }

    public DungeonUIHandler getUI(){return ui;}

    public void generateDungeon(){
        int floors = CommonConstants.RAND.nextInt(WorldConstants.MIN_FLOORS, WorldConstants.MAX_FLOORS+1);
        // TODO: Generate number of floors equal to number here, then add them to dungeon arraylist

    }

    public void generateFloor(int floorNum){
        int rooms = CommonConstants.RAND.nextInt(WorldConstants.MIN_ROOMS, WorldConstants.MAX_ROOMS + 1);
        Floor floor = new Floor();

        for(int i = 0; i < rooms; i++){
            floor.populateFloor(i,generateRoom(floorNum));
        }

        for(Room room : floor.getRoomLayout().values()){
            // TODO: Conditionals here for each direction - adding connections
        }

        // TODO: Generate rooms based on the number of rooms on this floor
        // TODO: THEN, run some algorithm to connect them randomly; for each room, run random boolean on each direction;
        //  see what rogue does?
        //  If yes, connect to another room and connect that room's opposite to this room (how to do that?)
        // TODO: Here, add rooms to the floor's graph of rooms

    }

    public Room generateRoom(int floorNum){
        // TODO: Run appearance chance calculation here, then populate the room that is created, then return it
        Room room = new Room(RoomType.getRandomRoomType());
        room.populateRoom(floorNum);
        return room;
    }

    // Main method to move player to new room and run room code
    public void moveTo(Entity mover, String direction){
        Room nextRoom = currentRoom.getNeighbor(direction);
        if(nextRoom != null){
            this.currentRoom = nextRoom;
            onRoomEnter(mover);
        }
    }

    private void onRoomEnter(Entity enterer){
        ui.printEnterRoom();
        ui.printRoomActions(enterer);
    }

    private void addRoomConnections(){

    }




}
