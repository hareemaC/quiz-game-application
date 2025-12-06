import java.util.Scanner;

public class level {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String playAgain;

        do {
            String topic = chooseTopic();
            int time = chooseLevel();

            System.out.println("\nReady to start the quiz on " + topic + " at selected level!");
            System.out.println("You have " + time + " minutes to complete it.");

            
            System.out.print("\nDo you want to play again? (yes/no): ");
            playAgain = sc.nextLine().toLowerCase();

            System.out.println(); 

        } while (playAgain.equals("yes"));

        System.out.println("Thank you for playing!");
        sc.close(); 
    }

    public static String chooseTopic() {
        String topic = "";
        do{
        System.out.println("Choose a topic:");
        System.out.println("1. Science");
        System.out.println("2. Math");
        System.out.println("3. Computer");
        System.out.println("4. English");
        System.out.println("5. General");
        System.out.print("Enter topic number: ");
        int topicChoice = sc.nextInt();
        sc.nextLine(); 
        switch (topicChoice) {
            case 1: 
            topic = "Science";
             break;
            case 2:
                 topic = "Math";
             break;
            case 3: 
            topic = "Computer";
             break;
            case 4: 
            topic = "English";
             break;
            case 5:
             topic = "General";
             break;
            default: 
                System.out.println("Invalid topic choice!");
        }
               
        }while(topic=="");
        System.out.println("You chose: " + topic + "\n");
        return topic;
    }

    public static int chooseLevel() {
        int time = 0;
        do{
  
        System.out.println("Choose a level:");
        System.out.println("1. Easy (15 minutes)");
        System.out.println("2. Medium (12 minutes)");
        System.out.println("3. Hard (10 minutes)");
        System.out.print("Enter level number: ");
        int levelChoice = sc.nextInt();
        sc.nextLine(); 

        
        switch (levelChoice) {
            case 1:
             time = 15; 
             System.out.println("You chose: Easy level"); 
             break;
            case 2:
             time = 12; 
             System.out.println("You chose: Medium level"); 
             break;
            case 3:
             time = 10; 
             System.out.println("You chose: Hard level");
             break;
            default: System.out.println("Invalid level choice!"); 
           
        }}while(time==0);
        
        return time;
    }
}
