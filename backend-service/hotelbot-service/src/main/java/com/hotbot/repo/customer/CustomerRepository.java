package com.hotbot.repo.customer;

import com.hotbot.model.commonModel.NotificationModel;
import com.hotbot.model.customerapplication.HotelMenu;
import com.hotbot.model.customerapplication.OrderDetails;

import java.util.List;

public interface CustomerRepository {
    boolean isValidHotel(String hotelId);
    List<OrderDetails> getCustomerPlacedOrder(String hotelId, Integer tableNO);
    HotelMenu getRestaurantMenu(String hotelId);
    boolean isTableAvailable(String hotelId, Integer table);
    boolean orderItems(OrderDetails orderItem);
    NotificationModel getCallWaiterStatus(NotificationModel notificationModel);
    boolean callWaiter(NotificationModel notificationModel);

    HotelMenu getRestaurantMenuByOutletId(String hotelId, String outletId);
}
