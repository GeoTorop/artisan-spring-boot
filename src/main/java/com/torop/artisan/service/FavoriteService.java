package com.torop.artisan.service;

import com.torop.artisan.model.Favorite;
import com.torop.artisan.model.Product;
import com.torop.artisan.model.User;
import com.torop.artisan.repository.FavoriteRepository;
import com.torop.artisan.repository.ProductRepository;
import com.torop.artisan.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public void addToFavorites(Long productId, Principal principal) {
        Product product = productRepository.findById(productId).get();
        User user = getUserByPrincipal(principal);
        if (product != null && user != null) {
            Favorite favorite = new Favorite(user, product);
            favoriteRepository.save(favorite);
        }
    }

    public List<Favorite> getFavoritesByUser(User user) {
        return favoriteRepository.findByUser(user);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) {
            return null;
        }
        return userRepository.findByEmail(principal.getName());
    }

    /*private final FavoriteRepository favoriteRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public void addToFavorites(Long productId, Principal principal) {
        Product product = productRepository.findById(productId).orElse(null);
        User user = getUserByPrincipal(principal);
        if (product != null && user != null) {
            Favorite favorite = new Favorite(user, product);
            favoriteRepository.save(favorite);
        }
    }

    public void removeFromFavorites(Long productId, Principal principal) {
        Product product = productRepository.findById(productId).orElse(null);
        User user = getUserByPrincipal(principal);
        if (product != null && user != null) {
            Favorite favorite = favoriteRepository.findByUserAndProduct(user, product);
            if (favorite != null) {
                favoriteRepository.delete(favorite);
            }
        }
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return null;
        return userRepository.findByEmail(principal.getName());
    }*/
}
