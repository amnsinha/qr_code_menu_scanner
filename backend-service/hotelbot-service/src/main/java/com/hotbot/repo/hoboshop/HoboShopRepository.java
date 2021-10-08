package com.hotbot.repo.hoboshop;

import com.hotbot.exception.MongoDBDocumentNotFoundException;
import com.hotbot.model.hoboshop.HoboShopAttribute;
import com.hotbot.model.hoboshop.HoboShopRegister;

public interface HoboShopRepository {
    boolean saveShopInfo(HoboShopAttribute imageData) throws MongoDBDocumentNotFoundException;

    HoboShopAttribute getShopInfo(String token);

    boolean editShopNameorNumber(String token, String shopname, String shopnumber);

    String login(String username, String password);

    boolean registerShop(HoboShopRegister hoboShopRegister);

}
