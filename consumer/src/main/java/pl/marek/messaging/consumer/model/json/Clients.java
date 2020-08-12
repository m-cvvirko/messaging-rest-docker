package pl.marek.messaging.consumer.model.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Clients {

    private List<Client> client = new ArrayList<>();

    private Clients(){ }

    public Clients(List<Client> client){
        this.client = client;
    }

    public List<Client> getClient() {
        return client;
    }

    public void setClient(List<Client> client) {
        this.client = client;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Clients =");
        for(Client c: client) {
            sb.append(c);
        }
        return sb.toString();
    }

    public static Clients build(JSONObject jclients) {
        JSONArray jclient = (JSONArray) jclients.get("client");
        List<Client> client = new ArrayList<>();
        for(Object jc: jclient) {
            client.add( Client.build((JSONObject)jc));
        }
        Clients clients =  new Clients();
        clients.setClient(client);
        return clients;
    }

}
