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
                }
                catch (Exception e) {
                    System.out.println("skipped line ");
                }
            }
        }
        catch(IOException e){
            System.out.println("Could not load " + fileName + ": " + e.getMessage());
        }
        return userTransaction;
    }

}
