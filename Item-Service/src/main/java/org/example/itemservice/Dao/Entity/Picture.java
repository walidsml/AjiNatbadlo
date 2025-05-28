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
    private String url;
    private String name;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;
}