package pl.marek.messaging.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    private PayloadServices payloadServices;

    @Autowired
    public ApiController(PayloadServices payloadServices) {
        super();
        this.payloadServices = payloadServices;
    }

    @PostMapping(value = "/consumer", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> process(@RequestBody Map<String, Object> jsonClients) {
        Map<String, Object> response = payloadServices.process(jsonClients);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/consumer")
                .buildAndExpand()
                .toUri();

        return ResponseEntity.created(uri)
                .body(response);
    }
}
