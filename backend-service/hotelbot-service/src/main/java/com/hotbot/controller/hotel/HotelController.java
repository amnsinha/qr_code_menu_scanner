package com.hotbot.controller.hotel;

import com.hotbot.exception.CureFullException;
import com.hotbot.model.hotel.HotelInfo;
import com.hotbot.model.hotel.HotelSettingsCustomerApplication;
import com.hotbot.service.hotel.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/hoboai/hotel")
public class HotelController {

    final static Logger LOGGER = LoggerFactory.getLogger(HotelController.class);

    @Autowired
    public HotelService hotelService;

    @RequestMapping(path = "/save-update-hotel-settings/{token}", method = RequestMethod.GET)
    public ResponseEntity<HotelSettingsCustomerApplication> saveUpdateHotelSettings(@PathVariable("token") String hotelId, @RequestBody HotelSettingsCustomerApplication hs) throws CureFullException {
        HotelSettingsCustomerApplication hoteSettings = hotelService.findHotelSettingsCustomerApplicationByHotelId(hotelId);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(hoteSettings);
    }

    @RequestMapping(path = "/getHotelSettings/{token}", method = RequestMethod.GET)
    public ResponseEntity<HotelSettingsCustomerApplication> getAllCategories(@PathVariable("token") String hotelId) throws CureFullException {
        HotelSettingsCustomerApplication hoteSettings = hotelService.findHotelSettingsCustomerApplicationByHotelId(hotelId);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(hoteSettings);
    }


    @RequestMapping(path = "/Image/{id}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("id") String id, HttpServletResponse response) {
        byte[] image = null;  //this just gets the data from a database
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        return ResponseEntity.ok(image);
    }

    @PostMapping(path = "/save-hotel-info")
    public ResponseEntity<Boolean> saveHotelInfo(@RequestBody HotelInfo hotelInfo) {
        Boolean b = hotelService.saveHotelInfo(hotelInfo);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(b);
    }
}
