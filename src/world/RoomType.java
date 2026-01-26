package world;

public enum RoomType {

    BASIC_ROOM(
            "Basic room",
            "It's just a room.",
            75.0,
            90.0,
            75.0,
            50.0,
            5.0,
            1.0
    ),
    COMPLEX_ROOM(
            "Complex room",
            "Complex? I find it rather simple, actually",
            25.0,
            85.0,
            10.0,
            2.0,
            15.0,
            90.0
    );

    private final String name, description;
    private final double appearanceChanceRaw,encounterChance, trapChanceRaw,enemyChanceRaw,treasureChanceRaw,
            secretChanceBaseRaw;

    RoomType(String name, String description, double appearanceChance, double encounterChance, double trapChance,
             double enemyChance, double treasureChance, double secretChanceBase){
        // All probabilities are doubles out of 100
        this.name = name;
        this.description = description;
        this.appearanceChanceRaw = appearanceChance;
        this.encounterChance = encounterChance;
        this.trapChanceRaw = trapChance;
        this.enemyChanceRaw = enemyChance;
        this.treasureChanceRaw = treasureChance;
        this.secretChanceBaseRaw = secretChanceBase;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getAppearanceChanceRaw(){
        return appearanceChanceRaw;
    }

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
}
