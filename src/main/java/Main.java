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
        ArrayList<UserInfo> userTransaction = loadTransaction(
                "src/main/java/transaction.csv");
        //load all the transaction from the file
        //each line in the file becomes one userinfo object
        Scanner scanner = new Scanner(System.in);
        //scanner lets us read what a user type from keyword
                homePage(userTransaction,scanner);
                // show home page first this is basically calling a method
                ledger(userTransaction, scanner);
                // show ledge screen

   }
    public static void homePage(ArrayList<UserInfo> userTransaction,Scanner scanner){
        System.out.println("----------welcome to the Abyssinia transactions application----------");
        //prints welcome banner
        System.out.println();
        // print empty line
        System.out.println(//prints menu options
                "press(h) to navigate home screen\n" +
                        "press(l) to navigate ledger\n" +
                        " press(m) to navigate to the help menu\n"+
                        "press (x) to exit the application\n");
        System.out.println("H,Home Screen");
        System.out.println("L,Ledger");
        System.out.println("M, helpMenu");

        System.out.print(" choose the letter option ");//print to keep the cursor on the same line
        boolean running=true;
        while (running) {
            String userChoiceOfLetter = scanner.nextLine();//read the whole line the user has types
          //  and  store it
            if (userChoiceOfLetter.equalsIgnoreCase("h"))//equalsignore the case means "h" and "H" are the same
            {
                homeScreen(userTransaction, scanner);
            } else if (userChoiceOfLetter.equalsIgnoreCase("l"))
            {
                ledger(userTransaction, scanner);
            }
             else if (userChoiceOfLetter.equalsIgnoreCase("x")) {
                System.exit(0);

            } else if(userChoiceOfLetter.equalsIgnoreCase("m")){
                 helpMenu();
            }
             else {
                System.out.println("please enter the correct letter either h , l ,r ");
                //tells the user to enter the correct option
            }

        }


    }

    public static void homeScreen( ArrayList <UserInfo> userTransaction,Scanner scanner)
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
                    addDeposit(userTransaction,scanner);
                    break;
                case "p":
                    //if the user picks p it will take it to the makePayment option
                    makePayment(userTransaction,scanner);
                    break;
                case "l":
                    ledger(userTransaction,scanner);
                    break;
                case "x":
                    exitTheApplication(userTransaction , scanner);
                    break;
                default:
                    System.out.println("please enter the correct letter");
            }
        }
       askTheUserWhereToGo(userTransaction,scanner);
}
public static void addDeposit( ArrayList<UserInfo> userTransaction, Scanner scanner) {
    LocalDate date = LocalDate.now();
    LocalTime time = LocalTime.now();
    String date1 = date.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
    String time1 = time.format(DateTimeFormatter.ofPattern("hh:mm:ss a"));
    System.out.println("--welcome to add deposit--");
    System.out.println("date and time for the transaction are automatically saved");
    String descriptionOfPayment = "";
    String vendor = "";
    double amount =0;
    while (true){
        System.out.println(" please enter the description");
        descriptionOfPayment = scanner.nextLine().trim();
        if (descriptionOfPayment.isEmpty()){
            System.out.println(" description can not be empty,please enter the description");
            continue;
        }
        System.out.println("please enter the vendor name ");
        vendor = scanner.nextLine().trim();
        if (vendor.isEmpty()){
            System.out.println("vendor can not be empty,please enter the vendor  name ");

        while (true) {
            System.out.println("please enter the amount");
            String input = scanner.nextLine().trim();
            try {
                amount = Double.parseDouble(input);
                if (amount > 0) {
                    break;
                }
                System.out.println("amount must be greater  than 0 ");
            } catch (NumberFormatException e) {
                System.out.println(" please enter number , like 30.34");
            }
        }
        }
        System.out.println("description:" + descriptionOfPayment);
        System.out.println("vendor name:" + vendor);
        System.out.println("amount:" + amount);
        System.out.println(" is this correct, please enter (y) to confirm or" +
                "(n) to re enter again ");
        String answer = scanner.nextLine().trim();
        if (answer.equalsIgnoreCase("y")) {
            break;
        } else {
            System.out.println(" its okay, re enter again");
        }
    }
    UserInfo deposit = new UserInfo(date, time, descriptionOfPayment, vendor, amount);
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/transaction.csv", true))) {
        writer.write(date1 + "|" + time1 + "|" + descriptionOfPayment + "|" + vendor + "|" + amount);
        writer.newLine();
        userTransaction.add(deposit);
        System.out.println(" your file is saved successfully");
    } catch (IOException e) {
        System.out.println("error:" + e.getMessage());
    }
    askTheUserWhereToGo(userTransaction, scanner);
}

    public static void makePayment(ArrayList<UserInfo>userTransaction,Scanner scanner) {

    LocalDate date = LocalDate.now();
    LocalTime time = LocalTime.now();
    String date1 = date.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
    String time1 = time.format(DateTimeFormatter.ofPattern("hh:mm:ss a"));
    System.out.println("--welcome to make payment--");
    System.out.println();
    System.out.println("date and time for the transaction are automatically saved");
    String descriptionOfPayment = "";
    String vendor = "";
    double amount = 0;
    while (true) {
        System.out.println(" please enter the description ");
        descriptionOfPayment = scanner.nextLine().trim();
        System.out.println("please enter the vendor name ");
        vendor = scanner.nextLine().trim();
        while (true) {
            System.out.println("please enter the amount");
            String input = scanner.nextLine().trim();
            try {
                amount = Double.parseDouble(input);
                if (amount > 0) {
                    break;
                }
                System.out.println("amount must be greater  than 0 ");
            } catch (NumberFormatException e) {
                System.out.println(" please enter number , like 30.34");
            }
        }
        System.out.println("description:" + descriptionOfPayment);
        System.out.println("vendor name:" + vendor);
        System.out.println("amount:" + amount);
        System.out.println(" is this correct, please enter (y) to confirm or" +
                "(n) to re enter again ");
        String answer = scanner.nextLine().trim();
        if (answer.equalsIgnoreCase("y")) {
            break;
        } else {
            System.out.println(" its okay, re enter again");
        }
    }
    amount = -amount;
    UserInfo deposit = new UserInfo(date, time, descriptionOfPayment, vendor, amount);

    try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/transaction.csv", true))) {
        writer.write(date1 + "|" + time1 + "|" + descriptionOfPayment + "|" + vendor + "|" + amount);
        writer.newLine();
        userTransaction.add(deposit);
        System.out.println(" your payment is saved successfully");
    } catch (IOException e) {
        System.out.println("error:" + e.getMessage());
    }
        askTheUserWhereToGo(userTransaction,scanner);
    }
