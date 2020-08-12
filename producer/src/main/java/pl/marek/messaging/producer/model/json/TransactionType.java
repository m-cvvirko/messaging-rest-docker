package pl.marek.messaging.producer.model.json;

public enum TransactionType {
    INCOME("income"), OUTCOME("outcome"), UNKNOWN("unknown");

    private String name;

    TransactionType(String name) {
        this.name = name;
    }

    public String getName() { return this.name; }

    public static TransactionType parse(String str) {
        TransactionType type;
        try {
            type = TransactionType.valueOf(str.toUpperCase());
        } catch (IllegalArgumentException e) {
            type = TransactionType.UNKNOWN;
        }
        return type;
    }
}
