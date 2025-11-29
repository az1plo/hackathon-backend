package com.hackathonbackend.api;

import com.hackathonbackend.models.dto.UserDTO;
import com.hackathonbackend.models.entity.User;
import com.hackathonbackend.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDTO loginOrRegister(@RequestBody java.util.Map<String, String> body) {
        String name = body.get("name");
        User user = userService.findOrCreateUser(name);
        return userService.getUserDTO(user.getId());
    }

    @GetMapping("/{userId}")
    public UserDTO getUser(@PathVariable Long userId) {
        return userService.getUserDTO(userId);
    }
}
