import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class TransactionManager {


    public static void addDeposit(ArrayList<UserInfo> userTransaction, Scanner scanner) {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        String date1 = date.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
        String time1 = time.format(DateTimeFormatter.ofPattern("hh:mm:ss a"));
        System.out.println("--welcome to add deposit--");
        System.out.println("date and time for the transaction are automatically saved");
        String descriptionOfPayment = "";
        String vendor = "";
        double amount = 0;
        while (true) {
            System.out.println(" please enter the description");
            descriptionOfPayment = scanner.nextLine().trim();
            if (descriptionOfPayment.isEmpty()) {
                System.out.println(" description can not be empty,please enter the description");
                continue;
            }
            System.out.println("please enter the vendor name ");
            vendor = scanner.nextLine().trim();
            if (vendor.isEmpty()) {
                System.out.println("vendor can not be empty,please enter the vendor  name ");
            }
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
        HomePage.askTheUserWhereToGo(userTransaction, scanner);
    }

    public static void makePayment(ArrayList<UserInfo> userTransaction, Scanner scanner) {

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
        HomePage.askTheUserWhereToGo(userTransaction, scanner);

    }
}