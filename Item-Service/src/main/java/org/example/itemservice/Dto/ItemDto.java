package org.example.itemservice.Dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemDto {

        private long id;
        private String userId;
        private String title;
        private String description;
        private String category;
        private String preferences;
        private Date createdAt;
        private Date updatedAt;
        private List<PictureDto> pictures;
}
