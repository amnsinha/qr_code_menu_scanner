package com.hotbot.service.hoboshop;

import com.hotbot.model.hoboshop.HoboShopAttribute;
import com.hotbot.model.hoboshop.HoboShopRegister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HoboShopService {
    boolean saveShopInfo(List<String> image, String token);

    HoboShopAttribute getShopInfo(String token);

    boolean editShopNameorNumber(String token, String shopname, String shopnumber);

    String login(String username, String password);

    boolean registerShop(HoboShopRegister hoboShopRegister);
}
