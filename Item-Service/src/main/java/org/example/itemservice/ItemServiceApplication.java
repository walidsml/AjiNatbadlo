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
                            .userId("57eb629d-3071-4181-91bd-49a39646a0e0") // amin
                            .title("Vintage 1957 Gibson LG-2 Acoustic Guitar")
                            .description("A classic acoustic guitar from 1957, known for its rich tone and historical value.")
                            .category("Musical Instruments")
                            .preferences("Looking to trade for a vintage electric guitar or high-quality amplifier.")
                            .createdAt(new Date())
                            .updatedAt(new Date())
                            .build(),
                    Item.builder()
                            .userId("36ee49fd-6dc0-4f54-ad2a-bb38f987bee9") // walid
                            .title("Trek Full Stache Mountain Bike")
                            .description("A full suspension 29+ mountain bike, perfect for trail enthusiasts seeking adventure.")
                            .category("Sports Equipment")
                            .preferences("Interested in trading for a road bike or high-end cycling gear.")
                            .createdAt(new Date())
                            .updatedAt(new Date())
                            .build(),
                    Item.builder()
                            .userId("53e85e3a-8d19-4418-a837-dfe60f3cc209") // user3
                            .title("Antique Long Case Wall Clock")
                            .description("An elegant antique wall clock from the 19th century, crafted by renowned clockmaker.")
                            .category("Antiques")
                            .preferences("Seeking vintage furniture or collectible art pieces.")
                            .createdAt(new Date())
                            .updatedAt(new Date())
                            .build(),
                    Item.builder()
                            .userId("57eb629d-3071-4181-91bd-49a39646a0e0") // amin
                            .title("Designer Floral Top Handle Handbag")
                            .description("A stylish designer handbag featuring a unique floral design, perfect for fashion enthusiasts.")
                            .category("Fashion Accessories")
                            .preferences("Looking to trade for high-end shoes or jewelry.")
                            .createdAt(new Date())
                            .updatedAt(new Date())
                            .build(),
                    Item.builder()
                            .userId("36ee49fd-6dc0-4f54-ad2a-bb38f987bee9") // walid
                            .title("Microsoft Xbox 360 Gaming Console")
                            .description("A popular gaming console with a vast library of games, ideal for entertainment.")
                            .category("Electronics")
                            .preferences("Interested in trading for a PlayStation console or gaming accessories.")
                            .createdAt(new Date())
                            .updatedAt(new Date())
                            .build(),
                    Item.builder()
                            .userId("53e85e3a-8d19-4418-a837-dfe60f3cc209") // user3
                            .title("ASUS 14.0\" Laptop with Intel Celero")
                            .description("A compact and efficient laptop suitable for everyday tasks and browsing.")
                            .category("Computers")
                            .preferences("Looking to trade for a tablet or smartphone of equivalent value.")
                            .createdAt(new Date())
                            .updatedAt(new Date())
                            .build(),
                    Item.builder()
                            .userId("57eb629d-3071-4181-91bd-49a39646a0e0") // amin
                            .title("Canon EOS R50 Mirrorless Camera")
                            .description("A high-quality mirrorless camera offering exceptional image quality for photography enthusiasts.")
                            .category("Photography")
                            .preferences("Seeking to trade for professional lighting equipment or a drone.")
                            .createdAt(new Date())
                            .updatedAt(new Date())
                            .build(),
                    Item.builder()
                            .userId("36ee49fd-6dc0-4f54-ad2a-bb38f987bee9") // walid
                            .title("Hyper 29\" Explorer Men's Dual")
                            .description("A robust mountain bike designed for off-road adventures, featuring dual suspension.")
                            .category("Sports Equipment")
                            .preferences("Interested in trading for camping gear or a kayak.")
                            .createdAt(new Date())
                            .updatedAt(new Date())
                            .build()
            );

// Add pictures to each item
            items.get(0).setPictures(
                    List.of(
                            Picture.builder().url("https://cdn11.bigcommerce.com/s-dks6ju/images/stencil/1280x1280/products/26577/287692/a537071a-453e-55a1-a761-3ba0fd3dcab5__79712.1716047868.jpg?c=2").name("Guitar Front View").item(items.get(0)).build(),
                            Picture.builder().url("https://truevintageguitar.com/cdn/shop/articles/1957-Gibson-LG-2-U439-004.jpg?v=1592316612&width=2564").name("Guitar Back View").item(items.get(0)).build()
                    )
            );

            items.get(1).setPictures(
                    List.of(
                            Picture.builder().url("https://hips.hearstapps.com/hmg-prod/images/trek-full-stache-main-1522937009.jpg").name("Bike Side View").item(items.get(1)).build(),
                            Picture.builder().url("https://singletrackworld.com/wp-content/uploads/2018/04/wsi-imageoptim-Milner_FullStache20.jpg").name("Bike in Action").item(items.get(1)).build()
                    )
            );

            items.get(2).setPictures(
                    List.of(
                            Picture.builder().url("https://onthesquareemporium.com/wp-content/uploads/2020/01/IMG_0640-scaled.jpg").name("Clock Full View").item(items.get(2)).build(),
                            Picture.builder().url("https://cdn20.pamono.com/p/g/2/0/2049186_s29q92ueeh/antique-victorian-walnut-vienna-wall-clock-by-gustav-becker-1860s-2.jpg").name("Clock Close-up").item(items.get(2)).build()
                    )
            );

            items.get(3).setPictures(
                    List.of(
                            Picture.builder().url("https://s.alicdn.com/@sc04/kf/H5d32321d77a6443aa363eaf0ffad83101.jpeg_720x720q50.jpg").name("Handbag Front View").item(items.get(3)).build()
                    )
            );

            items.get(4).setPictures(
                    List.of(
                            Picture.builder().url("https://img.olx.com.br/images/15/156512735684260.jpg").name("Xbox 360 Console").item(items.get(4)).build()
                    )
            );

            items.get(5).setPictures(
                    List.of(
                            Picture.builder().url("https://i.ebayimg.com/images/g/InIAAOSwViRmoWzU/s-l1200.jpg").name("ASUS Laptop Front View").item(items.get(5)).build()
                    )
            );

            items.get(6).setPictures(
                    List.of(
                            Picture.builder().url("https://www.photobohemian.com/wp-content/uploads/Canon-R50-title.jpg").name("Canon EOS R50 Front View").item(items.get(6)).build()
                    )
            );

            items.get(7).setPictures(
                    List.of(
                            Picture.builder().url("https://i.ebayimg.com/images/g/YFwAAOSwkfFiJ8SE/s-l1200.jpg").name("Hyper 29\" Explorer Full View").item(items.get(7)).build()
                    )
            );

            itemRepository.saveAll(items);


        };
}}