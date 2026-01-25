package data;

import utilities.Calculator;

public enum EnemySpecies {

    // Negative values in enemy stats ensure level 1 stats are fair

    SLIME(
          "Slime",
          "A gooey, disgusting blob!",
          9,
          1.02,
          0,
          1.001,
            -1,
            1.05,
            -1,
            0.95,
            9,
            0.2
    ),
    SKELETON(
            "Skeleton",
            "A bony creature!",
            4,
            0.9,
            1,
            1.05,
            -1,
            0.5,
            1,
            1.15,
            9,
            0.7
    ),
    CYBERNETIC_ROCK(
            "Cybernetic Rock",
            "A cybernetically enhanced... boulder?",
            20,
            1.1,
            2,
            1.05,
            1,
            1.1,
            0,
            0.3,
            5,
            0.25
    );

    private final String name, description;
    private final int maxHealthBase, attackBase, defenseBase, speedBase, maxAPBase;
    private final double maxHealthScale, attackScale, defenseScale, speedScale, maxAPScale;

    EnemySpecies(String name, String description, int maxHealthBase, double maxHealthScale, int attackBase,
                 double attackScale, int defenseBase, double defenseScale, int speedBase, double speedScale,
                 int maxAPBase, double maxAPScale){
        this.name = name;
        this.description = description;
        this.maxHealthBase = maxHealthBase;
        this.maxHealthScale = maxHealthScale;
        this.attackBase = attackBase;
        this.attackScale = attackScale;
        this.defenseBase = defenseBase;
        this.defenseScale = defenseScale;
        this.speedBase = speedBase;
        this.speedScale = speedScale;
        this.maxAPBase = maxAPBase;
        this.maxAPScale = maxAPScale;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getMaxHealthBase() {
        return maxHealthBase;
    }

    public int getAttackBase() {
        return attackBase;
    }

    public int getDefenseBase() {
        return defenseBase;
    }

    public int getSpeedBase() {
        return speedBase;
    }

    public int getMaxAPBase() {
        return maxAPBase;
    }

    public double getMaxHealthScale() {
        return maxHealthScale;
    }

    public double getAttackScale() {
        return attackScale;
    }

    public double getDefenseScale() {
        return defenseScale;
    }

    public double getSpeedScale() {
        return speedScale;
    }

    public double getMaxAPScale() {
        return maxAPScale;
    }

    // Calculate stats
    public int calculateMaxHP(int level){
        return Math.toIntExact(Math.round(
                Calculator.calculateLinearGrowth(maxHealthScale, level, maxHealthBase)
        ));
    }

    public int calculateAttack(int level){
        return Math.toIntExact(Math.round(
                Calculator.calculateLinearGrowth(attackScale,level,attackBase)
        ));
    }

    public int calculateDefense(int level){
        return Math.toIntExact(Math.round(
                Calculator.calculateLinearGrowth(defenseScale,level,defenseBase)
        ));
    }

    public int calculateSpeed(int level){
        return Math.toIntExact(Math.round(
                Calculator.calculateLinearGrowth(speedScale,level,speedBase)
        ));
    }

    public int calculateMaxAP(int level){
        return Math.toIntExact(Math.round(
                Calculator.calculateLinearGrowth(maxAPScale,level,maxAPBase)
        ));
    }




}
