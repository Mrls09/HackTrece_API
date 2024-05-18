package mx.edu.utez.hacktrece_api.controller.Billing;

import mx.edu.utez.hacktrece_api.model.Billing.Billing;
import mx.edu.utez.hacktrece_api.services.Billing.BillingServices;
import mx.edu.utez.hacktrece_api.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${apiPrefix}/billing")
@CrossOrigin(value = {"*"})
public class BillingController {
    @Autowired
    private BillingServices services;

    @GetMapping("/currently/{uid}")
    public ResponseEntity<Response<Billing>> generateBillingCurrently(@PathVariable("uid") String uid){
        return new ResponseEntity<>(
                this.services.generateCurrency(uid),
                HttpStatus.OK
        );
    }
    @GetMapping("/annual/{uid}")
    public ResponseEntity<Response<List<Billing>>> generateBillingAnnual(@PathVariable("uid") String uid){
        return new ResponseEntity<>(
                this.services.generateAboutYear(uid),
                HttpStatus.OK
        );
    }
}
