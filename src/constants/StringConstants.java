package constants;

public class StringConstants {

    // All string outputs for the game
    // Util strings
    public static final String ERROR_STRING = "ERROR:%s"; // Allows printing of custom errors
    public static final String PAGE_BREAK = "=============================================";
    public static final String SPACER_1 = " - ";
    public static final String SPACER_2 = " -> ";
    public static final String SPACER_3 = " >> ";
    public static final String SPACER_4 = " > ";

    // Choice strings
    // Action string including its number for choosing, name, and AP cost
    public static final String ACTION_STRING = "%d. %s (%d)";


    // Displaying entities.Entity
    public static final String ENTITY_DISPLAY = "%s%n%s%nLevel: %d%nHealth: %d/%d%nAttack: %d%nDefense: %d%nSpeed: %d%n";

    // Start Battle
    public static final String BATTLE_HEADER = "=============== BATTLE BEGINS ===============";
    public static final String BATTLE_LIST = SPACER_1 + "%s %s %s appears!";
    public static final String[] ENEMY_DESCRIPTORS = {"evil","wanton","nefarious","disgusting","tricksy",
            "putrid","scary"};
    // Battle ask for choice
    public static final String BATTLE_CHOICE = "Choose your action!%n1. %s (%d)%n2. %s (%d)%n3. %s (%d)%n4. %s (%d)%n" +
            "5. %s%n" + SPACER_3 + "Input 1-4";
    //TODO: Change the above: "%d. %s (%d)%n," then loop through this generic situation for all options printing
    public static final String BATTLE_CHOICE_INVALID = SPACER_3 + "Please input 1-%d!";
    //Battle ask for target
    public static final String BATTLE_TARGET = "Who is your target?%n" + SPACER_3 + "Input 1-%d";
    public static final String BATTLE_TARGET_LIST = "%d. %s";
    // Battle AP costs
    public static final String BATTLE_AP_PRINT = "AP: %d/%d";
    public static final String BATTLE_AP_DEFICIT = "You're too tired to %s!";
    // Battle Attack
    public static final String BATTLE_ATTACK = "%s attacks %s!%n%s takes %d damage!%n%s Health: %d/%d";
    public static final String BATTLE_ATTACK_NOTHING = "%s hits nothing!";
    // Battle Defend
    public static final String BATTLE_DEFEND = "%s defends!";
    // Battle Item Use
    public static final String BATTLE_ITEM = "%s uses an item!";
    // Battle Run String
    public static final String BATTLE_RUN = "%s attempts to run away!";
    public static final String BATTLE_RUN_SUCCESS = "%s successfully escaped!";
    public static final String BATTLE_RUN_FAIL = "%s's way was blocked...";
    // Battle Over Strings
    public static final String BATTLE_WON = "%s has been defeated! You win!";
    public static final String BATTLE_LOST = "Your body grows weaker and your vision fades...";
    public static final String BATTLE_ESCAPED_PLAYER = "You are free from the battle!";
    public static final String BATTLE_ESCAPED_ENEMY = "The battlefield is empty...";
    public static final String BATTLE_INTERRUPT = "%s has interrupted the battle!";
    public static final String BATTLE_OVER = "================ BATTLE OVER ================";

    // ROOM STRINGS
    // Enter Room String
    public static final String ENTER_ROOM = "You enter a %s room...";



}
