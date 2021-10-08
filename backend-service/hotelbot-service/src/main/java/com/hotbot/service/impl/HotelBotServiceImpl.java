package com.hotbot.service.impl;

import com.hotbot.dao.HotelBotRepository;
import com.hotbot.model.customerapplication.DishCategories;
import com.hotbot.service.HotelBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HotelBotServiceImpl implements HotelBotService {


    @Autowired
    HotelBotRepository hotelBotRepository;


    @Override
    public List<DishCategories> getAllCategories() {
        return hotelBotRepository.getAllCategories();
    }

    @Override
    public boolean addCategoryToHotel(String hotelId, DishCategories dishCategories) {
        return hotelBotRepository.addCategoryToHotel(hotelId, dishCategories);
    }

    @Override
    public boolean deleteDishCategory(Integer categoryId) {
        return hotelBotRepository.deleteDishCategory(categoryId);
    }

    @Override
    public boolean updateOneDishCategory(DishCategories dishCategories) {
        return hotelBotRepository.updateOneDishCategory(dishCategories);
    }

    @Override
    public List<DishCategories> getAllHotelCategories(String hotelId) {
        return hotelBotRepository.getAllHotelCategories(hotelId);
    }


}
