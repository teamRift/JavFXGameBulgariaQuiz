package application.classes;

import javafx.stage.Screen;

public class Values
{
    // IMAGE FILES
    public static String IMG_BG_MAP_FLAG = "application/resources/images/bulgaria1.png";
    public static String IMG_BACKGROUND = "application/resources/images/background.png";

    //FXML FILES
    public static String PATH_CITIES = "../resources/fxml/cities.fxml";

    //QUESTION FILES
    public static String QUESTIONS_SF = "sofiaQuestions.txt";
    public static String QUESTIONS_BS = "burgasQuestions.txt";
    public static String QUESTIONS_BLG = "blagoevgradQuestions.txt";
    public static String QUESTIONS_RS = "ruseQuestions.txt";
    public static String QUESTIONS_VN = "varnaQuestions.txt";
    public static String QUESTIONS_PLD = "plovdivQuestions.txt";
    public static String QUESTIONS_PLN = "plnQuestions.txt";
    public static String QUESTIONS_VT = "velikoturnovoQuestions.txt";
    public static String QUESTIONS_MOCK = "mordorQuestions.txt";

    //DB PATH
    public static String PATH_RANKINGS = "rankings.txt";

    //This allows you to create your design regardless of SCREEN SIZE
    public static double SCREEN_WIDTH = Screen.getPrimary().getBounds().getWidth() - (Screen.getPrimary().getBounds().getWidth() / 4); //get the width of the display
    public static double SCREEN_HEIGHT = Screen.getPrimary().getBounds().getHeight() - (Screen.getPrimary().getBounds().getHeight() / 4); //get the width of the display

    // размери базирани на големината на екрана
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
    public static String LABEL_SCORES_BTN = "Leaderboard";
    public static String LABEL_RANKINGS_TOP_5 = "Top 5 Players";

    public static String FILE_ERROR_ALERT_TITLE = "File error.";
    public static String FILE_ERROR_RANKINGS = "Could not load records file.";
    public static String FILE_ERROR_QUESTIONS = "Could not load questions file.";
    public static String INVALID_USERNAME = "Invalid username. Lenght must be at least 3 characters.";

}
