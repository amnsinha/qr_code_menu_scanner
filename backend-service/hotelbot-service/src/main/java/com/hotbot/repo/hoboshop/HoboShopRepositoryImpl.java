package com.hotbot.repo.hoboshop;

import com.hotbot.exception.MongoDBDocumentNotFoundException;
import com.hotbot.model.hoboshop.HoboShopAttribute;
import com.hotbot.model.hoboshop.HoboShopRegister;
import com.hotbot.model.hotel.HotelInfo;
import com.hotbot.mongoCrudHandler.CURDMongoHandler;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class HoboShopRepositoryImpl implements HoboShopRepository {

    @Autowired
    MongoOperations mongoOperations;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    CURDMongoHandler cURDMongoHandler;


    @Override
    public HoboShopAttribute getShopInfo(String hotelId) {
        Query findQuery = new Query();
        findQuery.addCriteria(
                Criteria.where("shopUniqueId").is(hotelId));
        return mongoOperations.findOne(findQuery, HoboShopAttribute.class, "hobo_shop_info");
    }

    @Override
    public boolean editShopNameorNumber(String token, String shopname, String shopnumber) {
        Boolean b = false;
        Query findQuery = new Query();
        findQuery.addCriteria(
                Criteria.where("shopUniqueId").is(token));
        Update update = new Update();
        if (shopname != null)
            update.set("shopContactNo", shopname);
        if (shopnumber != null)
            update.set("shopName", shopnumber);
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

    @Override
    public String login(String username, String password) {
        Query findQuery = new Query();
        findQuery.addCriteria(
                Criteria.where("shopUserName").is(username).and("shopPassword").is(password));
        HoboShopRegister hoboShopRegister = mongoOperations.findOne(findQuery, HoboShopRegister.class, "hobo_shop_info");
        return hoboShopRegister.getShopUniqueId();
    }

    @Override
    public boolean registerShop(HoboShopRegister hoboShopRegister) {
        return cURDMongoHandler.saveDocument(hoboShopRegister, "hobo_shop_info");
    }

    @Override
    public boolean saveShopInfo(HoboShopAttribute imageData) throws MongoDBDocumentNotFoundException {
        boolean b = false;
        Query findQuery = new Query();
        findQuery.addCriteria(
                Criteria.where("shopUserName").is(imageData.getShopUniqueId()));
        HoboShopAttribute hoboShopAttribute = mongoOperations.findOne(findQuery, HoboShopAttribute.class, "hobo_shop_info");
        if (hoboShopAttribute != null) {
            b = cURDMongoHandler.saveDocument(imageData, "hobo_shop_image");
        }
        return b;
    }


}
