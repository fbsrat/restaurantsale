import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Reports {

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
              Ledge.ledger(userTransaction,scanner);
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
    HomePage .askTheUserWhereToGo(userTransaction,scanner);
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
        HomePage.askTheUserWhereToGo(userTransaction,scanner);
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
        HomePage.askTheUserWhereToGo(userTransaction,scanner);
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
       HomePage. askTheUserWhereToGo(userTransaction,scanner);
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
        HomePage.askTheUserWhereToGo(userTransaction,scanner);
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
       HomePage. askTheUserWhereToGo(userTransaction,scanner);
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
        HomePage.askTheUserWhereToGo(userTransaction,scanner);
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
        HomePage.askTheUserWhereToGo(userTransaction,scanner);
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
        HomePage.askTheUserWhereToGo(userTransaction,scanner);
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
       HomePage. askTheUserWhereToGo(userTransaction,scanner);
    }
    public static void customByAmount(ArrayList<UserInfo>userTransaction,Scanner scanner) {
        double amount;
        // create a  variable
        while (true) {
            System.out.print("---please enter the amount--- ");
            String input = scanner.nextLine().trim();
            try {
                amount = Double.parseDouble(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println(" please enter number like 45.00");
            }
        }


        boolean found = false;

        for (int i = userTransaction.size() - 1; i >= 0; i--) {
            UserInfo info = userTransaction.get(i);
            if (info.getAmount() == amount) {
                System.out.println(info);
                found = true;
            }
        }
        if (!found) {
            System.out.println(" no transaction found");
        }
        HomePage.askTheUserWhereToGo(userTransaction, scanner);
    }
}