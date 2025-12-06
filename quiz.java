import java.io.*;
import java.util.Scanner;
// quiz method will receive the specific file (chosen by the user's requirements) as an argument

public class quiz {
    private static final int TOTAL_TIME = 600;

    public static String[][] getQuestions(){
        String[][] questions = new String[10][7];
        try{
            BufferedReader br = new BufferedReader(new FileReader("comp easy.txt"));
            int index = 0;
            while(index < 10){
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
            System.out.println("An erro occured");
        }
        return questions;
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[][] arr = getQuestions();
        String[][] dataArr = new String[10][3];

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
            

            //Get a random question from array
            int QNum = (int)(Math.random() * 10) + 1;
            int row = 0;

            //find index of the random question
            while(!arr[row][0].equals(Integer.toString(QNum))){
                row++;
            } 

            //print question
            System.out.println("Q" + i + ". " + arr[row][1]);
            System.out.println("A) " + arr[row][2]);
            System.out.println("B) " + arr[row][3]);
            System.out.println("C) " + arr[row][4]);
            System.out.println("D) " + arr[row][5]);
            String answer = arr[row][6];


            //take user's answers
            String userAnswer = "";
            while(userAnswer == ""){
                System.out.println("You Answer (A, B, C or D): ");
                char choice = input.next().charAt(0);
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
            //store question, correct answer and user's answer in a 2D array
            dataArr[i-1][0] = arr[row][1];
            dataArr[i-1][1] = answer;
            dataArr[i-1][2] = userAnswer;
        }

        long endTime = System.currentTimeMillis();
        double timeTaken = (endTime - startTime) / 1000.0;
        System.out.printf("\nYou took %.2f seconds.\n", timeTaken);
        input.close();
    }
}

    // if level is easy only first 10 questions 
    //send the array to the result method
    //send time taken to the result method
