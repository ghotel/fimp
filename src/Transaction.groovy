import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
/**
 * Created with IntelliJ IDEA.
 * User: Karol
 * Date: 04.12.12
 * Time: 11:31
 */
class Transaction {
    String description
    Date date
    BigDecimal amount

    Transaction(String description, Date date, BigDecimal amount) {
        this.description = description
        this.date = date
        this.amount = amount
    }

    String getDate() {
        return date.format("yyyy-MM-dd")
    }

    String getAmount() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator((char) ',');

        DecimalFormat formatter = new DecimalFormat("0.00", symbols);


        return formatter.format(amount)
    }
}
