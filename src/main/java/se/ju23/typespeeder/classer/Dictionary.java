package se.ju23.typespeeder.classer;

import java.util.List;

public class Dictionary {

    private String language;
    private List<String> words;

    public Dictionary(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<String> getWords() {
        this.words = words;
        return null;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }
    public void addWord(String word){
        words.add(word);
    }
    @Override
    public String toString() {
        return "Dictionary{" +
                "language='" + language + '\'' +
                '}';
    }
}
