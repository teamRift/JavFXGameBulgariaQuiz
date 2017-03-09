package application.classes;

public class City {
    private final static String QUESTION_FILE_SUFFIX = "Questions.txt";
    private String name;
    private String fileName;

    public City(){}

    public String getName() {
        return GUIHelper.capitalize(name);
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

}
