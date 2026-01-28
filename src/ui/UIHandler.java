package ui;

import constants.CommonConstants;
import constants.StringConstants;

///  Interface for all UIHandlers with common methods
/// //TODO: Eventually, this can be implemented by a broad CommandLineUIHandler, which then all others inherit from
public interface UIHandler {

    default void render(String text){
        System.out.println(text);
    }

    default void makeCustomSpacer(char spacer, int length){
        for(int i = 0; i < length; i++){
            render(String.valueOf(spacer));
        }
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



}
