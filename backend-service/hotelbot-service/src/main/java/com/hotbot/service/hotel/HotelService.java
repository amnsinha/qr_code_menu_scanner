package com.hotbot.service.hotel;

import com.hotbot.model.hotel.HotelInfo;
import com.hotbot.model.hotel.HotelSettingsCustomerApplication;
import org.springframework.stereotype.Service;

@Service
public interface HotelService {
    HotelSettingsCustomerApplication findHotelSettingsCustomerApplicationByHotelId(String hotelUniqueId);
    Boolean saveHotelInfo(HotelInfo hotelInfo);
}
