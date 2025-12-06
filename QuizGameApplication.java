import java.util.Scanner;
import java.io.*;

public class Pffinal {

    static Scanner sc = new Scanner(System.in);
    static String filePath = "D:\\semester 1\\pf\\lab work\\project final\\users.txt";

    public static void main(String[] args) {

        // ---------------- LOGIN MENU ----------------
        while (true) {
            System.out.println("\n--- LOGIN MENU ---");
            System.out.println("1. Sign Up");
            System.out.println("2. Sign In");
            System.out.println("3. Quit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
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
                System.out.println("Thank you for playing!");
                break;
            }

            else {
                System.out.println("Invalid choice. Try again!");
            }
        }

        sc.close();
    }

    // ---------------- HOW TO PLAY + TOPIC + LEVEL ---------------
    public static void startGame() {
        System.out.println("\nWelcome to the Quiz Game!");
        System.out.println("Rules:");
        System.out.println("1. Choose a topic: Science, English, Math, Computers, or General.");
        System.out.println("2. Choose a level: Easy, Medium, or Hard.");
        System.out.println("3. Easy = 15 mins, Medium = 12 mins, Hard = 10 mins.");
        System.out.println("4. Answer questions before time ends.");
        System.out.println("5. Score will be calculated at the end.");
        System.out.println("6. You can play again.\n");

        String playAgain;
        do {
            String topic = chooseTopic();
            int time = chooseLevel();
            System.out.println("\nStarting quiz on: " + topic);
            System.out.println("You have " + time + " minutes.");
            // Here you can add quiz questions later

            System.out.print("\nDo you want to play again? (yes/no): ");
            playAgain = sc.nextLine().toLowerCase();
        } while (playAgain.equals("yes"));
    }

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

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: topic = "Science"; break;
                case 2: topic = "Math"; break;
                case 3: topic = "Computer"; break;
                case 4: topic = "English"; break;
                case 5: topic = "General"; break;
                default: System.out.println("Invalid topic! Try again.");
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

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: time = 15; System.out.println("You chose: Easy level"); break;
                case 2: time = 12; System.out.println("You chose: Medium level"); break;
                case 3: time = 10; System.out.println("You chose: Hard level"); break;
                default: System.out.println("Invalid level! Try again.");
            }
        } while (time == 0);

        return time;
    }
}

