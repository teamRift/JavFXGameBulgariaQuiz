package application.classes;

import javafx.stage.Screen;

public class Values
{
    //This allows you to create your design regardless of SCREEN SIZE
    public static double SCREEN_WIDTH = Screen.getPrimary().getBounds().getWidth() / 2; //get the width of the display
    public static double SCREEN_HEIGHT = SCREEN_WIDTH;


    public static short PAUSE_VALUE = 1000;


    public static String PATH_RANKINGS = "rankings.txt";
    public static String FILE_ERROR_ALERT_TITLE = "File error.";
    public static String FILE_ERROR_RANKINGS = "Could not load records file.";
    public static String FILE_ERROR_QUESTIONS = "Could not load questions file.";
    public static String INVALID_USERNAME = "Invalid username. Lenght must be at least 3 characters.";
    public static String INVALID_EMAIL = "Invalid email address. Email length should be at least 10 characters and contain @";
}
