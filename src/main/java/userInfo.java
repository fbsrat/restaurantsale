import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;

public class userInfo {
    private LocalTime time;
    private LocalDate date;
    private String descriptionOfPayment;
    private String vendor;
    private double amount;

    public userInfo( LocalDate date, LocalTime time, String descriptionOfPayment, String vendor, double amount) {
        this.time = time;
        this.date = date;
        this.descriptionOfPayment = descriptionOfPayment;
        this.amount = amount;
        this.vendor = vendor;
    }

    public LocalTime getTime() {
        return time;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescriptionOfPayment() {
        return descriptionOfPayment;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }

    public String toString() {
        return String.format(Locale.US, "%s %s $%s %s %.2f",date, time, descriptionOfPayment, vendor, amount);
    }
}