package com.hotbot.dao;


import com.hotbot.model.customerapplication.DishCategories;

import java.util.List;


public interface HotelBotRepository {

    List<DishCategories> getAllCategories();

    List<DishCategories> getAllHotelCategories(String hotelId);

    boolean updateOneDishCategory(DishCategories dishCategories);

    boolean addCategoryToHotel(String hotelId, DishCategories dishCategories);

    boolean deleteDishCategory(Integer hotelId);


}
