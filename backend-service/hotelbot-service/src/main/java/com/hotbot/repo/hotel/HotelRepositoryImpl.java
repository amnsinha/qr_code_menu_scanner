package com.hotbot.repo.hotel;

import com.hotbot.model.hotel.HotelInfo;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class HotelRepositoryImpl implements HotelServiceRepository {

    @Autowired
    MongoOperations mongoOperations;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Boolean saveHotelInfo(HotelInfo hotelInfo) {
        Boolean b = false;
        Query findQuery = new Query();
        findQuery.addCriteria(
                Criteria.where("hotelUniqueId").is(hotelInfo.getHotelUniqueId()));
        Update update = new Update();
        if (hotelInfo.getHotelContactNo() != null)
            update.set("hotelContactNo", hotelInfo.getHotelContactNo());
        if (hotelInfo.getHotelAddress() != null)
            update.set("hotelAddress", hotelInfo.getHotelAddress());
        if (hotelInfo.getHotelAdmin() != null)
            update.set("hotelAdmin", hotelInfo.getHotelAdmin());
        if (hotelInfo.getHotelName() != null)
            update.set("hotelName", hotelInfo.getHotelName());
        if (hotelInfo.getHotelUniqueId() != null)
            update.set("hotelUniqueId", hotelInfo.getHotelUniqueId());
        if (hotelInfo.getHotelAdminEmailAddress() != null)
            update.set("hotelAdminEmailAddress", hotelInfo.getHotelAdminEmailAddress());
        if (hotelInfo.getHotelPlanActivated() != null)
            update.set("hotelPlanActivated", hotelInfo.getHotelPlanActivated());
        if (hotelInfo.getHotelManagerNumber() != null)
            update.set("hotelManagerNumber", hotelInfo.getHotelManagerNumber());
        try {
            UpdateResult updateResult = mongoOperations.upsert(findQuery, update, HotelInfo.class, "hotel_info");
            if (updateResult.getUpsertedId() != null) {
                b = true;
            }
        } catch (Exception e) {
            b = false;
        }
        return b;
    }
}

