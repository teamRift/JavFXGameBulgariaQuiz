package application.classes;

public class Score {

    private static final int BEGIN_INDEX = 0;
    private static final int END_INDEX = 1;
    private String userName;
    private String cityName;
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

    private void setScore(int scores) {
        this.scores = scores;
    }

    private void setCityName(String city){
        this.cityName = city;
    }

    public String getCityName(){ return this.cityName;}

    public String getUserName(){ return this.userName; }

    private void setUserName(String userName){ this.userName = userName;}

    public int getValue() { return this.scores; }

    public String prepareSave() {
        return String.format("%s ; %s ; %s", this.cityName, this.userName, this.scores);
    }

    private static String capitalize(String input){
        return input.substring(BEGIN_INDEX, END_INDEX).toUpperCase() + input.substring(END_INDEX);
    }

}

