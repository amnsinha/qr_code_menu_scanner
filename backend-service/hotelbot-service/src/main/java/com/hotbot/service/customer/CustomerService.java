package com.hotbot.service.customer;
import com.hotbot.model.commonModel.NotificationModel;
import com.hotbot.model.customerapplication.HotelMenu;
import com.hotbot.model.customerapplication.OrderDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;


@Service
public interface CustomerService {
    ModelMap isValidCustomerRequestToOrder(String token);
    OrderDetails
    getCustomerPlacedOrder(String token);
    HotelMenu getRestaurantMenu(String token);
    ModelMap orderItems(OrderDetails orderItems, String token, String outletId);
    NotificationModel getCallWaiterStatus(NotificationModel notificationModel);
    boolean callWaiter(NotificationModel notificationModel);
    HotelMenu getRestaurantMenuByOutletId(String token, String outletId);
}
