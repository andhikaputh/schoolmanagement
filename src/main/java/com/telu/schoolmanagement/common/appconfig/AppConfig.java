package com.telu.schoolmanagement.common.appconfig;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class AppConfig implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    public static Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

    @Override
    public void initialize(ConfigurableApplicationContext context) {
        System.setProperty("DB_URL", dotenv.get("DB_URL"));
        System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
        System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
        System.setProperty("REDIS_HOST", dotenv.get("REDIS_HOST"));
        System.setProperty("REDIS_PORT", dotenv.get("REDIS_PORT"));
    }

}