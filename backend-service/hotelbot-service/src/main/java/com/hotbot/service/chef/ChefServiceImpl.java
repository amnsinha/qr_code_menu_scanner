package com.hotbot.service.chef;

import com.hotbot.domain.ChefDetails;
import com.hotbot.model.customerapplication.OrderDetails;
import com.hotbot.repo.chef.ChefRepository;
import com.hotbot.utils.DataHideUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChefServiceImpl implements ChefService {


    @Autowired
    ChefRepository chefRepository;


    @Override
    public boolean setOrderItemStatus(String hotelId, String orderId, String itemId, String status) {
        return chefRepository.setOrderItemStatus(hotelId, orderId, itemId, status);
    }

    @Override
    public List<OrderDetails> getAllNewOrders(String token,String outletId) {
        return chefRepository.getAllNewOrders(token,outletId);
    }

    @Override
    public List<OrderDetails> getAllInProgressOrders(String token,String outletId) {
        return chefRepository.getAllInProgressOrders(token,outletId);
    }

    @Override
    public List<OrderDetails> getAllCompletedOrders(String token, String outletId) {
        return chefRepository.getAllCompletedOrders(token,outletId);
    }

    @Override
    public List<OrderDetails> getOrdersByDate(String token) {
        return chefRepository.getOrdersByDate(token);
    }

    @Override
    public boolean setOrderStatus(String token) {
        String hotelId = "";
        String orderId = null;
        String status = null;
        String token_dec = DataHideUtils.decodeFromBase64(token);
        String[] token_de = token_dec.split(":");
        if (token_de != null && token_de.length > 0) {
            hotelId = token_de[0];
            orderId = token_de[1];
            status = token_de[2];
        }
        return chefRepository.setOrderStatus(hotelId, status, orderId);
    }

    @Override
    public ChefDetails login(String username, String password) {
        return chefRepository.login(username, password);
    }
}
