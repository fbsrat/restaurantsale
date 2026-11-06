import java.util.ArrayList;
import java.util.Scanner;

public class HomeScreen {

    public static void homeScreen(ArrayList<UserInfo> userTransaction, Scanner scanner)
    //shows the home menu
    {
        String userChoiceOfLetter = ""; // asking the user to enter their   choice of letter
        //Scanner scanner = new Scanner(System.in);
        // Display menu options until the user selects Exit.
        // while (!userChoiceOfLetter.equals("x")) {
        System.out.println();
        System.out.println("----welcome to home screen------");
        System.out.println(
                "press(d) to navigate add deposit transaction\n" +
                        "press (p) to navigate make payment transaction\n" +
                        "press (l) to navigate to ledger\n" +
                        "press (x) to exit from the application\n");
        System.out.println("d, add deposit");
        System.out.println("p, make payment");
        System.out.println("l, ledger");
        System.out.println("X, exist from application");
        System.out.print("please enter the correct letter : ");
        while (!userChoiceOfLetter.equals("x")) // if the user didnt choose the letter x continue the program
        {
            userChoiceOfLetter = scanner.nextLine().trim().toLowerCase();
            switch (userChoiceOfLetter){
                case "d":
                    // if the user picks d  it will take it to addDeposit option
                TransactionManager.addDeposit(userTransaction,scanner);
                    break;
                case "p":
                    //if the user picks p it will take it to the makePayment option
                  TransactionManager.makePayment(userTransaction,scanner);
                    break;
                case "l":
                  Ledge .ledger(userTransaction,scanner);
                    break;
                case "x":
                    HomePage.exitTheApplication(userTransaction , scanner);
                    break;
                default:
                    System.out.println("please enter the correct letter");
            }
        }
        HomePage.askTheUserWhereToGo(userTransaction,scanner);
    }


}