public static void ledger( ArrayList<UserInfo> userTransaction,Scanner scanner){
    while (true){
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
            System.out.println(" please enter either a , d , p , r,b");

        }

        askTheUserWhereToGo(userTransaction,scanner);

    }

}
public static void exitTheApplication(ArrayList<UserInfo>useraTranaction , Scanner scanner){
    System.out.println("Thank you for using the application");
    System.exit(0);

}
public  static ArrayList<UserInfo> loadTransaction(String fileName ) {
    ArrayList<UserInfo> userTransaction = new ArrayList<>();
    //ArrayList<userInfo>userTransaction = loadTransaction(String fileName);
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M-d-yyyy");
    DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("h:mm:ss a", Locale.US);

    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
        reader.readLine(); // skip the header line
        String line;

        while ((line = reader.readLine()) != null) {
            try {
                String[] parts = line.split("\\|");
                LocalDate date = LocalDate.parse(parts[0].trim().toUpperCase(), dateFormat);
                LocalTime time = LocalTime.parse(parts[1].trim().toUpperCase(), timeFormat);
                String descriptionOfPayment = parts[2].trim();
                String vendor = parts[3].trim();
                double amount = Double.parseDouble(parts[4].trim());
                userTransaction.add(new UserInfo(date, time, descriptionOfPayment, vendor, amount));
            } catch (Exception e) {
                System.out.println(" skipping bad line" + line);
            }
        }
    }
    catch(IOException e){
            System.out.println("Could not load " + fileName + ": " + e.getMessage());
      }
    return userTransaction;
}
public static void showAllEntries(ArrayList<UserInfo>userTransaction, Scanner scanner){
        for(int i=userTransaction.size()-1;i>=0;i--) {
            System.out.println(userTransaction.get(i));

        }
    askTheUserWhereToGo(userTransaction,scanner);
}
public  static void displayAllTheDeposits(ArrayList<UserInfo> userTransaction, Scanner scanner){
        for (int i =userTransaction.size()-1;i>=0;i--){
         UserInfo info=userTransaction.get(i);
         if(info.getAmount()>0) {
             System.out.println(info);
            }
        }
    askTheUserWhereToGo(userTransaction,scanner);
}
public static void showAllThePayments(ArrayList<UserInfo>userTransaction ,Scanner scanner) {
    for (int i = userTransaction.size() - 1; i >= 0; i--) {
        UserInfo info = userTransaction.get(i);
        if (info.getAmount() < 0) {
            System.out.println(info);

        }
    }
    askTheUserWhereToGo(userTransaction,scanner);
}
        public static void navigateToReports (ArrayList<UserInfo> userTransaction, Scanner scanner) {
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
            //System.out.print("please choose an option ");
            while (true) {
                String usersChoiceOfChoice = scanner.nextLine();
                try{
                int userChoice = Integer.parseInt(usersChoiceOfChoice);
                }
                catch (NumberFormatException e){
                    System.out.println("please enter numbers 1 to 6");
                    continue;
                }
                if (usersChoiceOfChoice.equals("1")) {
                    monthToDate(userTransaction, scanner);
                } else if (usersChoiceOfChoice.equals("2")) {
                    previousMonth(userTransaction, scanner);
                } else if (usersChoiceOfChoice.equals("3")) {
                    yearToDate(userTransaction, scanner);
                } else if (usersChoiceOfChoice.equals("4")) {
                    previousYear(userTransaction, scanner);
                } else if (usersChoiceOfChoice.equals("5")) {
                    searchByVendor(userTransaction, scanner);
                } else if (usersChoiceOfChoice.equals("6")) {
                    customSearch(userTransaction, scanner);
                }
                else if(usersChoiceOfChoice.equals("0")){
                    ledger(userTransaction,scanner);
                }
                 else {
                    System.out.println("please enter numbers 0 to 6");
                }
        }
    }
    //show all the transaction from the first of the month up to date the user enters
        public static void monthToDate(ArrayList<UserInfo>userTransaction,Scanner scanner) {
        //create a variable
            LocalDate endDate;
            // keep asking until the user enters the valid date
            while (true) {
                // asks user for a date
                System.out.println(" please enter a date(yyyy-MM-dd)");
                // reads what the user typed and remove extra spaces
                String input = scanner.nextLine().trim();
                try {
                    // this line is to change the line "date  "to real date
                    endDate = LocalDate.parse(input);
                    // the date is valid so leave the loop
                    break;

                } catch (DateTimeException e) {
//if we entered a wrong format , ask the user to enter in the correct format
                    System.out.println(" wrong format please enter in (yyyy-MM-dd) format");
                }
            }
            //get the first day oy of the month if the user enters (2026-11-04 -2026-11-01)
            // withDayOfMonth () is a method that gives copy of that date with a day set to one
            LocalDate firstDaYOfMonth = endDate.withDayOfMonth(1);
            // nothing found yet
            boolean found = false;
            //go through transaction fron newest to oldest
                for (int i = userTransaction.size() - 1; i >= 0; i--) {
                    //get that transaction at position i
                    UserInfo info = userTransaction.get(i);
                    // get that transactions date
                    LocalDate date = info.getDate();
                    // check if the date is between the first day of the month and users date
                    if (!date.isBefore(firstDaYOfMonth) && !date.isAfter(endDate)) {
                        //print if it is in range
                        System.out.println(info);
                        // to show user find transaction
                        found = true;
                    }
                }     // after checkinh if nothing happens , tell the user transaction not found
                    if (!found) {
                        System.out.println(" no transaction found");
                    }
            askTheUserWhereToGo(userTransaction,scanner);
        }
        // ask the user  for month , the show all transactions before it
        public static void previousMonth(ArrayList<UserInfo>userTransaction, Scanner scanner ) {
            // create a variable outside of loop
            YearMonth enteredMonth;
            // keep asking until thr user enters a valid month with the format
            while (true) {
                // asking for a month
                System.out.println("please enter the month in (yyyy-MM) format like 2024-11");
                // read what the user types and remove extra spaces
                String input = scanner.nextLine().trim();
                try {
                    // to turn dat" " in to real date
                    enteredMonth = YearMonth.parse(input);
                    // the month is valid so leave the loop
                    break;
                } catch (DateTimeParseException e) {
                    // tells the user the input was not the right format,tell the user and the loop asks again
                    System.out.println("please enter  in the correct format like (2024-11)");
                }
            }    // go back one month from what the user entered, user entered (2026-11 change 2026 -10)
            // Year month is object
            //minusMonth () go back  one month
            YearMonth previous = enteredMonth.minusMonths(1);
            //to find the first day of the  previous month
            // after we get the previous month it will make it day one
            LocalDate firstDay = previous.atDay(1);
            //to find the last day of the previous month
           // atEndOMonth () always give us last day of the month ,any month it can be 28,30,31
            LocalDate lastDay = previous.atEndOfMonth();

            boolean found = false;//adding boolean
            // go through the transaction from newest to oldest
            for (int i = userTransaction.size() - 1; i >= 0; i--) {
                // get the transaction at position i
                UserInfo info = userTransaction.get(i);
                //get that transaction date
                LocalDate date = info.getDate();
                //check if the date is between the first and last day of the previous month
                if (!date.isBefore(firstDay) && !date.isAfter(lastDay)) {
                    // we found one
                    found = true;
                    System.out.println(info);
                }
            }
            if ((!found)) {
                // shows that no transaction found
                System.out.println(" no transaction found");
            }
            askTheUserWhereToGo(userTransaction,scanner);
        }
