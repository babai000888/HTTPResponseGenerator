package ru.babai.http.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Level;
import java.util.logging.Logger;

@Configuration
public class SwaggerConfiguration {
    private static final Logger log = Logger.getLogger(SwaggerConfiguration.class.getName());

    @Value("${server.port}")
    private String serverPort;
    @Value("${springdoc.swagger-ui.path}")
    private String uiPath;

    @Bean
    public void swaggerUI() {
        log.log(Level.WARNING, "Swagger UI: http://localhost:{0}/{1} ", new Object[] {serverPort, uiPath});
    }
}
