import java.util.Scanner;

public class LoginSystem {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String[] usernames = new String[10000];
        String[] passwords = new String[10000];
        int userCount = 0;

        while (true) {

            System.out.println("\n--- LOGIN MENU ---");
            System.out.println("1. Sign Up");
            System.out.println("2. Sign In");
            System.out.println("3. Quit");
            System.out.print("Choose an option: ");
            int choice = input.nextInt();
            input.nextLine();  
            // ---------------- SIGN UP ----------------
            if (choice == 1) {

                System.out.print("Enter new username: ");
                String newUser = input.nextLine();

                // Check if username already exists
                boolean exists = false;
                for (int i = 0; i < userCount; i++) {
                    if (usernames[i].equals(newUser)) {
                        exists = true;
                        break;
                    }
                }

                if (exists) {
                    System.out.println("Username already taken.");
                } else {

                    System.out.print("Enter new password: ");
                    String newPass = input.nextLine();

                    usernames[userCount] = newUser;
                    passwords[userCount] = newPass;
                    userCount++;

                    System.out.println("Account created successfully!");
                }
            }

            // ---------------- SIGN IN ----------------
            else if (choice == 2) {

                System.out.print("Enter username: ");
                String user = input.nextLine();

                System.out.print("Enter password: ");
                String pass = input.nextLine();

                boolean found = false;

                for (int i = 0; i < userCount; i++) {
                    if (usernames[i].equals(user) && passwords[i].equals(pass)) {
                        found = true;
                        break;
                    }
                }

                if (found) {
                    System.out.println("Login Successful! Welcome, " + user);
                } else {
                    System.out.println("Incorrect username or password.");
                }
            }

            // ---------------- QUIT ----------------
            else if (choice == 3) {
                System.out.println("Thank you for playing");
                break;
            }

            // ---------------- INVALID OPTION ----------------
            else {
                System.out.println("Invalid choice. Try again!");
            }
        }

        input.close();
    }
}

