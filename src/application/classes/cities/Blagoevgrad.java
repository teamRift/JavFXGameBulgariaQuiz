package application.classes.cities;

public class Blagoevgrad extends City{

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String fileName() {
        return "blagoevgradQuestions.txt";
    }
}
