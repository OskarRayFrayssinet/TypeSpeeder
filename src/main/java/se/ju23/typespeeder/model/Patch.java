package se.ju23.typespeeder.model;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "patchnews")
public class Patch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column (name ="version")
    private String patchVersion;
    @Column (name = "releasedate")
    public LocalDateTime releaseDateTime = LocalDateTime.now();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Patch(String patchVersion, LocalDateTime releaseDateTime) {
        this.patchVersion = patchVersion;
        this.releaseDateTime = releaseDateTime;
    }

    public Patch() {
    }

    public String getPatchVersion() {
        return patchVersion;
    }

    public void setPatchVersion(String patchVersion) {
        this.patchVersion = patchVersion;
    }

    public LocalDateTime getReleaseDateTime() {
        return releaseDateTime;
    }

    public void setReleaseDateTime(LocalDateTime releaseDateTime) {
        this.releaseDateTime = releaseDateTime;
    }

    public String getFormattedPublishDateTime() {
        return releaseDateTime.format(formatter);
    }

    @Override
    public String toString() {
        return "Patchversion: " + patchVersion + "Publiceringsdatum: " + getFormattedPublishDateTime();
    }
}
