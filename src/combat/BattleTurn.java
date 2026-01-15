package combat;

import combat.actions.*;
import constants.CommonConstants;
import entities.Enemy;
import entities.Entity;
import entities.Player;
import ui.BattleUIHandler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;

public class BattleTurn {

    private final BattleUIHandler ui;
    private final BattleScene parent;
    private final ArrayList<Entity> battlers;
    private final ArrayList<Entity> players;
    private final ArrayList<Entity> enemies;
    private final ArrayList<BattleAction> actions;
    private boolean isTurnOver;

    public BattleTurn(BattleUIHandler ui, BattleScene parentBattle) {
        this.ui = ui;
        parent = parentBattle;
        battlers = new ArrayList<>();
        battlers.addAll(parentBattle.getBattlers());
        players = new ArrayList<>();
        enemies = new ArrayList<>();
        for(Entity battler : battlers){
            if(battler instanceof Player){
                players.add(battler);
            }
            if(battler instanceof Enemy){
                enemies.add(battler);
            }
        }
        actions = new ArrayList<>();
        isTurnOver = false;
    }

    public ArrayList<Entity> getBattlers() {
        return battlers;
    }

    public boolean isTurnOver() {
        return isTurnOver;
    }

    public void setTurnOver(boolean turnOver) {
        isTurnOver = turnOver;
    }

    public void runSpeedCalc() {
        battlers.sort(Comparator.comparingInt(Entity::getSpeed).reversed());
    }


    public void collectActions(){
        for(Entity battler : battlers){
            if (!parent.getBattleOver()) {
                if (battler instanceof Player) {
                    // If player, print message informing them of inputs
                    ui.printPlayerChoose(battler);
                }
                // Run battle choice
                BattleAction action = makeBattleChoice(battler);
                while(action == null){
                    action = makeBattleChoice(battler);
                }
                actions.add(action);
            }
        }
    }

    public void sortActions(){
        actions.sort(
                // Highest priority first - 2, 1, 0
                Comparator.comparingInt(BattleAction::getPriority).reversed()
                .thenComparing(Comparator.comparingInt((BattleAction a) -> a.getActor().getSpeed()).reversed())
        );
    }

    public void executeActions(){
        for(BattleAction action : actions){
            // 1. If battle has ended, stop immediately
            if(parent.getBattleOver()) break;

            // 2. If user is dead, skip them (continue moves to next iteration of loop)
            if(action.getActor().checkDeath()) continue;

            // 3. Clear flags from last turn
            action.getActor().resetBattleState();

            // 4. If move is valid, execute (user is alive, or for attacks, user and target are alive)
            if(action.isValid()){
               action.execute(ui);
            }

            // 5. User is alive, but the isValid returned false (only for attack actions)
            else if(action instanceof AttackAction){
                ui.printAttackNothing(action.getActor());
            }
        }
    }






    public void runTurn() {
        int deadPlayers = 0;
        int deadEnemies = 0;

        // TODO: Use stream logic to refactor this; can check if all match a certain parameter

        for(Entity player : players){
            if(player.checkDeath()){
                deadPlayers += 1;
            }
            if(deadPlayers == players.size()){
                parent.endBattle(BattleResult.DEFEAT);
            }
        }
        for(Entity enemy : enemies){
            if(enemy.checkDeath()){
                deadEnemies += 1;
            }
            if(deadEnemies == enemies.size()){
                parent.endBattle(BattleResult.VICTORY);
            }
        }
        collectActions();
        sortActions();
        executeActions();
        isTurnOver = true;

        // Recover AP for all involved in battle - for now, just recover to full-- will be more complex later
        for(Entity battler : battlers){
            battler.recoverAP(battler.getMaxAP() - battler.getCurrentAP());
        }
    }

    private BattleAction makeBattleChoice(Entity chooser) {
        if(chooser instanceof Player){
            ui.printAP(chooser);
        }
        int choice = validatePlayerInput(chooser);
        BattleAction action;
        switch (choice) {
            case 1 -> {
                action = new AttackAction(parent, chooser,chooseTarget(chooser));
                return checkAP(chooser,action);
            }
            case 2 -> {
                action = new DefendAction(parent,chooser);
                return checkAP(chooser,action);
            }
            case 3 -> {
                action = new ItemAction(parent,chooser);
                return checkAP(chooser,action);
            }
            case 4 -> {
                ArrayList<Entity> others = new ArrayList<>();
                // If player, add all enemies. If enemy, add only the player
                if(chooser instanceof Player){
                    others.addAll(battlers);
                    others.remove(chooser);
                } else {
                    for(Entity e : battlers){
                        if(e instanceof Player){
                            others.add(e);
                        }
                    }
                }

                action = new RunAction(parent,chooser,others);

                return checkAP(chooser,action);
            }
            default -> {
                //  Error state if no proper answer is chosen
                ui.printError("BAD-INPUT");
                return null; // Happy accident; returning null here keeps player in the window
            }
        }
    }

    private BattleAction checkAP(Entity chooser, BattleAction action){
        if(chooser.hasAP(action.getApCostFinal())){
            return action;
        }
        ui.printNotEnoughAp(action.getType().getName());
        return null;
    }

    private int validatePlayerInput(Entity chooser) {
        // Validates if the players choice is within the proper range of options
        int choice = -1;
        while (choice < 1 || choice > 4) {
            try{
                choice = chooser.makeBattleChoice();
                if (choice < 1 || choice > 4) {
                    ui.printInvalidChoose(4);
                }
            } catch (InputMismatchException e){
                ui.printInvalidChoose(4);
                CommonConstants.SCAN.next();
            }
        }
        return choice;
    }

    private Entity chooseTarget(Entity chooser) {
        ArrayList<Entity> others = new ArrayList<>();
        // Need safety check here possibly
        if(chooser instanceof Player){
            for(Entity enemy : enemies){
                if(!enemy.checkDeath()){
                    others.add(enemy);
                }
            }
        }
        if(chooser instanceof Enemy){
            for(Entity player : players){
                if(!player.checkDeath()){
                    others.add(player);
                }
            }
        }
        int choice = -1;

        if (chooser instanceof Player) {
            if(others.size() > 1){
                //Displaying
                ui.printTargetChoice(others);

                for (int i = 0; i < others.size(); i++) {
                    ui.printTargetOptions(others);
                }

                while(choice < 1 || choice > others.size()){
                    try {
                        choice = CommonConstants.SCAN.nextInt();
                        if(choice < 1 || choice > others.size()){
                            ui.printInvalidChoose(others.size());
                        }
                    } catch (InputMismatchException e) {
                        ui.printInvalidChoose(others.size());
                        CommonConstants.SCAN.next();
                    }
                }
                return others.get(choice - 1);
            }
            return others.getFirst();
        }
        // Random choice by enemy
        int randomChoice = CommonConstants.RAND.nextInt(0, others.size());
        return others.get(randomChoice);
    }

}
