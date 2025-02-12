package org.example.exchangeservice.Mappers;

import org.example.exchangeservice.Dao.Entity.Exchange;
import org.example.exchangeservice.Dto.ExchangeDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ExchangeMapper {

    private final ModelMapper mapper = new ModelMapper();

    public Exchange fromExchangeDtoExchange(ExchangeDto exchangeDto) {
        return mapper.map(exchangeDto, Exchange.class);
    }
    public ExchangeDto fromExchangetoExchangeDto(Exchange exchange) {
        return mapper.map(exchange, ExchangeDto.class);
    }


}
