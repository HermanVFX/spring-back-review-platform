package com.hermanvfx.springbackreviewplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SpringBackReviewPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBackReviewPlatformApplication.class, args);
    }

}
