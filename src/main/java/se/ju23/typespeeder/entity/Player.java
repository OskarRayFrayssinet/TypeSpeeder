package se.ju23.typespeeder.entity;

import jakarta.persistence.*;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(name="username")
    String userName;
    String email;
    String password;
    int level; // för varje 100 XP levlar man en gång!
    int experience;
}
