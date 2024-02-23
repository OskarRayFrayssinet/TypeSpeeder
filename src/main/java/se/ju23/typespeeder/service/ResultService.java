/*
 * Class: ResultService
 * Description: A support class for the class Result.
 * Author: Kerem Bjävenäs Tazedal
 * Email: kerem.tazedal@iths.se
 * Date: 2024-02-18
 */
package se.ju23.typespeeder.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.IO.IO;
import se.ju23.typespeeder.enums.Status;
import se.ju23.typespeeder.logic.Challenge;
import se.ju23.typespeeder.model.Player;
import se.ju23.typespeeder.model.Result;
import se.ju23.typespeeder.repository.PlayerRepo;
import se.ju23.typespeeder.repository.ResultRepo;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@Service
public class ResultService {

    @Autowired
    StatusService statusService;
    @Autowired
    Challenge challenge;

    public void inputFromPlayerInGame(List<String> calculatedWords, IO io, ResultRepo resultRepo, Player activePlayer, PlayerRepo playerRepo) {
        if (statusService.getStatus().equals(Status.SVENSKA)){
            System.out.println(io.getInGameMessages().get(42));
        } else if (statusService.getStatus().equals(Status.ENGLISH)){
            System.out.println(io.getInGameMessages().get(43));
        }
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
        for (String word : words) {
            String coloredWord = "\u001B[31m" + word + "\u001B[0m";
            if (challenge.getRedWords().contains(coloredWord)) {
                correctAnswers++;
            }
        }

        System.out.println(correctAnswers);
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        long elapsedTimeInSeconds = elapsedTime / 1000;
        int calculatedPoints0 = calculateToungeTwister(calculatedWords, gameInput, elapsedTimeInSeconds);
        resultFromGameSession.setCompletionSpeed(elapsedTimeInSeconds);
        resultFromGameSession.setCorrectAnswers(correctAnswers);
        int calculatedPoints1 = calcuatePlayerPointsByAnswer(correctAnswers);
        int calculatedPoints2 = calcuatePlayerPointsByTime(correctAnswers, elapsedTimeInSeconds);
        int correctAnswersInRow = calculatePointsByCorrectAnswersInRow(words, calculatedWords);
        int calculatedPoints3 = correctAnswersInRow;
        resultFromGameSession.setCorrectAnswersInRow(correctAnswersInRow);
        activePlayer.setPoints(calculatedPoints0 + calculatedPoints1 + calculatedPoints2 + calculatedPoints3);
        activePlayer.setTotalPoints(calculatedPoints0 + calculatedPoints1 + calculatedPoints2 + calculatedPoints3);
        playerRepo.save(activePlayer);
        resultFromGameSession.setPlayer(activePlayer);
        resultRepo.save(resultFromGameSession);
        printResults(correctAnswers, correctAnswersInRow, elapsedTimeInSeconds, calculatedPoints0, calculatedPoints1, calculatedPoints2, calculatedPoints3, io);
    }


    public int calcuatePlayerPointsByAnswer(int correctAnswers) {
        int points = 0;
        if (correctAnswers == 0) {
            points = -50;
            return points;
        }
        else if (correctAnswers >= 1 && correctAnswers <= 5) {
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

    public int calcuatePlayerPointsByTime(int correctAnswers, long elapsedTimeInSeconds) {
        int points = 0;
        if (correctAnswers >= 1){
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
                points = -15;
                return points;
            }
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

    public void printResults(int correctAnswers, int correctAnswersInRow, long elapsedTimeInSeconds, int calculatedPoints0, int calculatedPoints1, int calculatedPoints2, int calculatedPoints3, IO io) {
        if (statusService.getStatus().equals(Status.SVENSKA)) {
            System.out.println(io.getInGameMessages().get(44) + correctAnswers);
            System.out.println((io.getInGameMessages().get(45) + correctAnswersInRow));
            System.out.println((io.getInGameMessages().get(46) + elapsedTimeInSeconds + (io.getInGameMessages().get(47))));
            System.out.println(io.getInGameMessages().get(48) + (calculatedPoints0 +  calculatedPoints1 + calculatedPoints2 + calculatedPoints3));
        } else if  (statusService.getStatus().equals(Status.ENGLISH)) {
            System.out.println(io.getInGameMessages().get(49) + correctAnswers);
            System.out.println((io.getInGameMessages().get(50) + correctAnswersInRow));
            System.out.println((io.getInGameMessages().get(51) + elapsedTimeInSeconds + (io.getInGameMessages().get(52))));
            System.out.println(io.getInGameMessages().get(53) + (calculatedPoints0 +  calculatedPoints1 + calculatedPoints2 + calculatedPoints3));
    }
        }

}
