package com.hotbot.domain;

import com.hotbot.model.AbstractDocument;

public class OutletDetails extends AbstractDocument {
    private String outletName;
    private String outletDesc;
    private String hotelId;
    private String outletUniqueId;

    public String getOutletName() {
        return outletName;
    }

    public void setOutletName(String outletName) {
        this.outletName = outletName;
    }

    public String getOutletDesc() {
        return outletDesc;
    }

    public void setOutletDesc(String outletDesc) {
        this.outletDesc = outletDesc;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getOutletUniqueId() {
        return outletUniqueId;
    }

    public void setOutletUniqueId(String outletUniqueId) {
        this.outletUniqueId = outletUniqueId;
    }
}
