package se.ju23.typespeeder;

import se.ju23.typespeeder.logic.*;

public class Main {
    public static void main(String[] args) {
        TextGenerator textGenerator = new RandomTextGenerator();
        UserInterface userInterface = new ConsoleUserInterface();

        Game game = new Game(textGenerator, userInterface);
        game.start();
    }
}
