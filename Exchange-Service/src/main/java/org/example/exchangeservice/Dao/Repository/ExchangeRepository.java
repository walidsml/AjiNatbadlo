package org.example.exchangeservice.Dao.Repository;

import org.example.exchangeservice.Dao.Entity.Exchange;
import org.example.exchangeservice.Dto.ExchangeDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExchangeRepository extends JpaRepository<Exchange, Long> {

    List<Exchange> findByReceiverId(String receiverId);
    List<Exchange> findBySenderId(String senderId);
    List<Exchange> findByStatus(String status);  // New method
    List<Exchange> findBySenderIdAndStatus(String senderId, String status);  // New method

}
