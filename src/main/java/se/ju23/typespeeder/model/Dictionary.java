package se.ju23.typespeeder.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class Dictionary {
    private String language;
    private ArrayList words;

    public Dictionary(String language) {
        this.language = language;
        this.words = new ArrayList();
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public ArrayList getWords() {
        return words;
    }

    public void setWords(ArrayList words) {
        this.words = words;
    }

    public void addWord(String word){
        words.add(word);
    }
}