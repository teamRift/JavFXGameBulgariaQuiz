package application.classes;

import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class
Scores {
    //TODO private String cityName;
    private List<Score> leaderboard;
    private static File scores = new File(Values.PATH_RANKINGS);

    public Scores(){
        try {
            leaderboard = load();
        } catch (Exception ex){
        }
    }
    private static Scores create(){
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
        //TODO if user exist replace old entry
        try(FileWriter fw = new FileWriter(Values.PATH_RANKINGS, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)) {
            out.println(score.prepareSave());
            out.close();
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
    public void findAndLoad() throws Exception {
        ArrayList<Score> scores = new ArrayList<>();
        try {
            Path filePath = Paths.get("./" + Values.PATH_RANKINGS);
            Files.lines(filePath).forEach(line -> {
                if (line.isEmpty()) {
                    return;
                }
                String[] tokens = line.replaceAll(" ","").split(";");
                //TODO implement loading user points
            });
        } catch (IOException e) {
        }
    }
    public List<Score> getScores(){
        return leaderboard;
    }
}
