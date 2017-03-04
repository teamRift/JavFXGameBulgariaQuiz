package application.classes;

import javafx.stage.Screen;

public class Values
{
    // IMAGE FILES

    public static String IMG_BG_MAP_FLAG = "application/resources/images/bulgaria1.png";
    public static String IMG_BACKGROUND = "application/resources/images/background.png";
    public static String IMG_BACKGROUND_CITIES = "application/resources/images/bulgaria2.png";

    //FXML FILES

    public static String PATH_CITIES = "../resources/fxml/cities.fxml";
    public static final String LABEL_DIFFICULTY = "Choose difficulty";
    public static final String DIFFICULTY_EASY = "__EASY";
    public static final String DIFFICULTY_NORMAL = "__NORMAL";
    public static final String DIFFICULTY_HARD = "__DIFFICULT";


    public static String PATH_RANKINGS = "scores.txt";

    //This allows you to create your design regardless of SCREEN SIZE

    public static double SCREEN_WIDTH = Screen.getPrimary().getBounds().getWidth() - (Screen.getPrimary().getBounds().getWidth() / 4); //get the width of the display
    public static double SCREEN_HEIGHT = Screen.getPrimary().getBounds().getHeight() - (Screen.getPrimary().getBounds().getHeight() / 4); //get the width of the display
//    public static double SCREEN_HEIGHT = 600;
//    public static double SCREEN_WIDTH = 800;
//    // размери базирани на големината на екрана
    // размери за височина

    public static double ONE_ROW = SCREEN_HEIGHT / 12; // 1 /12 от екрана - 1 ред
    public static double TWO_ROWS = SCREEN_HEIGHT / 6; // 1 / 6 от екрана  - 2 реда и т.н.
    public static double THREE_ROWS = SCREEN_HEIGHT / 4;
    public static double FOUR_ROWS = SCREEN_HEIGHT / 3;
    public static double SIX_ROWS = SCREEN_HEIGHT / 2;

    // размери за широчина
    public static double ONE_COL = SCREEN_WIDTH / 12; // 1/ 12 от екрана - 1 колона
    public static double TWO_COLS = SCREEN_WIDTH / 6; // 1 / 6 от екрана - 2 колони
    public static double THREE_COLS = SCREEN_WIDTH / 4;
    public static double FOUR_COLS = SCREEN_WIDTH / 3;
    public static double SIX_COLS = SCREEN_WIDTH / 2;

    public static short PAUSE_VALUE = 1000;

    public static String LABEL_BACK_BTN = "Back";
    public static String LABEL_START_BTN = "New game";
    public static String LABEL_EXIT_BTN = "Exit";
    public static String LABEL_RANKTABLE_BTN="Rank Table";
    public static String LABEL_CREDITS_BTN = "Credits";
    public static String LABEL_CHOOSE_CITY_BTN = "Choose city";
    public static String LABEL_SCORES_BTN = "Leaderboard";
    public static String LABEL_RANKINGS_TOP_5 = "Top 5 Players";
    public static String LABEL_GAME_TITLE = "Bulgaria Quiz";
    public static String LABEL_TEAM_RIFT = "Team Rift®";
    public static String LABEL_SOFTUNI = "SoftUni 2017";

    public static int H3 = (int) Math.round(ONE_COL/7);
    public static int H2 = (int) Math.round(ONE_COL/3);
    public static int H1 = (int) Math.round(ONE_COL);

    public static String FILE_ERROR_ALERT_TITLE = "File error.";
    public static String FILE_SCORES_MISSING = "There was an error with your scores db file. You need to restart the game to continue playing.";
    public static String FILE_ERROR_RANKINGS = "Could not load records file.";
    public static String FILE_ERROR_QUESTIONS = "Could not load questions file.";

    public static String DEFAULT_FONT = "Lucida Sans";

}
