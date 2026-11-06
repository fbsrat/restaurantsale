import java.util.ArrayList;
import java.util.Scanner;

public class HomePage {

    public static void homePage(ArrayList<UserInfo> userTransaction, Scanner scanner) {
        System.out.println("=======================================" +
                "======================================");
        System.out.println();
        System.out.println("----------welcome to the Ade resturant  " +
                "transactions application----------");
        System.out.println();
        //prints welcome banner
        System.out.println("\" Ade\" means mother in tigrigna ");
        System.out.println("===============================================");
        System.out.println();
        // print empty line
        System.out.println(//prints menu options
                "press(h) to navigate home screen\n" +
                        "press(l) to navigate ledger\n" +
                        " press(m) to navigate to the help menu\n" +
                        "press (x) to exit the application\n");
        System.out.println("H,Home Screen");
        System.out.println("L,Ledger");
        System.out.println("M, helpMenu");

        System.out.print(" choose the letter option ");//print to keep the cursor on the same line
        boolean running = true;
        while (running) {
            String userChoiceOfLetter = scanner.nextLine();//read the whole line the user has types
            //  and  store it
            if (userChoiceOfLetter.equalsIgnoreCase("h"))//equalsignore the case means "h" and "H" are the same
            {
                HomeScreen.homeScreen(userTransaction, scanner);
            } else if (userChoiceOfLetter.equalsIgnoreCase("l")) {
                Ledge.ledger(userTransaction, scanner);
            } else if (userChoiceOfLetter.equalsIgnoreCase("x")) {
                System.exit(0);

            } else if (userChoiceOfLetter.equalsIgnoreCase("m")) {
                HelpMenu.helpMenu();
            } else {
                System.out.println("please enter the correct letter either h , l ,r ");
                //tells the user to enter the correct option
            }

        }


    }


    public static void askTheUserWhereToGo(ArrayList<UserInfo> userTranaction, Scanner scanner) {
        while ((true)) {
            System.out.println(" what would you like to do next?");
            System.out.println("press (l), to go back to ledger page");
            System.out.println("press (b), to go back to home page");
            System.out.println("press(x),to exit the app");
            String next = scanner.nextLine().trim();
            if (next.equalsIgnoreCase("l")) {
                Ledge.ledger(userTranaction, scanner);
            } else if (next.equalsIgnoreCase("b")) {
                HomePage.homePage(userTranaction, scanner);
            } else if (next.equalsIgnoreCase("x")) {
                exitTheApplication(userTranaction, scanner);
            } else {
                System.out.println(" please, press (l) to go back to ledge page\n" +
                        "press (b) to go back to gome page \n" +
                        "press (x) to exist the application\n");
            }

        }

    }

    public static void exitTheApplication(ArrayList<UserInfo> useraTranaction, Scanner scanner) {
        System.out.println("Thank you for using the application");
        System.exit(0);
    }
}