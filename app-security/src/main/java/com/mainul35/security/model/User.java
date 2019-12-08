package com.mainul35.security.model;

import java.io.Serializable;
import java.util.Collection;

public interface User extends Serializable {

    public String getUsername();
    public String getPassword();
}
