package se.ju23.typespeeder.userInterfaces;

public interface IO {
    boolean yesNo(String prompt);

    String getString();

    String getEnter();

    String getYesOrNo();

    int getInt();

    void addString(String s);
    void addGameText(String s);

    void clear();

    void exit();
}
