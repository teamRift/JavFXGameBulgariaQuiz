package application.Ranking;

import application.classes.Score;
import application.classes.Scores;
import application.classes.Values;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
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
        List<Score> currentScores = new ArrayList<>();
        currentScores = Scores.load(Values.PATH_TO_SCORES + difficult + Values.PATH_RANKING);
        return currentScores;
    }
}
