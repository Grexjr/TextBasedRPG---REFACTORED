package ui;

import constants.CommonConstants;
import constants.StringConstants;
import data.GameActionType;
import entities.Entity;

import java.util.Comparator;
import java.util.List;

///  Interface for all UIHandlers with common methods
/// //TODO: Eventually, this can be implemented by a broad CommandLineUIHandler, which then all others inherit from
public interface UIHandler {

    default void render(String text){
        System.out.println(text);
    }

    default void makeCustomSpacer(char spacer, int length){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < length; i++){
            sb.append(spacer);
        }
        render(sb.toString());
    }

    default String selectArticle(String word){
        char firstLetter = Character.toLowerCase(word.charAt(0));
        if("aeiou".indexOf(firstLetter) != -1){
            return "An";
        }
        return "A";
    }

    default String selectDescriptor(String[] descriptors){
        return descriptors[
                CommonConstants.RAND.nextInt(
                        0,
                        descriptors.length
                )];
    }

    default void printError(String errorMessage){
        String message = String.format(
                StringConstants.ERROR_STRING,
                errorMessage
        );
        render(message);
    }

    default void printActions(List<GameActionType> actions, Entity chooser){
        //TODO: Now need a way to get the actions into a list
        String actionString;

        // Sort actions
        actions.sort(
                Comparator.comparingInt(GameActionType::getSortOrder)
        );

        for(int i = 0; i < actions.size(); i++){
            actionString = String.format(
                    StringConstants.ACTION_STRING,
                    i+1,
                    actions.get(i).getName(),
                    chooser.calculateAPCost(actions.get(i))
                    );
            render(actionString);
        }
    }



}
