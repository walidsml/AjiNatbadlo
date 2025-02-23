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

    private String senderId;
    private String receiverId;
    private long SenderitemId;
    private long ReceiverItemId;
    private String status;
    private Date createdAt;
    private Date updatedAt;
}

