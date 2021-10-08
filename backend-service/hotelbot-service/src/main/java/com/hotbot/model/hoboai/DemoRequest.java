package com.hotbot.model.hoboai;

import com.hotbot.model.AbstractDocument;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "demo_request")
public class DemoRequest extends AbstractDocument {
    private String name;
    private String emailorNumber;
    private String message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailorNumber() {
        return emailorNumber;
    }

    public void setEmailorNumber(String emailorNumber) {
        this.emailorNumber = emailorNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
