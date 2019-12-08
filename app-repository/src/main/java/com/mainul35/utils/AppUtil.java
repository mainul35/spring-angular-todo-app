package com.mainul35.utils;

import com.mainul35.repository.CriteriaQueryRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppUtil {

    public static Properties getBuiltProperties(String propertyFileName) {
        Properties properties = new Properties();
        InputStream input = CriteriaQueryRepository.class
                .getClassLoader().getResourceAsStream(propertyFileName);
        try {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }
}
