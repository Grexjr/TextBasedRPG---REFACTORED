package combat.actions;

public enum ActionType {

    ATTACK(
            1,
            "Attack",
            3,
            0
    ),
    DEFEND(
            2,
            "Defend",
            5,
            1
    ),
    ITEM(
            3,
            "Use an Item",
            2,
            0
    ),
    RUN(
            4,
            "Run",
            0,
            Integer.MAX_VALUE
    ),
    END_TURN(
            5,
            "End Turn",
            0,
            -1
    );

    private final int id, apBaseCost,priority;
    private final String name;

    ActionType(int id, String name, int apCost, int priority){
        this.id = id;
        this.name = name;
        this.apBaseCost = apCost;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public int getApBaseCost() {
        return apBaseCost;
    }

    public int getPriority() {
        return priority;
    }

    public String getName() {
        return name;
    }
}
