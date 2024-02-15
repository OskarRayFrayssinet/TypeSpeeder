package se.ju23.typespeeder.userInterfaces;

public interface IO {
    boolean yesNo(String prompt);

    String getString();

    String getYesOrNo();

    int getInt();

    void addString(String s);
    void addStringWithoutTranslation(String s);

    void clear();

    void exit();
}
