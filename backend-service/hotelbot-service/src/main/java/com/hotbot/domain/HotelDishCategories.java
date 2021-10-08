package com.hotbot.domain;

import com.hotbot.model.AbstractDocument;
import com.hotbot.model.customerapplication.DishCategories;

import java.util.List;

public class HotelDishCategories extends AbstractDocument {
    private String hotelId;
    private List<DishCategories> dishCategories;

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public List<DishCategories> getDishCategories() {
        return dishCategories;
    }

    public void setDishCategories(List<DishCategories> dishCategories) {
        this.dishCategories = dishCategories;
    }
}
