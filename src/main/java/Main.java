import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/* this application is made to make a user to save transactions whether  it is adding deposit or
making a payment and review their transactions . this file is located  in src/main/java/transaction.csv
this application has two main screens which are home screen and ledge .
it also have option to exit the application.
home screen have four main options .
which are addDeposit() which will able the user to enter and save their transaction,
makePayment() this method will be able to write and save their payment transaction.
payment transaction  will bw saved as negative transaction.
ledger screen --showallentries() shows user all our transaction , should show us the newest entry first
              --showalldeposit() should show user all deposit
              --display() all payment should show user all payment
              --reports() should show the user all transaction based on the users option
        */

public class Main {
    public static void main(String[] args) {
        //main method where the programs start running
        ArrayList<UserInfo> userTransaction = TransactionFile.loadTransaction(
                "src/main/java/transaction.csv");
        //load all the transaction from the file
        //each line in the file becomes one userinfo object
        Scanner scanner = new Scanner(System.in);
        //scanner lets us read what a user type from keyword
        HomePage.homePage(userTransaction, scanner);
        // show home page first this is basically calling a method


    }

}

