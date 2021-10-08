package com.hotbot.dao.Impl;

import com.hotbot.dao.HotelBotRepository;
import com.hotbot.model.customerapplication.DishCategories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HotelBotRepositoryImpl implements HotelBotRepository {

    @Autowired
    MongoOperations mongoOperations;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<DishCategories> getAllCategories() {
        Query query = new Query();
        return mongoOperations.find(new Query(), DishCategories.class, "dish_categories");

    }

    @Override
    public List<DishCategories> getAllHotelCategories(String hotelId) {
        Query findQuery = new Query();
        findQuery.addCriteria(Criteria.where("hotelId").is(hotelId));
        return mongoOperations.find(findQuery, DishCategories.class, "hotel_dish_categories");
    }

    protected DishCategories getOneDishCategories(String categoryId) {
        Query findQuery = new Query();
        findQuery.addCriteria(Criteria.where("categoryId").is(categoryId));
        return mongoOperations.findOne(findQuery, DishCategories.class, "hotel_dish_categories");
    }


    protected DishCategories getCateoryByName(String categoryName) {
        Query findQuery = new Query();
        findQuery.addCriteria(Criteria.where("categoryName").is(categoryName));
        return mongoOperations.findOne(findQuery, DishCategories.class, "hotel_dish_categories");
    }

    @Override
    public boolean addCategoryToHotel(String hotelId, DishCategories dishCategories) {

        boolean isSaved = false;
        DishCategories hotelDC = getCateoryByName(dishCategories.getName().toLowerCase());
        if (hotelDC == null) {
            dishCategories.setHotelId(hotelId);
            isSaved = this.saveNewHotelCategory(dishCategories);
        }
        return isSaved;
    }

    @Override
    public boolean deleteDishCategory(Integer CategoryId) {
        boolean isSaved = true;
        Query findQuery = new Query();
        findQuery.addCriteria(Criteria.where("categoryId").is(CategoryId));
        try {
            mongoOperations.remove(findQuery, DishCategories.class, "hotel_dish_categories");
        } catch (Exception e) {
            isSaved = false;
        }
        return isSaved;
    }

    @Override
    public boolean updateOneDishCategory(DishCategories dishCategory) {
        Query findQuery = new Query();
        findQuery.addCriteria(Criteria.where("categoryId").is(dishCategory.getCategoryId()));
        Update updateStatus = new Update();
        updateStatus.set("categoryDescription", dishCategory.getCategoryDescription());
        updateStatus.set("categoryName", dishCategory.getName());
        updateStatus.set("isCategoryAvailable", dishCategory.isCategoryAvailable());
        //WriteResult e = mongoOperations.upsert(findQuery, updateStatus, DishCategories.class, "hotel_dish_categories");
        return true;
    }

    private boolean saveNewHotelCategory(DishCategories dishCategories) {
        try {
            mongoOperations.save(dishCategories, "hotel_dish_categories");
        } catch (Exception e) {
            return false;
        }
        return true;
    }


}
