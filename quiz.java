import java.io.*;
import java.util.Scanner;
// quiz method will receive the specific file (chosen by the user's requirements) as an argument

public class quiz {
    private static final int TOTAL_TIME = 600;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        long startTime = System.currentTimeMillis();
        int totalQuestions = 10;

        System.out.println("Your 10 minutes begin now!");
        for (int i = 1; i <= totalQuestions; i++) {
        
            long elapsed = (System.currentTimeMillis() - startTime) / 1000;
            long remaining = TOTAL_TIME - elapsed;

            if (remaining <= 0) {
                System.out.println("\n⏰ Time is up! Quiz over.");
                break;
            }

            long minutes = remaining / 60;
            long seconds = remaining % 60;
            System.out.printf("⏱️ Time left: %02d:%02d\n", minutes, seconds);
            //open file
            try{
            BufferedReader reader = new BufferedReader(new FileReader("comp easy.txt"));
            }catch(FileNotFoundException e){
                System.out.println("File not found");
            }
            // Ask question

        }

        long endTime = System.currentTimeMillis();
        double timeTaken = (endTime - startTime) / 1000.0;
        System.out.printf("\nYou took %.2f seconds.\n", timeTaken);
    }
}


    //open file
    //control loop using time and questions<10
    //choose random question from the file
    //print the question and choices to user
    //take user's answers
    //store question, correct answer and user's answer in a 2D array
    //send the array to the result method
    //send time taken to the result method
