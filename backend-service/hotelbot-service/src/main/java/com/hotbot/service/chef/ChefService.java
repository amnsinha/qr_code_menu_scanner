package com.hotbot.service.chef;

import com.hotbot.domain.ChefDetails;
import com.hotbot.model.customerapplication.OrderDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChefService {
    List<OrderDetails> getAllNewOrders(String token, String outletId);

    List<OrderDetails> getAllInProgressOrders(String token, String outletId);

    List<OrderDetails> getAllCompletedOrders(String token, String outletId);

    List<OrderDetails> getOrdersByDate(String token);

    boolean setOrderStatus(String token);

    ChefDetails login(String username, String password);

    boolean setOrderItemStatus(String hotelId, String orderId, String itemId, String status);
}
