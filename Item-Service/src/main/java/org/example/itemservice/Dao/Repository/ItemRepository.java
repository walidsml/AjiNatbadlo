package org.example.itemservice.Dao.Repository;

import org.example.itemservice.Dao.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
