package com.hotbot.model.hotel;

import com.hotbot.model.AbstractDocument;
import com.hotbot.model.customerapplication.HeaderSettings;
import com.hotbot.model.customerapplication.PageSettings;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "hotel_settings")
public class HotelSettingsCustomerApplication extends AbstractDocument {

    String hotelId;
    private HeaderSettings headerSettings;
    private PageSettings pageSettings;

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public HeaderSettings getHeaderSettings() {
        return headerSettings;
    }

    public void setHeaderSettings(HeaderSettings headerSettings) {
        this.headerSettings = headerSettings;
    }

    public PageSettings getPageSettings() {
        return pageSettings;
    }

    public void setPageSettings(PageSettings pageSettings) {
        this.pageSettings = pageSettings;
    }
}

