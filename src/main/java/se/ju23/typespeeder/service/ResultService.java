package se.ju23.typespeeder.service;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.IO.IO;
import se.ju23.typespeeder.model.Player;
import se.ju23.typespeeder.model.Result;
import se.ju23.typespeeder.repository.PlayerRepo;
import se.ju23.typespeeder.repository.ResultRepo;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@Service
public class ResultService {

    public void inputFromPlayerInGame(List<String> calculatedWords, IO io, ResultRepo resultRepo, Player activePlayer, PlayerRepo playerRepo) {
        System.out.println("\nGo Go Go! Type / Skriv!");
        int correctAnswers = 0;
        long startTime = System.currentTimeMillis();
        Result resultFromGameSession = new Result();
        Scanner input = io.returnScanner();
        String gameInput = input.nextLine();
        String[] words = gameInput.split("\\s+");
        for (String word : words) {
            if (calculatedWords.contains(word)) {
                correctAnswers++;
            }
        }
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        long elapsedTimeInSeconds = elapsedTime / 1000;
        int calculatedPoints0 = calculateToungeTwister(calculatedWords, gameInput, elapsedTimeInSeconds);
        resultFromGameSession.setCompletionSpeed(elapsedTimeInSeconds);
        resultFromGameSession.setCorrectAnswers(correctAnswers);
        int calculatedPoints1 = calcuatePlayerPointsByAnswer(correctAnswers);
        int calculatedPoints2 = calcuatePlayerPointsByTime(elapsedTimeInSeconds);
        int correctAnswersInRow = calculatePointsByCorrectAnswersInRow(words, calculatedWords);
        int calculatedPoints3 = correctAnswersInRow;
        resultFromGameSession.setCorrectAnswersInRow(correctAnswersInRow);
        activePlayer.setPoints(calculatedPoints0 + calculatedPoints1 + calculatedPoints2 + calculatedPoints3);
        playerRepo.save(activePlayer);
        resultFromGameSession.setPlayer(activePlayer);
        resultRepo.save(resultFromGameSession);
        System.out.println("Correct answers: " + correctAnswers);
        System.out.println("Correct answers in a row: " + correctAnswersInRow);
        System.out.println("Time elapsed: " + elapsedTimeInSeconds + " seconds");
        System.out.println("Your points: " + (calculatedPoints0 +  calculatedPoints1 + calculatedPoints2 + calculatedPoints3));
    }


    public int calcuatePlayerPointsByAnswer(int correctAnswers) {
        int points = 0;
        if (correctAnswers > 0 && correctAnswers <= 5) {
            points = +10;
            return points;
        } else if (correctAnswers >= 6 && correctAnswers <= 10) {
            points = +20;
            return points;
        } else if (correctAnswers >= 11 && correctAnswers <= 15) {
            points = +30;
            return points;
        } else if (correctAnswers >= 16 && correctAnswers <= 19) {
            points = +40;
            return points;
        } else if (correctAnswers == 20) {
            points = +100;
            return points;
        }
        return points;
    }

    public int calcuatePlayerPointsByTime(long elapsedTimeInSeconds) {
        int points = 0;
        if (elapsedTimeInSeconds > 0 && elapsedTimeInSeconds <= 3) {
            points = 20;
            return points;
        } else if (elapsedTimeInSeconds >= 4 && elapsedTimeInSeconds <= 8) {
            points = +10;
            return points;
        } else if (elapsedTimeInSeconds >= 10 && elapsedTimeInSeconds <= 14) {
            points = 5;
            return points;
        } else if (elapsedTimeInSeconds >= 15) {
            return points;
        }
        return points;
    }

    public int calculatePointsByCorrectAnswersInRow(String[] words, List<String> getCalculatedWords) {
        int correctAnswersInRow = 0;

        for (int i = 0; i < words.length && i < getCalculatedWords.size(); i++) {
            String wordInList = getCalculatedWords.get(i);
            String wordInGameInput = words[i];

            if (wordInList.equals(wordInGameInput)) {
                correctAnswersInRow++;
            } else {
                break;
            }
        }
        return correctAnswersInRow;
    }

    public int calculateToungeTwister (List<String> calculatedWords, String gameInput,  long elapsedTimeInSeconds) {
        ArrayList<String> tempList = new ArrayList<>();
        tempList.add(gameInput);
        int points = 0;
        if (calculatedWords.equals(tempList) && (elapsedTimeInSeconds < 20)) {
            points = 100;
        }
        return points;
    }

}
