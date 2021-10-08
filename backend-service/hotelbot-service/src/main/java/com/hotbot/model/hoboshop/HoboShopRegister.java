package com.hotbot.model.hoboshop;

import com.hotbot.model.AbstractDocument;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Document(collection = "hobo_shop_info")
public class HoboShopRegister extends AbstractDocument {
    String shopName;
    String shopUniqueId;
    String shopContactNo;
    String shopUserName;
    String shopPassword;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopUniqueId() {
        return shopUniqueId;
    }

    public void setShopUniqueId(String shopUniqueId) {
        this.shopUniqueId = shopUniqueId;
    }

    public String getShopContactNo() {
        return shopContactNo;
    }

    public void setShopContactNo(String shopContactNo) {
        this.shopContactNo = shopContactNo;
    }

    public String getShopUserName() {
        return shopUserName;
    }

    public void setShopUserName(String shopUserName) {
        this.shopUserName = shopUserName;
    }

    public String getShopPassword() {
        return shopPassword;
    }

    public void setShopPassword(String shopPassword) {
        this.shopPassword = shopPassword;
    }
}
