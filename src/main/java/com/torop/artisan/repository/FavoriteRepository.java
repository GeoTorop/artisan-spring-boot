package com.torop.artisan.repository;

import com.torop.artisan.model.Favorite;
import com.torop.artisan.model.Product;
import com.torop.artisan.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    //Favorite findByUserAndProduct(User user, Product product);
    List<Favorite> findByUser(User user);
}
