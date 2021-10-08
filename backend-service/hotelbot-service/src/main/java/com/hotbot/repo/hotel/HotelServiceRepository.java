package com.hotbot.repo.hotel;
import com.hotbot.model.hotel.HotelInfo;

public interface HotelServiceRepository {
    Boolean saveHotelInfo(HotelInfo hotelInfo);
}
