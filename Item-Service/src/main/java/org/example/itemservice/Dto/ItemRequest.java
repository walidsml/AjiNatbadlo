package org.example.itemservice.Dto;


import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemRequest {
    private String title;
    private String description;
    private String category;
    private String preferences;
    private List<String> pictureUrls; // New field for picture URLs
}