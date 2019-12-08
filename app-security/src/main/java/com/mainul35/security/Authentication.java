package com.mainul35.security;

import com.mainul35.security.model.User;

public interface Authentication {
    public String getAccessToken();
    public User getAuthenticatedUser();

}
