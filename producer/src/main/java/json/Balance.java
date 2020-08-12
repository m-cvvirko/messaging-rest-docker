package json;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Balance {

    private static final Logger LOGGER = LoggerFactory.getLogger(Balance.class);

    BigDecimal total;
    Currency currency;
    Date date;

    private Balance() { }

    public Balance(BigDecimal total, Currency currency, Date date) {
        this.total = total;
        this.currency = currency;
        this.date = date;
    }

    public static Balance build(JSONObject jbalance) {
        String jtotal = (String) jbalance.get("total");
        String jcurrency = (String) jbalance.get("currency");
        String jdate = (String) jbalance.get("date");
        Balance balance = null;
        try{
            balance = new Balance(
                    new BigDecimal(jtotal),
                    Currency.parse(jcurrency),
                    new SimpleDateFormat("dd.MM.yyyy").parse(jdate));
        } catch(Exception e) {
            LOGGER.warn(e.getMessage());
            try {
                balance = new Balance(
                        BigDecimal.ZERO,
                        Currency.PLN,
                        new SimpleDateFormat("dd.MM.yyyy").parse("00.00.0000"));
            } catch (ParseException ex) {
                LOGGER.warn(ex.getMessage());
            }
        }
        return balance;
    }
}
