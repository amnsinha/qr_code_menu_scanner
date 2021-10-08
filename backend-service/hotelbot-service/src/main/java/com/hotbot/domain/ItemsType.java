package com.hotbot.domain;

import com.hotbot.model.AbstractDocument;

import java.util.Date;
import java.util.Random;


public class ItemsType extends AbstractDocument {

    private String itemId = generateRandomItemTypeNo();
    private String itemName;
    private boolean isItemAvailable;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public boolean isItemAvailable() {
        return isItemAvailable;
    }

    public void setItemAvailable(boolean itemAvailable) {
        isItemAvailable = itemAvailable;
    }

    public String generateRandomItemTypeNo() {
        return "ItemType" + new Date() + new Random().nextInt(2009920000);
    }


}
