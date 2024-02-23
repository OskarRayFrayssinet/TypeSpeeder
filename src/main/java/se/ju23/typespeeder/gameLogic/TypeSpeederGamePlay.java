package se.ju23.typespeeder.gameLogic;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.InfoForUsers.NewsLetter;
import se.ju23.typespeeder.classesFromDB.*;
import se.ju23.typespeeder.colors.ConsoleColor;
import se.ju23.typespeeder.userInterfaces.MenuService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.IntStream;

@Component
public class TypeSpeederGamePlay implements Playable {
    MenuService menuService;

    public int gameDifficulty = 0;

    public static LocalTime startGame = LocalTime.now();
    public static LocalTime endGame = LocalTime.now();
    int xpLimitToLevelUp;
    int totalCalculatedPoints = 0;
    boolean levelLimit;
    int numOfWords = 0;
    int correctAnswers = 0;
    int correctAnswersInRow = 0;
    String currentPointsForPrinting = "";
    String currentUserGuess = "";
    boolean levelUp = false;
    public int currentUserId = 0;
    public int currentTaskId = 0;
    double timeResult = 0;
    public String[] currentUsername = {"", "", ""};
    public String[] currentAlias = {"", ""};
    public int currentXp = 0;
    public int currentLevel = 0;
    public String[] currentPassword = {"", "", ""};
    public static List<String> currentSolution = new ArrayList<>();




    public TypeSpeederGamePlay() {

    }

    @Autowired
    UsersRepo usersRepo;
    @Autowired
    AttemptRepo attemptRepo;
    @Autowired
    PointParamRepo pointParamRepo;
    @Autowired
    TasksRepo tasksRepo;

    public Status checkUser(String email, String password) {



        Optional<Users> users = usersRepo.findByEmailAndPassword(email, password);
        if (users.isPresent()) {
            Users found = users.get();
            currentUserId = found.getUserId();
            currentUsername[0] = found.getEmail();
            currentAlias[0] = found.getAlias();
            currentXp = found.getXp();
            currentLevel = found.getLevel();
            currentPassword[0] = found.getPassword();
            getXpLimit();
            return Status.VERIFIED;
        } else {
            currentUsername[0] = "1";

            return Status.NO_USER_FOUND;
        }
    }
    @Override
    public void calculateTotalPointsForGame(String userAnswer){

        correctAnswers = 0;
        correctAnswersInRow = 0;
        currentUserGuess = userAnswer;

        if (gameDifficulty == 1){

            if (isLetterGame(currentSolution)){
                userAnswer = userAnswer.toLowerCase().replaceAll("\\s+", "");
                for (int i = 0; i < userAnswer.length(); i++) {
                    char currentChar = userAnswer.charAt(i);
                    String currentCharAsString = String.valueOf(currentChar);
                    for (int j = 0; j < currentSolution.size(); j++) {
                        if (currentSolution.get(j).equalsIgnoreCase(currentCharAsString)) {
                            correctAnswers++;
                        }
                    }
                }
            } else if (isSentenceGame(currentSolution)) {
                List<String> userAnswerList = new ArrayList<>(Arrays.asList(userAnswer.split("\\s+")));
                for (int i = 0; i < userAnswerList.size(); i++) {
                    for (int j = 0; j < currentSolution.size(); j++) {
                        if (currentSolution.get(j).equalsIgnoreCase(userAnswerList.get(i))) {
                            correctAnswers++;
                        }
                    }
                }
            }

            System.out.println(currentSolution);
            System.out.println(userAnswer);
            System.out.println(correctAnswers);


        } else {
            List<String> userAnswerList = new ArrayList<>(Arrays.asList(userAnswer.split("\\s+")));
            for (int i = 0; i < userAnswerList.size(); i++) {
                for (int j = 0; j < currentSolution.size(); j++) {
                    if (userAnswerList.get(i).equals(currentSolution.get(j))){
                        correctAnswers++;
                    }
                }
            }
            int find = 0;
            for (int i = 0; i < userAnswerList.size(); i++) {
                if (find == numOfWords) break;
                if (userAnswerList.get(i).equals(currentSolution.get(find))){
                    correctAnswersInRow++;
                }
                find++;
            }
        }

        totalPointsAfterCountingWords();
    }
    public boolean isSentenceGame(List<String> words) {
        int countWordsWithMoreThanOneLetter = 0;
        for (String word : words) {
            if (word.length() > 1) {
                countWordsWithMoreThanOneLetter++;
            }
        }
        return countWordsWithMoreThanOneLetter > 2;
    }

