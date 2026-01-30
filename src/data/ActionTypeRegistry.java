package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionTypeRegistry {
    // Master map of all actions
    private static final Map<String, GameActionType> ALL_ACTION_TYPES = new HashMap<>();

    // Categorized lists
    private static final List<BattleActionType> BATTLE_ACTION_TYPES = new ArrayList<>();
    private static final List<RoomActionType> ROOM_ACTION_TYPES = new ArrayList<>();

    // Runs once when the file is loaded into memory, so basically at the beginning of things
    static{
        // Register battle actions
        for(BattleActionType battleActionType : BattleActionType.values()){
            ALL_ACTION_TYPES.put(battleActionType.getId(), battleActionType);
            BATTLE_ACTION_TYPES.add(battleActionType);
        }
        // Register room actions
        for(RoomActionType roomActionType : RoomActionType.values()){
            ALL_ACTION_TYPES.put(roomActionType.getId(),roomActionType);
            ROOM_ACTION_TYPES.add(roomActionType);
        }
    }

    public static Map<String, GameActionType> getAllActionTypes(){return ALL_ACTION_TYPES;}

    public static GameActionType getById(String id){return ALL_ACTION_TYPES.get(id);}

    public static List<BattleActionType> getBattleActionTypes(){return BATTLE_ACTION_TYPES;}

}
