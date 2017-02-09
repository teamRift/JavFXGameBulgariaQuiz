package application.classes;

import application.controllers.QuestionsController;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Question {

    static Random rand = new Random();
    static int score = 0;
    static int questionCount = 1;
    static int questionIndex = 0;
    static int questionsCorrect = 0;
    static ArrayList<Button> buttons;
    final static String DELIMITER = ";";

    String question;
    String correctAnswer;
    ArrayList<String> wrongsAnswers;
    Button randomButton;

    public Question(String question, String correct, String wrong1, String wrong2, String wrong3) {
        this.question = question;
        this.correctAnswer = correct;
        this.wrongsAnswers = new ArrayList<>();
        this.wrongsAnswers.add(wrong1);
        this.wrongsAnswers.add(wrong2);
        this.wrongsAnswers.add(wrong3);
    }

    public static ArrayList<Question> loadQuestions(String filename) {
        ArrayList<Question> questions = new ArrayList<>();
        String projectPath = System.getProperty("user.dir");

        try {
            Path filePath = Paths.get(projectPath + "/src/application/resources/questions/" + filename);

            Files.lines(filePath).forEach(line -> {
                if (line.isEmpty()) {
                    return;
                }
                String[] tokens = line.split(DELIMITER);
                questions.add(new Question(tokens[0].trim(), tokens[1].trim(), tokens[2].trim(), tokens[3].trim(), tokens[4].trim()));
            });
        } catch (IOException e) {
            e.printStackTrace();
            Alert notFound = new Alert(Alert.AlertType.ERROR);
            notFound.setTitle("Questions File Error");
            notFound.showAndWait();

            Platform.exit();
            System.exit(0);
        }



        return questions;
    }



    public static void setButtons(Button...buttonsArray) {
        buttons = new ArrayList<>(Arrays.asList(buttonsArray));
    }



    public static int getQuestionIndex() { return questionIndex; }



    public void displayQuestion(Label lbl, Label correctLabel) {

       ArrayList<Button> buttonsCopy = new ArrayList<>(buttons);


        for (Button b : buttonsCopy) {

            b.getStyleClass().remove("correct");
            b.getStyleClass().remove("wrong");
        }

        lbl.setText(this.question);
        correctLabel.setText("Question " + questionCount);


        int randInt = rand.nextInt(4);
        randomButton = buttonsCopy.get(randInt);


        buttonsCopy.get(randInt).setText(this.correctAnswer);
        buttonsCopy.remove(randInt);



        Collections.shuffle(this.wrongsAnswers);
        for (Button b : buttonsCopy) {

            b.setText(this.wrongsAnswers.get(buttonsCopy.indexOf(b)));
        }
    }



    public void checkCorrect(Button b, ArrayList<Question> questions, Label scoreLabel) {


        if (this.randomButton == b) {

            score += 10;
            scoreLabel.setText("Score: " + score);
            questionsCorrect += 1;
        } else {

            this.randomButton.getStyleClass().add("correct");
        }


        if (questions.size() == questionCount) {
            QuestionsController.finished(score, questionsCorrect);
        }

        questionCount += 1;
        questionIndex += 1;
    }
    public ArrayList jokerBtn(ArrayList<Button> btn){

        ArrayList<Button> list = new ArrayList<>();

        ArrayList<String> str1 = new ArrayList<>();

        for (Button button : btn) {

            String str = button.getText();

            if (!str.equals(correctAnswer) && !str1.contains(str)){
                list.add(button);
                str1.add(str);
            }
        }
        return list;
    }
}
