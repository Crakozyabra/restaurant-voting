package com.restaurant.voting.web;

import com.restaurant.voting.to.user.UserDto;
import com.restaurant.voting.service.UserService;
import com.restaurant.voting.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Objects;

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
    public ResponseEntity<String> create(@Valid @RequestBody UserDto userDto, BindingResult bindingResult){
        ValidationUtil.checkNew(userDto);
        ResponseEntity<String> response = ValidationUtil.bindError(bindingResult);
        if (Objects.nonNull(response)) {
            return response;
        }
        UserDto created = userService.create(userDto);
        URI uriOfCreatedResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfCreatedResource).build();
    }

    @GetMapping("/{id}")
    public UserDto get(@PathVariable Integer id) {
        return userService.get(id);
    }

    @GetMapping
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    @PutMapping
    public ResponseEntity<String> update(@Valid @RequestBody UserDto userDto, BindingResult bindingResult){
        Assert.isTrue(!userDto.isNew(), "id must be not null");
        ResponseEntity<String> response = ValidationUtil.bindError(bindingResult);
        if (Objects.nonNull(response)) {
            return response;
        }
        userService.update(userDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        userService.delete(id);
    }
}
