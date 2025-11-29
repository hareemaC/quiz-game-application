import java.util.Scanner;

/*
result method receives an array from quiz method that contains questions asked,
the correct answer and user's answer. It also receives time taken by user to solve the quiz.
*/

public class result {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[][] arr = {{"What does CPU stand for?", "Central Processing Unit", "Central Program Unit"},
        {"Which device is used to point and click on a computer screen?", "Mouse", "Mouse"},
        {"Which is an input device?", "Keyboard", "Keyboard"},
        {"What does RAM stand for?", "Random Access Memory", "Read Any Memory"},
        {"What does CPU stand for?", "Central Processing Unit", "Central Program Unit"},
        {"Which is an input device?", "Keyboard", "Keyboard"},
        {"What does RAM stand for?", "Random Access Memory", "Read Any Memory"},
        {"Which device is used to point and click on a computer screen?", "Mouse", "Mouse"},
        {"Which device is used to point and click on a computer screen?", "Mouse", "Mouse"},
        {"Which device is used to point and click on a computer screen?", "Mouse", "Mouse"}
        };
        int timeTaken = 600;
        if(timeTaken == 600){
            System.out.println("You ran out of time :( Try again!");
        }
        else{
            //first the user's answers are compared to the correct answers and a counter is incremented for every correct ans.
            int score = 0;
            for(int i = 0; i < 10; i++){
                if(arr[i][1].equals(arr[i][2])){
                    score++;
                }
            }
            //total result is output (8/10 answers were correct!)
            System.out.println("=====================================");
            System.out.println("          QUIZ RESULT SUMMARY        ");
            System.out.println("=====================================");
            System.out.println("Score: " + score + " / " + 10);
            System.out.println("Time Taken: " + timeTaken);
            if(score==10){
                System.out.println("Excellent! That's a perfect score!");
            }
            else if(score >= 7){
                System.out.println("Great job!");
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
                char choice = input.next().charAt(0);
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
        input.close();

    }
}
