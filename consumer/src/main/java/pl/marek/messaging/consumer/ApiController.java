package pl.marek.messaging.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.marek.messaging.consumer.model.json.Clients;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class ApiController {

    private PayloadServices payloadServices;

    @Autowired
    public ApiController(PayloadServices payloadServices) {
        super();
        this.payloadServices = payloadServices;
    }

    @PostMapping(path = "/consumer")
    @ResponseBody
    public ResponseEntity<Clients> processPayloads(@RequestBody Clients clients) {
        String response = payloadServices.process(clients);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/consumer-payloads")
                .buildAndExpand()
                .toUri();

        return ResponseEntity.created(uri)
                .body(clients);
    }
}
