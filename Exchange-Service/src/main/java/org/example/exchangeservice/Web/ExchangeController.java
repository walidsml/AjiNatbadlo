package org.example.exchangeservice.Web;

import org.example.exchangeservice.Dto.ExchangeDto;
import org.example.exchangeservice.Service.ExchangeService;
import org.example.exchangeservice.Service.ExchangeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/exchanges")
public class ExchangeController {

    @Autowired
    private ExchangeServiceImpl exchangeService;


    @GetMapping
    public List<ExchangeDto> getAllExchanges() {
        return exchangeService.getAllExchanges();
    }
}
