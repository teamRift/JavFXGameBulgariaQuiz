package application.classes;

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
        this.questionsDifficulty = "__EASY";
        this.currentUser = "User";
        this.scores = new ArrayList<>();
        this.city = new City();
    }

    public String getCityName(){
        return city.getName();
    }

    public  String getFileName(){
        return city.getFileName();
    }

    public  String getCurrentUser() {
        return currentUser;
    }

    public  City getCity(){
        return city;
    }

    public  int getCurrentUserPoints() {
        return currentUserPoints;
    }

    public  int getUserMaxPoints() {
        return userMaxPoints;
    }

    public  int getMaxScore(){
        return maxPoints;
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
        if (city != null) {
            city.setName(cityName);
            city.setFileName();
        }
        else {
            city = new City();
            city.setName("mordor");
            city.setFileName();
        }
    }

    public  void setCurrentUserPoints(int currentUserPoint) {
        currentUserPoints = currentUserPoint;
        hasPlayed = true;
    }

    public void setUserMaxPoints(int userMaxPoints) {
        userMaxPoints = userMaxPoints;
    }

    public void setMaxScore(int maxScore){
        maxPoints = maxScore;
    }

    public  List<Score> getScores() {
        setScores();
        return scores;
    }

    private  void setScores() {
        Scores s = new Scores();
        scores = s.getScores();
        setMaxScore(scores.get(0).getValue());
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
        return questionsDifficulty;
    }

    public  void setQuestionsDifficulty(String questionsCurrentDifficulty) {
        questionsDifficulty = questionsCurrentDifficulty;
    }

    public  boolean playerHasPlayed() {
        return hasPlayed;
    }

    public  void setHasPlayed(boolean hasPlayed) {
        this.hasPlayed = hasPlayed;
    }

    public  void init(){
        setScores();
    }
}
