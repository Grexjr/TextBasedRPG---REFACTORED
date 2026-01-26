package world;

import java.util.HashMap;
import java.util.Map;

public class Floor {

    private final Map<String, Room> roomLayout;
    private int floorLevel;
    private int difficulty;

    public Floor(){
        this.roomLayout = new HashMap<>();
        this.floorLevel = 0;
        this.difficulty = calculateDifficulty();
    }

    public Map<String, Room> getRoomLayout(){return roomLayout;}

    public int getFloorLevel(){return floorLevel;}

    public void setFloorLevel(int floorLevel){this.floorLevel = floorLevel;}

    public int getDifficulty(){return this.difficulty;}

    public void setDifficulty(int difficulty){this.difficulty = difficulty;}

    private int calculateDifficulty(){
        return 1;
    }


}
