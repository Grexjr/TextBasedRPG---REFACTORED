package data;

import constants.CommonConstants;

public enum RoomType {

    BASIC_ROOM(
            "R_BASIC",
            "Basic room",
            "It's just a room.",
            75.0,
            90.0,
            75.0,
            50.0,
            5.0,
            1.0,
            new String[]{"SLIME","SKELETON"}
    ),
    COMPLEX_ROOM(
            "R_COMPLEX",
            "Complex room",
            "Complex? I find it rather simple, actually",
            25.0,
            85.0,
            10.0,
            2.0,
            15.0,
            90.0,
            new String[]{"CYBERNETIC_ROCK"}
    );

    private final String id, name, description;
    private final double appearanceChanceRaw,encounterChance, trapChanceRaw,enemyChanceRaw,treasureChanceRaw,
            secretChanceBaseRaw;
    private final String[] enemyPool;

    RoomType(String id, String name, String description, double appearanceChance, double encounterChance, double trapChance,
             double enemyChance, double treasureChance, double secretChanceBase, String[] enemyPool){
        // All probabilities are doubles out of 100
        this.id = id;
        this.name = name;
        this.description = description;
        this.appearanceChanceRaw = appearanceChance;
        this.encounterChance = encounterChance;
        this.trapChanceRaw = trapChance;
        this.enemyChanceRaw = enemyChance;
        this.treasureChanceRaw = treasureChance;
        this.secretChanceBaseRaw = secretChanceBase;

        this.enemyPool = enemyPool;
    }

    public String getID(){return id;}

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getAppearanceChanceRaw(){
        return appearanceChanceRaw;
    }

    public double getEncounterChance(){return encounterChance;}

    public double getTrapChanceRaw() {
        return trapChanceRaw;
    }

    public double getEnemyChanceRaw() {
        return enemyChanceRaw;
    }

    public double getTreasureChanceRaw() {
        return treasureChanceRaw;
    }

    public double getSecretChanceBaseRaw() {
        return secretChanceBaseRaw;
    }

    public String getRandomEnemyID(){
        int index = CommonConstants.RAND.nextInt(enemyPool.length);
        return enemyPool[index];
    }

    // Can use static method to get some data from the rooms enum; find which type claims which values
    public static RoomType getRandomRoomType(){
        // 1. Calculate total weight;
        double totalWeight = 0.0;
        // Can iterate through the enum like this | NOTE: Can add variance here, if wanted
        for(RoomType type : RoomType.values()){
            totalWeight += type.getAppearanceChanceRaw();
        }

        // 2. Do a random roll of double type
        double roll = CommonConstants.RAND.nextDouble() * totalWeight;

        // 3. Find which room type matches the roll
        // Cursor is the value that results to prevent a chain of if statements
        double cursor = 0.0;
        for(RoomType type : RoomType.values()){
            // Basically add up cursor
            cursor += type.getAppearanceChanceRaw();
            // But if you add and the roll is now lower than it, return that room with the value below it
            if(roll <= cursor){
                return type;
            }
        }
        // Safety in case things go wrong
        return BASIC_ROOM;
    }
}
