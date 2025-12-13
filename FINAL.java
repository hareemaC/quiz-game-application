import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*;

public class FINAL {

    static Scanner sc = new Scanner(System.in);
    static String filePath = "users.txt";

    public static void main(String[] args) {
        int choice;
        // ---------------- LOGIN MENU ----------------
        while (true) {
                System.out.println("\n--- LOGIN MENU ---");
                System.out.println("1. Sign Up");
                System.out.println("2. Sign In");
                System.out.println("3. Quit");
                System.out.print("Choose an option: ");
                 choice = -1;

            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a NUMBER.");
                sc.nextLine();
                continue;
            }
            sc.nextLine();
            
            // SIGN UP
            if (choice == 1) {
                System.out.print("Enter new username: ");
                String newUser = sc.nextLine();

                try {
                    File file = new File(filePath);
                    if (!file.exists()) {
                        file.createNewFile();
                    }

                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String line;
                    boolean exists = false;

                    while ((line = br.readLine()) != null) {
                        String pass = br.readLine(); // password line
                        if (line.equals(newUser)) {
                            exists = true;
                            break;
                        }
                    }
                    br.close();

                    if (exists) {
                        System.out.println("Username already taken.");
                    } else {
                        System.out.print("Enter new password: ");
                        String newPass = sc.nextLine();

                        BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
                        bw.write(newUser + "\n");
                        bw.write(newPass + "\n");
                        bw.close();

                        System.out.println("Account created successfully!");
                        startGame();
                        System.out.println("Thank you for playing!");
                        break;  // exit after playing
                    }

                } catch (IOException e) {
                    System.out.println("Error in sign up.");
                }

            }

            // SIGN IN
            else if (choice == 2) {
                System.out.print("Enter username: ");
                String user = sc.nextLine();
                System.out.print("Enter password: ");
                String pass = sc.nextLine();

                try {
                    File file = new File(filePath);
                    if (!file.exists()) {
                        System.out.println("No users found. Please sign up first.");
                        continue;
                    }

                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String line;
                    boolean foundUser = false;

                    while ((line = br.readLine()) != null) {
                        String passwordLine = br.readLine(); // password
                        if (line.equals(user)) {
                            foundUser = true;
                            if (passwordLine.equals(pass)) {
                                System.out.println("Login Successful! Welcome, " + user);
                                startGame();
                                System.out.println("Thank you for playing!");
                                break;
                            } else {
                                System.out.println("Password is wrong.");
                                break;
                            }
                        }
                    }
                    br.close();

                    if (!foundUser) {
                        System.out.println("Username does not exist.");
                    }

                } catch (IOException e) {
                    System.out.println("Error in sign in.");
                }
            }

            // QUIT
            else if (choice == 3) {
                System.out.println("Logged out.");
                break;
            }

            else {
                System.out.println(  "Invalid choice! Please choose from 1-3.");
                
            }
        }

