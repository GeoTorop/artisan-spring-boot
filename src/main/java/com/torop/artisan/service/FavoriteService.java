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
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public Favorite addToFavorites(Long productId, Principal principal) {
        Product product = productRepository.findById(productId).get(); // delete .get & add .orElse(null);
        User user = getUserByPrincipal(principal);
        List<Favorite> favoriteList = favoriteRepository.findByUser(user); // for duplicated products
        List<Favorite> filteredList = favoriteList.stream().filter(x -> x.getProduct().getId() == productId).collect(Collectors.toList());
        if (filteredList.size() > 0) {
            return null;
        }
        if (product != null && user != null) {
            Favorite favorite = new Favorite(user, product);
            favoriteRepository.save(favorite);
        }
        return null;// added with favoriteList & filteredList
    }

    public void removeFromFavorites(Long productId) {
        favoriteRepository.deleteById(productId);
    }

    public boolean isProductInFavorites(Long productId, User user) {
        return favoriteRepository.existsByProduct_IdAndUser(productId, user);
    }

    public List<Favorite> getFavoritesByUser(User user) {
        return favoriteRepository.findByUser(user);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) {
            return new User(); // was: return null
        }
        return userRepository.findByEmail(principal.getName());
    }
}
