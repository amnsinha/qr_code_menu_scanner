package com.hotbot.model.adminModel;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class PaymentFiles {
    List<FilesInfo> files;
    String hotelId;
    String name;
    String singleFileName;
    MultipartFile file;

    public String getSingleFileName() {
        return singleFileName;
    }

    public void setSingleFileName(String singleFileName) {
        this.singleFileName = singleFileName;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public List<FilesInfo> getFiles() {
        return files;
    }

    public void setFiles(List<FilesInfo> files) {
        this.files = files;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
