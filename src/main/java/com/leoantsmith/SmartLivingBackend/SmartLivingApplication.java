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
    private final Logger logger = Logger.getLogger(LightService.class.getName());

    public static void main(final String[] args)  {
        dotEnvSafeCheck();
        SpringApplication.run(SmartLivingApplication.class, args);
    }

    private static void dotEnvSafeCheck() {
        final var dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .load();

        stream(DotEnv.values())
                .map(DotEnv::name)
                .filter(varName -> dotenv.get(varName, "").isEmpty())
                .findFirst()
                .ifPresent(varName -> {
                    throw new RuntimeException("MISSING ENV");
                });
    }

    enum DotEnv {
        THE_DB,
        DB_USER,
        DB_PASSWORD
    }
}
