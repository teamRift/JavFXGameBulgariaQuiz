package application.classes.cities;

import application.classes.Values;

public class Ruse extends City {

    @Override
    public String name() {
        return "Ruse";
    }

    @Override
    public String fileName() {
        return Values.QUESTIONS_RS;
    }
}
