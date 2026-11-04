
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.Collections;

import static java.awt.SystemColor.info;

public class Main {
    public static void main(String[] args) {
        ArrayList<userInfo> userTransaction = loadTransaction("src/main/java/transaction.csv");
       // ArrayList<userInfo> userTransaction=new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
            System.out.println("----------welcome to the transactions application----------");
            System.out.println("H,Home Screen");
            System.out.println("L,Ledger");
            System.out.println("R,Reports");
            System.out.print(" choose the letter option ");
            while (true) {
            String userChoiceOfLetter = scanner.nextLine();
            if (userChoiceOfLetter.equalsIgnoreCase("h")) {
                homeScreen(userTransaction, scanner);
            } else if (userChoiceOfLetter.equalsIgnoreCase("l")) {
                ledger(userTransaction, scanner);
            } else if (userChoiceOfLetter.equalsIgnoreCase("r")) {
                navigateToReports(userTransaction, scanner);
            } else {
                System.out.println("please enter the correct letter either h , l ,r ");
            }

        }
    }
    public static void homeScreen( ArrayList <userInfo> userTransaction,Scanner scanner){
        String userChoiceOfLetter = "";
        //Scanner scanner = new Scanner(System.in);
        // Display menu options until the user selects Exit.
        while (!userChoiceOfLetter.equals("x")) {
            System.out.println();
            System.out.println("----welcome to home screen------");
            System.out.println("d, add deposit");
            System.out.println("p, make payment");
            System.out.println("l, ledger");
            System.out.println("X, exist from homes creen");
            System.out.print("please enter the correct letter : ");
            userChoiceOfLetter = scanner.nextLine().trim().toLowerCase();
            switch (userChoiceOfLetter){
                case "d":
                    addDeposit(userTransaction,scanner);
                    break;
                case "p":
                    makePayment(userTransaction,scanner);
                    break;
                case "l":
                    ledger(userTransaction,scanner);
                default:
                    System.out.println("please enter the correct letter");
            }
        }
}
public static void addDeposit( ArrayList<userInfo> userTransaction, Scanner scanner){
    //LocalDateTime now=LocalDateTime.now();
    LocalDate date=LocalDate.now();
    LocalTime time=LocalTime.now();
     String date1 = date.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
     String time1 = time.format(DateTimeFormatter.ofPattern("hh:mm:ss a"));
    System.out.println(" please enter the description ");
    String descriptionOfPayment=scanner.nextLine().trim();
    System.out.println("please enter the vendor name ");
    String vendor = scanner.nextLine().trim();
    System.out.println(" enter the amount");
    double amount = scanner.nextDouble();
    scanner.nextLine();
    userInfo deposit= new userInfo( date ,time,descriptionOfPayment,vendor,amount);
    userTransaction.add(deposit);
    try (BufferedWriter writer  =new BufferedWriter( new  FileWriter("src/main/java/transaction.csv",true))){
        writer.write(date1 + "|" + time1 + "|" + descriptionOfPayment + "|" + vendor + "|" + amount);
        writer.newLine();
        writer.close();
    }
    catch (IOException e){
        e.printStackTrace();

    }
}
public static void makePayment(ArrayList<userInfo>userTransaction,Scanner scanner){

    LocalDate date=LocalDate.now();
    LocalTime time=LocalTime.now();
    String date1 = date.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
    String time1 = time.format(DateTimeFormatter.ofPattern("hh:mm:ss a"));
    System.out.println(" please enter the description ");
    String descriptionOfPayment=scanner.nextLine().trim();
    System.out.println("please enter the vendor name ");
    String vendor = scanner.nextLine().trim();
    System.out.println(" enter the amount");
    double amount = scanner.nextDouble();
    amount= -amount;
    scanner.nextLine();
    userInfo deposit= new userInfo( date ,time,descriptionOfPayment,vendor,amount);
    userTransaction.add(deposit);
    try (BufferedWriter writer  =new BufferedWriter( new  FileWriter("src/main/java/transaction.csv",true))) {
        writer.write(date1 + "|" + time1 + "|" + descriptionOfPayment + "|" + vendor + "|" + amount);
        writer.newLine();
        writer.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
public static void ledger( ArrayList<userInfo> userTransaction,Scanner scanner){
    System.out.println();
    System.out.println("a , display all transaction");
    System.out.println("d , display all the deposits");
    System.out.println("p , display all the payment");
    System.out.println("r , navigate to reports");
    System.out.print(" please enter option ");
    //String userChoiceOfLetter = scanner.nextLine();

    while (true){
        String userChoiceOfLetter = scanner.nextLine();
        if (userChoiceOfLetter.equalsIgnoreCase("a")){
            showAllEntries(userTransaction,scanner);
        } else if ( userChoiceOfLetter.equalsIgnoreCase("d")) {
            displayAllTheDeposits(userTransaction,scanner);
        } else if (userChoiceOfLetter.equalsIgnoreCase("p")) {
            showAllThePayments( userTransaction,scanner);
        }
        else if(userChoiceOfLetter.equalsIgnoreCase("r")){
            navigateToReports(userTransaction,scanner);
        }
        else {
            System.out.println(" please enter either a , d , p , r");
        }
    }
}
public  static ArrayList<userInfo> loadTransaction(String fileName ){
        ArrayList<userInfo> userTransaction=new ArrayList<>();
    //ArrayList<userInfo>userTransaction = loadTransaction(String fileName);
    DateTimeFormatter dateFormat=DateTimeFormatter.ofPattern("M-d-yyyy");
    DateTimeFormatter timeFormat=DateTimeFormatter.ofPattern("h:mm:ss a", Locale.US);

    try (BufferedReader reader = new BufferedReader(new FileReader( fileName))) {
        reader.readLine(); // skip the header line
        String line;
        while ((line = reader.readLine()) != null) {
           // LocalDate date=LocalDate.now();
           // LocalTime time=LocalTime.now();
            String[] parts = line.split("\\|");
            LocalDate date =LocalDate.parse(parts[0].trim().toUpperCase(),dateFormat);
            LocalTime time=LocalTime.parse(parts[1].trim().toUpperCase(),timeFormat);
           // String time2 = parts[1];
            String descriptionOfPayment = parts[2].trim();
            String vendor = parts[3].trim();
            double amount=Double.parseDouble(parts[4].trim());
            userTransaction.add(new userInfo(date, time , descriptionOfPayment,vendor,amount));

        }

    }
    catch (IOException e){
        System.out.println("Could not load " + fileName + ": " + e.getMessage());
    }
    return userTransaction;
}
public static void showAllEntries(ArrayList<userInfo>userTransaction,Scanner scanner){
        ArrayList<userInfo> newTransactionFirst=new ArrayList<>(userTransaction);
        Collections.reverse(newTransactionFirst);
        for(int i=userTransaction.size()-1;i>=0;i--) {
            System.out.println(userTransaction.get(i));
            //for (userInfo info :userTransaction)
        }
      //  System.out.println(info);
}
public  static void displayAllTheDeposits(ArrayList<userInfo> userTransaction, Scanner scanner){
        for (int i =userTransaction.size()-1;i>=0;i--){
         userInfo info=userTransaction.get(i);
         if(info.getAmount()>0) {
             System.out.println(info);
            }
        }

}
public static void showAllThePayments(ArrayList<userInfo>userTransaction,Scanner scanner) {
    for (int i = userTransaction.size() - 1; i >= 0; i--) {
        userInfo info = userTransaction.get(i);
        if (info.getAmount() < 0) {
            System.out.println(info);

        }
    }
}
        public static void navigateToReports (ArrayList<userInfo> userTransaction, Scanner scanner){

            //while (true){
            System.out.println();
            System.out.println("-----welcome to report screen----");
            System.out.println("1, month to date ");
            System.out.println("2, previous month");
            System.out.println("3, year to date ");
            System.out.println("4, previous year");
            System.out.println("5, search by vendor");
            System.out.println("6, custom search");
            System.out.println("0, back to ledger page");
            System.out.print("please choose an option ");
            while (true){
           String usersChoiceOfChoice=scanner.nextLine();
            int userChoice=Integer.parseInt(usersChoiceOfChoice);
            if (usersChoiceOfChoice.equals("1")){
                monthToDate(userTransaction,scanner);
            }
            else if (usersChoiceOfChoice.equals("2")) {
                previousMonth(userTransaction,scanner);
            }
            else if( usersChoiceOfChoice.equals("3"))
            {
             yearToDate( userTransaction,scanner);
            }
         else if(usersChoiceOfChoice.equals("4")){
           previousYear(userTransaction,scanner);
            }
         else if(usersChoiceOfChoice.equals("5")){
             searchByVendor(userTransaction,scanner);
            }
         else if (usersChoiceOfChoice.equals("6")){
             customSearch(userTransaction,scanner);
            }
         else if (usersChoiceOfChoice.equals("0")) {
             backToLedgerPage(userTransaction,scanner);
            }
         else {
                System.out.println("please enter numbers 0 to 6");
            }
        }
        }
        public static void monthToDate(ArrayList<userInfo>userTransaction,Scanner scanner){
      LocalDate  today=LocalDate.now();
      LocalDate firstDaYOfMonth= today.withDayOfMonth(1);
            for (int i =userTransaction.size()-1;i>=0;i--) {
                userInfo info = userTransaction.get(i);
                LocalDate date = info.getDate();
                boolean found=true;
                if (!date.isBefore(firstDaYOfMonth) && !date.isAfter(today)) {
                    System.out.println(info);
                    found=true;
                }
            if(!found) {
                    System.out.println(" no transaction found");
                }
            }

        }
        public static void previousMonth(ArrayList<userInfo>userTransaction,Scanner scanner){
            LocalDate firstDay = LocalDate.now().minusMonths(1).withDayOfMonth(1);
            LocalDate lastDay = firstDay.withDayOfMonth(firstDay.lengthOfMonth());
            boolean found = false;//adding boolean

            for (int i =userTransaction.size()-1;i>=0;i--) {
                userInfo info = userTransaction.get(i);
                LocalDate date = info.getDate();
                //capstone m
                //  LocalDate date=LocalDate.now();
                //LocalDate firstDay = LocalDate.now().minusMonths(1).withDayOfMonth(1);
                //LocalDate lastDay = firstDay.withDayOfMonth(firstDay.lengthOfMonth());
                //boolean found = false;

                if (!date.isBefore(firstDay) && !date.isAfter(lastDay)) {
                    found = true;
                    System.out.println(info);
                }
            }
                if ((!found)) {
                    System.out.println(" no transaction found");
                }
            }

        public static void yearToDate(ArrayList<userInfo> userTransaction,Scanner scanner){

        }
        public static void previousYear(ArrayList< userInfo> userTransaction , Scanner scanner){

        }
        public static void searchByVendor(ArrayList<userInfo>userTransaction, Scanner scanner){

        }
        public static void backToLedgerPage(ArrayList<userInfo>userTransaction,Scanner scanner){

        }
        public static void customSearch(ArrayList<userInfo>userTransaction,Scanner scanner){

        }

public static void goBackToHomeScreen(ArrayList<userInfo> userTransaction,Scanner scanner){

}
    }