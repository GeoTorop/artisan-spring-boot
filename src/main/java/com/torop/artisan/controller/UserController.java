package com.torop.artisan.controller;

import com.torop.artisan.model.User;
import com.torop.artisan.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/*@Controller
@RequiredArgsConstructor
public class UserConroller {
    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }
}*/

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String login(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "login";
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/profile/edit")
    public String editProfile(@RequestParam("name") String name, @RequestParam("phoneNumber") String phoneNumber, Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        userService.updateNameAndPhoneNumber(user, name, phoneNumber);
        return "redirect:/profile";
    }

    @GetMapping("/registration")
    public String registration(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(User user, Model model) {
        if (!userService.createUser(user)) {
            model.addAttribute("errorMessage", "Пользователь с email: " + user.getEmail() + " уже существует!");
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/user/{user}")
    public String userInfo(@PathVariable("user") User user, Model model, Principal principal) {
        model.addAttribute("user", user);
        model.addAttribute("userByPrincipal", userService.getUserByPrincipal(principal));
        model.addAttribute("products", user.getProducts());
        return "user-info";
    }
    /*
    //временная проверка работы security
    @GetMapping("/hello")
    public String securityUrl() {
        return "hello";
    }
    */
}
