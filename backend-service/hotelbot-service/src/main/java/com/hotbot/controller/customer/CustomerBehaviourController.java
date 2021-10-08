package com.hotbot.controller.customer;

import com.hotbot.exception.CureFullException;
import com.hotbot.model.commonModel.NotificationModel;
import com.hotbot.model.customerapplication.HotelMenu;
import com.hotbot.model.customerapplication.OrderDetails;
import com.hotbot.service.customer.CustomerBehaviourService;
import com.hotbot.service.customer.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/api/hoboai/customer-behaviour")
public class CustomerBehaviourController {

    final static Logger LOGGER = LoggerFactory.getLogger(CustomerBehaviourController.class);

    @Autowired
    public CustomerBehaviourService customerBehaviourService;

    @GetMapping(path = "/get-customer-behaviour/{number}")
    public ResponseEntity<CustomerBehaviour> getRestaurantMenu(@PathVariable("number") String token) {
        CustomerBehaviour customerBehaviour = customerBehaviourService.getCustomerServiceBehaviour(token);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(customerBehaviour);
    }


}
