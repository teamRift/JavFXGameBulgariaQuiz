package application.ranking;

import application.classes.Score;

import java.util.List;

public interface Loading {
    List<Score> load(String path);
}
