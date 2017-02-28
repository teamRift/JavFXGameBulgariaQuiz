package application.classes;

public class City {
    private final static String QUESTION_FILE_SUFFIX = "Questions.txt";
    private String name;
    private String fileName;
    public City(){}
    public String getName() {
        if (this.name.equalsIgnoreCase("velikoturnovo")){
            return String.format("%s %s",
                    capitalize(name.substring(0,6)),
                    capitalize(name.substring(6)));
        }
        return capitalize(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName() {
        this.fileName = name + QUESTION_FILE_SUFFIX;
    }

    public static String capitalize(String input){
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

}
