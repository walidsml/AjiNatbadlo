package org.example.exchangeservice.Dao.Repository;

import org.example.exchangeservice.Dao.Entity.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRepository extends JpaRepository<Exchange, Long> {
}
