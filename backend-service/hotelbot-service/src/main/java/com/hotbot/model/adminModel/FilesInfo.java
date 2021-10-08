package com.hotbot.model.adminModel;

import java.util.Random;

public class FilesInfo {
    String fileLink;
    String files;
    String name;
    Integer id = this.generateRandomItemTypeNo();

    public Integer generateRandomItemTypeNo() {
        return new Random().nextInt(2009920000);
    }

    public String getFileLink() {
        return fileLink;
    }

    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
