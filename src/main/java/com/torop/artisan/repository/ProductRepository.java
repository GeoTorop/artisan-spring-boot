package com.torop.artisan.repository;

import com.torop.artisan.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByTitle(String title);
    List<Product> findByTitleContaining(String searchWord);
    List<Product> findByTitleContainingAndCity(String searchWord, String searchCity);

    @Query("SELECT p FROM Product p WHERE p.city = :city")
    List<Product> findByCity(@Param("city") String searchCity);
}