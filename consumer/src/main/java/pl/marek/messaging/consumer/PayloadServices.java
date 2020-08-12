package pl.marek.messaging.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.marek.messaging.consumer.model.json.Clients;

import java.util.Map;

@Service
public class PayloadServices {

//    private final PayloadRepository repository;

//    @Autowired
//    PayloadServices(PayloadRepository repository) {
//        this.repository = repository;
//    }

    public String process(Map<String, Object> clients) {
        //repository.save(clients);
        return computeResponse(clients);
    }

    private String computeResponse(Map<String, Object> clients) {
        //TODO
        return clients.toString();
    }
}
