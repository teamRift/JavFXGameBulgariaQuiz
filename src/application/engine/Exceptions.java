package application.engine;

import application.engine.utils.Errors;

/**
 * Created by Administrator on 2/4/2017.
 */
public class Exceptions {
    public static Exception InvalidUserName() throws Exception {
        throw new IllegalArgumentException(Errors.INVALID_USERNAME);
    }
    public static Exception InvalidUserEmail() throws Exception {
        throw new IllegalArgumentException(Errors.INVALID_EMAIL);
    }
    public static Exception InvalidQuestionsPath() throws Exception {
        throw new IllegalArgumentException(Errors.FILE_ERROR_QUESTIONS);
    }
    public static Exception InvalidRankingsPath() throws Exception {
        throw new IllegalArgumentException(Errors.FILE_ERROR_RANKINGS);
    }
}
