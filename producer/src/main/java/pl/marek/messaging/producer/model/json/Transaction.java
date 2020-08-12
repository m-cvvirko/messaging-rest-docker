package pl.marek.messaging.producer.model.json;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {

    private static final Logger LOGGER = LoggerFactory.getLogger(Transaction.class);

    TransactionType type;
    String description;
    Date date;
    BigDecimal value;
    Currency currency;

    private Transaction() { }

    public Transaction(TransactionType type, String description, Date date, BigDecimal value, Currency currency) {
        this.type = type;
        this.description = description;
        this.date = date;
        this.value = value;
        this.currency = currency;
    }

    public static Transaction build(JSONObject jtransaction) {
        String jtype = (String) jtransaction.get("type");
        String jdescription = (String) jtransaction.get("description");
        String jdate = (String) jtransaction.get("date");
        String jvalue = (String) jtransaction.get("value");
        String jcurrency = (String) jtransaction.get("currency");

        Transaction transaction = null;
        try{
            transaction = new Transaction(
                    TransactionType.parse(jtype),
                    jdescription,
                    new SimpleDateFormat("dd.MM.yyyy").parse(jdate),
                    new BigDecimal(jvalue),
                    Currency.parse(jcurrency));
        } catch(Exception e) {
            LOGGER.warn(e.getMessage());
            try {
                transaction = new Transaction(
                        TransactionType.parse(jtype),
                        jdescription,
                        new SimpleDateFormat("dd.MM.yyyy").parse("00.00.0000"),
                        new BigDecimal(0),
                        Currency.parse(jcurrency));
            } catch (ParseException ex) {
                LOGGER.warn(ex.getMessage());
            }
        }
        return transaction;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
