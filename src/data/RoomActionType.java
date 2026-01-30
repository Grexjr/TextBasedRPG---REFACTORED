package data;

public enum RoomActionType implements GameActionType{
    SCOUT(
            "SCOUT_ACTION",
            "Scout",
            10,
            1,
            0
    ),
    INVESTIGATE(
            "INVESTIGATE_ACTION",
            "Investigate",
            20,
            4,
            0
    ),
    ENGAGE(
            "ENGAGE_ACTION",
            "Engage",
            30,
            2,
            0
    ),
    REST(
            "REST_ACTION",
            "Rest",
            40,
            0, // Uses max of entity
            0
    );


    private final String id, name;
    private final int sortOrder, apBaseCost,priority;

    RoomActionType(String id, String name, int sortOrder, int apBaseCost, int priority){
        this.id = id;
        this.name = name;
        this.sortOrder = sortOrder;
        this.apBaseCost = apBaseCost;
        this.priority = priority;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public int getSortOrder() {
        return sortOrder;
    }

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