//asks the user to enter the year and show all transactions that happened after january 1st
        public static void yearToDate(ArrayList<UserInfo> userTransaction , Scanner scanner) {
            // create a variable
            Year enteredYear;
            // keep asking the user to enter valid year
            while (true) {
                // asks the user to enter the year
                System.out.println("please enter the year int (yyyy) format ,2026");
                // reads the input and remove extra  spaces
                String input = scanner.nextLine().trim();
                try {
                    // turn the text like string to year
                    enteredYear = Year.parse(input);
                    //the year is valid so leave the loop
                    break;
                } catch (DateTimeParseException e) {
                    //the  text was not valid year so enter the correct year
                    System.out.println(" please enter the year in the correct format, (yyyy)");
                }
            }
                // find the first day of the year
                LocalDate firstDayOfTheTear = enteredYear.atDay(1);
                // get todays date
                LocalDate today = LocalDate.now();
                // nothing found yet
                boolean found = false;
// go through transactions from newest to oldest
                for (int i = userTransaction.size() - 1; i >= 0; i--) {
                    // get the transaction at posotion i
                    UserInfo info = userTransaction.get(i);
                    // get the transactions date
                    LocalDate date = info.getDate();
                    //check if the day is  between january and today
                    if ((!date.isBefore(firstDayOfTheTear) && !date.isAfter(today))) {
                        System.out.println(info);
                        found = true;

                    }
                }
                    if (!found) {
                        System.out.println("transaction not found");

                }
            askTheUserWhereToGo(userTransaction,scanner);
        }
