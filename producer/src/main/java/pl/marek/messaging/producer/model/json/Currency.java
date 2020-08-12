package pl.marek.messaging.producer.model.json;

public enum Currency {
    PLN, UNKNOWN;

    public static Currency parse(String str) {
        Currency currency;
        try {
            currency = Currency.valueOf(str);
        } catch (IllegalArgumentException e) {
            currency = Currency.UNKNOWN;
        }
        return currency;
    }
}
