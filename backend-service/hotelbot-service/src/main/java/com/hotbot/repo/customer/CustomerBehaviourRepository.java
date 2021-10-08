package com.hotbot.repo.customer;

import com.hotbot.controller.customer.CustomerBehaviour;
import com.hotbot.model.commonModel.NotificationModel;
import com.hotbot.model.customerapplication.HotelMenu;
import com.hotbot.model.customerapplication.OrderDetails;

import java.util.List;

public interface CustomerBehaviourRepository {

    CustomerBehaviour getCustomerServiceBehaviour(String token);
}
