package pl.marek.messaging.consumer.model.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Client {

    Info info;
    Balance balance;
    List<Transaction> transactions;

    private Client() { }

    public static Client build(JSONObject jclient) {
        JSONObject jinfo = (JSONObject) jclient.get("info");
        JSONObject jbalance = (JSONObject) jclient.get("balance");
        JSONArray jtransactions = (JSONArray) jclient.get("transactions");
        Client client = new Client();
        client.setInfo( Info.build(jinfo));
        client.setBalance( Balance.build(jbalance));

        List<Transaction> transactions = new ArrayList<>();
        for(Object jtran : jtransactions) {
            transactions.add(Transaction.build((JSONObject) jtran));
        }
        client.setTransactions(transactions);
        return client;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public Balance getBalance() {
        return balance;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
