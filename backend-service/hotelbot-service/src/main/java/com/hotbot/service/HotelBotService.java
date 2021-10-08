package com.hotbot.service;

import com.hotbot.model.customerapplication.DishCategories;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HotelBotService {

    List<DishCategories> getAllCategories();

    boolean addCategoryToHotel(String hotelId, DishCategories hotelDishCategories);

    boolean updateOneDishCategory(DishCategories hotelDishCategories);

    List<DishCategories> getAllHotelCategories(String hotelId);

    boolean deleteDishCategory(Integer categoryId);
}
