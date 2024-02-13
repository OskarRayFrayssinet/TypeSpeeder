package se.ju23.typespeeder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TypeGoalText {
    @Id
    private int id;
    @Column(name = "word_or_char")
    private String wordOrChar;
    @Column(name = "is_swedish")
    private boolean isSwedish;
}
