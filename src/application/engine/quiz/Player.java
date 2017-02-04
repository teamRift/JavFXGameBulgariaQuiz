package application.engine.quiz;

import application.engine.Exceptions;
import application.engine.utils.Errors;

import static application.engine.Exceptions.InvalidUserName;

/**
 * Created by Administrator on 2/4/2017.
 */
public class Player {

    private String name;
    private String email;
    private Score scores;

    public Player(){
        this.scores = new Score("username",0);
    }

    public String getName() {
        return name;
    }

    public Player setName(String name) {
        if (name != null & name.length() > 3){
            this.name = name;
            return this;    
        } else {
            this.name = "DUMMY_NAME";
            return this;
            //  throw InvalidUserName();
        }
    }

    public String getEmail() {
        return email;
    }

    public Player setEmail(String email) {
        if (email != null & email.contains("@") & email.length() > 5){
            this.email = email;
            return this;
        } else {
            this.email = "DUMMY@EMAIL.FTW";
            return this;
            //  throw Exceptions.InvalidUserEmail();
        }
    }

    public Score getPoints() {
        return scores;
    }

    public Player setPoints(Score scores) {
        this.scores = scores;
        return this;
    }

//    public String INFO = "TEST";
    public String INFO = String.format("%s ; %s", name, email);
}
