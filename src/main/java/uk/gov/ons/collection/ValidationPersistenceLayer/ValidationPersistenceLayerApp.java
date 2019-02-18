package uk.gov.ons.collection.ValidationPersistenceLayer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
public class ValidationPersistenceLayerApp {
    public static void main(String[] args) {
        {
            SpringApplication.run(uk.gov.ons.collection.ValidationPersistenceLayer.ValidationPersistenceLayerApp.class, args);
        }

    }
}
