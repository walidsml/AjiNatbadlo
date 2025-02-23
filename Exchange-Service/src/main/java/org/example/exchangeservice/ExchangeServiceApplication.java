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
                    .senderId("36ee49fd-6dc0-4f54-ad2a-bb38f987bee9")
                    .receiverId("53e85e3a-8d19-4418-a837-dfe60f3cc209")
                    .SenderitemId(101)
                    .ReceiverItemId(202)
                    .status("Pending")
                    .createdAt(new Date())
                    .updatedAt(new Date())
                    .build();



            // Create the second Exchange instance using the builder
            Exchange exchange2 = Exchange.builder()
                    .senderId("53e85e3a-8d19-4418-a837-dfe60f3cc209")
                    .receiverId("36ee49fd-6dc0-4f54-ad2a-bb38f987bee9")
                    .SenderitemId(103)
                    .ReceiverItemId(204)
                    .status("Completed")
                    .createdAt(new Date())
                    .updatedAt(new Date())
                    .build();

            Exchange exchange3 = Exchange.builder()
                    .senderId("36ee49fd-6dc0-4f54-ad2a-bb38f987bee9")
                    .receiverId("53e85e3a-8d19-4418-a837-dfe60f3cc209")
                    .SenderitemId(101)
                    .ReceiverItemId(202)
                    .status("Rejected")
                    .createdAt(new Date())
                    .updatedAt(new Date())
                    .build();


            repository.save(exchange1);
            repository.save(exchange2);
            repository.save(exchange3);
        };
    }
}
