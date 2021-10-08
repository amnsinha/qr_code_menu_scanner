package com.hotbot.model.customerapplication;

import com.hotbot.domain.ItemsType;
import com.hotbot.model.AbstractDocument;

import java.util.List;
import java.util.Random;


public class Items {

    private int itemId = generateRandomItemNo();
    private int id = generateRandomItemNo();
    private String name;
    private int qty;
    private float minPrice;
    private float price;
    private String itemTagImage;
    private String ItemDescription;
    private String isGoodFor;
    private List<SelectedQuantity> selectedQuantity;
    private List<SelectedQuantity> availableQuantity;
    private List<ItemsType> itemType;
    private String cuisineName;
    private boolean isRecommended;
    private boolean isItemAvailable;
    private boolean isVeg;
    private int totalRating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int generateRandomItemNo() {
        return new Random().nextInt(20020000);
    }


    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public float getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(float minPrice) {
        this.minPrice = minPrice;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getItemTagImage() {
        return itemTagImage;
    }

    public void setItemTagImage(String itemTagImage) {
        this.itemTagImage = itemTagImage;
    }

    public String getItemDescription() {
        return ItemDescription;
    }

    public void setItemDescription(String itemDescription) {
        ItemDescription = itemDescription;
    }

    public String getIsGoodFor() {
        return isGoodFor;
    }

    public void setIsGoodFor(String isGoodFor) {
        this.isGoodFor = isGoodFor;
    }

    public List<SelectedQuantity> getSelectedQuantity() {
        return selectedQuantity;
    }

    public void setSelectedQuantity(List<SelectedQuantity> selectedQuantity) {
        this.selectedQuantity = selectedQuantity;
    }

    public List<SelectedQuantity> getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(List<SelectedQuantity> availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public List<ItemsType> getItemType() {
        return itemType;
    }

    public void setItemType(List<ItemsType> itemType) {
        this.itemType = itemType;
    }

    public String getCuisineName() {
        return cuisineName;
    }

    public void setCuisineName(String cuisineName) {
        this.cuisineName = cuisineName;
    }

    public boolean isRecommended() {
        return isRecommended;
    }

    public void setRecommended(boolean recommended) {
        isRecommended = recommended;
    }

    public boolean isItemAvailable() {
        return isItemAvailable;
    }

    public void setItemAvailable(boolean itemAvailable) {
        isItemAvailable = itemAvailable;
    }

    public boolean isVeg() {
        return isVeg;
    }

    public void setVeg(boolean veg) {
        isVeg = veg;
    }

    public int getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(int totalRating) {
        this.totalRating = totalRating;
    }
}
