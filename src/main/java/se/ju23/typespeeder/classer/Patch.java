package se.ju23.typespeeder.classer;
import java.time.LocalDateTime;

public class Patch {
    private String patchVersion = "1.0.ABRAKADARBA";
    public LocalDateTime releaseDateTime;

    public Patch() {
        this.releaseDateTime = LocalDateTime.of(2024, 02, 18, 13, 34, 25);
    }

    public Patch(String patchVersion, LocalDateTime releaseDateTime) {
        this.patchVersion = patchVersion;
        this.releaseDateTime = releaseDateTime;
    }

    public Patch(LocalDateTime releaseDateTime) {
        this.releaseDateTime = releaseDateTime;
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
}

