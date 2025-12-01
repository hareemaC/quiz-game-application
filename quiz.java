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
            System.out.printf("Time left: %02d:%02d\n", minutes, seconds);
            try{
                //open file
                File obj = new File("comp easy.txt");
                Scanner Reader = new Scanner(obj);
                //choose random question from the file
                String Qnum = Integer.toString((int)(Math.random() * 10) + 1);
                
                //print the question and choices to user
                String line = "";
                while(line != Qnum || Reader.hasNextLine()){
                    line = Reader.nextLine();
                }
                String Question = Reader.nextLine();
                String choice1 = Reader.nextLine();
                String choice2 = Reader.nextLine();
                String choice3 = Reader.nextLine();
                String choice4 = Reader.nextLine();
                String answer = Reader.nextLine();
                System.out.println("Q " + i + ". "+Question + "?");
                System.out.println("A) " + choice1);
                System.out.println("B) " + choice2);
                System.out.println("C) " + choice3);
                System.out.println("D) " + choice4);
                //take user's answers
                String userAnswer = "";
                while(userAnswer == ""){
                    System.out.println("You Answer (A, B, C or D): ");
                    char choice = input.next().charAt(0);
                    if(choice == 'a' || choice == 'A'){
                        userAnswer = choice1;
                    }
                    else if(choice == 'b' || choice == 'B'){
                        userAnswer = choice2;
                    }
                    else if(choice == 'c' || choice == 'C'){
                        userAnswer = choice3;
                    }
                    else if(choice == 'd' || choice == 'D'){
                        userAnswer = choice4;
                    }
                    else{
                        System.out.println("Invalid input. Enter again.");
                        userAnswer = "";
                    }
                }
                //store question, correct answer and user's answer in a 2D array
                String[][] dataArr = new String[10][3];
                dataArr[i-1][0] = Question;
                dataArr[i-1][1] = answer;
                dataArr[i-1][2] = userAnswer;
            }catch(IOException e){
                System.out.println("File not found");
            }  

        }

        long endTime = System.currentTimeMillis();
        double timeTaken = (endTime - startTime) / 1000.0;
        System.out.printf("\nYou took %.2f seconds.\n", timeTaken);
    }
}
    //send the array to the result method
    //send time taken to the result method
