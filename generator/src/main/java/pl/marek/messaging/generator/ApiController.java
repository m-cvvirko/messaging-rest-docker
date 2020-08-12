package pl.marek.messaging.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    private PayloadServices payloadServices;

    @Autowired
    public ApiController(PayloadServices payloadServices){
        super();
        this.payloadServices = payloadServices;
    }

    @GetMapping("/generator-payloads")
    @ResponseBody
    public String getClientPayloads(){
        return this.payloadServices.getClientPayloadsTemplate();
    }
}
