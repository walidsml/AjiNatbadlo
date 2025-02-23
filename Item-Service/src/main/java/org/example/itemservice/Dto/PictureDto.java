package org.example.itemservice.Dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PictureDto {
        private long id;
        private String url;
        private String name;
        private long itemId; // Include item ID for reference

}
