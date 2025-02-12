package org.example.itemservice.Dao.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String url; // URL of the picture
    private String name; // Optional: Name or description of the picture

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;
}