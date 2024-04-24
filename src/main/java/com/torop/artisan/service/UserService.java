package com.torop.artisan.service;

import com.torop.artisan.model.Favorite;
import com.torop.artisan.model.Product;
import com.torop.artisan.model.User;
import com.torop.artisan.model.enums.Role;
import com.torop.artisan.repository.FavoriteRepository;
import com.torop.artisan.repository.ProductRepository;
import com.torop.artisan.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final FavoriteRepository favoriteRepository;

    public boolean createUser(User user) {
        String email = user.getEmail();
        if (userRepository.findByEmail(email) != null) return false;
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_USER);
        log.info("Saving new User with email: {}", email);
        userRepository.save(user);
        return true;
    }

    public List<User> list() {
        return userRepository.findAll();
    }

    @Transactional
    public void banUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            if (user.isActive()) {
                user.setActive(false);
                log.info("Ban user with id = {}; email: {}", user.getId(), user.getEmail());

                List<Product> userProducts = user.getProducts();
                for (Product product : userProducts) {
                    for (Favorite favorite : product.getFavorites()) {
                        if (favorite.getUser().getId().equals(user.getId())) {
                            product.removeFavorite(favorite);
                            favoriteRepository.delete(favorite); // remove from favorites; bug 2
                        }
                    }
                    productRepository.delete(product);
                }
                userProducts.clear();
            } else {
                user.setActive(true);
                log.info("Unban user with id = {}; email: {}", user.getId(), user.getEmail());
            }
            userRepository.save(user);
        }
        //userRepository.save(user);
    }

    public void updateNameAndPhoneNumber(User user, String name, String phoneNumber) {
        user.setName(name);
        user.setPhoneNumber(phoneNumber);
        userRepository.save(user);
    }

    public void changeUserRoles(User user, Map<String, String> form) {
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepository.save(user);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }
}
