package com.mainul35.security.enums;

import java.util.ArrayList;
import java.util.List;

public enum Role {
    VENDOR(1, "Vendor"),
    CUSTOMER(2, "Customer"),
    ALL(3, "All");

    private final int code;
    private final String value;

    Role(int code, String value) {
        this.code = code;
        this.value = value;
    }


    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Role{" +
                "code=" + code +
                ", value='" + value + '\'' +
                '}';
    }
}
