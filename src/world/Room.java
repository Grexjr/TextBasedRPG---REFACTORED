package world;

import constants.CommonConstants;
import data.EnemySpecies;
import data.RoomType;
import entities.Enemy;
import entities.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Room {

    private final Floor parent;
    private final RoomType type;
    private final String id;
    private final Map<String,Room> exits;
    private final ArrayList<Entity> enemies;
    // Need for secrets and treasures and traps

    private boolean hasStairs, hasNPC;
    private int secretCount;

    public Room(Floor parent, RoomType type, String id){
        this.parent = parent;
        this.type = type;
        this.id = id;
        this.exits = new HashMap<>();
        this.enemies = new ArrayList<>();
    }

    public void addExit(String exitName, Room neighbor){
        exits.put(exitName.toLowerCase(),neighbor);
    }

    public void removeExit(String exitName, Room neighbor){
        exits.remove(exitName,neighbor);
    }

    public Room getNeighbor(String exitName){
        return exits.get(exitName.toLowerCase());
    }

    public void populateRoom(int floorDifficulty){
        // Populates room with secrets, treasure, enemies, traps


    }

    private void spawnEnemy(){
        while(canSpawnEnemy()){
            // Need random selection for enemy here, then add them to the arraylist... for now just slime
            // Will probably do some calculation to floor difficulty for enemy level
            enemies.add(new Enemy(EnemySpecies.SLIME,parent.getDifficulty()));
        }
    }

    /**
     * Formula for whether to spawn enemy:
     * chance = (baseChance) / (1 + ((baseChance x numEnemies) / floorDifficulty))
     * @return
     */
    private boolean canSpawnEnemy(){
        double numerator = type.getEnemyChanceRaw();
        double denominator = 1 + ((numerator * enemies.size())/parent.getDifficulty());
        double chance = numerator / denominator;
        return CommonConstants.RAND.nextDouble() < (chance/100);
    }





}
