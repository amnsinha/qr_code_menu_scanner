package com.hotbot.service.impl;

import com.hotbot.model.customerapplication.DishCategories;
import com.hotbot.service.ItemService;
import com.hotbot.dao.ItemsRepository;
import com.hotbot.model.customerapplication.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemServiceImpl implements ItemService {


    @Autowired
    ItemsRepository itemsRepository;


    @Override
    public List<Items> getAllItem() {
        return itemsRepository.getAllItem();
    }

    @Override
    public boolean addItemToHotel(String hotelId, DishCategories dishCategories) {
        return itemsRepository.addItemToHotel(hotelId, dishCategories);
    }

    @Override
    public boolean deleteItem(Integer categoryId) {
        return itemsRepository.deleteItem(categoryId);
    }

    @Override
    public boolean updateOneItem(DishCategories dishCategories) {
        return itemsRepository.updateOneItem(dishCategories);
    }

    @Override
    public List<DishCategories> getAllHotelItems(String hotelId) {
        return itemsRepository.getAllHotelItems(hotelId);
    }


}