    public boolean isLetterGame(List<String> letters) {
        for (String letter : letters) {
            if (letter.length() != 1) {
                return false;
            }
        }
        return true;
    }

    public static Timestamp getCurrentTime(){
        return (new Timestamp(System.currentTimeMillis()));
    }

    @Override
    public String returnChallengeResult(){
        String toReturn;
        if (gameDifficulty == 1){
            toReturn = ConsoleColor.BLUE + "--------\n" +
                    "| Correct Answers: " + correctAnswers + " |" +
                    "| Your Time: " + getTimeResult() + " Seconds" + " |" + "| Points: " +
                    currentPointsForPrinting + " |" +
                    "\n--------\n" + ConsoleColor.RESET;
        } else {
            toReturn = ConsoleColor.BLUE + "--------\n" +
                    "| Correct Answers: " + correctAnswers + " |" +
                    "| Correct Answers in row: " + correctAnswersInRow + " |" +
                    "| Your Time: " + getTimeResult() + " Seconds" + " |" + "| Points: " +
                    currentPointsForPrinting + " |" +
                    "\n--------\n" + ConsoleColor.RESET;
        }
        if (levelUp){
            return toReturn + ConsoleColor.BRIGHT_YELLOW + "NEW LEVEL!: " + currentLevel + ConsoleColor.RESET + "\n";
        } else if (levelLimit){
            return toReturn + ConsoleColor.BRIGHT_YELLOW + "YOU HAVE REACHED THE HIGHEST LEVEL CONTINUE TO IMPROVE YOUR SCORE!" + ConsoleColor.RESET;
        }else {
            return toReturn;
        }


    }
    @Override
    public String scoreBoardBasedOnLevel(){

        List<Users> users = usersRepo.findAll();

        List<Users> topListOfUsers = new ArrayList<>(users);
        StringBuilder result = new StringBuilder(ConsoleColor.CYAN + "    \u001B[1mSCOREBOARD BASED ON LEVEL \n    player                        Level     XP\n" + ConsoleColor.RESET);
        int pos = 1;
        topListOfUsers.sort((p1,p2) -> Integer.compare(p2.getLevel(), p1.getLevel()));
        for (Users u : topListOfUsers){
            result.append(String.format(ConsoleColor.CYAN + "\u001B[1m%5d %-5s%-17s%9.0f%9.0f%n" + ConsoleColor.RESET, pos, u.getAlias(), " (" +  u.getEmail() + ")", (double)u.getLevel(),(double)u.getXp()));
            if (pos++ ==10) break;
        }

        return result.toString();
    }
    @Override
    public String scoreBoardBasedOnThree(){

        List<Users> users = usersRepo.findAll();
        List<UserScores> userScores = new ArrayList<>();
        for (Users user : users){
            List<Attempt> attempts = attemptRepo.findByUserId(user.getUserId());
            if (attempts.size() > 5){
                double correctPercentage = calculateCorrectPercentage(user);
                double correctInOrderPercentage = calculateCorrectInOrderPercentage(user);
                double speedAverage = calculateSpeedAverage(user);
                double userScore = calculateUserScore(correctPercentage, correctInOrderPercentage, speedAverage);
                userScores.add(new UserScores(user.getEmail(),user.getAlias(),user.getLevel(),user.getXp(),userScore));
            }
        }
        userScores.sort(Comparator.comparingDouble(UserScores::getScore).reversed());
        StringBuilder result = new StringBuilder(ConsoleColor.BRIGHT_MAGENTA + "    \u001B[1mSCOREBOARD BASED ON 5 LATEST ACHIEVEMENTS\n SCORE BASED ON:" +
                " SPEED, ACCURACY, ACCURACY IN ORDER " +
                "\n    player                            Level     XP     Score\n" + ConsoleColor.RESET);
        int pos = 1;
        for (UserScores userScore : userScores) {
            result.append(String.format(ConsoleColor.MAGENTA + "\u001B[1m%5d %-9s%-17s%9.0f%9.0f%9.0f%n" + ConsoleColor.RESET, pos, userScore.getAlias(), "(" + userScore.getUsername() + ")",
                    (double)userScore.getLevel(),
                    (double)userScore.getXp(), userScore.getScore()));
            if (pos++ == 10) break;
        }
        return result.toString();
    }

