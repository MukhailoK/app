package de.ait.app.controllers;

import de.ait.app.model.User;
import de.ait.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserControllerWeb {
    @Autowired
    UserService userService;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/register")
    public String blablabla() {
        return "signup.html";
    }

    @PostMapping("/users")
    public String createUser(@RequestParam("user_name") String name, @RequestParam String email) {
        System.out.println("Start add user: " + name + " " + email);
        userService.createUser(name, email);
        return "addUserSuccess";
    }
    @GetMapping("/users")
    public String getAllUsers(Model model){
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "userList";
    }
}
