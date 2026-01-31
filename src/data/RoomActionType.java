package data;

public enum RoomActionType implements GameActionType{
    SCOUT(
            "SCOUT_ACTION",
            "Scout",
            10,
            1
    ),
    INVESTIGATE(
            "INVESTIGATE_ACTION",
            "Investigate",
            20,
            4
    ),
    ENGAGE(
            "ENGAGE_ACTION",
            "Engage",
            30,
            2
    ),
    REST(
            "REST_ACTION",
            "Rest",
            40,
            0 // Uses max of entity
    );


    private final String id, name;
    private final int sortOrder, apBaseCost;

    RoomActionType(String id, String name, int sortOrder, int apBaseCost){
        this.id = id;
        this.name = name;
        this.sortOrder = sortOrder;
        this.apBaseCost = apBaseCost;
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
    public String getName() {
        return name;
    }
}