    private double calculateCorrectPercentage(Users user){
        int correct = 0;
        int questions = 0;
        List<Attempt> attemptsToCount = attemptRepo.findTop5ByUserIdOrderByAttemptIdDesc(user.getUserId());
        for (int i = 0; i < attemptsToCount.size(); i++) {
            int attid = attemptsToCount.get(i).getAttemptId();
            List<PointParam> pointParams = pointParamRepo.findPointParamByAttemptId(attid);
            for (int j = 0; j < pointParams.size(); j++) {
                correct += pointParams.get(j).getCorrect();
                questions += pointParams.get(j).getQuestions();
            }
        }
        if (attemptsToCount.size() < 5){
            return 0;
        } else {
            return ((double) correct /(attemptsToCount.size()*questions)*100);
        }


    }

    private double calculateCorrectInOrderPercentage(Users user) {
        int correctInOrder = 0;
        int questions = 0;
        List<Attempt> attemptsToCount = attemptRepo.findTop5ByUserIdOrderByAttemptIdDesc(user.getUserId());
        for (int i = 0; i < attemptsToCount.size(); i++) {
            int attid = attemptsToCount.get(i).getAttemptId();
            List<PointParam> pointParams = pointParamRepo.findPointParamByAttemptId(attid);
            for (int j = 0; j < pointParams.size(); j++) {
                correctInOrder += pointParams.get(j).getCorrectInOrder();
                questions += pointParams.get(j).getQuestions();
            }
        }
        return ((double) correctInOrder/(attemptsToCount.size()*questions)*100);
    }
    private double calculateSpeedAverage(Users user){
        double speed = 0;

        List<Attempt> attemptsToCount = attemptRepo.findTop5ByUserIdOrderByAttemptIdDesc(user.getUserId());
        for (int i = 0; i < attemptsToCount.size(); i++) {
            int attid = attemptsToCount.get(i).getAttemptId();
            List<PointParam> pointParams = pointParamRepo.findPointParamByAttemptId(attid);
            for (int j = 0; j < pointParams.size(); j++) {
                speed += pointParams.get(j).getSpeedInSec();
            }
        }
        return speed/attemptsToCount.size();
    }
    private double calculateUserScore(double correctPercentage, double correctInOrderPercentage, double speedAverage) {

        double weightCorrectPercentage = 0.9;
        double weightCorrectInOrderPercentage = 0.4;
        double weightSpeedAverage = 0.8;

        return (correctPercentage * weightCorrectPercentage) +
                (correctInOrderPercentage * weightCorrectInOrderPercentage) +
                (speedAverage * weightSpeedAverage);
    }

    public int getXpLimit() {
        int xpLimit = 20;
        Optional<Users> found = usersRepo.findByUserId(currentUserId);
        if (found.isPresent()){
            int level = found.get().getLevel();
            if (level > 0 ) {
                for (int i = 0; i < level; i++) {
                    xpLimit += 5;
                }
                xpLimitToLevelUp = xpLimit;
            }
        }
        return xpLimit;

    }

