package org.example.exchangeservice.Dao.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Exchange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ExchangeId;
    private String senderId;
    private String receiverId;
    private long SenderitemId;
    private long ReceiverItemId;
    private String status;
    private Date createdAt;
    private Date updatedAt;
}
