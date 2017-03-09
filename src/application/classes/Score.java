package application.classes;

public class Score {

    private String userName;
    private String cityName;
    private String difficulty;
    private String dateSet;
    private int scores;

    public Score(){}

    public Score(String cityName, String userName, int scores) {
        this.setCityName(capitalize(cityName));
        this.setUserName(userName);
        this.setScore(scores);
    }

    public String getDate(){
        return this.dateSet;
    }

    public void setDate(String date){
        this.dateSet = date;
    }

    public void setScore(int scores) {
        this.scores = scores;
    }

    public void setCityName(String city){
        this.cityName = city;
    }

    public String getCityName(){ return cityName;}

    public String getUserName(){ return userName; }

    public void setUserName(String userName){ this.userName = userName;}

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getValue() { return scores; }

    public String prepareSave() {
        return String.format("%s ; %s ; %s", cityName, userName, scores);
    }

    public static String capitalize(String input){
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

}

