package com.hotbot.repo.customer;

import com.hotbot.model.commonModel.NotificationModel;
import com.hotbot.model.customerapplication.HotelMenu;
import com.hotbot.model.customerapplication.HotelTableSessions;
import com.hotbot.model.customerapplication.OrderDetails;
import com.hotbot.model.hotel.HotelInfo;
import com.hotbot.mongoCrudHandler.CURDMongoHandler;
import com.hotbot.utils.OrderItemConstants;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    @Autowired
    MongoOperations mongoOperations;

    @Autowired
    MongoTemplate mongoTemplate;


    @Autowired
    CURDMongoHandler cURDMongoHandler;


    @Override
    public boolean isValidHotel(String hotelId) {
        Query findQuery = new Query();
        findQuery.addCriteria(Criteria.where("hotelUniqueId").is(hotelId));
        return mongoOperations.exists(findQuery, HotelInfo.class, "hotel_info");
    }

    @Override
    public List<OrderDetails> getCustomerPlacedOrder(String hotelId, Integer tableNo) {
        Query findQuery = new Query();
        findQuery.addCriteria(
                Criteria.where
                        ("orderTableNo").is(tableNo).and
                        ("ORDERED_PLACED").ne(OrderItemConstants.COMPLETED).and
                        ("hotelId").is(hotelId));
        return mongoOperations.find(findQuery, OrderDetails.class, "customer_order");

    }

    @Override
    public HotelMenu getRestaurantMenu(String hotelId) {
        Query findQuery = new Query();
        findQuery.addCriteria(
                Criteria.where("hotelId").is(hotelId));
        return mongoOperations.findOne(findQuery, HotelMenu.class, "restaurant_menu");
    }

    @Override
    public boolean isTableAvailable(String hotelId, Integer tableNo) {
        Query findQuery = new Query();
        findQuery.addCriteria(
                Criteria.where("hotelUniqueId").is(hotelId)
                        .and("hotelTableNo").is(tableNo).and("isEngaged").is(false));
        return !mongoOperations.exists(findQuery, HotelTableSessions.class, "hotel_table_sessions");
    }

    @Override
    public boolean orderItems(OrderDetails orderItem) {
        return cURDMongoHandler.saveDocument(orderItem, "customer_order");
    }

    @Override
    public NotificationModel getCallWaiterStatus(NotificationModel notificationModel) {
        Query findQuery = new Query();
        findQuery.addCriteria(
                Criteria.where
                        ("hotelUniqueId").is(notificationModel.getHotelUniqueId()).and
                        ("hotelTableNo").is(notificationModel.getHotelTableNo()).and
                        ("session").is(notificationModel.getSession())
        );
        return mongoOperations.findOne(findQuery, NotificationModel.class, "hotel_waiter_calling_sessions");
    }


    @Override
    public boolean callWaiter(NotificationModel notificationModel) {
        Boolean callWaiterUpdated = false;
        Query findQuery = new Query();
        findQuery.addCriteria(
                Criteria.where("hotelUniqueId").is(notificationModel.getHotelUniqueId())
                        .and("hotelTableNo").is(notificationModel.getHotelTableNo()));
        Update update = new Update();

        if (notificationModel.getHotelTableNo() != null)
            update.set("hotelTableNo", notificationModel.getHotelTableNo());
        if (notificationModel.getSession() != null)
            update.set("session", notificationModel.getSession());
        if (notificationModel.getHotelUniqueId() != null)
            update.set("hotelUniqueId", notificationModel.getHotelUniqueId());
        if (notificationModel.getWaterCallingStatus() != null)
            update.set("waterCallingStatus", notificationModel.getWaterCallingStatus());

        UpdateResult updateResult = mongoOperations.upsert(findQuery, update, NotificationModel.class, "hotel_waiter_calling_sessions");
        if (updateResult != null) {
            callWaiterUpdated = true;
        }
        return callWaiterUpdated;
    }

    @Override
    public HotelMenu getRestaurantMenuByOutletId(String hotelId, String outletId) {
        Query findQuery = new Query();
        findQuery.addCriteria(
                Criteria.where("hotelId").is(hotelId).and("outletId").is(outletId));
        return mongoOperations.findOne(findQuery, HotelMenu.class, "restaurant_menu");

    }
}
