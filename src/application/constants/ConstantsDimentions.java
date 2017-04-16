package application.constants;

import javafx.stage.Screen;

public class ConstantsDimentions {
    //This allows you to create your design reg ardless of SCREEN SIZE
    public static final  double SCREEN_WIDTH = Screen.getPrimary().getBounds().getWidth() - (Screen.getPrimary().getBounds().getWidth() / 4); //get the width of the display
    public static final  double SCREEN_HEIGHT = Screen.getPrimary().getBounds().getHeight() - (Screen.getPrimary().getBounds().getHeight() / 4); //get the width of the display

    //размер на височина
    public static final  double ONE_ROW = SCREEN_HEIGHT / 12; // 1 /12 от екрана - 1 ред
    public static final  double TWO_ROWS = SCREEN_HEIGHT / 6; // 1 / 6 от екрана  - 2 реда и т.н.
    public static final  double THREE_ROWS = SCREEN_HEIGHT / 4;
    public static final  double FOUR_ROWS = SCREEN_HEIGHT / 3;
    public static final  double SIX_ROWS = SCREEN_HEIGHT / 2;

    // размери за широчина
    public static final double ONE_COL = SCREEN_WIDTH / 12; // 1/ 12 от екрана - 1 колона
    public static final double TWO_COLS = SCREEN_WIDTH / 6; // 1 / 6 от екрана - 2 колони
    public static final double THREE_COLS = SCREEN_WIDTH / 4;
    public static final double FOUR_COLS = SCREEN_WIDTH / 3;
    public static final double SIX_COLS = SCREEN_WIDTH / 2;
    // размери за големина на шрифт
    public static final int H3 = (int) Math.round(ONE_COL/7);
    public static final int H2 = (int) Math.round(ONE_COL/3);
    public static final int H1 = (int) Math.round(ONE_COL);
}
