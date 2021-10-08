package com.hotbot.repo.customer;

import com.hotbot.controller.customer.CustomerBehaviour;
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
public class CustomerBehaviourRepositoryImpl implements CustomerBehaviourRepository {

    @Autowired
    MongoOperations mongoOperations;

    @Autowired
    MongoTemplate mongoTemplate;


    @Autowired
    CURDMongoHandler cURDMongoHandler;


    @Override
    public CustomerBehaviour getCustomerServiceBehaviour(String token) {
        Query findQuery = new Query();
        findQuery.addCriteria(Criteria.where("customerNumber").is(token));
        return mongoOperations.findOne(findQuery, CustomerBehaviour.class, "customer_behaviour_master");
    }
}