// asks the user for a year , then shows all transactions a year before
        public static void previousYear(ArrayList< UserInfo> userTransaction ,Scanner scanner){
        // create a variable
        Year enteredYear;
        while(true){
            // keep asking until the user types valid year format
            System.out.println(" please enter the year in format (yyyy)");
            // reads the input and removes extra spaces
            String input=scanner.nextLine().trim();
            try{
               enteredYear=Year.parse(input) ;
                // change the input in to real year
                break;
                // the year is valid , so leave the while loop
            }
            catch (DateTimeParseException e){
                // asks to enter the valid format
                System.out.println("please enter the valid year format(yyyy)");
            }
        }
        // go back one year from the user entered
        Year lastYear=enteredYear.minusYears(1);
        // nothing found yet
        boolean found = false;
        // go true all transactions from newest to oldest
            for (int i =userTransaction.size()-1;i>=0;i--) {
                // get the transaction at position i
                UserInfo info = userTransaction.get(i);
                // get the  year of this transaction
                Year transactionYear=Year.from(info.getDate());
                // checks if the transaction happened in previous year
                if(transactionYear.equals(lastYear)){
                    System.out.println(info);
                    // print the info
                    found=true;
                }
            }
            if (!found){
                // shows no tranasction happened
                System.out.println("no transaction found");
            }
            askTheUserWhereToGo(userTransaction,scanner);
        }
        // asks the user vendor name and shows all tranaction from that vendor
        public static void searchByVendor(ArrayList<UserInfo>userTransaction, Scanner scanner) {
            String vendorName;
            // create a variable
            while (true) {
                //keep asking  the user to enter vendor name
                System.out.print("---please enter the vendor name--- ");
                // asks the user to enter the vendor name
                vendorName = scanner.nextLine().trim();
                // reads the vendor name and removes extra space
                if (!vendorName.isEmpty()) {
                    // if the user types something leave the loop
                    break;

                }
                System.out.println(" vendor name cannot be empty");
                //if user didnt type anything
            }
            boolean found = false;
            for (int i =userTransaction.size()-1;i>=0;i--){
                UserInfo info = userTransaction.get(i);
                if (info.getVendor().equalsIgnoreCase(vendorName)){
                    System.out.println(info);
                    found=true;
                }
            }
            if(!found){
                System.out.println(" no transaction found");
            }
            askTheUserWhereToGo(userTransaction,scanner);
        }

        public static void customSearch(ArrayList<UserInfo>userTransaction,Scanner scanner) {

            System.out.println();
            System.out.println("---welcome to the custom search---");
            System.out.println();
            System.out.println("---Instructions---");
            System.out.println("press(s) to custom search by date\n" +
                    "press(e) to custom search by end date\n" +
                    "press(D) to custom search by description\n" +
                    "press(v) to custom search by vendor name\n" +
                    "press (a) to custom search by amount\n");
            System.out.println("s, custom search by start date");
            System.out.println("s, custom search by end date");
            System.out.println("d, custom search by description");
            System.out.println("v, custom search by vendor");
            System.out.println("a, custom search by amount");
            System.out.print("please choose an option");
            String userChoiceOfLetter = scanner.nextLine();

            if (userChoiceOfLetter.equalsIgnoreCase("s")) {
                customByStartDate(userTransaction, scanner);

            } else if (userChoiceOfLetter.equalsIgnoreCase("e")) {
                customByEndDate(userTransaction, scanner);

            } else if (userChoiceOfLetter.equalsIgnoreCase("d")) {
                customByDescription(userTransaction, scanner);

            } else if (userChoiceOfLetter.equalsIgnoreCase("v")) {
                customByVendor(userTransaction, scanner);

            } else if (userChoiceOfLetter.equalsIgnoreCase("a")) {
                customByAmount(userTransaction, scanner);

            } else {
                System.out.println(" please enter letter either s,e,d,v,a");

            }
            askTheUserWhereToGo(userTransaction,scanner);
        }

    // asks the user for the start date and show all transactions happened  after that date2
        public static void  customByStartDate(ArrayList<UserInfo> userTransaction,Scanner scanner){
            System.out.println("--- custom by start date show transactions after given date---");
        LocalDate startDate;
        // create a variable
            while (true) {
                System.out.print("please enter the date(yyyy-MM-dd)");
                String input=scanner.nextLine().trim();
            // reads the input and removes extra space
                try {
                    startDate=LocalDate.parse(input);
                    // change the input in to thr read date
                    break;
                    //the date is valid
                }
                catch (DateTimeParseException e){
                    System.out.println(" please enter in the correct  format(yyyy-MM-dd) ");
                }

            }
        for (UserInfo info  : userTransaction ) {
            if (!info.getDate().isBefore(startDate)) {
                System.out.println(info);
            }
        }
            askTheUserWhereToGo(userTransaction,scanner);
        }
