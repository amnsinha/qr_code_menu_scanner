package com.hotbot.service.hotel;

import com.hotbot.model.hotel.HotelInfo;
import com.hotbot.model.hotel.HotelSettingsCustomerApplication;
import com.hotbot.repo.hotel.HotelServiceRepository;
import com.hotbot.repo.hotel.HotelSettingsCustomerApplicationRepository;
import com.hotbot.utils.DataHideUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class HotelServiceImpl implements HotelService {

    @Autowired
    HotelSettingsCustomerApplicationRepository hotelSettingsCustomerApplicationRepository;

    @Autowired
    HotelServiceRepository hotelServiceRepository;

    @Override
    public HotelSettingsCustomerApplication findHotelSettingsCustomerApplicationByHotelId(String hotelUniqueId) {
        return hotelSettingsCustomerApplicationRepository.findHotelSettingsCustomerApplicationByHotelId(hotelUniqueId);
    }

    @Override
    public Boolean saveHotelInfo(HotelInfo hotelInfo) {
        String token = hotelInfo.getHotelName() + ":" + Math.random() + ":" + new Date().getTime() + ":" + Math.random();
        String newToken1 = DataHideUtils.encrypt(token);
        String newToken2 = DataHideUtils.encrypt(newToken1);
        String finalToken = newToken2.replaceAll("=", "XSAD4D");
        // New Unique Id Generated
        hotelInfo.setHotelUniqueId(finalToken);
        return hotelServiceRepository.saveHotelInfo(hotelInfo);

    }


}
