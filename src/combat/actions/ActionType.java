package combat.actions;

public enum ActionType {

    ATTACK(
            1,
            "Attack",
            1,
            0
    ),
    DEFEND(
            2,
            "Defend",
            1,
            1
    ),
    ITEM(
            3,
            "ItemUse",
            1,
            0
    ),
    RUN(
            4,
            "Run",
            1,
            Integer.MAX_VALUE
    );

    private final int id,apCost,priority;
    private final String name;

    ActionType(int id, String name, int apCost, int priority){
        this.id = id;
        this.name = name;
        this.apCost = apCost;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public int getApCost() {
        return apCost;
    }

    public int getPriority() {
        return priority;
    }

    public String getName() {
        return name;
    }
}
