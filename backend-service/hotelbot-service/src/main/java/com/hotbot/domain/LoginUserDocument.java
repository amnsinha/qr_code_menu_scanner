package com.hotbot.domain;

import com.hotbot.model.AbstractDocument;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection = "user_login_details")
public class LoginUserDocument extends AbstractDocument {

    @Field("user_id")
    private String userId;

    @Field("password")
    private String password;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
