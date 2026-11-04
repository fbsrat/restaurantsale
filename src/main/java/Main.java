import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<userInfo> userTransaction = loadTransaction("src/main/java/transaction.csv");
       // ArrayList<userInfo> userTransaction=new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
            System.out.println("----------welcome to the transactions application----------");
        System.out.println();
        System.out.println(
                "press(h) to navigate home screen\n" +
                "press(l) to navigate ledger\n" +
                "press(r) to navigat reports\n" +
                "press (x) to exit the application\n");
            System.out.println("H,Home Screen");
            System.out.println("L,Ledger");
            System.out.println("R,Reports");
            System.out.print(" choose the letter option ");
            boolean running=true;
            while (running) {
                String userChoiceOfLetter = scanner.nextLine();
                if (userChoiceOfLetter.equalsIgnoreCase("h")) {
                    homeScreen(userTransaction, scanner);
                } else if (userChoiceOfLetter.equalsIgnoreCase("l")) {
                    ledger(userTransaction, scanner);
                } else if (userChoiceOfLetter.equalsIgnoreCase("r")) {
                    navigateToReports(userTransaction, scanner);
                }
                else if (userChoiceOfLetter.equalsIgnoreCase("x")){
                 System.exit(0) ;

                }

                else
                {
                    System.out.println("please enter the correct letter either h , l ,r ");
                }
                ledger(userTransaction, scanner);

                askUserToContinue(userTransaction, scanner);
            }
    }
    public static void homeScreen( ArrayList <userInfo> userTransaction,Scanner scanner){
        String userChoiceOfLetter = "";
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
            while (!userChoiceOfLetter.equals("x")) {
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
                    break;
                case "x":
                    exitTheApplication(userTransaction);
                    break;
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
    System.out.println("--welcome to add deposit--");
    System.out.println("date and time for the transaction are automatically saved");
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
    }
    catch (IOException e){
        System.out.println("error:"+e.getMessage());
    }
    System.out.println(" your file is saved successfully");
    goBackToHomePage(userTransaction,scanner);
    askUserToContinue(userTransaction,scanner);
}
public static void makePayment(ArrayList<userInfo>userTransaction,Scanner scanner){

    LocalDate date=LocalDate.now();
    LocalTime time=LocalTime.now();
    String date1 = date.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
    String time1 = time.format(DateTimeFormatter.ofPattern("hh:mm:ss a"));
    System.out.println("--welcome to make payment--");
    System.out.println();
    System.out.println("date and time for the transaction are automatically saved");
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
    goBackToHomePage(userTransaction,scanner);
    askUserToContinue(userTransaction,scanner);
}
public static void ledger( ArrayList<userInfo> userTransaction,Scanner scanner){
    System.out.println("-- welcome to ledge page --");
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
    //String userChoiceOfLetter = scanner.nextLine();

    //while (true){
    boolean running=true;
    while (running){
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
          //  ledger(userTransaction,scanner);
        }
        else {
            System.out.println(" please enter either a , d , p , r");
        }

        goBackToHomePage(userTransaction,scanner);
    }
}
public static void exitTheApplication(ArrayList<userInfo>useraTranaction){
    System.out.println("Thank you for using the application");
    System.exit(0);

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
public static void showAllEntries(ArrayList<userInfo>userTransaction, Scanner scanner){
        ArrayList<userInfo> newTransactionFirst=new ArrayList<>(userTransaction);
        Collections.reverse(newTransactionFirst);
        for(int i=userTransaction.size()-1;i>=0;i--) {
            System.out.println(userTransaction.get(i));
            //for (userInfo info :userTransaction)
        }
        goBackToHomePage(userTransaction,scanner);

}
public  static void displayAllTheDeposits(ArrayList<userInfo> userTransaction, Scanner scanner){
        for (int i =userTransaction.size()-1;i>=0;i--){
         userInfo info=userTransaction.get(i);
         if(info.getAmount()>0) {
             System.out.println(info);
            }
        }
goBackToHomePage(userTransaction,scanner);
}
public static void showAllThePayments(ArrayList<userInfo>userTransaction ,Scanner scanner) {
    for (int i = userTransaction.size() - 1; i >= 0; i--) {
        userInfo info = userTransaction.get(i);
        if (info.getAmount() < 0) {
            System.out.println(info);

        }
    }
    goBackToHomePage(userTransaction,scanner);
}
        public static void navigateToReports (ArrayList<userInfo> userTransaction, Scanner scanner){
            System.out.println("-----welcome to report screen----");
            System.out.println("The report screen helps users to find review their transaction.\n" +
                    "they can search by date range,vendor name to see \n" +
                    "the exact transaction they are looking for ");
            System.out.println();
            System.out.println("-----Instructions------");
            System.out.println("press(1) to navigate to month to date\n" +
                    "press(2) to navigate to previous month\n " +
                    "press(3) to navigate to year to date\n" +
                    "press(4) to navigate to prevoius year\n " +
                    "press(5) to navigate search by vendor\n" +
                    "press(6)to navigate to custom search\n" +
                    "press(0) to go back to ledger screen\n");
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
             return;
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
            goBackToHomePage(userTransaction,scanner);

        }
        public static void previousMonth(ArrayList<userInfo>userTransaction, Scanner scanner ){
            LocalDate firstDay = LocalDate.now().minusMonths(1).withDayOfMonth(1);
            LocalDate lastDay = firstDay.withDayOfMonth(firstDay.lengthOfMonth());
            boolean found = false;//adding boolean

            for (int i =userTransaction.size()-1;i>=0;i--) {
                userInfo info = userTransaction.get(i);
                LocalDate date = info.getDate();
                if (!date.isBefore(firstDay) && !date.isAfter(lastDay)) {
                    found = true;
                    System.out.println(info);
                }
            }
                if ((!found)) {
                    System.out.println(" no transaction found");
                }
                goBackToHomePage(userTransaction,scanner);
            }

        public static void yearToDate(ArrayList<userInfo> userTransaction , Scanner scanner){
        LocalDate today=LocalDate.now();
        LocalDate firstDayOfTheTear=today.withDayOfYear(1);
        boolean found=false;

            for (int i =userTransaction.size()-1;i>=0;i--) {
                userInfo info = userTransaction.get(i);
                LocalDate date = info.getDate();
                if ((!date.isBefore(firstDayOfTheTear)&&!date.isAfter(today))){
                    System.out.println(info);
                    found=true;
                }
                if (!found){
                    System.out.println("transaction not found");
                }

            }
            goBackToHomePage(userTransaction,scanner);
        }
        public static void previousYear(ArrayList< userInfo> userTransaction ,Scanner scanner){
        Year lastYear=Year.now().minusYears(1);
        boolean found = false;
            for (int i =userTransaction.size()-1;i>=0;i--) {
                userInfo info = userTransaction.get(i);
                Year transactionYear=Year.from(info.getDate());
                if(transactionYear.equals(lastYear)){
                    System.out.println(info);
                    found=true;
                }
            }
            if (!found){
                System.out.println("no transaction found");
            }
            goBackToHomePage(userTransaction,scanner);
        }
        public static void searchByVendor(ArrayList<userInfo>userTransaction, Scanner scanner){
            System.out.print("---please enter the vendor name--- ");
            String vendorName=scanner.nextLine();
            boolean found = false;
            for (int i =userTransaction.size()-1;i>=0;i--){
                userInfo info = userTransaction.get(i);
                if (info.getVendor().equalsIgnoreCase(vendorName)){
                    System.out.println(info);
                    found=true;
                }
            }
            if(!found){
                System.out.println(" no transaction found");
            }
            goBackToHomePage(userTransaction,scanner);
        }
        public static void backToLedgerPage(ArrayList<userInfo>userTransaction,Scanner scanner){
            System.out.println(" you are going back to ledger page");
            System.out.println("enter (l) to go back to ledger ");
            String userChoiceOfLetter=scanner.nextLine().trim();
            ledger(userTransaction,scanner);
        }
        public static void customSearch(ArrayList<userInfo>userTransaction,Scanner scanner){

            System.out.println();
            System.out.println("---welcome to the custom search---");
            System.out.println("S, custom search by start date");
            System.out.println("E, custom search by end date");
            System.out.println("D, custom search by description");
            System.out.println("V, custom search by vendor");
            System.out.println("A, custom search by amount");
            System.out.print("please choose an option");
            String userChoiceOfLetter=scanner.nextLine();

                if (userChoiceOfLetter.equalsIgnoreCase("s")){
                  customByStartDate(userTransaction,scanner) ;
                  goBackToHomePage(userTransaction,scanner);
                }
                else if (userChoiceOfLetter.equalsIgnoreCase("e")){
                    customByEndDate(userTransaction,scanner);
                    goBackToHomePage(userTransaction,scanner);
                }
                else if (userChoiceOfLetter.equalsIgnoreCase("d")){
                    customByDescription( userTransaction,scanner);
                    goBackToHomePage(userTransaction,scanner);
                }
                else if (userChoiceOfLetter.equalsIgnoreCase("v")){
                    customByVendor(userTransaction,scanner);
                    goBackToHomePage(userTransaction,scanner);
                }
                else if  (userChoiceOfLetter.equalsIgnoreCase("a")){
                    customByAmount(userTransaction,scanner);
                    goBackToHomePage(userTransaction,scanner);
                }
                else{
                    System.out.println(" please enter letter either s,e,d,v,a");
                    goBackToHomePage(userTransaction,scanner);
                }
        }
        public static void  customByStartDate(ArrayList<userInfo> usertransaction,Scanner scanner){
            //System.out.println("--- custom by start date show transactions after given date---");
            System.out.print("please enter the date(yyyy-MM-dd)");
            LocalDate startDate=LocalDate.parse(scanner.nextLine().trim());
        for (userInfo info  : usertransaction ){
            if (!info.getDate().isBefore(startDate)){
                System.out.println(info);
            }
        }
        }
        public static void customByEndDate(ArrayList<userInfo>userTransaction,Scanner scanner){
            System.out.println();
            System.out.println("--custom search  by end date(yyyy-MM-dd");
            LocalDate endDate=LocalDate.parse(scanner.nextLine().trim());
            for (userInfo info :userTransaction){
                if ((!info.getDate().isAfter(endDate))){
                    System.out.println(info);
                }
            }
        }
        public static void customByDescription(ArrayList<userInfo> userTransaction , Scanner scanner){
            System.out.print("---please enter the description name--- ");
            String description = scanner.nextLine();
            boolean found = false;
            for (int i = userTransaction.size() - 1; i >= 0; i--) {
                userInfo info = userTransaction.get(i);
                if (info.getDescriptionOfPayment().equalsIgnoreCase(description)) {
                    System.out.println(info);
                    found = true;
                }
            }
            if (!found) {
                System.out.println(" no transaction found");
            }
        }
        public static void customByVendor(ArrayList<userInfo>userTransaction,Scanner scanner) {
            System.out.print("---please enter the vendor name--- ");
            String vendorName = scanner.nextLine();
            boolean found = false;
            for (int i = userTransaction.size() - 1; i >= 0; i--) {
                userInfo info = userTransaction.get(i);
                if (info.getVendor().equalsIgnoreCase(vendorName)) {
                    System.out.println(info);
                    found = true;
                }
            }
            if (!found) {
                System.out.println(" no transaction found");
            }
        }
        public static void customByAmount(ArrayList<userInfo>userTransaction,Scanner scanner){
            System.out.print("---please enter the amount--- ");
            double amount = Double.parseDouble(scanner.nextLine().trim());
            boolean found = false;
            for (int i = userTransaction.size() - 1; i >= 0; i--) {
                userInfo info = userTransaction.get(i);
                if (info.getAmount()==amount) {
                    System.out.println(info);
                    found = true;
                }
            }
            if (!found) {
                System.out.println(" no transaction found");
            }
        }
public static void goBackToHomePage(ArrayList<userInfo> userTransaction,Scanner scanner){
    System.out.println(" press h to go back home screen");
    String choice=scanner.nextLine().trim();
    if (choice.equalsIgnoreCase("h")){
        homeScreen(userTransaction,scanner);
    }
    else {
        System.out.println(" please enter h to back to home screen");
    }
}
public static void askUserToContinue(ArrayList<userInfo> userTransaction ,Scanner scanner) {
    System.out.println(" hello , press(d) for deposit,(p) for payment," +
            "(l) ledger to ledger screen");
    System.out.println("press (x) to exit from the program");
    System.out.print("please enter the option");
    String userChoiceOfLetter = scanner.nextLine();
    if ((userChoiceOfLetter.equalsIgnoreCase("d"))) {
        System.out.println("-- addDeposit--");
        addDeposit(userTransaction, scanner);
    } else if (userChoiceOfLetter.equalsIgnoreCase("p")){
        System.out.println("--make payment--");
      makePayment(userTransaction, scanner);
}
     else if(userChoiceOfLetter.equalsIgnoreCase("x")){
        System.out.println("you are exising from the program");
        System.exit(0);
    }
     else{
        System.out.println(" please enter the correct option ");
    }
}
    }