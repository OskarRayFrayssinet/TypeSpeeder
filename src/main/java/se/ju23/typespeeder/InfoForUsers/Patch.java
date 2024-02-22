package se.ju23.typespeeder.InfoForUsers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


public class Patch {
    public String patchVersion;
    public LocalDateTime releaseDateTime;

    public Patch() {
        this.patchVersion = getPatchVersion();
        this.releaseDateTime = getReleaseDateTime();
    }
    public static String getFormattedPublishDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(formatter);
    }
    public String getPatchVersion(){
        String text = null;
        Path currentWorkingdir = Paths.get("").toAbsolutePath();
        String path = currentWorkingdir + File.separator + "src"  + File.separator + "Patch.txt";
        text = readTextFromFile(path);

        return text;
    }

    public static LocalDateTime getReleaseDateTime() {
        LocalDateTime a = null;
        Path currentWorkingdir = Paths.get("").toAbsolutePath();
        String patho = currentWorkingdir + File.separator + "src"  + File.separator + "Patch.txt";
        try {
            Path path = Paths.get(patho);
            BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class);
            a = LocalDateTime.ofInstant(attrs.lastModifiedTime().toInstant(), ZoneId.systemDefault());
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file attributes: " + e.getMessage());

        }
        return a;
    }
    public String readTextFromFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try {
            Path path = Paths.get(filePath);
            BufferedReader reader = new BufferedReader(new FileReader(path.toFile()));
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }

        return content.toString();
    }

    //TODO SÄTT FÄRG
    @Override
    public String toString() {
        return "Patch" +
                "content='" + patchVersion + '\'' +
                ", publishDateTime=" + getFormattedPublishDateTime(releaseDateTime) +
                '}';
    }
}
