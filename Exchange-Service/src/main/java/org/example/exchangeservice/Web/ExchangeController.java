package org.example.exchangeservice.Web;

import org.example.exchangeservice.Dto.ExchangeDto;
import org.example.exchangeservice.Service.ExchangeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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



    @GetMapping("/receiver/{receiverId}")
    public List<ExchangeDto> getByReceiverId(@PathVariable String receiverId) {
        return exchangeService.findByReceiverId(receiverId);
    }

    @GetMapping("/sender/{senderId}")
    public List<ExchangeDto> getBySenderId(@PathVariable String senderId) {
        return exchangeService.findBySenderId(senderId);
    }


    @GetMapping("/status/{status}")  // New endpoint
    public List<ExchangeDto> getByStatus(@PathVariable String status) {
        return exchangeService.findByStatus(status);
    }


    @GetMapping("/sender/{senderId}/status/{status}")  // New endpoint
    public List<ExchangeDto> getBySenderIdAndStatus(@PathVariable String senderId, @PathVariable String status) {
        return exchangeService.findBySenderIdAndStatus(senderId, status);
    }



}
