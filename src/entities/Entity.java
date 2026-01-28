package entities;

import data.BattleActionType;
import constants.BattleConstants;
import constants.CommonConstants;
import constants.StringConstants;

import java.util.List;
import java.util.UUID;

public abstract class Entity {

    // Number that incoming damage is divided by when defending
    private static final int DEFEND_MOD = 2;

    private final UUID id;
    private int level,maxHealth,currentHealth,attack,defense,speed,maxAP,currentAP;
    private String name,description;

    // Battle variables
    private boolean isDefending = false, isDeciding = false;
    private int tempDefense = 0;

    public Entity(int level, int maxHealth, int attack, int defense, int speed, String name, String description,
                  int maxAP){
        id = UUID.randomUUID();
        this.level = level;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.name = name;
        this.description = description;

        // Battle variables
        this.maxAP = maxAP;
        this.currentAP = maxAP;
    }

    @Override
    public String toString(){
        return String.format(
                "id=%s%nlvl=%d%nmhlth=%d%nchlth=%d%natk=%d%ndef=%d%nspd=%d%nnme=%s%ndsc=%s%n",
                id,
                level,
                maxHealth,
                currentHealth,
                attack,
                defense,
                speed,
                name,
                description
        );
    }

    // Getters and Setters

    public int getLevel() {
        return level;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpeed() {
        return speed;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean getDefending(){return isDefending;}

    public int getMaxAP(){return maxAP;}

    public int getCurrentAP(){return currentAP;}

    public int getTempDefense(){return tempDefense;}

    public boolean getDeciding(){return isDeciding;}

    public void setLevel(int level) {
        this.level = level;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDefending(boolean defending){this.isDefending = defending;}

    public void setMaxAP(int ap){this.maxAP = ap;}

    public void setCurrentAP(int ap){this.currentAP = ap;}

    public void setTempDefense(int tempDefense){this.tempDefense = tempDefense;}

    public void setDeciding(boolean deciding){this.isDeciding = deciding;}

    // Methods
    public String displayEntity(){
        return String.format(
                StringConstants.ENTITY_DISPLAY,
                name,
                description,
                level,
                currentHealth,
                maxHealth,
                attack,
                defense,
                speed
        );
    }

    /**
     * Applies damage to the player health by setting current health to a calculated damage amount.
     * @param damage the raw amount of damage taken by the player, dealt by the enemy.
     * @return The calculated damage dealt
     */
    public int takeDamage(int damage){
        int calcDamage = calcDamage(damage);
        setCurrentHealth(Math.max(0,getCurrentHealth() - calcDamage));
        return calcDamage;
    }

    public boolean checkDeath(){
        return currentHealth <= 0;
    }

    public boolean attemptRun(List<Entity> runFrom){
        double speedToBeat = runFrom.stream()
                .mapToDouble(Entity::getSpeed)
                .average().orElse(0.0);

        // For now, if speed is greater, run succeeds - if equal, random chance
        if(getSpeed() > speedToBeat){
            return true;
        }
        if(getSpeed() == speedToBeat){
            return CommonConstants.RAND.nextBoolean();
        }
        return false;
    }


    /**
     * Calculates damage based on the defense of the player.
     * @param rawDamage The raw damage dealt by the enemy.
     * @return The adjusted damage value, clamped to 0.
     */
    private int calcDamage(int rawDamage){
        return Math.max(0,rawDamage - (defense + tempDefense));
    }

    // Battle methods
    public void defend(){
        // Adds new temporary defense gain to the already existing temporary defense
        setTempDefense(tempDefense + calculateDefenseGain());
    }

    private int calculateDefenseGain(){
        // Defense times the base gain value
        double baseGain = defense * BattleConstants.DEFEND_DEFENSE_RATIO;

        // Half-life/sensitivity is 15% of max health (so when temp defense = 10% of max health, efficacy halved)
        double halfLife = maxHealth * BattleConstants.DEFENSE_HALF_LIFE_HP_RATIO;

        // The minimum defense if values are too low that gets added constantly with no fall off
        int minimumDefense = BattleConstants.MINIMUM_DEFENSE;

        // Formula: gain = baseGain / (1 + (tempDefense / halfLife))
        double gain = Math.max(minimumDefense,baseGain / (1 + (tempDefense / halfLife)));

        return Math.toIntExact(Math.round(gain));
    }

    public void endDefense(){
        tempDefense = 0;
    }

    public void consumeAP(int consumption){
        currentAP = Math.max(0,currentAP - consumption);
    }

    public void recoverAP(int recovery){
        currentAP = Math.min(maxAP, currentAP + recovery);
    }

    public int calculateAPCost(BattleActionType action){
        int finalAPCost = action.getApBaseCost();
        // If run, takes the max AP of the entity
        if(action.equals(BattleActionType.RUN)){
            finalAPCost = this.getMaxAP();
        }
        return finalAPCost;
    }

    public boolean hasAP(int cost){
        // True means has AP, false means does not have enough
        return currentAP >= cost;
    }

    public void resetBattleState(){
        endDefense();
    }

    // Abstract methods
    public abstract int makeBattleChoice();

}
