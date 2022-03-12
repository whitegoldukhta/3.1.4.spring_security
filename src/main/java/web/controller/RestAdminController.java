package web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
public class RestAdminController {

    private final UserService userService;

    public RestAdminController(UserService usServ) {
        this.userService = usServ;
    }


    @GetMapping("/admin/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @GetMapping("/admin/user")
    public ResponseEntity<User> showAdminByUsername(Principal principal) {
        User user = userService.getUserByName(principal.getName());
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/user/user")
    public ResponseEntity<User> showUserByUsername(Principal principal) {
        User user = userService.getUserByName(principal.getName());
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/admin/add")
    public ResponseEntity<User> create(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok().body(user);
    }


    @PutMapping("/admin/update")
    public ResponseEntity<User> update(@RequestBody User user) {
        userService.update(user);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}