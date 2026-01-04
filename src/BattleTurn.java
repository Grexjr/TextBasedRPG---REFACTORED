import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;

public class BattleTurn {

    private final BattleScene parent;
    private final ArrayList<Entity> battlers;
    private boolean isTurnOver;

    public BattleTurn(BattleScene parentBattle) {
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
        for (Entity battler : battlers) {
            // Reset the battler
            battler.resetBattleState();
            if (!parent.getBattleOver()) {
                if (battler instanceof Player) {
                    // If player, print message informing them of inputs
                    Printer.printMessage(String.format(StringConstants.BATTLE_CHOICE));
                }
                // Run battle choice and print it to the screen
                Printer.printMessage(makeBattleChoice(battler));
            }
        }
        isTurnOver = true;
    }

    private String makeBattleChoice(Entity chooser) {
        switch (validatePlayerInput(chooser)) {
            case 1 -> {
                //  Attacking runs proper attack logic between chooser and target
                Entity target = chooseTarget(chooser);

                return String.format(
                        StringConstants.BATTLE_ATTACK,
                        chooser.getName(),
                        target.getName(),
                        target.getName(),
                        runAttack(chooser, target),
                        target.getName(),
                        target.getCurrentHealth(),
                        target.getMaxHealth()
                );
            }
            case 2 -> {
                //  Defending sets the defense boolean to true
                chooser.defend();
                return String.format(
                        StringConstants.BATTLE_DEFEND,
                        chooser.getName()
                );
            }
            case 3 -> {
                //  Using an item does nothing yet
                return String.format(
                        StringConstants.BATTLE_ITEM,
                        chooser.getName()
                );
            }
            case 4 -> {
                // Running sets the battle as over and interrupts the turn
                parent.setBattleOver(true);
                //TODO: Run calculation, then add that to the string below
                return String.format(
                        StringConstants.BATTLE_RUN,
                        chooser.getName()
                );
            }
            default -> {
                //  Error state if no proper answer is chosen
                return String.format(StringConstants.ERROR_STRING);
            }
        }
    }

    private int validatePlayerInput(Entity chooser) {
        // Validates if the players choice is within the proper range of options
        // TODO: Validate if the player choice is a number!
        int choice = -1;
        while (choice < 1 || choice > 4) {
            choice = chooser.makeBattleChoice();

            if (choice < 1 || choice > 4) {
                System.out.println("Invalid choice! 1-4!");
            }
        }
        return choice;
    }

    private Entity chooseTarget(Entity chooser) {
        ArrayList<Entity> others = new ArrayList<>();
        others.addAll(battlers);
        others.remove(chooser);

        if (chooser instanceof Player) {
            //Displaying
            System.out.println("Who is the target? (1-xx)");
            for (int i = 0; i < others.size(); i++) {
                System.out.println((i + 1) + ". " + others.get(i).getName());
            }
            int choice = CommonConstants.SCAN.nextInt();
            return others.get(choice - 1);
        }
        // Random choice by enemy
        int randomChoice = CommonConstants.RAND.nextInt(0, others.size());
        return others.get(randomChoice);
    }

    private int runAttack(Entity attacker, Entity defender) {
        return defender.takeDamage(attacker.getAttack());
    }

}
