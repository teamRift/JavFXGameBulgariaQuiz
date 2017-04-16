package application.classes;

import application.constants.ConstantsDimentions;
import application.constants.ConstantsErrorMsg;
import application.constants.ConstantsPath;
import application.controllers.QuestionsController;
import application.dependencies.DependencyInjectionContainer;
import javafx.application.Platform;
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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Question {

    private static final String DELIMITER = ";";

    private Random rand = new Random();
    private int score = 0;
    private int questionCount = 1;
    private int questionIndex = 0;
    private int questionsCorrect = 0;
    private ArrayList<Button> buttons = new ArrayList<>();

    private String question;
    private String correctAnswer;
    private ArrayList<String> wrongsAnswers = new ArrayList<>();
    private Button randomButton;

    private GameManager gameManager = DependencyInjectionContainer.getGameManagerInstance();
    private QuestionsController questionsController = DependencyInjectionContainer.getQuestionsControllerInstance();
    private Song song = DependencyInjectionContainer.getSongInstance();

    public Question() {
    }

    public Question(String question, String correct, String wrong1, String wrong2, String wrong3) {
        this.question = question;
        this.correctAnswer = correct;
        this.wrongsAnswers = new ArrayList<>();
        this.wrongsAnswers.add(wrong1);
        this.wrongsAnswers.add(wrong2);
        this.wrongsAnswers.add(wrong3);
    }

    public ArrayList<Question> loadQuestions(String filename, String difficulty) {
        ArrayList<Question> questions = new ArrayList<>();
        try {
            Path filePath = Paths.get(ConstantsPath.PATH_TO_PROJECT + ConstantsPath.PATH_TO_QUESTIONS + filename);
            Files.lines(filePath).forEach(line -> {
                if (line.isEmpty() | !line.contains(difficulty)) {
                    return;
                }

                String[] tokens = line.split(DELIMITER);
                questions.add(new Question(tokens[1].trim(), tokens[2].trim(), tokens[3].trim(), tokens[4].trim(), tokens[5].trim()));

            });
        } catch (IOException e) {
            e.printStackTrace();
            Alert notFound = new Alert(Alert.AlertType.ERROR);
            notFound.setTitle(ConstantsErrorMsg.FILE_ERROR_QUESTIONS);
            notFound.showAndWait();
            Platform.exit();
            System.exit(0);
        }
        return questions;
    }

    public void setButtons(Button... buttonsArray) {
        this.buttons = new ArrayList<>(Arrays.asList(buttonsArray));
    }

    public int getQuestionIndex() {
        return this.questionIndex;
    }

    public void displayQuestion(Label currentLabel, Label correctLabel) {
        ArrayList<Button> buttonsCopy = new ArrayList<>(this.buttons);

        currentLabel.setText(this.question);
        correctLabel.setText("Question " + this.questionCount);

        int randInt = this.rand.nextInt(4);
        this.randomButton = buttonsCopy.get(randInt);

        buttonsCopy.get(randInt).setText(this.correctAnswer);
        buttonsCopy.get(randInt).setTextFill(Color.BLACK);
        buttonsCopy.get(randInt).setBackground(new Background(new BackgroundFill(Paint.valueOf("#FFFFFF"), new CornerRadii(7), new Insets(5, 5, 5, 5))));

        buttonsCopy.remove(randInt);
        Collections.shuffle(this.wrongsAnswers);
        for (Button b : buttonsCopy) {
            b.setTextFill(Color.BLACK);
            b.setBackground(new Background(new BackgroundFill(Paint.valueOf("#FFFFFF"), new CornerRadii(7), new Insets(5, 5, 5, 5))));
            b.setText(this.wrongsAnswers.get(buttonsCopy.indexOf(b)));
        }
    }

    public void checkCorrect(Button b, ArrayList<Question> questions, Label scoreLabel) {
        if (this.randomButton == b) {
            b.setTextFill(Color.WHITE);
            b.setBackground(new Background(new BackgroundFill(Paint.valueOf("#006600"), new CornerRadii(7), new Insets(5, 5, 5, 5))));
            this.song.winSound();
            for (Question currentQuestion : questions) {
                currentQuestion.score += 10;
                currentQuestion.questionsCorrect++;
            }
            scoreLabel.setText("Score: " + score);
        } else {
            b.setTextFill(Color.WHITE);
            b.setBackground(new Background(new BackgroundFill(Paint.valueOf("#cc3745"), new CornerRadii(7), new Insets(5, 5, 5, 5))));
            this.song.wrongSound();
        }

        if (questions.size() == this.questionCount) {
            this.questionsController.finished(this.score, this.questionsCorrect, questions);
            try {
                showScores(b);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (Question currentQuestion : questions) {
            currentQuestion.setQuestionCount(currentQuestion.getQuestionCount() + 1);
        }
        DependencyInjectionContainer.getQuestionInstance().questionIndex++;

    }

    private void showScores(Button b) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(ConstantsPath.PATH_TO_RANKINGS_WINDOW));
        Stage stage = (Stage) b.getScene().getWindow();
        stage.setScene(new Scene(root, ConstantsDimentions.SCREEN_WIDTH, ConstantsDimentions.SCREEN_HEIGHT));
        stage.show();
    }

    public ArrayList jokerBtn(ArrayList<Button> btn) {

        ArrayList<Button> list = new ArrayList<>();
        ArrayList<String> str1 = new ArrayList<>();

        for (Button button : btn) {
            String str = button.getText();
            if (!str.equals(this.correctAnswer) && !str1.contains(str)) {
                list.add(button);
                str1.add(str);
            }
        }
        return list;
    }

    public void reset() {
        this.score = 0;
        this.questionCount = 1;
        this.questionIndex = 0;
        this.questionsCorrect = 0;
        this.gameManager.setCurrentUserPoints(0);
    }

    public int getQuestionCount() {
        return this.questionCount;
    }

    public void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;
    }

    public void setQuestionIndex(int questionIndex) {
        this.questionIndex = questionIndex;
    }
}
