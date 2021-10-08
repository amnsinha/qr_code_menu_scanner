package com.hotbot.controller.customer;

import com.hotbot.exception.CureFullException;
import com.hotbot.model.commonModel.NotificationModel;
import com.hotbot.model.customerapplication.HotelMenu;
import com.hotbot.model.customerapplication.OrderDetails;
import com.hotbot.service.customer.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/api/hoboai/customer")
public class CustomerController {

    final static Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    public CustomerService customerService;

    @GetMapping(path = "/validate/{CUSTOMER_VALIDATE_TOKEN}")
    public ResponseEntity<ModelMap> isValidCustomerRequestToOrder(@PathVariable("CUSTOMER_VALIDATE_TOKEN") String token) throws CureFullException {
        ModelMap map = new ModelMap();
        ModelMap hoteSettings = customerService.isValidCustomerRequestToOrder(token);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(hoteSettings);
    }

    @PostMapping(path = "/order/{CUSTOMER_VALIDATE_TOKEN}/{OUTLET_ID}")
    public ResponseEntity<ModelMap> OrderItems(@PathVariable("CUSTOMER_VALIDATE_TOKEN") String token,
                                               @PathVariable("OUTLET_ID") String outletId,
                                               @RequestBody OrderDetails orderItems) throws CureFullException {
        ModelMap map = customerService.orderItems(orderItems, token, outletId);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(map);
    }
    @PostMapping(path = "/getCallWaiterStatus")
    public ResponseEntity<NotificationModel> getCallWaiterStatus(@RequestBody NotificationModel notificationModel){
        NotificationModel map = customerService.getCallWaiterStatus(notificationModel);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(map);
    }

    @GetMapping(path = "/callWaiter")
    public ResponseEntity<Boolean> callWaiter(@RequestBody NotificationModel notificationModel){
        Boolean map = customerService.callWaiter(notificationModel);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(map);
    }

    @GetMapping(path = "/get-my-placed-orders/{token}")
    public ResponseEntity<OrderDetails> getCustomerPlacedOrder(@PathVariable("token") String token){
        OrderDetails map = customerService.getCustomerPlacedOrder(token);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(map);
    }

    @GetMapping(path = "/get-restaurant-menu/{token}")
    public ResponseEntity<HotelMenu> getRestaurantMenu(@PathVariable("token") String token){
        HotelMenu map = customerService.getRestaurantMenu(token);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(map);
    }
    @GetMapping(path = "/get-restaurant-menu-by-outlet-id/{token}/{outletId}")
    public ResponseEntity<HotelMenu> getRestaurantMenuByOutletId(@PathVariable("token") String token,
                                                       @PathVariable("outletId") String outletId){
        HotelMenu map = customerService.getRestaurantMenuByOutletId(token, outletId);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(map);
    }

}
