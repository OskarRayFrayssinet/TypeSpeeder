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
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Component
public class TypeSpeederGamePlay implements Playable {
    MenuService menuService;

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
    IChallenge challenge;




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
        totalPoints();
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
        Attempt newAttempt = new Attempt(totalCalculatedPoints,currentSolutionToString(),currentUserGuess,u,t);
        attemptRepo.save(newAttempt);
        PointParam pointParam = new PointParam(getTimeResult(),correctAnswers,correctAnswersInRow,newAttempt,numOfWords);
        pointParamRepo.save(pointParam);

    }


    @Override
    public String printChallengeResult(){
        getXpLimit();
        String toReturn = ConsoleColor.BLUE + "--------\n" +
                "| Correct Answers: " + correctAnswers + " |" +
                "| Correct Answers in row: " + correctAnswersInRow + " |" +
                "| Your Time: " + getTimeResult() + " Seconds" + " |" + "| Points: " +
                currentPointsForPrinting + " |" +
                "\n--------\n" + ConsoleColor.RESET;
        if (levelUp){
            return toReturn + ConsoleColor.BRIGHT_YELLOW + "NEW LEVEL!: " + currentLevel + ConsoleColor.RESET +
                    " | XP: " + currentXp + "/" + xpLimitToLevelUp + " Until next level |" + "\n";
        } else if (levelLimit){
            return toReturn + ConsoleColor.BRIGHT_YELLOW + "YOU HAVE REACHED THE HIGHEST LEVEL CONTINUE TO IMPROVE YOUR SCORE!" + ConsoleColor.RESET;
        }else {
            return toReturn;
        }
    }
    @Override
    public String printScoreBoardBasedOnThree(){

        List<Users> users = usersRepo.findAll();
        List<UserScores> userScores = new ArrayList<>();
        for (Users user : users){
            List<Attempt> attempts = attemptRepo.findByUserId(user.getUserId());
            if (attempts.size() > 5){
                double correctPercentage = calculateCorrectPercentage(user);
                double correctInOrderPercentage = calculateCorrectInOrderPercentage(user);
                double speedAverage = calculateSpeedAverage(user);
                double userScore = calculateUserScore(correctPercentage, correctInOrderPercentage, speedAverage);
                userScores.add(new UserScores(user.getEmail(),user.getLevel(),user.getXp(),userScore));
            }
        }
        userScores.sort(Comparator.comparingDouble(UserScores::getScore).reversed());
        StringBuilder result = new StringBuilder(ConsoleColor.BRIGHT_MAGENTA + "    \u001B[1mSCOREBOARD BASED ON 5 LATEST ACHIEVEMENTS\n SCORE BASED ON: SPEED, ACCURACY, ACCURACY IN ORDER \n    player          Level   XP   Score\n" + ConsoleColor.RESET);
        int pos = 1;
        for (UserScores userScore : userScores) {
            result.append(String.format(ConsoleColor.MAGENTA + "\u001B[1m%5d %-9s%7.0f%7.0f%7.2f%n" + ConsoleColor.RESET, pos, userScore.getUsername(),
                    (double)userScore.getLevel(),
                    (double)userScore.getXp(), userScore.getScore()));
            if (pos++ == 10) break;
        }
        return result.toString();
    }
    @Override
    public NewsLetter printNewsletter(){
        String text = null;
        Path currentWorkingdir = Paths.get("").toAbsolutePath();
        System.out.println(currentWorkingdir);
        File file;
        String path = currentWorkingdir + File.separator + "src"  + File.separator + "Newsletter.txt";
        text = readTextFromFile(path);

        NewsLetter newsLetter = new NewsLetter(text, LocalDateTime.of(2024,1,1,12,12,12));
        System.out.println(newsLetter);
        return newsLetter;
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
    private double calculateCorrectPercentage(Users user){
        double calPro = 0;
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
        // Viktningar för varje prestation
        double weightCorrectPercentage = 0.9;  // Viktning för andel korrekta svar
        double weightCorrectInOrderPercentage = 0.4;  // Viktning för andel korrekta svar i ordning
        double weightSpeedAverage = 0.8;  // Viktning för snitthastighet

        // Beräkna den totala poängen genom att multiplicera varje prestation med dess viktning och sedan summera dem

        return (correctPercentage * weightCorrectPercentage) +
                (correctInOrderPercentage * weightCorrectInOrderPercentage) +
                (speedAverage * weightSpeedAverage);
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


    @Override
    public String printScoreBoardBasedOnLevel(){

        List<Users> users = usersRepo.findAll();

        List<Users> topListOfUsers = new ArrayList<>(users);
        StringBuilder result = new StringBuilder(ConsoleColor.CYAN + "    \u001B[1mSCOREBOARD BASED ON LEVEL \n    player          Level   XP\n" + ConsoleColor.RESET);
        int pos = 1;
        topListOfUsers.sort((p1,p2) -> Integer.compare(p2.getLevel(), p1.getLevel()));
        for (Users u : topListOfUsers){
            result.append(String.format(ConsoleColor.CYAN + "\u001B[1m%5d %-9s%7.0f%7.0f%n" + ConsoleColor.RESET, pos, (u.getEmail()), (double)u.getLevel(),(double)u.getXp()));
            if (pos++ ==10) break;
        }

        return result.toString();
    }
    @Override
    public double totalPoints(){
        double points;
        double userTime = getTimeResult();
        double maxTime = 120;
        if (userTime>maxTime){
            points = 0;

        } else if (correctAnswers == 0 && correctAnswersInRow == 0){
            points = 0;
        } else {
            points = (correctAnswers +  correctAnswersInRow+ (10 * Math.pow((1 - (userTime / maxTime)), 1)));
        }
        totalCalculatedPoints = (int) points;
        setXpToDB((int) points);
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance(Locale.US);
        DecimalFormat df = new DecimalFormat("#.##", symbols);
        double a = Double.parseDouble(df.format(points));
        currentPointsForPrinting = String.valueOf(a);

        return a;
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
    public String printUserInfo(){
        return ConsoleColor.BLUE + "| Alias " + currentAlias[0] + " | Level: " + currentLevel + " | XP: " + currentXp + ConsoleColor.RESET;
    }
    @Override
    public String beforeGameStartsText(){
        return ConsoleColor.BOLD + "You will get a text with green marked words write them as fast as you can, " +
                "this is a case sensitive challenge. " +
                "\nTime starts when you press ENTER, READY SET...(enter)" + ConsoleColor.RESET;
    }
    @Override
    public Status standbyInMainMenu(int input) {
        currentPassword[2] = "";
        currentUsername[2] = "";

        Status status = null;
        switch (input) {
            case 0 -> status = Status.EXIT;
            case 1 -> status = Status.SETTING_LANGUAGE;
            case 2 -> status = Status.ACTIVE_IN_GAME;
            case 3 -> status = Status.IN_STATS;
            case 4 -> status = Status.IN_GAME_SETTINGS;
            case 5 -> status = Status.NEWSLETTER;
        }
        return status;
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
    //alias möjligt att ha samma som annan spelare men username är unikt
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

    public static LocalTime getStartGame() {
        return startGame;
    }
    @Override
    public void setStartGame(LocalTime startGame1) {
       startGame = startGame1;
    }

    public static LocalTime getEndGame() {
        return endGame;
    }
    @Override
    public void setEndGame(LocalTime endGame1) {
        endGame = endGame1;
    }

@Override
    public void setCurrentTaskId(int currentTaskId1) {
        currentTaskId = currentTaskId1;
    }

    public void setCurrentUserGuess(String currentUserGuess) {
        this.currentUserGuess = currentUserGuess;
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
    public String noUserFoundText() {
        return null;
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
    public int getCurrentXp() {
        return currentXp;
    }
    @Override
    public int getCurrentLevel() {
        return currentLevel;
    }
}
