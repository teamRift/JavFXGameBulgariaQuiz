package application.ranking;

import application.classes.GameManager;
import application.classes.Score;
import application.dependencies.DependencyInjectionContainer;
import application.ranking.Loading;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static application.constants.ConstantsPath.*;

public class Scores implements Loading{
    private final static int INDEX_CITY_NAME = 0;
    private static final int INDEX_USER_NAME = 1;
    private static final int INDEX_VALUE_SCORE = 2;
    private static final int INDEX_BEST_FIVE = 5;
    private static final String DELIMITER = ";";
    private static final String EMPTY_TEXT_LINE ="BG ; TEAM RIFT ; 0";
    private static final String MSG_WHEN_CREATE = "Created";

    private List<Score> leaderboard;
    private  List<Score> scores;

    private GameManager gameManager = DependencyInjectionContainer.getGameManagerInstance();

    public Scores() {
        this.leaderboard = this.load(PATH_RANKING_GLOBAL);
    }

    private  void create() {
        Path filePath = Paths.get(PATH_RANKING_GLOBAL);
        try {
            Files.createFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> fileContent = new ArrayList<String>();
        for (int i = INDEX_CITY_NAME; i < INDEX_BEST_FIVE; i++){
            fileContent.add(EMPTY_TEXT_LINE);
        }

        try {
            Files.write(filePath, fileContent, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public  List<Score> load(String path) {
        this.scores = new ArrayList<>();
        try {
            Path filePath = Paths.get(path);
            if (!Files.exists(filePath)) {
                create();
            }

            Files.lines(filePath).forEach(line -> {
                if (line.isEmpty()) {
                    return;
                }
                String[] tokens = line.split(DELIMITER);
                scores.add(new Score(tokens[INDEX_CITY_NAME].trim(),tokens[INDEX_USER_NAME].trim(), Integer.parseInt(tokens[INDEX_VALUE_SCORE].trim())));
            });
        } catch (IOException e) {
            create();
            System.out.println(MSG_WHEN_CREATE);
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


    public  void findAndLoad(String userName) throws Exception {
        this.scores = new ArrayList<>();
        try {
            Path filePath = Paths.get(PATH_RANKING_GLOBAL);
            Files.lines(filePath).forEach(line -> {
                if (line.isEmpty()) {
                    line.replace(line, line);
                    return;
                }
                String[] tokens = line.replaceAll(" ","").split(DELIMITER);
                if (tokens.length > 2) {
                    if (tokens[INDEX_USER_NAME].equalsIgnoreCase(userName)) {
                        this.gameManager.setUserMaxPoints(Integer.parseInt(tokens[INDEX_VALUE_SCORE]));
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Score> getScores(){
        if (this.leaderboard.isEmpty()){
            load(PATH_RANKING_GLOBAL);
        }
        return this.leaderboard;
    }

    public  void save(Score score, String difficult) {
        Path filePath = Paths.get(PATH_TO_SCORES + difficult + PATH_RANKING);
        List<String> fileContent = null;

        try {
            fileContent = new ArrayList<>(Files.readAllLines(filePath, StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(fileContent != null) {
            for (int i = INDEX_CITY_NAME; i < fileContent.size(); i++) {
                String[] tokens = fileContent.get(i).replaceAll(" ", "").split(DELIMITER);

                if (tokens.length > 2) {
                    if (tokens[INDEX_CITY_NAME].equals("BG") || (tokens[INDEX_CITY_NAME].equals(score.getCityName()) && tokens[INDEX_USER_NAME].equals(score.getUserName()))) {
                        if (score.getValue() > Integer.valueOf(tokens[INDEX_VALUE_SCORE])) {
                            fileContent.set(i, score.prepareSave());
                            break;
                        }
                    } else {
                        if (i == fileContent.size() - 1) {
                            fileContent.add(score.prepareSave());
                            break;
                        }
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
