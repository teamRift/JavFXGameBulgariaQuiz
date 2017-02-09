package application.classes;

import application.classes.cities.City;
import application.classes.cities.Mordor;
import javafx.scene.control.Label;

import java.util.List;

public class GameManager {

    private String currentUser;

    private int maxPoints;

    private int userMaxPoints;

    private City city;

    private List<Score> scores;

    public GameManager(){
        init();
    }
    public void init(){
        setScores();
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
    }

    public void setCity(City city){
        if (city != null) this.city = city;
        else this.city = new Mordor();
    }

    public void setUserMaxPoints(int userMaxPoints) {
        this.userMaxPoints = userMaxPoints;
    }

    public void setMaxScore(int maxScore){
        this.maxPoints = maxScore;
    }

    public void setFactsLabel(Label label){
        label.setText("Знаете ли че на дъното на Черно море, лежат останки от цивилизация изчезнала преди 10 хиляди години?");
    }

    public List<Score> getScores() {
        return scores;
    }

    private void setScores() {
        try {
            this.scores = Scores.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int length = scores.size();
        if (length>5){
            length = 5;
        }
        scores = scores.subList(0,length);
        for (Score score : scores) {
            System.out.println(score.prepareSave());
        }
        this.setMaxScore(this.scores.get(0).getValue());
        System.out.println("DB loaded.");
    }
}
