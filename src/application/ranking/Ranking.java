package application.ranking;

import application.classes.Score;
import application.classes.Scores;
import application.classes.Values;
import application.constants.ConstantsPath;
import application.enums.DifficultEnum;

import java.util.List;

public class Ranking {
    private static String difficult = DifficultEnum.EASY.name().toLowerCase();

    public static String getDifficult(){
        return difficult;
    }

    public static void setDifficult(String currentDifficult){
        difficult = currentDifficult;
    }

    public static void init(){}

    public static List<Score> loadAndSortRanking(String difficult) {
        return Scores.load(ConstantsPath.PATH_TO_SCORES + difficult + ConstantsPath.PATH_RANKING);
    }

}
