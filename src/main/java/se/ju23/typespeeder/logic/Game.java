package se.ju23.typespeeder.logic;

public class Game {
    private TextGenerator textGenerator;
    private UserInterface userInterface;

    public Game(TextGenerator textGenerator, UserInterface userInterface) {
        this.textGenerator = textGenerator;
        this.userInterface = userInterface;
    }

    public void start() {
        String text = textGenerator.generateText();
        userInterface.displayText(text);
        String userInput = userInterface.getUserInput();
    }
}
