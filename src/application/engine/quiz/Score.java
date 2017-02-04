package application.engine.quiz;

import java.util.Date;

/**
 * Created by Administrator on 2/4/2017.
 */
public class Score {
    private int scores;
    private String userName;
    public Score(String userName, int scores){
        this.userName = userName;
        this.scores = scores;
    }
    public int getValue() { return scores; }
    public String prepareSave(){
        //todo get current city name and save
        return String.format("%s ; %s", userName,scores);
    }
}