// the user for an end date , then shows all transactions on before that date
        public static void customByEndDate(ArrayList<UserInfo>userTransaction,Scanner scanner){
        LocalDate endDate;
        // create a variable
         while (true) {
             System.out.println("--custom search  by end date(yyyy-MM-dd");
             String  input  = scanner.nextLine().trim();
             // reads the input and removes extra  spaces
             try {
                 endDate=LocalDate.parse(input);
                 break;
             }
             catch (DateTimeParseException e){
                 System.out.println(" please enter the correct format (yyyy-MM-dd");
             }
         }
         boolean found= false;
            for (UserInfo info :userTransaction){
                if ((!info.getDate().isAfter(endDate))){
                    System.out.println(info);
                    found=true;
                }
            }
            if(!found){
                System.out.println(" no transaction found");
            }
            askTheUserWhereToGo(userTransaction,scanner);
        }
// asks a description and shows all the transcations by that description
        public static void customByDescription(ArrayList<UserInfo> userTransaction , Scanner scanner){
            System.out.print("---please enter the description name--- ");
            String description = scanner.nextLine();
            boolean found = false;
            for (int i = userTransaction.size() - 1; i >= 0; i--) {
                UserInfo info = userTransaction.get(i);
                if (info.getDescriptionOfPayment().equalsIgnoreCase(description)) {
                    System.out.println(info);
                    found = true;
                }
            }
            if (!found) {
                System.out.println(" no transaction found");
            }
            askTheUserWhereToGo(userTransaction,scanner);
        }
        public static void customByVendor(ArrayList<UserInfo>userTransaction,Scanner scanner) {
            System.out.print("---please enter the vendor name--- ");
            String vendorName = scanner.nextLine();
            boolean found = false;
            for (int i = userTransaction.size() - 1; i >= 0; i--) {
                UserInfo info = userTransaction.get(i);
                if (info.getVendor().equalsIgnoreCase(vendorName)) {
                    System.out.println(info);
                    found = true;
                }
            }
            if (!found) {
                System.out.println(" no transaction found");
            }
            askTheUserWhereToGo(userTransaction,scanner);
        }
        public static void customByAmount(ArrayList<UserInfo>userTransaction,Scanner scanner){
        double amount;
        // create a  variable
            while(true) {
                System.out.print("---please enter the amount--- ");
                 String input  = scanner.nextLine().trim();
                 try {
                     amount=Double.parseDouble(input);
                     break;
                 }
                 catch (NumberFormatException e)
                 {
                     System.out.println(" please enter number like 45.00");
                 }
            }

            boolean found = false;
            for (int i = userTransaction.size() - 1; i >= 0; i--) {
                UserInfo info = userTransaction.get(i);
                if (info.getAmount()==amount) {
                    System.out.println(info);
                    found = true;
                }
            }
            if (!found) {
                System.out.println(" no transaction found");
            }
            askTheUserWhereToGo(userTransaction,scanner);
        }

