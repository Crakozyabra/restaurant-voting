package com.restaurant.voting.controller;

import com.restaurant.voting.dto.user.UserDto;
import com.restaurant.voting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = UserController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    public static final String REST_URL = "/admin/users";

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDto create(@RequestBody UserDto userDto){
        Assert.isNull(userDto.getId(),"must be null");
        return userService.create(userDto);
    }

    @GetMapping("/{id}")
    public UserDto get(@PathVariable Integer id) {
        return userService.get(id);
    }

    @GetMapping("/by-email")
    public UserDto getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    @PutMapping
    public UserDto update(@RequestBody UserDto userDto){
        Assert.notNull(userDto.getId(),"must not be null");
        return userService.create(userDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        userService.delete(id);
    }
}
