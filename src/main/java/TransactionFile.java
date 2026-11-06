import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class TransactionFile {


    public  static ArrayList<UserInfo> loadTransaction(String fileName ) {
        // create empty list to store transaction loaded from the file
        ArrayList<UserInfo> userTransaction = new ArrayList<>();
        //ArrayList<userInfo>userTransaction = loadTransaction(String fileName);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M-d-yyyy");
        // define the date format used in the transaction file
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("h:mm:ss a", Locale.US);
// define the tome format used in transaction file
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            // read the file and automatically close it when it finished
            reader.readLine(); // skip the header line
            String line;
            // variable to store each line read from the file

            while ((line = reader.readLine()) != null) {
                //read the file one line at a time until the end
                try {
                    //try to convert each line in to transaction
                    String[] parts = line.split("\\|");
                    // split the lines into parts using | as  separator
                    LocalDate date = LocalDate.parse(parts[0].trim().toUpperCase(), dateFormat);
                    // convert the first part in to a local date
                    LocalTime time = LocalTime.parse(parts[1].trim().toUpperCase(), timeFormat);
                    // convert the second part in to local time
                    String descriptionOfPayment = parts[2].trim();
                    // get the transaction description and remove extra spaces
                    String vendor = parts[3].trim();
                    // get the vendor name and remove extra spaces
                    double amount = Double.parseDouble(parts[4].trim());
                    //  convert the the string in to double
                    userTransaction.add(new UserInfo(date, time, descriptionOfPayment, vendor, amount));
                    //create a new userinfo object and add it to the list
                }
                catch (Exception e) {
                    System.out.println("skipped line ");
                    // skips a transaction if its data can not be converted
                }
            }
        }
        catch(IOException e){
            System.out.println("Could not load " + fileName + ": " + e.getMessage());
        }
        return userTransaction;
        // rerurn all successful loaded transaction
    }

}
