package application.constants;

import application.enums.DifficultEnum;

public class ConstantsDifficulty {
    public static final String DIFFICULTY_EASY = "__" + DifficultEnum.EASY.name();
    public static final String DIFFICULTY_NORMAL = "__" + DifficultEnum.NORMAL.name();
    public static final String DIFFICULTY_HARD = "__" + DifficultEnum.HARD.name();
    public static final String DIFFICULT_GLOBAL = DifficultEnum.GLOBAL.name().toLowerCase();
}