public static Void askTheUserWhereToGo(ArrayList<UserInfo> userTranaction,Scanner scanner) {
        while ((true)){
            System.out.println(" what would you like to do next?");
            System.out.println("press (l), to go back to ledger page");
            System.out.println("press (b), to go back to home page");
            System.out.println("press(x),to exit the app");
            String next=scanner.nextLine().trim();
            if(next.equalsIgnoreCase("l")){
                ledger(userTranaction,scanner);
            }
            else if(next.equalsIgnoreCase("b")){
                homePage(userTranaction,scanner);
            }
            else if (next.equalsIgnoreCase("x")){
                exitTheApplication(userTranaction,scanner);
            }
            else{
                System.out.println(" please, press (l) to go back to ledge page\n" +
                        "press (b) to go back to gome page \n" +
                        "press (x) to exist the application\n");
            }

        }

}
public static void helpMenu(){
    System.out.println("\n======Help Menu=====\n");
    System.out.println("\n ----- home page options-----\n");
    System.out.println("H,Home screen");
    System.out.println("L, ledger");
    System.out.println("M, help menu");
    System.out.println("X , exit the application");



    System.out.println("\n----Home Screen options----\n");
    System.out.println("D- Add Deposit");
    System.out.println("P- Make Payment(debit)");
    System.out.println("L-ledger");
    System.out.println("X- Exit the application");


    System.out.println("\n----Ledger Options----\n");
    System.out.println("A- Display All Transactions");
    System.out.println("D- Display All Deposits");
    System.out.println("P- Display All Payments");
    System.out.println("R- Reports");


    System.out.println("\n----Report Options----\n");
    System.out.println("1- Month To Date");
    System.out.println("2- Previous Month");
    System.out.println("3-Year To Date");
    System.out.println("4-Previous Year");
    System.out.println("5-Search By Vendor");
    System.out.println("6-Custom Search");
    System.out.println("0-to go back ledge page");


    System.out.println("\n----Custom Search Options----\n");
    System.out.println("S- Search By Start Date");
    System.out.println("E- Search By End Date");
    System.out.println("D-Search By Description");
    System.out.println("V- Search By Vendor");
    System.out.println("A- Search By Amount");


    System.out.println(" =====================================");






}
}
