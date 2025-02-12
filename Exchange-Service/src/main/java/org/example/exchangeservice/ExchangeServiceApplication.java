package org.example.exchangeservice;

import org.example.exchangeservice.Dao.Entity.Exchange;
import org.example.exchangeservice.Dao.Repository.ExchangeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
@EnableDiscoveryClient
public class ExchangeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExchangeServiceApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(ExchangeRepository repository) {
        return args -> {
            Exchange exchange1 = Exchange.builder()
                    .senderId(1)
                    .receiverId(2)
                    .itemId(101)
                    .requestedItemId(202)
                    .status("Pending")
                    .createdAt(new Date())
                    .updatedAt(new Date())
                    .build();

            // Create the second Exchange instance using the builder
            Exchange exchange2 = Exchange.builder()
                    .senderId(3)
                    .receiverId(4)
                    .itemId(103)
                    .requestedItemId(204)
                    .status("Completed")
                    .createdAt(new Date())
                    .updatedAt(new Date())
                    .build();

            repository.save(exchange1);
            repository.save(exchange2);
        };
    }
}
