package application.ranking;

import application.classes.Score;
import application.classes.Scores;
import application.classes.Values;

import java.util.ArrayList;
import java.util.List;

public class Ranking {

    private static String difficult = "easy";

    public static String getDifficult(){
        return difficult;
    }

    public static void setDifficult(String currentDifficult){
        difficult = currentDifficult;
    }

    public static void init(){}

    public static List<Score> loadAndSortRanking(String difficult) {
        List<Score> currentScores = Scores.load(Values.PATH_TO_SCORES + difficult + Values.PATH_RANKING);
        return currentScores;
    }
}
