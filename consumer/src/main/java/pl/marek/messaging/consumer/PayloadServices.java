package pl.marek.messaging.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import pl.marek.messaging.consumer.model.json.Clients;

public class PayloadServices {

    private final PayloadRepository repository;

    @Autowired
    PayloadServices(PayloadRepository repository) {
        this.repository = repository;
    }

    public String process(Clients clients) {
        repository.save(clients);
        return computeResponse(clients);
    }

    private String computeResponse(Clients clients) {
        //TODO
        return clients.toString();
    }
}
