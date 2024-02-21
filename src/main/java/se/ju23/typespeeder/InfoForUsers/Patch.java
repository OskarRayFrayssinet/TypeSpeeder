package se.ju23.typespeeder.InfoForUsers;

import java.time.LocalDateTime;

public class Patch {
    private String patchVersion;
    private LocalDateTime releaseDateTime;

    public Patch(String patchVersion, LocalDateTime releaseDateTime) {
        this.patchVersion = patchVersion;
        this.releaseDateTime = releaseDateTime;
    }

    public String getPatchVersion() {
        return patchVersion;
    }

    public LocalDateTime getReleaseDateTime() {
        return releaseDateTime;
    }
}