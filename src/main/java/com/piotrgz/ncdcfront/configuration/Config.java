package com.piotrgz.ncdcfront.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Value("${books.baseurl}")
    private String baseUrl;

    public String getBaseUrl() {
        return baseUrl;
    }
}
