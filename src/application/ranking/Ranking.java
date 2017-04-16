package application.ranking;

import application.classes.Score;
import application.classes.Scores;
import application.constants.ConstantsPath;
import application.dependencies.DependencyInjectionContainer;
import application.enums.DifficultEnum;

import java.util.List;

public class Ranking {

    private Scores scoresInstance = DependencyInjectionContainer.getScoresInstance();
    private String difficult = DifficultEnum.EASY.name().toLowerCase();

    public String getDifficult() {
        return this.difficult;
    }

    public void setDifficult(String currentDifficult) {
        this.difficult = currentDifficult;
    }

    public void init() {
    }

    public List<Score> loadAndSortRanking(String difficult) {
        return this.scoresInstance.load(ConstantsPath.PATH_TO_SCORES + difficult + ConstantsPath.PATH_RANKING);
    }

}
