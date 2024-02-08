package se.ju23.typespeeder.constants;

public class EnglishCharacterTypes {
    public static final String UPPER_CASE_REGEX ="[A-Z]";
    public static final String LOWER_CASE_REGEX ="[a-z]";
    public static final String DIGIT_REGEX ="[0-9]";
    public static final String SPECIAL_CHARACTER_REGEX ="[^A-Za-z0-9]";
    public static final String UPPER_LOWER_STRINGS = UPPER_CASE_REGEX + LOWER_CASE_REGEX;
    public static final String UPPER_DIGIT_STRINGS = UPPER_LOWER_STRINGS + DIGIT_REGEX;
    public static final String LOWER_DIGIT_STRINGS = LOWER_CASE_REGEX + DIGIT_REGEX;
    public static final String THREE_STRINGS = UPPER_CASE_REGEX + LOWER_CASE_REGEX + DIGIT_REGEX;
    public static final String ALL_STRINGS = UPPER_LOWER_STRINGS + DIGIT_REGEX + SPECIAL_CHARACTER_REGEX;

}
