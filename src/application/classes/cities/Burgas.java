package application.classes.cities;

import application.classes.Values;

public class Burgas extends City{

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }
    public String fileName() {
        return Values.QUESTIONS_BLG;
    }
}
