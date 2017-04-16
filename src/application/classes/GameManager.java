package application.classes;

import application.constants.ConstantsDifficulty;
import application.dependencies.DependencyInjectionContainer;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.util.List;

public  class GameManager {

    private boolean hasPlayed;
    private String questionsDifficulty;
    private String currentUser;
    private int maxPoints;
    private int currentUserPoints;
    private int userMaxPoints;
    private City city;
    private List<Score> scores;


    public GameManager(){
        this.questionsDifficulty = ConstantsDifficulty.DIFFICULTY_EASY;
        this.currentUser = "User";
        this.scores = new ArrayList<>();
        this.city = new City();
    }

    public String getCityName(){
        return this.city.getName();
    }

    public  String getFileName(){
        return this.city.getFileName();
    }

    public  String getCurrentUser() {
        return this.currentUser;
    }

    public  City getCity(){
        return this.city;
    }

    public  int getCurrentUserPoints() {
        return this.currentUserPoints;
    }

    public  int getUserMaxPoints() {
        return this.userMaxPoints;
    }

    public  int getMaxScore(){
        return this.maxPoints;
    }

    public  void setCurrentUser(String currentInputUser) {
        this.currentUser = currentInputUser;
        try {
            DependencyInjectionContainer.getScoresInstance().findAndLoad(currentUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  void setCityName(String cityName) {
        if (this.city != null) {
            this.city.setName(cityName);
            this.city.setFileName();
        }
        else {
            this.city = new City();
            this.city.setName("mordor");
            this.city.setFileName();
        }
    }

    public  void setCurrentUserPoints(int currentUserPoint) {
        this.currentUserPoints = currentUserPoint;
        this.hasPlayed = true;
    }

    public void setUserMaxPoints(int userMaxPoints) {
        userMaxPoints = userMaxPoints;
    }

    public void setMaxScore(int maxScore){
        this.maxPoints = maxScore;
    }

    public  List<Score> getScores() {
        setScores();
        return this.scores;
    }

    private  void setScores() {
        Scores s = new Scores();
        this.scores = s.getScores();
        setMaxScore(this.scores.get(0).getValue());
    }

    public  void setFactsLabel(Label label){
        label.setText("Did you know an ancient city, dated back 10.000 BC, has been discovered on the bottom of the Black sea?");
    }

    public  void setUserName(TextField t) {
        if (getCurrentUser() == null) {
            t.setText("USERNAME");
        } else {
            t.setText(getCurrentUser());
        }
    }

    public  String getQuestionsDifficulty() {
        return this.questionsDifficulty;
    }

    public  void setQuestionsDifficulty(String questionsCurrentDifficulty) {
        this.questionsDifficulty = questionsCurrentDifficulty;
    }

    public  boolean playerHasPlayed() {
        return this.hasPlayed;
    }

    public  void setHasPlayed(boolean hasPlayed) {
        this.hasPlayed = hasPlayed;
    }

    public  void init(){
        setScores();
    }
}
