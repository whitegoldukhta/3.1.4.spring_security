package web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/admin")
public class RestAdminController {

    private final UserService userService;

    public RestAdminController(UserService usServ) {
        this.userService = usServ;
    }


    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        // все юзеры
        return userService.getAllUsers();
    }

    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/showUserByUsername")
    public User showUserByUsername(Principal principal){
        return userService.getUserByName(principal.getName());
    }

    @PostMapping("/create")
    public void create(@RequestBody User user) {
        userService.save(user);
    }


    @PutMapping("/update")
    public User update(@RequestBody User user) {
        userService.update(user);
        return user;
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        userService.delete(id);
    }
}