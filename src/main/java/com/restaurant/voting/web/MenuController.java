package com.restaurant.voting.web;

import com.restaurant.voting.service.MenuService;
import com.restaurant.voting.to.menu.AdminMenuDto;
import com.restaurant.voting.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Objects;

@RestController
@RequestMapping(value = MenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuController {

    public static final String REST_URL = "/admin/menu";

    private MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody AdminMenuDto adminMenuDto, BindingResult bindingResult) {
        ValidationUtil.checkNew(adminMenuDto);
        ResponseEntity<String> response = ValidationUtil.bindError(bindingResult);
        if (Objects.nonNull(response)) {
            return response;
        }
        AdminMenuDto created = menuService.create(adminMenuDto);
        URI uriOfCreatedResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfCreatedResource).build();
    }

    @GetMapping("/{id}")
    public AdminMenuDto get(@PathVariable Integer id) {
        return menuService.get(id);
    }

    @PutMapping
    public ResponseEntity<String> update(@Valid @RequestBody AdminMenuDto adminMenuDto, BindingResult bindingResult) {
        Assert.isTrue(!adminMenuDto.isNew(), "id must be not null");
        ResponseEntity<String> response = ValidationUtil.bindError(bindingResult);
        if (Objects.nonNull(response)) {
            return response;
        }
        menuService.update(adminMenuDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        menuService.delete(id);
    }
}
