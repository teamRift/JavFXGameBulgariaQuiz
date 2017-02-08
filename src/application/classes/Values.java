package application.classes;

import javafx.stage.Screen;

public class Values
{
    //This allows you to create your design regardless of SCREEN SIZE
    public static double SCREEN_WIDTH = Screen.getPrimary().getBounds().getWidth() - (Screen.getPrimary().getBounds().getWidth() / 4); //get the width of the display
    public static double SCREEN_HEIGHT = Screen.getPrimary().getBounds().getHeight() - (Screen.getPrimary().getBounds().getHeight() / 4); //get the width of the display

    public static double ONE_ROW = SCREEN_HEIGHT / 12;
    public static double TWO_ROWS = SCREEN_HEIGHT / 6;
    public static double THREE_ROWS = SCREEN_HEIGHT / 4;
    public static double FOUR_ROWS = SCREEN_HEIGHT / 3;
    public static double SIX_ROWS = SCREEN_HEIGHT / 2;

    public static double ONE_COL = SCREEN_HEIGHT / 12;
    public static double TWO_COLS = SCREEN_HEIGHT / 6;
    public static double THREE_COLS = SCREEN_HEIGHT / 4;
    public static double FOUR_COLS = SCREEN_HEIGHT / 3;
    public static double SIX_COLS = SCREEN_HEIGHT / 2;

    public static short PAUSE_VALUE = 1000;
    public static String LABEL_BACK_BTN = "Back";
    public static String LABEL_START_BTN = "New game";
    public static String LABEL_EXIT_BTN = "Exit";
    public static String LABEL_SCORES_BTN = "Leaderboard";
    public static String LABEL_RANKINGS_TOP_5 = "Top 5 Players";
    public static String QUESTIONS_SF = "sofiaQuestions.txt";
    public static String QUESTIONS_BS = "burgasQuestions.txt";
    public static String QUESTIONS_BLG = "blagoevgradQuestions.txt";
    public static String QUESTIONS_RS = "ruseQuestions.txt";
    public static String QUESTIONS_VN = "varnaQuestions.txt";
    public static String QUESTIONS_PLD = "plovdivQuestions.txt";
    public static String QUESTIONS_PLN = "plnQuestions.txt";
    public static String QUESTIONS_VT = "velikoturnovoQuestions.txt";
    public static String QUESTIONS_MOCK = "mordorQuestions.txt";
    public static String PATH_RANKINGS = "rankings.txt";
    public static String FILE_ERROR_ALERT_TITLE = "File error.";
    public static String FILE_ERROR_RANKINGS = "Could not load records file.";
    public static String FILE_ERROR_QUESTIONS = "Could not load questions file.";
    public static String INVALID_USERNAME = "Invalid username. Lenght must be at least 3 characters.";
    public static String INVALID_EMAIL = "Invalid email address. Email length should be at least 10 characters and contain @";
}
