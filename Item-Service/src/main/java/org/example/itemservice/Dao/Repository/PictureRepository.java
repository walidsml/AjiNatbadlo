package org.example.itemservice.Dao.Repository;

import org.example.itemservice.Dao.Entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture, Long> {
}
