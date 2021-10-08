package com.hotbot.model;

import java.util.Date;

public class AbstractDocument {

    protected String _id;
    protected Date createdOn = getCreatedDate();
    protected String createdBy;
    protected String updatedBy;
    protected Date updatedOn;

    public Date getCreatedDate() {
        return new Date();
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn() {
        this.createdOn = getCreatedDate();
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

}
