package pl.marek.messaging.consumer.model.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
import java.util.Objects;

@Document
public class Client {
    @Id
    private String id;

    @Indexed
    private Map<String, Object> info;

    private Map<String, Object> balance;

    @Indexed
    private Map<String, Object> transactions;


    public Client(Map<String, Object> info,
                  Map<String, Object> balance,
                  Map<String, Object> transactions) {
        this.info = info;
        this.balance = balance;
        this.transactions = transactions;
    }

    protected Client() {
    }

    public String getId() {
        return id;
    }

    public Map<String, Object> getInfo() {
        return info;
    }

    public Map<String, Object> getBalance() {
        return balance;
    }

    public Map<String, Object> getTransactions() {
        return transactions;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id='" + id + '\'' +
                ", info=" + info +
                ", balance=" + balance +
                ", transactions=" + transactions +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id.equals(client.id) &&
                info.equals(client.info) &&
                balance.equals(client.balance) &&
                transactions.equals(client.transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, info, balance, transactions);
    }
}
