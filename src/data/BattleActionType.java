package data;

public enum BattleActionType implements GameActionType {

    ATTACK(
            "ATTACK_ACTION",
            10,
            "Attack",
            3,
            0
    ),
    DEFEND(
            "DEFEND_ACTION",
            20,
            "Defend",
            5,
            1
    ),
    ITEM(
            "ITEM_ACTION",
            30,
            "Use an Item",
            2,
            0
    ),
    RUN(
            "RUN_ACTION",
            50,
            "Run",
            0,
            Integer.MAX_VALUE
    ),
    END_TURN(
            "END_TURN_ACTION",
            99,
            "End Turn",
            0,
            -1
    );

    private final String id, name;
    private final int sortOrder, apBaseCost,priority;

    BattleActionType(String id, int sortOrder, String name, int apCost, int priority){
        this.id = id;
        this.sortOrder = sortOrder;
        this.name = name;
        this.apBaseCost = apCost;
        this.priority = priority;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public int getSortOrder(){return sortOrder;}

    @Override
    public int getApBaseCost() {
        return apBaseCost;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public String getName() {
        return name;
    }
}
