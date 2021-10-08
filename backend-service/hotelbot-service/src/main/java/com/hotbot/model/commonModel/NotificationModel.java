package com.hotbot.model.commonModel;

import com.hotbot.model.AbstractDocument;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "hotel_waiter_calling_sessions")
public class NotificationModel extends AbstractDocument {

    String hotelUniqueId;
    Integer hotelTableNo;
    String waterCallingStatus;
    String session;

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getHotelUniqueId() {
        return hotelUniqueId;
    }

    public void setHotelUniqueId(String hotelUniqueId) {
        this.hotelUniqueId = hotelUniqueId;
    }

    public Integer getHotelTableNo() {
        return hotelTableNo;
    }

    public void setHotelTableNo(Integer hotelTableNo) {
        this.hotelTableNo = hotelTableNo;
    }

    public String getWaterCallingStatus() {
        return waterCallingStatus;
    }

    public void setWaterCallingStatus(String waterCallingStatus) {
        this.waterCallingStatus = waterCallingStatus;
    }
}
