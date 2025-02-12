package org.example.exchangeservice.Dto;

import lombok.*;

import java.util.Date;



@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExchangeDto {

    private long senderId;
    private long receiverId;
    private long itemId;
    private long requestedItemId;
    private String status;
    private Date createdAt;
    private Date updatedAt;

}

