package world;

import constants.CommonConstants;
import constants.WorldConstants;
import data.EnemySpecies;
import data.RoomType;
import entities.Enemy;
import entities.EnemyFactory;
import entities.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Room {

    private final RoomType type;
    private final Map<String,Room> exits;
    private final ArrayList<Entity> enemies;
    // Need for secrets and treasures and traps

    private boolean hasStairs, hasNPC;
    private int secretCount;

    public Room(RoomType type){
        this.type = type;
        this.exits = new HashMap<>();
        this.enemies = new ArrayList<>();
    }

    public RoomType getType(){return type;}

    public void addExit(String exitName, Room neighbor){
        exits.put(exitName.toLowerCase(),neighbor);
    }

    public void removeExit(String exitName, Room neighbor){
        exits.remove(exitName,neighbor);
    }

    public Room getNeighbor(String exitName){
        return exits.get(exitName.toLowerCase());
    }

    public void setStairs(boolean stairs){this.hasStairs = stairs;}

    public void setNPC(boolean npc){this.hasNPC = npc;}

    public void setSecretCount(int count){this.secretCount = count;}

    public void populateRoom(int floorDifficulty){
        // Populates room with secrets, treasure, enemies, traps
        spawnEnemy(floorDifficulty);
        spawnTraps();
        spawnTreasures();
        spawnSecrets();
    }

    // Runs every time player does action in room
    public boolean runEncounterChance(){
        return CommonConstants.RAND.nextDouble() < (type.getEncounterChance()/100);
    }

    private void spawnEnemy(int floorDifficulty){
        // Calculate enemyDifficulty to be an average based on floor difficulty with a certain variance?
        int enemyDifficulty = floorDifficulty;

        while(canSpawnEnemy(enemyDifficulty)){
            String enemyID = null;

            // Global rare spawn that can appear anywhere, need global enemy pool
            if(CommonConstants.RAND.nextDouble() < WorldConstants.GLOBAL_RARE_ENEMY_CHANCE){
                // Use global enemy pool in same way as below
            } else {
                enemyID = type.getRandomEnemyID();
            }

            enemies.add(EnemyFactory.createEnemy(enemyID,enemyDifficulty));
        }
    }

    private void spawnTraps(){}

    private void spawnTreasures(){}

    private void spawnSecrets(){}

    /**
     * Formula for whether to spawn enemy:
     * chance = (baseChance) / (1 + ((baseChance x numEnemies) / floorDifficulty))
     * @param difficulty the difficulty of the floor
     * @return true if spawning should continue, false if spawning should stop
     */
    private boolean canSpawnEnemy(int difficulty){
        double numerator = type.getEnemyChanceRaw();
        double denominator = 1 + ((numerator * enemies.size())/difficulty);
        double chance = numerator / denominator;
        return CommonConstants.RAND.nextDouble() < (chance/100);
    }

    private boolean canSpawnTrap(){
        return false;
    }

    private boolean canSpawnTreasure(){
        return false;
    }

    private boolean canSpawnSecret(){
        return false;
    }





}
