package application.engine.quiz;

import application.engine.Exceptions;
import application.engine.utils.Errors;
import application.engine.utils.Strings;
import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Rankings {
    //TODO private String cityName;
    private List<Score> leaderboard;
    private File scores = new File(Strings.PATH_RANKINGS);

    public Rankings(){
        try {
            leaderboard = load();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public Rankings create(){
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(Strings.PATH_RANKINGS), "utf-8"))) {
            writer.write("<-- START -->");
            writer.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Rankings();
    }
    public static void save(Score score) {
        try(FileWriter fw = new FileWriter(Strings.PATH_RANKINGS, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)) {
            out.println(score.prepareSave());
            //more code
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static ArrayList<Score> load() throws Exception {
        ArrayList<Score> scores = new ArrayList<>();

        try {
            Path filePath = Paths.get("./" + Strings.PATH_RANKINGS);

            Files.lines(filePath).forEach(line -> {
                if (line.isEmpty()) {
                    return;
                }
                String[] tokens = line.split(";");
                scores.add(new Score(tokens[0].trim(), Integer.parseInt(tokens[1].trim())));
            });
        } catch (IOException e) {

            Alert notFound = new Alert(Alert.AlertType.ERROR);
            notFound.setTitle(Errors.FILE_ERROR_ALERT_TITLE);
            notFound.setContentText(Errors.FILE_ERROR_RANKINGS);
            notFound.showAndWait();

            Platform.exit();
            System.exit(0);
            throw Exceptions.InvalidRankingsPath();
        }
        return scores;
    }
    public void findAndLoad(Player player) throws Exception {
        ArrayList<Score> scores = new ArrayList<>();
        try {
            Path filePath = Paths.get("./" + Strings.PATH_RANKINGS);
            Files.lines(filePath).forEach(line -> {
                if (line.isEmpty()) {
                    return;
                }
                String[] tokens = line.split(";");
                if (tokens[0].equalsIgnoreCase(player.getName()) & tokens[1].equalsIgnoreCase(player.getEmail())){
                    player.setPoints(new Score(tokens[0],Integer.parseInt(tokens[1])));
                }
            });
        } catch (IOException e) {
            throw Exceptions.InvalidRankingsPath();
        }
    }
    public List<Score> getScores(){
        return leaderboard;
    }
    // TODO    public Rankings top10(){}
    // TODO    public Rankings top5(){}
}