    public void totalPointsAfterCountingWords(){
        double points;
        double userTime = getTimeResult();
        double maxTime = 120;
        if (userTime>maxTime){
            points = 0;

        } else if (correctAnswers == 0 && correctAnswersInRow == 0){
            points = 0;
        } else {
            if (gameDifficulty == 1){
                points = ((double) correctAnswers /4 + (10 * Math.pow((0.5 - (userTime / maxTime)), 1)));
            } else {
                points = (correctAnswers +  correctAnswersInRow + (10 * Math.pow((1 - (userTime / maxTime)), 1)));
            }

        }
        totalCalculatedPoints = (int) points;
        setXpToDB((int) points);
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance(Locale.US);
        DecimalFormat df = new DecimalFormat("#.##", symbols);
        double a = Double.parseDouble(df.format(points));
        currentPointsForPrinting = String.valueOf(a);

    }
    public void setXpToDB(int totPoints){
        levelUp = false;
        int xpLimit;
        int latestPoints = 0;
        Optional<Users> finduser = usersRepo.findById(currentUserId);
        if (finduser.isPresent()) {
            List<Attempt> attempts = attemptRepo.findTop1ByUserIdOrderByAttemptIdDesc(currentUserId);
            for (int i = 0; i < attempts.size(); i++) {
                latestPoints = (int) attempts.get(i).getTotalPoints();
            }
            Users found = finduser.get();
            int xp = finduser.get().getXp();
            int xpToCountoff = xp;
            int level = finduser.get().getLevel();
            xpLimit = getXpLimit();
            if (level >= 25){
                levelLimit = true;
                levelUp = false;
            } else {
                if (latestPoints > totPoints || totPoints == 0) {
                    xp = (xp - 5);
                    if (xp<0) xp = 0;
                } else {
                    xp += totPoints;
                    if (xp >= xpLimit){
                        int sum = xpLimit - xpToCountoff;
                        level++;
                        levelUp = true;
                        xp = totPoints - sum;



                    }
                }
            }

            currentLevel = level;
            currentXp = xp;
            found.setXp(xp);
            found.setLevel(level);
            usersRepo.save(found);
            saveAttemptToDB();
        }
    }
    public void saveAttemptToDB(){
        Tasks t = null;
        Users u = null;
        Optional<Tasks> findTask = tasksRepo.findById(currentTaskId);
        if (findTask.isPresent()){
            t = findTask.get();
        }
        Optional<Users> findUser = usersRepo.findById(currentUserId);
        if (findUser.isPresent()){
            u = findUser.get();
        }
        Attempt newAttempt = new Attempt(totalCalculatedPoints,currentSolutionToString(),currentUserGuess,u,t, getCurrentTime());
        attemptRepo.save(newAttempt);
        PointParam pointParam = new PointParam(getTimeResult(),correctAnswers,correctAnswersInRow,newAttempt,numOfWords);
        pointParamRepo.save(pointParam);

    }
    public String currentSolutionToString(){
        StringBuilder sb = new StringBuilder();
        for (String s : currentSolution){
            sb.append(s).append(" ");
        }
        return sb.toString();
    }

    @Override
    public void setCurrentSolution(List<String> currentSolution1) {
        currentSolution = currentSolution1;
    }
    @Override
    public String returnUserInfo(){
        return ConsoleColor.BLUE + "| Alias " + currentAlias[0] + " | Level: " + currentLevel + " | XP: " + currentXp + "/" + getXpLimit() + " Next level: " + (getCurrentLevel()+1) + ConsoleColor.RESET;
    }
    @Override
    public String beforeGameStartsText(){
        if (gameDifficulty == 1){
            return ConsoleColor.BOLD + "This is an easy test, you will gain points for every correct highlighted you type. Maximum time 2 min. " +
                    "It's \u001B[92mTYPE INSENSITIVE\u001B[0m\nTime starts when you press ENTER, READY SET...(enter)" + ConsoleColor.RESET;
        } else {
            return  ConsoleColor.BOLD + "You will get a text with highlighted words write them as fast as you can, " +
                    "this is a \u001B[92mTYPE SENSITIVE\u001B[0m challenge. You have a limit of 2 minutes to complete the challenge " +
                    "\nTime starts when you press ENTER, READY...(enter)" + ConsoleColor.RESET;
        }
    }
    @Override
    public Status standbyInMainMenu(int input) {
        currentPassword[2] = "";
        currentUsername[2] = "";
        gameDifficulty = 0;

        Status status = null;
        switch (input) {
            case 0 -> status = Status.EXIT;
            case 1 -> status = Status.SETTING_LANGUAGE;
            case 2 -> status = Status.ACTIVE_IN_GAME_HARD;
            case 3 -> status = Status.ACTIVE_IN_GAME_EASY;
            case 4 -> status = Status.IN_STATS;
            case 5 -> status = Status.IN_GAME_SETTINGS;
            case 6 -> status = Status.NEWSLETTER;
        }
        return status;
    }
@Override
    public void setGameDifficulty(int gameDifficulty1) {
        this.gameDifficulty = gameDifficulty1;
    }

