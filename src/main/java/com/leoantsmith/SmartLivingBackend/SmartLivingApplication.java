package com.leoantsmith.SmartLivingBackend;

import io.github.cdimascio.dotenv.Dotenv;
import com.leoantsmith.SmartLivingBackend.service.LightService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.logging.Logger;

import static java.util.Arrays.stream;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableScheduling
public class SmartLivingApplication {

    public static void main(final String[] args)  {
        SpringApplication.run(SmartLivingApplication.class, args);
    }
}
