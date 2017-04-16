package application.dependencies;

import application.classes.GameManager;
import application.classes.Question;
import application.classes.Scores;
import application.controllers.QuestionsController;
import application.ranking.Ranking;

/**
 * Created by Dani on 16.4.2017 г..
 */
public class DependencyInjectionContainer {

    private static final GameManager GAME_MANAGER_INSTANCE = new GameManager();
    private static final Question QUESTION_INSTANCE = new Question();
    private static final QuestionsController QUESTIONS_CONTROLLER_INSTANCE = new QuestionsController();
    private static final Scores SCORES_INSTANCE = new Scores();
    private static final Ranking RANKING_INSTANCE = new Ranking();

    public static GameManager getGameManagerInstance() {
        return GAME_MANAGER_INSTANCE;
    }

    public static Question getQuestionInstance() { return QUESTION_INSTANCE; }

    public static QuestionsController getQuestionsControllerInstance() {
        return QUESTIONS_CONTROLLER_INSTANCE;
    }

    public static Scores getScoresInstance() {
        return SCORES_INSTANCE;
    }

    public static Ranking getRankingInstance() {
        return RANKING_INSTANCE;
    }


    private DependencyInjectionContainer() {
    }
}
