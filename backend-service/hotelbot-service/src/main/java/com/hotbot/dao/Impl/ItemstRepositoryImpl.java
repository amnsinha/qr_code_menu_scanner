package com.hotbot.dao.Impl;


import com.hotbot.dao.ItemsRepository;
import com.hotbot.model.customerapplication.DishCategories;
import com.hotbot.model.customerapplication.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemstRepositoryImpl implements ItemsRepository {

    @Autowired
    MongoOperations mongoOperations;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Items> getAllItem() {
        Query query = new Query();
        return mongoOperations.find(new Query(), Items.class, "dish_categories");

    }

    @Override
    public List<DishCategories> getAllHotelItems(String hotelId) {
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
    public boolean addItemToHotel(String hotelId, DishCategories dishCategories) {

        boolean isSaved = false;
        DishCategories hotelDC = getCateoryByName(dishCategories.getName().toLowerCase());
        if (hotelDC == null) {
            dishCategories.setHotelId(hotelId);
            isSaved = this.saveNewItem(dishCategories);
        }
        return isSaved;
    }

    @Override
    public boolean deleteItem(Integer CategoryId) {
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
    public boolean updateOneItem(DishCategories dishCategory) {
        Query findQuery = new Query();
        findQuery.addCriteria(Criteria.where("categoryId").is(dishCategory.getCategoryId()));
        Update updateStatus = new Update();
        updateStatus.set("categoryDescription", dishCategory.getCategoryDescription());
        updateStatus.set("categoryName", dishCategory.getName());
        updateStatus.set("isCategoryAvailable", dishCategory.isCategoryAvailable());
        //WriteResult e = mongoOperations.upsert(findQuery, updateStatus, DishCategories.class, "hotel_dish_categories");
        return true;
    }

    private boolean saveNewItem(DishCategories dishCategories) {
        try {
            mongoOperations.save(dishCategories, "hotel_dish_categories");
        } catch (Exception e) {
            return false;
        }
        return true;
    }


}
