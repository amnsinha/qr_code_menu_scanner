package com.hotbot.repo.chef;

import com.hotbot.domain.ChefDetails;
import com.hotbot.exception.MongoDBDocumentNotFoundException;
import com.hotbot.model.customerapplication.OrderDetails;
import com.hotbot.model.customerapplication.OrderItem;
import com.hotbot.mongoCrudHandler.CURDMongoHandler;
import com.hotbot.utils.OrderItemConstants;
import com.mongodb.client.result.UpdateResult;
import net.minidev.json.writer.BeansMapper;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ChefRepositoryImpl implements ChefRepository {

    @Autowired
    MongoOperations mongoOperations;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    CURDMongoHandler cURDMongoHandler;


    @Override
    public List<OrderDetails> getAllNewOrders(String token, String outletId) {
        Query findQuery = new Query();
        findQuery.addCriteria(
                Criteria.where("hotelId").is(token).and("orderStatus").is(OrderItemConstants.ORDERED_PLACED)
                        .and("outletId").is(outletId));
        return cURDMongoHandler.getDocuments(OrderDetails.class, findQuery, "customer_order");
    }

    @Override
    public List<OrderDetails> getAllInProgressOrders(String token, String outletId) {
        Query findQuery = new Query();
        findQuery.addCriteria(
                Criteria.where("hotelId").is(token).and("orderStatus").is(OrderItemConstants.IN_PROGRESS)
                        .and("outletId").is(outletId));
        return cURDMongoHandler.getDocuments(OrderDetails.class, findQuery, "customer_order");
    }

    @Override
    public List<OrderDetails> getAllCompletedOrders(String token, String outletId) {
        Query findQuery = new Query();
        findQuery.addCriteria(
                Criteria.where("hotelId").is(token).and("orderStatus").is(OrderItemConstants.COMPLETED).and("outletId").is(outletId));
        return cURDMongoHandler.getDocuments(OrderDetails.class, findQuery, "customer_order");
    }

    @Override
    public List<OrderDetails> getOrdersByDate(String token) {
        Query findQuery = new Query();
        findQuery.addCriteria(
                Criteria.where("hotelId").is(token).and("orderStatus").is(OrderItemConstants.ORDERED_PLACED));
        return cURDMongoHandler.getDocuments(OrderDetails.class, findQuery, "customer_order");
    }

    @Override
    public boolean setOrderStatus(String hotelId, String status, String orderId) {
        Update update = new Update();
        Query findQuery = new Query();
        findQuery.addCriteria(
                Criteria.where("hotelId").is(hotelId)
                        .and("orderId").is(orderId));

        OrderDetails orderDetails = mongoOperations.findOne(findQuery, OrderDetails.class, "customer_order");
        List<OrderItem> ls = new ArrayList<>();
        if (status.equals(OrderItemConstants.DROP_WHOLE_ORDER)) {
            for (OrderItem oI : orderDetails.getOrderItem()) {
                OrderItem newOrderItem = new OrderItem();
                BeanUtils.copyProperties(oI, newOrderItem);
                newOrderItem.setItemStatus(OrderItemConstants.ORDER_CANCEL_FOR_ITEM);
                ls.add(newOrderItem);
            }
            update.set("orderStatus", OrderItemConstants.DROP_WHOLE_ORDER);
        }

        if (status.equals(OrderItemConstants.COMPLETED)) {
            for (OrderItem oI : orderDetails.getOrderItem()) {
                OrderItem newOrderItem = new OrderItem();
                BeanUtils.copyProperties(oI, newOrderItem);
                if (!newOrderItem.getItemStatus().equals(OrderItemConstants.ORDER_CANCEL_FOR_ITEM)) {
                    newOrderItem.setItemStatus(OrderItemConstants.ORDER_COMPLETED_FOR_ITEM);
                }
                ls.add(newOrderItem);
            }
            update.set("orderStatus", OrderItemConstants.COMPLETED);
        }
        if (status.equals(OrderItemConstants.IN_PROGRESS)) {
            for (OrderItem oI : orderDetails.getOrderItem()) {
                OrderItem newOrderItem = new OrderItem();
                BeanUtils.copyProperties(oI, newOrderItem);
                if (!(newOrderItem.getItemStatus().equals(OrderItemConstants.ORDER_CANCEL_FOR_ITEM))) {
                    newOrderItem.setItemStatus(OrderItemConstants.ORDER_INPROGRESS_FOR_ITEM);
                }
                ls.add(newOrderItem);
            }
            update.set("orderStatus", OrderItemConstants.IN_PROGRESS);
        }


        update.set("orderItem", ls);
        return cURDMongoHandler.updateDocument(findQuery, update, OrderDetails.class, "customer_order");
    }

    @Override
    public ChefDetails login(String username, String password) {
        Query findQuery = new Query();
        findQuery.addCriteria(
                Criteria.where("chefUserName").is(username).and("chefPassword").is(password));
        return mongoOperations.findOne(findQuery, ChefDetails.class, "hotel_chef_details");
        //  return cURDMongoHandler.checkExist(ChefDetails.class, findQuery, "hotel_chef_details");
    }


    public OrderDetails getOrderByOrderId(String hotelId, String orderId) throws MongoDBDocumentNotFoundException {
        Query findQuery = new Query();
        findQuery.addCriteria(
                Criteria.where("hotelId").is(hotelId).and("orderId").is(orderId));
        return mongoOperations.findOne(findQuery, OrderDetails.class, "customer_order");
    }


    @Override
    public boolean setOrderItemStatus(String hotelId, String orderId, String itemId, String status) {
        Query findQuery = new Query();
        findQuery.addCriteria(
                Criteria.where("hotelId").is(hotelId)
                        .and("orderId").is(orderId));
        OrderDetails orderDetails = mongoOperations.findOne(findQuery, OrderDetails.class, "customer_order");

        orderDetails.getOrderItem().stream()
                .filter(p -> p.getItemUniqueId().equals(itemId))
                .findAny()
                .orElseThrow(IllegalArgumentException::new)
                .setItemStatus(status);
        ///

        Update update = new Update();
        update.set("orderItem", orderDetails.getOrderItem());
        return cURDMongoHandler.updateDocument(findQuery, update, OrderDetails.class, "customer_order");
    }
}
