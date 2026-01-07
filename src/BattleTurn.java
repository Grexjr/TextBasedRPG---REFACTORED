import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;

public class BattleTurn {

    private final BattleUIHandler ui;
    private final BattleScene parent;
    private final ArrayList<Entity> battlers;
    private boolean isTurnOver;

    public BattleTurn(BattleUIHandler ui, BattleScene parentBattle) {
        this.ui = ui;
        parent = parentBattle;
        battlers = new ArrayList<>();
        battlers.addAll(parentBattle.getBattlers());
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

    public void runTurn() {
        runSpeedCalc();
        // Reset the battlers
        for(Entity battler : battlers) {
            battler.resetBattleState();
            // Issue: the battler who goes last will immediately have things reset for them... hmm.... but, if
            // we have it reset at the beginning of turn, then enemy can defend, player goes, then enemy goes..
            // I may need to rethink the battle system; you see what the enemy does first
            // Maybe force defends to occur at the start of the turn
        }
        for (Entity battler : battlers) {

            if (!parent.getBattleOver()) {
                if (battler instanceof Player) {
                    // If player, print message informing them of inputs
                    ui.printPlayerChoose();
                }
                // Run battle choice
                makeBattleChoice(battler);
            }
        }
        isTurnOver = true;
    }

    private void makeBattleChoice(Entity chooser) {
        switch (validatePlayerInput(chooser)) {
            case 1 -> {
                //  Attacking runs proper attack logic between chooser and target
                Entity target = chooseTarget(chooser);
                int damage = runAttack(chooser,target);

                ui.printAttack(chooser,target,damage);
                //TODO: In multi-enemy battles, this ends the battle if one dies - fix with check for all
                if(target.checkDeath()){
                    if(target instanceof Player){
                        parent.endBattle(BattleResult.DEFEAT);
                    } else {
                        parent.endBattle(BattleResult.VICTORY);
                    }
                }
            }
            case 2 -> {
                //  Defending sets the defense boolean to true
                chooser.defend();
                ui.printDefense(chooser);
            }
            case 3 -> {
                //  Using an item does nothing yet
                ui.printItem(chooser);
            }
            case 4 -> {
                // Running sets the battle as over and interrupts the turn
                ArrayList<Entity> others = new ArrayList<>();
                others.addAll(battlers);
                others.remove(chooser);

                boolean runSuccess = chooser.attemptRun(others);
                ui.printRun(chooser,runSuccess);
                if(runSuccess){
                    if(chooser instanceof Player){
                        parent.endBattle(BattleResult.ESCAPED);
                    } else {
                        parent.endBattle(BattleResult.ENEMY_ESCAPED);
                    }
                }
            }
            default -> {
                //  Error state if no proper answer is chosen
                ui.printError("BAD-INPUT");
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
