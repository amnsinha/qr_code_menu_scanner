package com.hotbot.model.customerapplication;

import com.hotbot.model.AbstractDocument;

import java.util.List;
import java.util.Random;

public class DishCategories extends AbstractDocument {

    private Integer categoryId = getRandomCategory();
    private String hotelId;
    private String name;
    private List<Items> items;
    private String categoryDescription;
    private boolean isCategoryAvailable;

    public int getRandomCategory() {
        return new Random().nextInt(20020000);
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public boolean isCategoryAvailable() {
        return isCategoryAvailable;
    }

    public void setCategoryAvailable(boolean categoryAvailable) {
        isCategoryAvailable = categoryAvailable;
    }
}
