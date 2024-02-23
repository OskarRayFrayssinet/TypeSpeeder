package se.ju23.typespeeder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Patch {
    private String patchVersion;
    public LocalDateTime realeaseDateTime;
    private String patchNotes;
    private String headLine;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Patch(String patchVersion, LocalDateTime realeaseDateTime, String patchNotes, String headLine) {
        this.patchVersion = patchVersion;
        this.realeaseDateTime = realeaseDateTime;
        this.patchNotes = patchNotes;
        this.headLine = headLine;
    }

    public Patch() {
        setRealeaseDateTime(LocalDateTime.now());
    }

    public String getHeadLine() {
        return headLine;
    }

    public void setHeadLine(String headLine) {
        this.headLine = headLine;
    }

    public String getPatchVersion() {
        return patchVersion;
    }

    public void setPatchVersion(String patchVersion) {
        this.patchVersion = patchVersion;
    }

    public LocalDateTime getRealeaseDateTime() {
        return realeaseDateTime;
    }

    public void setRealeaseDateTime(LocalDateTime realeaseDateTime) {
        this.realeaseDateTime = realeaseDateTime;
    }

    public String getPatchNotes() {
        return patchNotes;
    }

    public void setPatchNotes(String patchNotes) {
        this.patchNotes = patchNotes;
    }
    @Override
    public String toString() {
        return headLine + "\tVersion: " + patchVersion + "\n" + "Publicerad " + realeaseDateTime.format(formatter)
                + "\n" + patchNotes;
    }
}
