import java.util.List;
import java.util.stream.Collectors;

public class BattleUIHandler {

    private void render(String text){
        System.out.println(text);
    }
    
    public void printBattleStart(List<Entity> battlers){
        render(StringConstants.BATTLE_HEADER);
        for(Entity e : battlers){
            if(!(e instanceof Player)){
                String message = String.format(StringConstants.BATTLE_LIST, e.getName());
                render(message);
            }
        }
    }





}
