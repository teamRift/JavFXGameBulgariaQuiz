package application.classes;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scores {
    private List<Score> leaderboard;
    private String difficult;

    public Scores() {
        leaderboard = load(Values.PATH_RANKING_GLOBAL);
    }

    private static void create() {
        Path filePath = Paths.get(Values.PATH_RANKING_GLOBAL);
        try {
            Files.createFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> fileContent = new ArrayList<String>();
        for (int i = 0; i < 5; i++){
            fileContent.add("BG ; TEAM RIFT ; 0");
        }

        try {
            Files.write(filePath, fileContent, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Score> load(String path) {
        ArrayList<Score> scores = new ArrayList<>();
        try {
            Path filePath = Paths.get(path);
            if (!Files.exists(filePath)) {
                create();
            }

            Files.lines(filePath).forEach(line -> {
                if (line.isEmpty()) {
                    return;
                }
                String[] tokens = line.split(";");
                scores.add(new Score(tokens[0].trim(),tokens[1].trim(), Integer.parseInt(tokens[2].trim())));
            });
        } catch (IOException e) {
            create();
            System.out.println("Created");
            e.printStackTrace();
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

    static void findAndLoad(String userName) throws Exception {
        ArrayList<Score> scores = new ArrayList<>();
        try {
            Path filePath = Paths.get(Values.PATH_RANKING_GLOBAL);
            Files.lines(filePath).forEach(line -> {
                if (line.isEmpty()) {
                    line.replace(line,line);
                    return;
                }
                String[] tokens = line.replaceAll(" ","").split(";");
                if (tokens.length > 2) {
                    if (tokens[1].equalsIgnoreCase(userName)) {
                        GameManager.setUserMaxPoints(Integer.parseInt(tokens[2]));
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    List<Score> getScores(){
        if (leaderboard.isEmpty()){
            load(Values.PATH_RANKING_GLOBAL);
        }
        return leaderboard;
    }

    public static void save(Score score, String difficult) {
        Path filePath = Paths.get(Values.PATH_TO_SCORES + difficult + Values.PATH_RANKING);
        List<String> fileContent = null;

        try {
            fileContent = new ArrayList<>(Files.readAllLines(filePath, StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < fileContent.size(); i++) {
            String[] tokens = fileContent.get(i).replaceAll(" ", "").split(";");

            if (tokens.length > 2) {
                if (tokens[0].equals("BG") | ( tokens[0].equals(score.getCityName()) & tokens[1].equals(score.getUserName()) )) {
                    if ( score.getValue() > Integer.valueOf(tokens[2]) ) {
                        fileContent.set(i, score.prepareSave());
                        break;
                    }
                } else {
                    if( i == fileContent.size()-1){
                        fileContent.add(score.prepareSave());
                        break;
                    }
                }
            }
        }

        try {
            Files.write(filePath, fileContent, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
