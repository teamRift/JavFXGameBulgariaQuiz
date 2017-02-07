package application.classes;

import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class
Rankings {
    //TODO private String cityName;
    private List<Score> leaderboard;
    private File scores = new File(Values.PATH_RANKINGS);

    public Rankings(){
        try {
            leaderboard = load();
        } catch (Exception ex){
        }
    }
    public static Rankings create(){
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(Values.PATH_RANKINGS), "utf-8"))) {
            writer.write("<-- START -->\n");
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
        try(FileWriter fw = new FileWriter(Values.PATH_RANKINGS, true);
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
            Path filePath = Paths.get("./" + Values.PATH_RANKINGS);

            Files.lines(filePath).forEach(line -> {
                if (line.isEmpty()) {
                    return;
                }
                String[] tokens = line.split(";");
                scores.add(new Score(tokens[0].trim(), Integer.parseInt(tokens[1].trim())));
            });
        } catch (IOException e) {

            Alert notFound = new Alert(Alert.AlertType.ERROR);
            notFound.setTitle(Values.FILE_ERROR_ALERT_TITLE);
            notFound.setContentText(Values.FILE_ERROR_RANKINGS);
            notFound.showAndWait();

            Platform.exit();
            System.exit(0);
        }
        return scores;
    }
    public void findAndLoad(Player player) throws Exception {
        ArrayList<Score> scores = new ArrayList<>();
        try {
            Path filePath = Paths.get("./" + Values.PATH_RANKINGS);
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
        }
    }
    public List<Score> getScores(){
        return leaderboard;
    }
}
