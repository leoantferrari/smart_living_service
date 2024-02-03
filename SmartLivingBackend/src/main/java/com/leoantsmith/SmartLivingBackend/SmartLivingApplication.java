package com.leoantsmith.SmartLivingBackend;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@Log4j2
@SpringBootApplication
@ConfigurationPropertiesScan
public class SmartLivingApplication {

    public static void main(final String[] args)  {
        SpringApplication.run(SmartLivingApplication.class, args);
    }
}
