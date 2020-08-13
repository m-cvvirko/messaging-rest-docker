package pl.marek.messaging.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.marek.messaging.consumer.model.domain.Client;

import java.util.*;

@Service
public class PayloadServices {

    private final PayloadRepository repository;

    @Autowired
    PayloadServices(PayloadRepository repository) {
        this.repository = repository;
    }

    public Map<String, Object> process(Map<String, Object> jsonClients) {
        Map<String, Object> jsonObjClients = (Map<String, Object>)jsonClients.get("clients");
        List<Object> jsonObjClientList = (List<Object>)jsonObjClients.get("client");
        List<Map<String, Object>> clientsInRequest = new ArrayList<>();
        for(Object objClient: jsonObjClientList) {
            Map<String, Object> jsonObjClient = (Map<String, Object>)objClient;
            Map<String, Object> clientInfo = (Map<String, Object>)jsonObjClient.get("info");
            Map<String, Object> clientBalance = (Map<String, Object>)jsonObjClient.get("balance");
            List<Object> clientTransactions = (List<Object>)jsonObjClient.get("transactions");
            Client client = new Client(clientInfo, clientBalance, clientTransactions);
            repository.save(client);
            clientsInRequest.add(clientInfo);
        }
        List<Map<String, Object>> response = computeResponse(clientsInRequest);
        return jsonClients;
    }

    private List<Map<String, Object>> computeResponse(List<Map<String, Object>> clients) {
        for(Map<String, Object> client: clients) {
            String name = (String)client.get("name");
            String surname = (String)client.get("surname");
            repository.findBalanceByClientInfoNameAndSurnameOfToday(name, surname);
            repository.findTurnoverOnAccountsByClientInfoNameAndSurnameOfToday(name, surname);
            repository.findIncomesByClientInfoNameAndSurnameOfToday(name, surname);
            repository.findExpendituresByClientInfoNameAndSurnameOfToday(name, surname);
        }
        return clients;
    }
}