        sc.close();
    }

    // ---------------- HOW TO PLAY + TOPIC + LEVEL ---------------
    public static void startGame() {
        System.out.println("\nWELCOME TO QUIZ GAME!");

        int start;
        while (true) {
            try{
                System.out.print("\n1. Start quiz\n2. Log out\nEnter choice: ");
                start = sc.nextInt();
                sc.nextLine();

                if (start == 2|| start == 1) {
                    break; // valid input
                } else {
                    System.out.println("Please enter 1 or 2");
                }
            }catch(InputMismatchException e){
                System.out.println("Please enter a number");
                sc.nextLine();
            }
        }
        
        if(start == 1){
            String playAgain;
            
            System.out.println("\nRules:");
            System.out.println("1. You get to choose a topic: Science, English, Math, Computers, or General.");
            System.out.println("2. You get to choose a level: Easy, Medium, or Hard.");
            System.out.println("3. Time per level: Easy = 15 mins, Medium = 12 mins, Hard = 10 mins.");
            System.out.println("4. Answer questions before time ends!");
            System.out.println("5. Score will be calculated at the end.");
            System.out.println("6. You can play again.\n");
            do {
                String topic = chooseTopic();
                int time = chooseLevel();
                System.out.println("\nStarting quiz on: " + topic);
                System.out.println("You have " + time + " minutes.");

                quiz(time);

                // validate play-again input
                while (true) {
                    System.out.print("\nDo you want to play again? (Y/N): ");
                    playAgain = sc.nextLine().trim().toLowerCase();

                    if (playAgain.equals("y") || playAgain.equals("n")) {
                        break; // valid input
                    } else {
                        System.out.println("Invalid input. Please enter Y or N.");
                    }
                }

            } while (playAgain.equals("y"));
        }
        else{
            System.out.println("Logged out.");
        }


    }

    static String fileName = "";
    // ---------------- CHOOSE TOPIC ----------------
    public static String chooseTopic() {
        String topic = "";
        do {
            System.out.println("\nChoose a topic:");
            System.out.println("1. Science");
            System.out.println("2. Math");
            System.out.println("3. Computer");
            System.out.println("4. English");
            System.out.println("5. General");
            System.out.print("Enter option: ");

            int choice = -1;

            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Enter a NUMBER.");
                sc.nextLine();
                continue;
            }
            sc.nextLine();

            switch (choice) {
                case 1:
                    topic = "Science";
                    fileName = "scientific.txt";
                    break;
                case 2:
                    topic = "Math"; 
                    fileName = "math.txt";
                    break;
                case 3:
                    topic = "Computer";
                    fileName = "comp.txt";
                    break;
                case 4:
                    topic = "English";
                    fileName = "eng.txt";
                    break;
                case 5:
                    topic = "General"; 
                    fileName = "general.txt";
                    break;
                default: System.out.println("Invalid choice! Choose from 1-5.");
            }
        } while (topic.equals(""));

        System.out.println("You chose: " + topic + "\n");
        return topic;
    }

    // ---------------- CHOOSE LEVEL ----------------
    public static int chooseLevel() {
        int time = 0;
        do {
            System.out.println("\nChoose a level:");
            System.out.println("1. Easy (15 min)");
            System.out.println("2. Medium (12 min)");
            System.out.println("3. Hard (10 min)");
            System.out.print("Enter option: ");

             int choice = -1;

            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Enter a NUMBER.");
                sc.nextLine();
                continue;
            }
            sc.nextLine();

            switch (choice) {
                case 1:
                    time = 15; System.out.println("You chose: Easy level");
                    break;
                case 2: 
                    time = 12; System.out.println("You chose: Medium level");
                    break;
                case 3:
                    time = 10; System.out.println("You chose: Hard level"); 
                    break;
                default: System.out.println("Invalid level! Try again.");
            }
        } while (time == 0);

        return time;
    }

    // questions method will receive the specific file (chosen by the user's requirements) as an argument
    public static String[][] getQuestions(){
        String[][] questions = new String[40][7];
        try{
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            int index = 0;
            while(index < 40){
                questions[index][0] = br.readLine();
                questions[index][1] = br.readLine();
                questions[index][2] = br.readLine();
                questions[index][3] = br.readLine();
                questions[index][4] = br.readLine();
                questions[index][5] = br.readLine();
                questions[index][6] = br.readLine();
                index++;
            }
            br.close();
        }catch(IOException e){
            System.out.println("An error occured");
        }
        return questions;
    }

    // if level is easy only first 10 questions 
    //send the array to the result method
    //send time taken to the result method
    public static void quiz(int TOTAL_TIME){
        try{
            int questionsAccessed;
            String[][] arr = getQuestions();
            String[][] dataArr = new String[10][3];
            if(TOTAL_TIME == 15){
                questionsAccessed = 20;
            }
            else if(TOTAL_TIME == 12){
                questionsAccessed = 30;
            }
            else{
                questionsAccessed = 40;
            }
            TOTAL_TIME = TOTAL_TIME * 60;
            long startTime = System.currentTimeMillis();
            int totalQuestions = 10;
            int[] usedQuestions = new int[10];
            int usedCount = 0;
            int questionsAnswered = 0;

            //control loop using time and questions<10
            for (int i = 1; i <= totalQuestions; i++) {
            
                long elapsed = (System.currentTimeMillis() - startTime) / 1000;
                long remaining = TOTAL_TIME - elapsed;

                if (remaining <= 0) {
                    System.out.println("\n Time is up! Quiz over.");
                    break;
                }

                long minutes = remaining / 60;
                long seconds = remaining % 60;
                System.out.println("-------------------------------------");
                System.out.printf("Time left: %02d:%02d\n", minutes, seconds);
                

                //Get a random question from array
                int QNum;
                boolean alreadyUsed;

                do {
                    QNum = (int)(Math.random() * questionsAccessed) + 1;
                    alreadyUsed = false;

                    for (int j = 0; j < usedCount; j++) {
                        if (usedQuestions[j] == QNum) {
                            alreadyUsed = true;
                            break;
                        }
                    }
                } while (alreadyUsed);

                usedQuestions[usedCount++] = QNum;

                //find index of the random question
                int row = 0;
                while (row < arr.length && !arr[row][0].equals(Integer.toString(QNum))) {
                    row++;
                }
                // if index not found search for another question num
                if (row == arr.length) {
                    i--;
                    continue;
                }

                //print question
                System.out.println("-------------------------------------");
                System.out.println("Q" + i + ". " + arr[row][1]);
                System.out.println("A) " + arr[row][2]);
                System.out.println("B) " + arr[row][3]);
                System.out.println("C) " + arr[row][4]);
                System.out.println("D) " + arr[row][5]);
                String answer = arr[row][6];
                System.out.println("-------------------------------------");

                //take user's answers
                String userAnswer = "";
                while(userAnswer.equals("")){
                    System.out.println("You Answer (A, B, C or D): ");
                    char choice = sc.next().charAt(0);
                    sc.nextLine();
                    if(choice == 'a' || choice == 'A'){
                        userAnswer = arr[row][2];
                    }
                    else if(choice == 'b' || choice == 'B'){
                        userAnswer = arr[row][3];
                    }
                    else if(choice == 'c' || choice == 'C'){
                        userAnswer = arr[row][4];
                    }
                    else if(choice == 'd' || choice == 'D'){
                        userAnswer = arr[row][5];
                    }
                    else{
                        System.out.println("Invalid input. Enter again.");
                        userAnswer = "";
                    }
                }
                System.out.println();
                //store question, correct answer and user's answer in a 2D array
                dataArr[i-1][0] = arr[row][1];
                dataArr[i-1][1] = answer;
                dataArr[i-1][2] = userAnswer;
                questionsAnswered = i;
            }
            long endTime = System.currentTimeMillis();
            double timeTaken = (endTime - startTime) / 1000.0;
            result(dataArr, timeTaken, TOTAL_TIME, questionsAnswered);
        }catch(Exception e){
            System.out.println("Error:" + e);
        }
    }

    /*
    result method receives an array from quiz method that contains questions asked,
    the correct answer and user's answer. It also receives time taken by user to solve the quiz.
    */
    public static void result(String[][] arr, double timeTaken, double TOTAL_TIME, int questionsAnswered){
        if(timeTaken >= TOTAL_TIME){
            System.out.println("You ran out of time :( Try playing again!");
        }
        else{
            //first the user's answers are compared to the correct answers and a counter is incremented for every correct ans.
            int score = 0;
            for(int i = 0; i < questionsAnswered; i++){
                if(arr[i][1].equals(arr[i][2])){
                    score++;
                }
            }
            //total result is output (8/10 answers were correct!)
            System.out.println("=====================================");
            System.out.println("          QUIZ RESULT SUMMARY        ");
            System.out.println("=====================================");
            System.out.println("Score: " + score + " / " + 10);
            long minutes = (int)timeTaken / 60;
            long seconds = (int)timeTaken % 60;
            System.out.printf("Time taken: %02d:%02d\n", minutes, seconds);
            if(score==10){
                System.out.println("Excellent! That's a perfect score!");
            }
            else if(score >= 7){
                System.out.println("That's an impressive score!");
            }
            else if(score>=4){
                System.out.println("Not bad, keep practicing.");
            }
            else{
                System.out.println("Better luck next time!");
            }
            //ask the user "would you like to review your answer?"
            boolean flag = false;
            while(flag != true){
                System.out.print(("Would you like to review your answers?(Y/N) "));
                char choice = sc.next().charAt(0);
                sc.nextLine();
                if(choice == 'y' || choice == 'Y'){
                    flag = true;
                    //if yes then print the questions from the array and tell user which answers were correct
                    System.out.println("\n===== REVIEW =====");
                    for (int i = 0; i < arr.length; i++) {
                        System.out.println("Q" + (i + 1) + ": " + arr[i][0]);
                        System.out.println("   Correct Answer: " + arr[i][1]);
                        System.out.println("   Your Answer   : " + arr[i][2]);
                        if (arr[i][1].equals(arr[i][2])) {
                            System.out.println("   ✅ Correct!\n");
                        } else {
                            System.out.println("   ❌ Wrong!\n");
                        }
                    }
                }
                else if(choice == 'n' || choice == 'N'){
                    flag = true;
                    System.out.println("Thank you for playing!");
                    //if no then exit the result method
                }
                else{
                    System.out.println("Invalid input. Try again.");
                }
            }  
        }
    }
}