    @Override
    public Status standbyInSettingsMenu(int input) {
        Status status = null;
        switch (input) {
            case 1 -> status = Status.CHANGING_ALIAS;
            case 2 -> status = Status.CHANGING_PASSWORD;
            case 3 -> status = Status.CHANGING_USERNAME;
        }
        return status;
    }
    @Override
    //alias är möjligt att ha samma som annan spelare, men username är unikt
    public void setNewAlias(String input) {

        List<Users> aliasFromDB = usersRepo.findByAlias(getCurrentAlias(0));
        for (Users u : aliasFromDB) {
            u.setAlias(input);
            usersRepo.save(u);
            currentAlias[0] = input;
            currentAlias[1] = "1";
        }
    }
    @Override
    public void setNewUsername(String newUsername) {
        Optional<Users> usernameFromDB = usersRepo.findByUserId(getCurrentUserId());
        if (usernameFromDB.isPresent()) {
            Users found = usernameFromDB.get();
            found.setEmail(newUsername);
            usersRepo.save(found);
            currentUsername[1] = "1";
        }
    }
    @Override
    public void setNewPassword(String newPassword) {
        Optional<Users> passwordFromDB = usersRepo.findById(getCurrentUserId());
        if (passwordFromDB.isPresent()) {
            Users found = passwordFromDB.get();
            found.setPassword(newPassword);
            usersRepo.save(found);
            currentPassword[1] = "1";
        }
    }
    @Override
    public boolean checkCurrentUsername(String input) {
        if (Objects.equals(input, getCurrentUsername(0))) {
            currentUsername[1] = input;
            return true;
        } else {
            currentUsername[2] = "2";
            return false;
        }

    }
    @Override
    public boolean checkIfUserNameIsBusy(String input) {
        Optional<Users> checkIfBusy = usersRepo.findByEmail(input);
        if (checkIfBusy.isEmpty()) {
            return true;
        } else {
            currentUsername[1] = "";
            currentUsername[2] = "2";
            return false;
        }
    }
    @Override
    public boolean checkCurrentPassword(String input) {
        if (Objects.equals(input, getPassword(0))) {
            currentPassword[1] = input;
            return true;
        } else {
            currentPassword[2] = "2";
            return false;
        }
    }
    public double getTimeResult() {
        return timeResult;
    }
    @Override
    public void setTimeResult(double timeResult) {
        this.timeResult = timeResult;
    }
    @Override
    public void setNumOfWords(int words){
        numOfWords = words;
    }
    @Override
    public void setCurrentTaskId(int currentTaskId1) {
        currentTaskId = currentTaskId1;
    }
    @Override
    public String getCurrentUsername(int place) {
        return currentUsername[place];
    }
    @Override
    public void setCurrentUsername(String input) {
        currentUsername[0] = input;
    }
    @Override
    public int getCurrentUserId() {
        return currentUserId;
    }
    @Override
    public String getCurrentAlias(int place) {
        return currentAlias[place];
    }
    @Override
    public String getPassword(int place) {
        return currentPassword[place];
    }
    @Override
    public int getCurrentLevel() {
        return currentLevel;
    }



}
