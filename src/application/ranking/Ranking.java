package application.ranking;

import application.classes.Score;
import application.constants.ConstantsPath;
import application.dependencies.DependencyInjectionContainer;
import application.enums.DifficultEnum;

import java.util.List;

public class Ranking implements Loading{

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

    @Override
    public List<Score> load(String difficult) {
        return this.scoresInstance.load(ConstantsPath.PATH_TO_SCORES + difficult + ConstantsPath.PATH_RANKING);
    }
}
