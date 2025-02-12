package org.example.exchangeservice.Service;

import org.example.exchangeservice.Dao.Entity.Exchange;
import org.example.exchangeservice.Dao.Repository.ExchangeRepository;
import org.example.exchangeservice.Dto.ExchangeDto;
import org.example.exchangeservice.Mappers.ExchangeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExchangeServiceImpl implements ExchangeService {

    @Autowired
    private ExchangeRepository exchangeRepository;

    @Autowired
    private ExchangeMapper exchangeMapper;

    @Override
    public List<ExchangeDto> getAllExchanges() {
        // Retrieve all Exchange entities from the database
        List<Exchange> exchanges = exchangeRepository.findAll();

        // Map each Exchange entity to an ExchangeDto using the ExchangeMapper
        return exchanges.stream()
                .map(exchangeMapper::fromExchangetoExchangeDto)
                .collect(Collectors.toList());
    }

}
