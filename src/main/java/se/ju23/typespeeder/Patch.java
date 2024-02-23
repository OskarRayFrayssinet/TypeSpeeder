package se.ju23.typespeeder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Patch implements Message{
    private String patchVersion;
    private LocalDateTime releaseDateTime;
    private String patchNotes;
    private String headLine;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Patch(String patchVersion, LocalDateTime releaseDateTime, String patchNotes, String headLine) {
        this.patchVersion = patchVersion;
        this.releaseDateTime = releaseDateTime;
        this.patchNotes = patchNotes;
        this.headLine = headLine;
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

    public LocalDateTime getReleaseDateTime() {
        return releaseDateTime;
    }

    public void setReleaseDateTime(LocalDateTime releaseDateTime) {
        this.releaseDateTime = releaseDateTime;
    }

    public String getPatchNotes() {
        return patchNotes;
    }

    public void setPatchNotes(String patchNotes) {
        this.patchNotes = patchNotes;
    }
    @Override
    public String toString() {
        return headLine + "\tVersion: " + patchVersion + "\n" + "Publicerad " + releaseDateTime.format(formatter)
                + "\n" + patchNotes;
    }
}
