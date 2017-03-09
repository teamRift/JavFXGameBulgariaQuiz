package application.classes;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.util.List;

public  class GameManager {

    private static boolean hasPlayed = false;
    private static String questionsDifficulty = "__EASY";
    private static String currentUser = "User";
    private static int maxPoints = 0;
    private static int currentUserPoints = 0;
    private static int userMaxPoints = 0;
    private static City city = new City();
    private static List<Score> scores = new ArrayList<>();

    public GameManager(){
        init();
    }

    public static String getCityName(){
        return city.getName();
    }

    public static String getFileName(){
        return city.getFileName();
    }

    public static String getCurrentUser() {
        return currentUser;
    }

    public static City getCity(){
        return city;
    }

    public static int getCurrentUserPoints() {
        return currentUserPoints;
    }

    public static int getUserMaxPoints() {
        return userMaxPoints;
    }

    public static int getMaxScore(){
        return maxPoints;
    }

    public static void setCurrentUser(String currentUser) {
        currentUser = currentUser;
        try {
            Scores.findAndLoad(currentUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setCityName(String cityName) {
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

    public static void setCurrentUserPoints(int currentUserPoints) {
        currentUserPoints = currentUserPoints;
        hasPlayed = true;
    }

    static void setUserMaxPoints(int userMaxPoints) {
        userMaxPoints = userMaxPoints;
    }

    static void setMaxScore(int maxScore){
        maxPoints = maxScore;
    }

    public static List<Score> getScores() {
        setScores();
        return scores;
    }

    private static void setScores() {
        Scores s = new Scores();
        scores = s.getScores();
        setMaxScore(scores.get(0).getValue());
    }

    public static void setFactsLabel(Label label){
        label.setText("Did you know an ancient city, dated back 10.000 BC, has been discovered on the bottom of the Black sea?");
    }

    public static void setUserName(TextField t) {
        if (getCurrentUser() == null) {
            t.setText("USERNAME");
        } else {
            t.setText(getCurrentUser());
        }
    }

    public static String getQuestionsDifficulty() {
        return questionsDifficulty;
    }

    public static void setQuestionsDifficulty(String questionsDifficulty) {
        questionsDifficulty = questionsDifficulty;
    }

    public static boolean playerHasPlayed() {
        return hasPlayed;
    }

    public static void setHasPlayed(boolean hasPlayed) {
        GameManager.hasPlayed = hasPlayed;
    }

    public static void init(){
        setScores();
    }
}
