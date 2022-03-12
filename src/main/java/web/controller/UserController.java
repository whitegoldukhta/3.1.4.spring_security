package web.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.model.User;
import web.service.UserDetailsServiceImpl;
import web.service.UserService;
import web.service.UserServiceImpl;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    private User viewUser(@AuthenticationPrincipal UserService user) {
        return user.getUser();
    }

}
