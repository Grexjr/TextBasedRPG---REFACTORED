package core;

import entities.Player;

public class Tester {

    public static void main(String[] args){
        testDefense();
    }



    // Tets for defense at different healths to ensure it lines up with expectations
    public static void testDefense(){
        Player testPlayer = new Player();

        // TEST 1: MINIMUMS
        // 10 health, 0 defense, defends 1 time
        testPlayer.setMaxHealth(10);
        testPlayer.setDefense(0);

        testPlayer.defend();
        int expectedDefense = 1;
        int testValue = testPlayer.getTempDefense();
        if(expectedDefense == testValue){
            System.out.println("TEST ONE-1 SUCCESS");
        } else {
            System.out.println("TEST ONE-1 FAIL");
        }

        // Defends 4 times
        testPlayer.defend();
        testPlayer.defend();
        testPlayer.defend();

        expectedDefense = 4;
        testValue = testPlayer.getTempDefense();
        if(expectedDefense == testValue){
            System.out.println("TEST ONE-2 SUCCESS");
        } else {
            System.out.println("TEST ONE-2 FAIL");
        }

        testPlayer.resetBattleState();

        // TEST 2: MEDIUMS
        // 100 health, 50 defense
        testPlayer.setMaxAP(100);
        testPlayer.setDefense(50);

        testPlayer.defend();

        expectedDefense = 10;
        testValue = testPlayer.getTempDefense();
        if(expectedDefense == testValue){
            System.out.println("TEST 2-1 SUCCESS");
        } else {
            System.out.println("TEST 2-1 FAIL");
        }
    }

}
