package com.hotbot.model.hoboshop;

import com.hotbot.model.AbstractDocument;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Document(collection = "hobo_shop_menu")
public class ImageData extends AbstractDocument {
    String shopUniqueId;
    private List<byte[]> arrays;

    public String getShopUniqueId() {
        return shopUniqueId;
    }

    public void setShopUniqueId(String shopUniqueId) {
        this.shopUniqueId = shopUniqueId;
    }

    public List<byte[]> getArrays() {
        return arrays;
    }

    public void setArrays(List<byte[]> arrays) {
        this.arrays = arrays;
    }
}
