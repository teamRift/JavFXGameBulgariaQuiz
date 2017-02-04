package application.engine.utils;

import javafx.stage.Screen;

/**
 * Created by mws on 2/3/2017.
 * Contains all static dimension properties;
 */
public class Dimensions {
    public static double SCREEN_WIDTH = Screen.getPrimary().getBounds().getWidth(); //get the width of the display
    public static double SCREEN_HEIGHT = SCREEN_WIDTH;
}
