package org.example.itemservice;

import org.example.itemservice.Dao.Entity.Item;
import org.example.itemservice.Dao.Entity.Picture;
import org.example.itemservice.Dao.Repository.ItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class ItemServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItemServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(ItemRepository itemRepository) {
        return args -> {
            List<Item> items = List.of(
                    Item.builder()
                            .title("Sample Item 1")
                            .description("Sample Description 1")
                            .category("Category A")
                            .preferences("Preferences 1")
                            .createdAt(new Date())
                            .updatedAt(new Date())
                            .build(),
                    Item.builder()
                            .title("Sample Item 2")
                            .description("Sample Description 2")
                            .category("Category B")
                            .preferences("Preferences 2")
                            .createdAt(new Date())
                            .updatedAt(new Date())
                            .build()
            );
            // Add pictures to each item
            items.get(0).setPictures(
                    List.of(
                            Picture.builder().url("https://www.datocms-assets.com/119921/1714077019-mv-agusta-superveloce-2020-review-price-spec_25.jpg?auto=format&w=740").name("Item 1 Pic 1").item(items.get(0)).build(),
                            Picture.builder().url("https://images.ctfassets.net/h6goo9gw1hh6/2sNZtFAWOdP1lmQ33VwRN3/24e953b920a9cd0ff2e1d587742a2472/1-intro-photo-final.jpg?w=1200&h=992&fl=progressive&q=70&fm=jpg").name("Item 1 Pic 2").item(items.get(0)).build()

                    )
            );


            items.get(1).setPictures(
                    List.of(
                            Picture.builder().url("https://images.medialinksonline.com/imagestream/38817/7209032x1024x0_FFFFFF_L_0.jpg").name("Item 2 Pic 1").item(items.get(1)).build()
                    )
            );
            itemRepository.saveAll(items);
        };
    }
}