package application.classes;

import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static application.controllers.BootController.gameManager;

public class Scores {

    private List<Score> leaderboard;

    private static File scores;

    public Scores() {

        try {

            leaderboard = load();

        } catch (Exception ex){

        }

    }

    private static Scores create() {

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(

                new FileOutputStream(Values.PATH_RANKINGS), "utf-8"))) {

            writer.write("<-- START -->\n");

            writer.write("MORDOR ; SAURON ; 0\n");

            writer.close();

        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

        return new Scores();
    }

    public static void save(Score score) {

        Path filePath = Paths.get("./" + Values.PATH_RANKINGS);

        List<String> fileContent = null;

        try {

            fileContent = new ArrayList<>(Files.readAllLines(filePath, StandardCharsets.UTF_8));

        } catch (IOException e) {

            e.printStackTrace();

        }

        for (int i = 0; i < fileContent.size(); i++) {

            String[] tokens = fileContent.get(i).replaceAll(" ", "").split(";");

            if (tokens.length > 2) {

                if (tokens[0].equalsIgnoreCase(score.getCityName()) & tokens[1].equalsIgnoreCase(score.getUserName()) & Integer.valueOf(tokens[2]) < score.getValue()) {

                    fileContent.set(i, score.prepareSave());
                    break;

                }

            }

        }

        try {

            Files.write(filePath, fileContent, StandardCharsets.UTF_8);

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    public static ArrayList<Score> load() throws Exception {

        ArrayList<Score> scores = new ArrayList<>();

        try {
            Path filePath = Paths.get("./" + Values.PATH_RANKINGS);

            Files.lines(filePath).forEach(line -> {

                if (line.isEmpty() | line.contains("<-- START -->")) {

                    return;

                }

                String[] tokens = line.split(";");

                scores.add(new Score(tokens[0].trim(),tokens[1].trim(), Integer.parseInt(tokens[2].trim())));

            });

        } catch (IOException e) {

            create();

            Alert notFound = new Alert(Alert.AlertType.ERROR);

            notFound.setTitle(Values.FILE_ERROR_ALERT_TITLE);

            notFound.setContentText(Values.FILE_ERROR_RANKINGS);

            notFound.showAndWait();


            Platform.exit();

            System.exit(0);

        }


        Collections.sort(scores, (s1, s2) -> {

            if (s1.getValue() > s2.getValue())

                return -1; // replace with 1 to reverse

            if (s1.getValue() < s2.getValue())

                return 1; // replace with -1 to reverse

            return 0;

        });

        return scores;
    }

    public static void findAndLoad(String userName) throws Exception {

        ArrayList<Score> scores = new ArrayList<>();

        try {

            Path filePath = Paths.get("./" + Values.PATH_RANKINGS);

            Files.lines(filePath).forEach(line -> {

                if (line.isEmpty()) {
                    line.replace(line,line);
                    return;

                }

                String[] tokens = line.replaceAll(" ","").split(";");

                if (tokens.length > 2) {

                    if (tokens[1].equalsIgnoreCase(userName)) {

                        gameManager.setUserMaxPoints(Integer.parseInt(tokens[2]));

                    }

                }

            });

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    public List<Score> getScores(){
        return leaderboard;
    }

    private boolean userExist(String userName){

        return false;
    }
}
