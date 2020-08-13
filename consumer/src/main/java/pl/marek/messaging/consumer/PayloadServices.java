package pl.marek.messaging.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.marek.messaging.consumer.model.domain.Client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class PayloadServices {

    private final PayloadRepository repository;

    @Autowired
    PayloadServices(PayloadRepository repository) {
        this.repository = repository;
    }

    public Map<String, Object> process(Map<String, Object> jsonClients) {
        Map<String, Object> jsonObjClients = (Map<String, Object>)jsonClients.get("clients");
        Map<String, Object> jsonObjClient = (Map<String, Object>)jsonObjClients.get("client");

        Map<String, Object> clientInfo = (Map<String, Object>)jsonObjClient.get("info");
        Map<String, Object> clientBalance = (Map<String, Object>)jsonObjClient.get("balance");
        Map<String, Object> clientTransactions = (Map<String, Object>)jsonObjClient.get("transactions");
        Client client = new Client(clientInfo, clientBalance, clientTransactions);
        repository.save(client);
        List<Client> response = computeResponse(clientInfo);
        return clientBalance;
    }

    private List<Client> computeResponse(Map<String, Object> client) {
        Map<String, Object> info = (Map<String, Object>)client.get("info");
        String name = (String)info.get("name");
        String surname = (String)info.get("surname");
        return repository.findByClientInfoNameAndSurname(name, surname);
//                .orElseThrow(() ->
//                new NoSuchElementException("client("
//                        + name + " " + surname));
    }
}
