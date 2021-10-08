package com.hotbot.model.customerapplication;

import com.hotbot.model.AbstractDocument;

public class PageSettings {
    private String aboutus;
    private String contactus;
    private String contactEmailForCustomer;
    private String contactNoforCustomer;
    private String contactNoforCustomer2;

    public String getContactNoforCustomer2() {
        return contactNoforCustomer2;
    }

    public void setContactNoforCustomer2(String contactNoforCustomer2) {
        this.contactNoforCustomer2 = contactNoforCustomer2;
    }

    public String getContactEmailForCustomer() {
        return contactEmailForCustomer;
    }

    public void setContactEmailForCustomer(String contactEmailForCustomer) {
        this.contactEmailForCustomer = contactEmailForCustomer;
    }

    public String getContactNoforCustomer() {
        return contactNoforCustomer;
    }

    public void setContactNoforCustomer(String contactNoforCustomer) {
        this.contactNoforCustomer = contactNoforCustomer;
    }

    public String getAboutus() {
        return aboutus;
    }

    public void setAboutus(String aboutus) {
        this.aboutus = aboutus;
    }

    public String getContactus() {
        return contactus;
    }

    public void setContactus(String contactus) {
        this.contactus = contactus;
    }
}
