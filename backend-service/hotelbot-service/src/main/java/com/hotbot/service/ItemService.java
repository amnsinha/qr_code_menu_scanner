package com.hotbot.service;


import com.hotbot.model.customerapplication.DishCategories;
import com.hotbot.model.customerapplication.Items;

import java.util.List;

public interface ItemService {

    List<Items> getAllItem();

    boolean addItemToHotel(String hotelId, DishCategories hotelDishCategories);

    boolean updateOneItem(DishCategories hotelDishCategories);

    List<DishCategories> getAllHotelItems(String hotelId);

    boolean deleteItem(Integer categoryId);
}
