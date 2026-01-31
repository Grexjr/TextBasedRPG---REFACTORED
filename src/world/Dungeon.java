package world;

import java.util.HashMap;
import java.util.Map;

public class Dungeon {

    public Map<Integer, Floor> floors;

    public String dungeonName;
    public int dungeonDifficulty;

    public Dungeon(String name, int difficulty){
        this.dungeonName = name;
        this.dungeonDifficulty = difficulty;
        this.floors = new HashMap<>();
    }




}
