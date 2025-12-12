import java.util.Scanner;
import java.io.*;

public class QuizGameApplication {

    static Scanner sc = new Scanner(System.in);
    static String filePath = "users.txt";

    public static void main(String[] args) {

        while (true) {
            try {
                System.out.println("\n--- LOGIN MENU ---");
                System.out.println("1. Sign Up");
                System.out.println("2. Sign In");
                System.out.println("3. Quit");
                System.out.print("Choose an option: ");

                int choice;

                try {
                    choice = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter numbers only.");
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
                            String pass = br.readLine();
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

                            try {
                                BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
                                bw.write(newUser + "\n");
                                bw.write(newPass + "\n");
                                bw.close();
                            } catch (IOException e) {
                                System.out.println("Error writing new user to file.");
                            }

                            System.out.println("Account created successfully!");
                            startGame();
                            System.out.println("Thank you for playing!");
                            break;
                        }

                    } catch (IOException e) {
                        System.out.println("Error during sign-up process.");
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
                            String passwordLine = br.readLine();

                            if (line.equals(user)) {
                                foundUser = true;

                                if (passwordLine != null && passwordLine.equals(pass)) {
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

                    } catch (FileNotFoundException e) {
                        System.out.println("User data file not found.");
                    } catch (IOException e) {
                        System.out.println("Error reading user data.");
                    }
                }

                // QUIT
                else if (choice == 3) {
                    System.out.println("Thank you for playing!");
                    break;
                }

                // INVALID MENU OPTION
                else {
                    System.out.println("Invalid choice. Try again!");
                }

            } catch (Exception e) {
                System.out.println("Unexpected error occurred. Restarting menu...");
            }
        }

        sc.close();
    }

    public static void startGame() {
        System.out.println("Game starts here...");
        // Your quiz logic goes here
    }

}
