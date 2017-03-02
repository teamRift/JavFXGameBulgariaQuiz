package application.classes;

import application.controllers.QuestionsController;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import static application.controllers.BootController.gameManager;

public class Question {

    private static Random rand = new Random();

    private static int score = 0;
    private static int questionCount = 1;
    private static int questionIndex = 0;
    private static int questionsCorrect = 0;
    private static ArrayList<Button> buttons;
    private final static String DELIMITER = ";";

    private String question;
    private String correctAnswer;
    private ArrayList<String> wrongsAnswers;
    private Button randomButton;

    public Question(String question, String correct, String wrong1, String wrong2, String wrong3) {
        this.question = question;
        this.correctAnswer = correct;
        this.wrongsAnswers = new ArrayList<>();
        this.wrongsAnswers.add(wrong1);
        this.wrongsAnswers.add(wrong2);
        this.wrongsAnswers.add(wrong3);
    }
    public static ArrayList<Question> loadQuestions(String filename, String difficulty) {
        ArrayList<Question> questions = new ArrayList<>();
        String projectPath = System.getProperty("user.dir");
        try {
            Path filePath = Paths.get(projectPath + "/src/application/resources/questions/" + filename);
            Files.lines(filePath).forEach(line -> {
                if (line.isEmpty() | !line.contains(difficulty)) {
                    return;
                }

                String[] tokens = line.split(DELIMITER);
                questions.add(new Question(tokens[1].trim(), tokens[2].trim(), tokens[3].trim(), tokens[4].trim(),tokens[5].trim()));

            });

        } catch (IOException e) {
            e.printStackTrace();
            Alert notFound = new Alert(Alert.AlertType.ERROR);
            notFound.setTitle(Values.FILE_ERROR_QUESTIONS);
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
    public void displayQuestion(Label currentLabel, Label correctLabel) {
        ArrayList<Button> buttonsCopy = new ArrayList<>(buttons);

        currentLabel.setText(this.question);
        correctLabel.setText("Question " + questionCount);

        int randInt = rand.nextInt(4);
        randomButton = buttonsCopy.get(randInt);
        buttonsCopy.get(randInt).setText(this.correctAnswer);
        buttonsCopy.remove(randInt);
        Collections.shuffle(this.wrongsAnswers);
        for (Button b : buttonsCopy) {
            b.setTextFill(Color.BLACK);
            b.setBackground(new Background(new BackgroundFill(Paint.valueOf("#FFFFFF"),new CornerRadii(7), new Insets(5,5,5,5))));
            b.setText(this.wrongsAnswers.get(buttonsCopy.indexOf(b)));
        }

    }
    public void checkCorrect(Button b, ArrayList<Question> questions, Label scoreLabel) {
        if (this.randomButton == b) {
            this.randomButton.setTextFill(Color.WHITE);
            this.randomButton.setBackground(new Background(new BackgroundFill(Paint.valueOf("#006600"),new CornerRadii(7), new Insets(5,5,5,5))));
            score += 10;
            scoreLabel.setText("Score: " + score);
            questionsCorrect += 1;
        }


        if (questions.size() == questionCount) {
            QuestionsController.finished(score, questionsCorrect);
            try {
                showScores(b);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        questionCount += 1;

        questionIndex += 1;



    }
    private void showScores(Button b) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("../resources/fxml/rankings.fxml"));

        Stage stage = (Stage)b.getScene().getWindow();

        stage.setScene(new Scene(root, Values.SCREEN_WIDTH,Values.SCREEN_HEIGHT));

        stage.show();
    }
    public ArrayList jokerBtn(ArrayList<Button> btn) {

        ArrayList<Button> list = new ArrayList<>();

        ArrayList<String> str1 = new ArrayList<>();

        for (Button button : btn) {

            String str = button.getText();

            if (!str.equals(correctAnswer) && !str1.contains(str)) {

                list.add(button);

                str1.add(str);

            }

        }

        return list;

    }
    public static void reset() {

        score = 0;

        questionCount = 1;

        questionIndex = 0;

        questionsCorrect = 0;

        gameManager.setCurrentUserPoints(0);

    }
}
