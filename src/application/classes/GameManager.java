package application.classes;

import application.classes.cities.City;
import application.classes.cities.Mordor;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.List;

public class GameManager {

    public static boolean isLogged;

    public static boolean hasPlayed;

    private String currentUser;

    private int maxPoints;

    private int currentUserPoints;

    private int userMaxPoints;

    private City city;

    private List<Score> scores;

    public GameManager(){
        init();
    }

    public String getCityName(){
        return this.city.getClass().getSimpleName();
    }

    public String getFileName(){
        return this.city.fileName();
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public City getCity(){
        return this.city;
    }

    public int getCurrentUserPoints() {
        return currentUserPoints;
    }

    public int getUserMaxPoints() {
        return userMaxPoints;
    }

    public int getMaxScore(){
        return maxPoints;
    }

    public void setCurrentUser(String currentUser) {

        this.currentUser = currentUser;

        try {

            Scores.findAndLoad(currentUser);

        } catch (Exception e) {

            e.printStackTrace();

        }

        isLogged = true;

    }

    public void setCity(City city) {

        if (city != null) this.city = city;

        else this.city = new Mordor();

    }

    public void setCurrentUserPoints(int currentUserPoints) {

        this.currentUserPoints = currentUserPoints;

        hasPlayed = true;
    }

    public void setUserMaxPoints(int userMaxPoints) {
        this.userMaxPoints = userMaxPoints;
    }

    public void setMaxScore(int maxScore){
        this.maxPoints = maxScore;
    }

    public List<Score> getScores() {

        setScores();

        return scores;
    }

    private void setScores() {

        Scores s = new Scores();

        this.scores = s.getScores();

        int length = scores.size();

        if (length > 5) {

            length = 5;

        }

        this.setMaxScore(this.scores.get(0).getValue());

    }

    public void setFactsLabel(Label label){

        label.setText("Did you know an ancient city, dated back 10.000 BC, has been discovered on the bottom of the Black sea?");

    }

    public void setUserName(TextField t){

        if (this.getCurrentUser() == null) {

            t.setText("USERNAME");

        } else {

            t.setText(this.getCurrentUser());

        }
    }

    public void init(){
        setScores();
    }

}
