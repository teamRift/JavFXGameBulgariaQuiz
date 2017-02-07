package application.classes.cities;

public class Mordor extends City {
    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String fileName() {
        return "mordorQuestions.txt";
//         ako imeto na faila i klasa e edno i syshto
//         return this.getClass().getSimpleName().toLowerCase() + "Questions.txt";
    }

}
