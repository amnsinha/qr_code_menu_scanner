package com.hotbot.model.customerapplication;

import com.hotbot.model.AbstractDocument;

import java.util.List;

public class Menu extends AbstractDocument {

    private String name;
    private List<DishCategories> categories;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DishCategories> getCategories() {
        return categories;
    }

    public void setCategories(List<DishCategories> categories) {
        this.categories = categories;
    }
}
