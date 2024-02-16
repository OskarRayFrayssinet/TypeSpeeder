package se.ju23.typespeeder.logic;

import java.util.Random;

public class RandomTextGenerator implements TextGenerator {
    private static final String[] COLORS = {"\u001B[31m", "\u001B[32m", "\u001B[33m", "\u001B[34m", "\u001B[35m", "\u001B[36m"};
    private static final String RESET_COLOR = "\u001B[0m";
    private Random random = new Random();

    @Override
    public String generateText() {
        String text = "femur, tibia, fibula, humerus, clavicula, sternum, costaeII";
        String[] words = text.split("\\s+");
        StringBuilder coloredText = new StringBuilder();

        for(String word : words) {
            String color = COLORS[random.nextInt((COLORS.length))];
            coloredText.append(color).append(word).append(" ");
        }

        coloredText.append(RESET_COLOR);

        return coloredText.toString();
    }
}
