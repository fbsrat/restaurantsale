import java.util.ArrayList;
import java.util.Scanner;

public class Ledge {


    public static void ledger(ArrayList<UserInfo> userTransaction, Scanner scanner) {
        while (true) {
            System.out.println("-- welcome to ledge screen --");
            System.out.println(" The ledge screen helps user to see their all transactions including\n" +
                    "deposit transaction specifically and payment transactions ");
            System.out.println();
            System.out.println("-----Instructions-----");
            System.out.println("press(a) to navigate display all transaction\n" +
                    "press(d) to navigate display all deposits\n" +
                    "press(p) to navigate display all payment\n" +
                    "press(r) to navigate to reports\n");
            System.out.println("a , display all transaction");
            System.out.println("d , display all the deposits");
            System.out.println("p , display all the payment");
            System.out.println("r , navigate to reports");
            System.out.print(" please enter option ");


            String userChoiceOfLetter = scanner.nextLine();
            if (userChoiceOfLetter.equalsIgnoreCase("a")) {
                showAllEntries(userTransaction, scanner);
            } else if (userChoiceOfLetter.equalsIgnoreCase("d")) {
                displayAllTheDeposits(userTransaction, scanner);
            } else if (userChoiceOfLetter.equalsIgnoreCase("p")) {
                showAllThePayments(userTransaction, scanner);
            } else if (userChoiceOfLetter.equalsIgnoreCase("r")) {
               Reports. navigateToReports(userTransaction, scanner);
                //  ledger(userTransaction,scanner);
            } else {
                System.out.println(" please enter either a , d , p , r,b");

            }

           HomePage. askTheUserWhereToGo(userTransaction, scanner);

        }

    }


    public static void showAllEntries(ArrayList<UserInfo> userTransaction, Scanner scanner) {
        for (int i = userTransaction.size() - 1; i >= 0; i--) {
            System.out.println(userTransaction.get(i));

        }
        HomePage.askTheUserWhereToGo(userTransaction, scanner);
    }

    public static void displayAllTheDeposits(ArrayList<UserInfo> userTransaction, Scanner scanner) {
        for (int i = userTransaction.size() - 1; i >= 0; i--) {
            UserInfo info = userTransaction.get(i);
            if (info.getAmount() > 0) {
                System.out.println(info);
            }
        }
        HomePage.askTheUserWhereToGo(userTransaction, scanner);
    }

    public static void showAllThePayments(ArrayList<UserInfo> userTransaction, Scanner scanner) {
        for (int i = userTransaction.size() - 1; i >= 0; i--) {
            UserInfo info = userTransaction.get(i);
            if (info.getAmount() < 0) {
                System.out.println(info);

            }
        }
        HomePage.askTheUserWhereToGo(userTransaction, scanner);

    }
}