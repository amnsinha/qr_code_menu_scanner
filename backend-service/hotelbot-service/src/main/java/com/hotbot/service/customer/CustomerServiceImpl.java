package com.hotbot.service.customer;

import com.hotbot.model.commonModel.NotificationModel;
import com.hotbot.model.customerapplication.HotelMenu;
import com.hotbot.model.customerapplication.OrderDetails;
import com.hotbot.model.customerapplication.OrderItem;
import com.hotbot.repo.customer.CustomerRepository;
import com.hotbot.utils.DataHideUtils;
import com.hotbot.utils.OrderItemConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerServiceImpl implements CustomerService {


    @Autowired
    CustomerRepository customerRepository;

    @Override
    public ModelMap isValidCustomerRequestToOrder(String token) {

        String hotelId = "";
        Integer table = null;
        String token_dec = DataHideUtils.decodeFromBase64(token);
        String[] token_de = token_dec.split(":");
        if (token_de != null && token_de.length > 0) {
            hotelId = token_de[0];
            table = Integer.parseInt(token_de[1]);
        }
        ModelMap modelMap = new ModelMap();

        boolean exist = customerRepository.isValidHotel(hotelId);
        if (!exist) {
            modelMap.addAttribute("404", "Hotel Is Not Registered");
        } else {
            if (customerRepository.isTableAvailable(hotelId, table)) {
                modelMap.addAttribute("STATUS_CODE", "200");
                modelMap.addAttribute("STATUS_MESSAGE", "Table is available");
            } else {
                modelMap.addAttribute("STATUS_CODE", "404");
                modelMap.addAttribute("STATUS_MESSAGE", "This Table is not available");
            }
        }
        return modelMap;
    }

    @Override
    public ModelMap orderItems(OrderDetails orderDetails, String token, String outletId) {
        ModelMap map = new ModelMap();
        String hotelId = "";
        Integer table = null;
        String token_dec = DataHideUtils.decodeFromBase64(token);
        String[] token_de = token_dec.split(":");
        if (token_de != null && token_de.length > 0) {
            hotelId = token_de[0];
            table = Integer.parseInt(token_de[1]);
        }
        orderDetails.setSpecialInstruction(orderDetails.getSpecialInstruction());
        orderDetails.setOrderId(DataHideUtils.getAlphaNumericString());
        orderDetails.setOrderTableNo(table);
        orderDetails.setOutletId(outletId);
        orderDetails.setOrderStatus(OrderItemConstants.ORDERED_PLACED);
        orderDetails.setHotelId(hotelId);
        orderDetails.setOrderItem(orderDetails.getOrderItem());

        boolean b = customerRepository.orderItems(orderDetails);
        if (b) {
            map.addAttribute("STATUS_CODE", "200");
            map.addAttribute("STATUS_MESSAGE", "Successfully Ordered, Sit back and Realx...");
        } else {
            map.addAttribute("STATUS_CODE", "404");
            map.addAttribute("STATUS_CODE", "Sorry, Order Not Placed Successfully, Can you try re-ordering?");
        }
        return map;
    }

    @Override
    public NotificationModel getCallWaiterStatus(NotificationModel notificationModel) {
        return customerRepository.getCallWaiterStatus(notificationModel);
    }

    @Override
    public boolean callWaiter(NotificationModel notificationModel) {
        return customerRepository.callWaiter(notificationModel);
    }

    @Override
    public HotelMenu getRestaurantMenuByOutletId(String token, String outletId) {
        String hotelId = "";
        Integer table = null;
        String token_dec = DataHideUtils.decodeFromBase64(token);
        String[] token_de = token_dec.split(":");
        if (token_de != null && token_de.length > 0) {
            hotelId = token_de[0];
            table = Integer.parseInt(token_de[1]);
        }

        return customerRepository.getRestaurantMenuByOutletId(hotelId, outletId);
    }

    @Override
    public OrderDetails getCustomerPlacedOrder(String token) {
        String hotelId = "";
        Integer table = null;
        System.out.println(token);
        String token_dec = DataHideUtils.decodeFromBase64(token);
        System.out.println(token_dec);

        String[] token_de = token_dec.split(":");
        if (token_de != null && token_de.length > 0) {
            hotelId = token_de[0];
            table = Integer.parseInt(token_de[1]);
        }

        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setOrderItem(new ArrayList<>());
        List<OrderDetails> OrderDetailsList = customerRepository.getCustomerPlacedOrder(hotelId, table);
        for (OrderDetails orderDetails1 : OrderDetailsList) {
            orderDetails.setHotelId(orderDetails1.getHotelId());
            orderDetails.setOrderTableNo(orderDetails1.getOrderTableNo());
            orderDetails.setOrderId(orderDetails1.getOrderId());

            List<OrderItem>  filtertedOrderItems = orderDetails1.getOrderItem().stream()
                    .filter(line -> !OrderItemConstants.ORDER_CANCEL_FOR_ITEM.equals(line.getItemStatus()))
                    .collect(Collectors.toList());
            orderDetails.getOrderItem().addAll(filtertedOrderItems);
        }
        return orderDetails;
    }

    @Override
    public HotelMenu getRestaurantMenu(String token) {
        String hotelId = "";
        Integer table = null;
        String token_dec = DataHideUtils.decodeFromBase64(token);
        String[] token_de = token_dec.split(":");
        if (token_de != null && token_de.length > 0) {
            hotelId = token_de[0];
            table = Integer.parseInt(token_de[1]);
        }

        return customerRepository.getRestaurantMenu(hotelId);
    }
}
