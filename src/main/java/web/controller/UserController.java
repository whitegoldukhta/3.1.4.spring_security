package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {


    @GetMapping(value = "/admin/page")
    public String getAdminPage() {
        return "admin";
    }

    @GetMapping(value = "/user/page")
    public String getUserPage() {
        return "user";
    }
}
