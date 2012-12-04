import java.text.DecimalFormatSymbols
import java.text.DecimalFormat
/**
 * Created with IntelliJ IDEA.
 * User: Karol
 * Date: 04.12.12
 * Time: 11:22
 * To change this template use File | Settings | File Templates.
 */
class TransactionsReader {


    static DecimalFormat formatter
    static {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator((char) ',');

        formatter = new DecimalFormat("0.00", symbols);
        formatter.setParseBigDecimal(true)
    }

    public static Collection<Transaction> readFile(String filePath){
        def transactions = []


        new File(filePath).eachLine {
            line -> String[] values = line.split(';')
            transactions.add( new Transaction(
                    values[1],
                    Date.parse("yyyy-M-d", values[0]),
                    formatter.parse(values[2]) ))
        }

        return transactions
    }

}
