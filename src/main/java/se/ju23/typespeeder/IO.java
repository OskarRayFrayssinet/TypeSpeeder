package se.ju23.typespeeder;

public interface IO {
    boolean yesNo(String prompt);

    String getString();
    int getInt();

    void addString(String s);

    void clear();

    void exit();
}
