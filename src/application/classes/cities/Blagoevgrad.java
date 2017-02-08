package application.classes.cities;

import application.classes.Values;

public class Blagoevgrad extends City{

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String fileName() {
        return Values.QUESTIONS_BLG;
    }
}
