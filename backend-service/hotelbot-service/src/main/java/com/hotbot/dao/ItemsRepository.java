package com.hotbot.dao;


import com.hotbot.model.customerapplication.DishCategories;
import com.hotbot.model.customerapplication.Items;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsRepository {

    List<Items> getAllItem();

    List<DishCategories> getAllHotelItems(String hotelId);

    boolean updateOneItem(DishCategories dishCategories);

    boolean addItemToHotel(String hotelId, DishCategories dishCategories);

    boolean deleteItem(Integer hotelId);


}
