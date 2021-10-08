package com.hotbot.service.customer;
import com.hotbot.controller.customer.CustomerBehaviour;
import com.hotbot.model.commonModel.NotificationModel;
import com.hotbot.model.customerapplication.HotelMenu;
import com.hotbot.model.customerapplication.OrderDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;


@Service
public interface CustomerBehaviourService {
    CustomerBehaviour getCustomerServiceBehaviour(String token);
}
