package com.torop.artisan.controller;

import com.torop.artisan.model.Favorite;
import com.torop.artisan.model.User;
import com.torop.artisan.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class FavoriteController {
    private final FavoriteService favoriteService;

    @PostMapping("/addToFavorites/{productId}")
    public String addToFavorites(@PathVariable(name = "productId") Long productId, Principal principal) {
        favoriteService.addToFavorites(productId, principal);
        return "redirect:/favorites";
    }

    @GetMapping("/favorites")
    public String showFavorites(Model model, Principal principal) {
        User user = favoriteService.getUserByPrincipal(principal);
        List<Favorite> favorites = favoriteService.getFavoritesByUser(user);
        model.addAttribute("user", user);
        model.addAttribute("favorites", favorites);
        return "favorites";
    }

    /*private final FavoriteService favoriteService;

    @PostMapping("/favorite/add/{productId}")
    public String addToFavorites(@PathVariable Long productId, Principal principal) {
        favoriteService.addToFavorites(productId, principal);
        return "redirect:/";
    }

    @PostMapping("/favorite/remove/{productId}")
    public String removeFromFavorites(@PathVariable Long productId, Principal principal) {
        favoriteService.removeFromFavorites(productId, principal);
        return "redirect:/favorites";
    }

    @GetMapping("/favorites")
    public String favorites(Model model, Principal principal) {
        User user = favoriteService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("favorites", user.getProducts());
        return "favorites";
    }*/
}
