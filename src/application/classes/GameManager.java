package application.classes;

import application.classes.cities.City;
import application.classes.cities.Mordor;
import javafx.scene.control.Label;

public class GameManager {
    private String currentUser;

    //TODO set when loading scores
    private int maxPoints;

    //TODO set when loading user
    private int userMaxPoints;

    private City city;
    public GameManager(){}
    public void setCity(City city){
        if (city != null) this.city = city;
        else this.city = new Mordor();
    }
    public City getCity(){
        return this.city;
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

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
        // TODO
        // if user exist load it

    }
    public void setFactsLabel(Label label){
        label.setText("Знаете ли че на дъното на Черно море, лежат останки от цивилизация изчезнала преди 10 хиляди години?");
    }
}
