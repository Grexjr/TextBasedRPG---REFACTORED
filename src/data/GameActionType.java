package data;

public interface GameActionType {
    String getId();
    int getSortOrder();
    int getApBaseCost();
    int getPriority();
    String getName();
}
