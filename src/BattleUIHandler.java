import java.util.List;
import java.util.stream.Collectors;

public class BattleUIHandler {

    private void render(String text){
        System.out.println(text);
    }
    
    public void printBattleStart(List<Entity> battlers){
        render(StringConstants.BATTLE_HEADER);
        // Print list of enemies with randomized descriptors
        for(Entity e : battlers){
            if(!(e instanceof Player)){
                String name = e.getName();
                String descriptor = selectDescriptor();
                String message = String.format(
                        StringConstants.BATTLE_LIST,
                        selectArticle(descriptor),
                        descriptor,
                        name
                );
                render(message);
            }
        }
        render(StringConstants.PAGE_BREAK);
    }

    private String selectArticle(String word){
        char firstLetter = Character.toLowerCase(word.charAt(0));
        if("aeiou".indexOf(firstLetter) != -1){
            return "An";
        }
        return "A";
    }

    private String selectDescriptor(){
        return StringConstants.DESCRIPTORS[
                CommonConstants.RAND.nextInt(
                0,
                StringConstants.DESCRIPTORS.length
        )];
    }

    public void printBattleEnd(){
        //TODO: Different messages based on enum of how the battle ends (win, loss, run)
        render(StringConstants.BATTLE_OVER);
    }





}
