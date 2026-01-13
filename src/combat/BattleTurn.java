package combat;

import combat.actions.*;
import constants.CommonConstants;
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
    private final ArrayList<BattleAction> actions;
    private boolean isTurnOver;

    public BattleTurn(BattleUIHandler ui, BattleScene parentBattle) {
        this.ui = ui;
        parent = parentBattle;
        battlers = new ArrayList<>();
        battlers.addAll(parentBattle.getBattlers());
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
                    ui.printPlayerChoose();
                }
                // Run battle choice
                actions.add(makeBattleChoice(battler));
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
        //TODO: Figure out where to add AP
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
        collectActions();
        sortActions();
        executeActions();
        isTurnOver = true;
    }

    private BattleAction makeBattleChoice(Entity chooser) {
        //TODO: Check here if the player has enough AP to do the move -- or create helper method
        int choice = validatePlayerInput(chooser);
        switch (choice) {
            case 1 -> {
                return new AttackAction(parent, chooser,chooseTarget(chooser));
            }
            case 2 -> {
                return new DefendAction(parent, chooser);
            }
            case 3 -> {
                return new ItemAction(parent, chooser);
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

                return new RunAction(parent, chooser,others);
            }
            default -> {
                //  Error state if no proper answer is chosen
                ui.printError("BAD-INPUT");
                return null; // TODO: find way to handle this
            }
        }
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
        others.addAll(battlers);
        others.remove(chooser);
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

    private int runAttack(Entity attacker, Entity defender) {
        return defender.takeDamage(attacker.getAttack());
    }

}
