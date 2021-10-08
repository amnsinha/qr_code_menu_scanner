package com.hotbot.repo.chef;


import com.hotbot.domain.ChefDetails;
import com.hotbot.model.customerapplication.OrderDetails;

import java.util.List;

public interface ChefRepository {
    List<OrderDetails> getAllNewOrders(String token,String outletId);
    List<OrderDetails> getAllInProgressOrders(String token,String outletId);
    List<OrderDetails> getAllCompletedOrders(String token,String outletId);
    List<OrderDetails> getOrdersByDate(String token);
    boolean setOrderStatus(String hotelId, String status, String orderId);
    ChefDetails login(String username, String password);
    boolean setOrderItemStatus(String hotelId, String orderId, String itemId, String status);
}
