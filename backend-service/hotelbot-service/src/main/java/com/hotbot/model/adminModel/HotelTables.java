package com.hotbot.model.adminModel;

import com.hotbot.model.AbstractDocument;

public class HotelTables extends AbstractDocument {
    private String hotelUniqueId;
    private String hotelTableNo;
    private String hotelTableUniqueId;
    private String hotelQRCodeValue;

    public String getHotelUniqueId() {
        return hotelUniqueId;
    }

    public void setHotelUniqueId(String hotelUniqueId) {
        this.hotelUniqueId = hotelUniqueId;
    }

    public String getHotelTableNo() {
        return hotelTableNo;
    }

    public void setHotelTableNo(String hotelTableNo) {
        this.hotelTableNo = hotelTableNo;
    }

    public String getHotelTableUniqueId() {
        return hotelTableUniqueId;
    }

    public void setHotelTableUniqueId(String hotelTableUniqueId) {
        this.hotelTableUniqueId = hotelTableUniqueId;
    }

    public String getHotelQRCodeValue() {
        return hotelQRCodeValue;
    }

    public void setHotelQRCodeValue(String hotelQRCodeValue) {
        this.hotelQRCodeValue = hotelQRCodeValue;
    }
}
