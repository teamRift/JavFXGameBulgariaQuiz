package application.classes;

import com.sun.org.apache.bcel.internal.classfile.SourceFile;
import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static application.controllers.BootController.gameManager;

public class Scores {

    private List<Score> leaderboard;

    public Scores() {

        leaderboard = load();

    }

    private static void create() {

        Path filePath = Paths.get("./" + Values.PATH_RANKINGS);

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

    public static List<Score> load() {

        ArrayList<Score> scores = new ArrayList<>();

        try {

            Path filePath = Paths.get("./" + Values.PATH_RANKINGS);

            if (!Files.exists(filePath)) {

                Utils.log(Scores.class,"");

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

        if (leaderboard.isEmpty()){

            load();

        }

        return leaderboard;

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
