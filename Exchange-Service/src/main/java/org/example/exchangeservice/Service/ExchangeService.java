package org.example.exchangeservice.Service;

import org.example.exchangeservice.Dao.Entity.Exchange;
import org.example.exchangeservice.Dto.ExchangeDto;

import java.util.List;


public interface ExchangeService {

    List<ExchangeDto> getAllExchanges();

    List<ExchangeDto> findByReceiverId(String receiverId);
    List<ExchangeDto> findBySenderId(String senderId);
    List<ExchangeDto> findByStatus(String status);  // New method
    List<ExchangeDto> findBySenderIdAndStatus(String senderId, String status);  // New method

//    ExchangeDto createExchange(ExchangeDto exchangeDto);

}
