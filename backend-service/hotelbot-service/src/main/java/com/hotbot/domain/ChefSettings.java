package com.hotbot.domain;

import com.hotbot.model.AbstractDocument;

public class ChefSettings extends AbstractDocument {
    private boolean autoProgressOrder;
    private String hotelUniqueId;

    public boolean isAutoProgressOrder() {
        return autoProgressOrder;
    }

    public void setAutoProgressOrder(boolean autoProgressOrder) {
        this.autoProgressOrder = autoProgressOrder;
    }

    public String getHotelUniqueId() {
        return hotelUniqueId;
    }

    public void setHotelUniqueId(String hotelUniqueId) {
        this.hotelUniqueId = hotelUniqueId;
    }
}
