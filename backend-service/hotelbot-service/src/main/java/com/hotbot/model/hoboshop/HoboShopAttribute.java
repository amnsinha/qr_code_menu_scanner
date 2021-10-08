package com.hotbot.model.hoboshop;

import com.hotbot.model.AbstractDocument;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Document(collection = "hobo_shop_menu")
public class HoboShopAttribute extends AbstractDocument {
    String shopName;
    String shopUniqueId;
    String shopContactNo;
    MultipartFile file;
    private List<byte[]> arrays;
    private List<String> filedata;

    public List<String> getFiledata() {
        return filedata;
    }

    public void setFiledata(List<String> filedata) {
        this.filedata = filedata;
    }

    public List<byte[]> getArrays() {
        return arrays;
    }

    public void setArrays(List<byte[]> arrays) {
        this.arrays = arrays;
    }

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

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
