public class StringConstants {

    // All string outputs for the game
    // Util strings
    public static String ERROR_STRING = "ERROR:%s"; // Allows printing of custom errors
    public static String PAGE_BREAK = "=============================================";
    public static String SPACER_1 = " - ";
    public static String SPACER_2 = " -> ";
    public static String SPACER_3 = " >> ";


    // Displaying Entity
    public static String ENTITY_DISPLAY = "%s%n%s%nLevel: %d%nHealth: %d/%d%nAttack: %d%nDefense: %d%nSpeed: %d%n";

    // Start Battle
    public static String BATTLE_HEADER = "=============== BATTLE BEGINS ===============";
    public static String BATTLE_LIST = SPACER_1 + "%s %s %s appears!";
    public static String[] DESCRIPTORS = {"evil","wanton","nefarious","disgusting","tricksy","putrid","scary"};
    // Battle ask for choice
    public static String BATTLE_CHOICE = "Choose your action!%n1. Attack%n2. Defend%n3. Use an item%n4. Run Away%n" +
            SPACER_3 + "Input 1-4";
    public static String BATTLE_CHOICE_INVALID = SPACER_3 + "Please input 1-%d!";
    //Battle ask for target
    public static String BATTLE_TARGET = "Who is your target?%n" + SPACER_3 + "Input 1-%d";
    public static String BATTLE_TARGET_LIST = "%d. %s";
    // Battle Attack
    public static String BATTLE_ATTACK = "%s attacks %s!%n%s takes %d damage!%n%s Health: %d/%d";
    // Battle Defend
    public static String BATTLE_DEFEND = "%s defends!";
    // Battle Item Use
    public static String BATTLE_ITEM = "%s uses an item!";
    // Battle Run String
    public static String BATTLE_RUN = "%s attempts to run away!";
    public static String BATTLE_RUN_SUCCESS = "%s successfully escaped!";
    public static String BATTLE_RUN_FAIL = "%s's way was blocked...";
    // Battle Over String
    public static String BATTLE_OVER = "The battle is over!";



}
