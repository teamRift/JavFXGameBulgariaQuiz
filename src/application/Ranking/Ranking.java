package application.Ranking;

import application.classes.Score;
import application.classes.Values;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ranking {

    private static String difficult = "easy";

    public static String getDifficult(){
        return difficult;
    }

    public static void setDifficult(String currentDifficult){
        difficult = currentDifficult;
    }

    public static void init(){

    }

    public static List<Score> loadAndSortRanking(String difficult) {
        ArrayList<Score> currentScores = new ArrayList<>();
        try {
            Path filePath = Paths.get(Values.PATH_TO_SCORES + difficult + Values.PATH_RANKING);
            Files.lines(filePath).forEach(line -> {
                if (line.isEmpty()) {
                    return;
                }
                String[] tokens = line.split(";");
                currentScores.add(new Score(tokens[0].trim(),tokens[1].trim(), Integer.parseInt(tokens[2].trim())));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.sort(currentScores, (s1, s2) -> {
            if (s1.getValue() > s2.getValue())
                return -1; // replace with 1 to reverse
            if (s1.getValue() < s2.getValue())
                return 1; // replace with -1 to reverse
            return 0;
        });

        return currentScores;
    }
}
