package application.classes.cities;

public class Plovdiv extends City {
    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String fileName() {
        return "plovdivQuestions.txt";
//         ako imeto na faila i klasa e edno i syshto
//         return this.getClass().getSimpleName().toLowerCase() + "Questions.txt";
    }

}
