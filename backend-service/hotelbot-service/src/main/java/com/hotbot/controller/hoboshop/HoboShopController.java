package com.hotbot.controller.hoboshop;

import com.hotbot.exception.CureFullException;
import com.hotbot.model.hoboshop.HoboShopAttribute;
import com.hotbot.model.hoboshop.HoboShopRegister;
import com.hotbot.service.hoboshop.HoboShopService;
import com.hotbot.utils.ResponseBuilder;
import com.hotbot.utils.ResponseSuccessData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hoboai/hoboshop")
public class HoboShopController {

    final static Logger LOGGER = LoggerFactory.getLogger(HoboShopController.class);

    @Autowired
    public HoboShopService hoboShopService;

    @PostMapping(value = "/save-shop-images/{token}")
    public ResponseEntity<ResponseSuccessData> saveShopInfo(@RequestBody List<String> image, @PathVariable("token") String token) throws CureFullException {
        boolean b = hoboShopService.saveShopInfo(image, token);
        return ResponseBuilder.getSuccessResponse(b);
    }

    @PostMapping(value = "/register-shop")
    public ResponseEntity<ResponseSuccessData> register(@RequestBody HoboShopRegister hoboShopRegister) throws CureFullException {
        boolean b = hoboShopService.registerShop(hoboShopRegister);
        return ResponseBuilder.getSuccessResponse(b);
    }

    @GetMapping(value = "/login")
    public ResponseEntity<ResponseSuccessData> login(@RequestParam("username") String username, @RequestParam("password") String password) throws CureFullException {
        String shopUniqueId = hoboShopService.login(username, password);
        return ResponseBuilder.getSuccessResponse(shopUniqueId);
    }


    @GetMapping(value = "/get-shop-info/{token}")
    public ResponseEntity<ResponseSuccessData> getShopInfo(@PathVariable("token") String token) throws CureFullException {
        HoboShopAttribute hoboShopAttribute = hoboShopService.getShopInfo(token);
        return ResponseBuilder.getSuccessResponse(hoboShopAttribute);
    }

    @GetMapping(value = "/edit-shop-info/{token}")
    public ResponseEntity<ResponseSuccessData> editShopNameorNumber(@PathVariable("token") String token, @RequestParam("shopname") String shopname, @RequestParam("shopnumber") String shopnumber) throws CureFullException {
        boolean b = hoboShopService.editShopNameorNumber(token, shopname, shopnumber);
        return ResponseBuilder.getSuccessResponse(b);
    }
}
