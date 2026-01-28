package ui;

import combat.BattleResult;
import combat.actions.ActionType;
import constants.CommonConstants;
import constants.StringConstants;
import entities.Entity;
import entities.Player;

import java.util.ArrayList;
import java.util.List;

public class BattleUIHandler implements UIHandler {

    public void printBattleStart(List<Entity> battlers){
        render(StringConstants.BATTLE_HEADER);
        // Print list of enemies with randomized descriptors
        for(Entity e : battlers){
            if(!(e instanceof Player)){
                String name = e.getName();
                String descriptor = selectDescriptor(StringConstants.ENEMY_DESCRIPTORS);
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

    public void printPlayerChoose(Entity choosingPlayer){
        String message = String.format(
                StringConstants.BATTLE_CHOICE,
                ActionType.ATTACK.getName(),
                choosingPlayer.calculateAPCost(ActionType.ATTACK),
                ActionType.DEFEND.getName(),
                choosingPlayer.calculateAPCost(ActionType.DEFEND),
                ActionType.ITEM.getName(),
                choosingPlayer.calculateAPCost(ActionType.ITEM),
                ActionType.RUN.getName(),
                choosingPlayer.calculateAPCost(ActionType.RUN),
                ActionType.END_TURN.getName()
        );
        render(message);
    }

    public void printInvalidChoose(int choices){
        String message = String.format(StringConstants.BATTLE_CHOICE_INVALID, choices);
        render(message);
    }

    public void printAP(Entity subject){
        String message = String.format(StringConstants.BATTLE_AP_PRINT, subject.getCurrentAP(),subject.getMaxAP());
        render(message);
    }

    public void printNotEnoughAp(String actionName){
        String message = String.format(StringConstants.BATTLE_AP_DEFICIT,actionName);
        render(message);
    }

    public void printTargetChoice(List<Entity> targets){
        String message = String.format(StringConstants.BATTLE_TARGET,targets.size());
        render(message);
    }

    public void printTargetOptions(List<Entity> targets){
        for(int i = 0; i < targets.size(); i++){
            String message = String.format(StringConstants.BATTLE_TARGET_LIST,i+1,targets.get(i).getName());
            render(message);
        }
    }

    public void printAttack(Entity attacker, Entity target, int damage){
        String message = String.format(
                StringConstants.BATTLE_ATTACK,
                attacker.getName(),
                target.getName(),
                target.getName(),
                damage,
                target.getName(),
                target.getCurrentHealth(),
                target.getMaxHealth()
        );
        render(message);
    }

    public void printAttackNothing(Entity attacker){
        String message = String.format(
                StringConstants.BATTLE_ATTACK_NOTHING,
                attacker.getName()
        );
    }

    public void printDefense(Entity defender){
        String message = String.format(
                StringConstants.BATTLE_DEFEND,
                defender.getName()
        );
        render(message);
    }

    public void printItem(Entity user){
        //TODO: Add items to this method
        String message = String.format(
                StringConstants.BATTLE_ITEM,
                user.getName()
        );
        render(message);
    }

    public void printRun(Entity runner, boolean success){
        String message = String.format(
                StringConstants.BATTLE_RUN,
                runner.getName()
        );
        String message2;
        if(success){
            message2 = String.format(
                    StringConstants.BATTLE_RUN_SUCCESS,
                    runner.getName()
            );
        } else {
            message2 = String.format(
                    StringConstants.BATTLE_RUN_FAIL,
                    runner.getName()
            );
        }
        render(message);
        // TODO: Add a delay
        render(message2);
    }

    public void printBattleEnd(List<Entity> battlers, BattleResult battleEnd){
        switch(battleEnd){
            case VICTORY -> {
                ArrayList<Entity> others = new ArrayList<>();
                others.addAll(battlers);
                for(Entity e : others){
                    if(e instanceof Player){
                        others.remove(e);
                    }
                }
                for(Entity e : others){
                    String message = String.format(StringConstants.BATTLE_WON,e.getName());
                    render(message);
                }
            }
            case DEFEAT -> {
                render(StringConstants.BATTLE_LOST);
            }
            case ESCAPED -> {
                render(StringConstants.BATTLE_ESCAPED_PLAYER);
            }
            case ENEMY_ESCAPED -> {
                render(StringConstants.BATTLE_ESCAPED_ENEMY);
            }
            case INTERRUPTED -> {
                String message = String.format(StringConstants.BATTLE_INTERRUPT, "PLACEHOLDER");
                render(message);
            }
            default -> {
                render(StringConstants.BATTLE_OVER);
            }
        }
        render(StringConstants.BATTLE_OVER);
    }

}
