//package web.controller;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import web.repos.RolesRepository;
//import web.repos.UserRepository;
//import web.service.UserService;
//
//import java.security.Principal;
//
//
//@Controller
//@RequestMapping("/admin")
//public class AdminController {
//
//    private final UserService userService;
//    private final RolesRepository rolesRepository;
//    private final UserRepository userRepository;
//
//
//    @Autowired
//    public AdminController(UserService usServ, RolesRepository rolesRepository, UserRepository userRepository) {
//        this.userService = usServ;
//        this.rolesRepository = rolesRepository;
//        this.userRepository = userRepository;
//    }
//
//
//    @GetMapping()
//    public String getAllUsers(Model model, Principal principal) {
//        model.addAttribute("users", userService.getAllUsers());
//        model.addAttribute("allRoles", rolesRepository.findAll());
//        model.addAttribute("newUser", userService.getUserByName(principal.getName()));
//        // все юзеры
//        return "admin";
//    }
//}