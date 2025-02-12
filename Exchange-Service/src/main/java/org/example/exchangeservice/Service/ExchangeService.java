package org.example.exchangeservice.Service;

import org.example.exchangeservice.Dao.Entity.Exchange;
import org.example.exchangeservice.Dto.ExchangeDto;

import java.util.List;

public interface ExchangeService {

    List<ExchangeDto> getAllExchanges();

//    ExchangeDto createExchange(ExchangeDto exchangeDto);

}
