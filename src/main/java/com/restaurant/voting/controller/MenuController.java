package com.restaurant.voting.controller;

import com.restaurant.voting.dto.menu.AdminMenuDto;
import com.restaurant.voting.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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
    public ResponseEntity<AdminMenuDto> create(@RequestBody AdminMenuDto adminMenuDto) {
        Assert.isNull(adminMenuDto.getId(), "must be null");
        AdminMenuDto created = menuService.create(adminMenuDto);
        URI uriOfCreatedResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfCreatedResource).body(created);
    }

    @GetMapping("/by-price")
    List<AdminMenuDto> getAllByPriceBetween(@RequestParam Double startPrice, @RequestParam Double endPrice) {
        return menuService.getAllByPriceBetween(startPrice, endPrice);
    }

    @GetMapping("/page")
    List<AdminMenuDto> getAllPaged(@RequestParam Integer start, @RequestParam Integer limit) {
        return menuService.getAllPaged(start, limit);
    }

    @PutMapping
    public AdminMenuDto update(@RequestBody AdminMenuDto adminMenuDto) {
        Assert.notNull(adminMenuDto.getId(), "must not be null");
        return menuService.update(adminMenuDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        menuService.delete(id);
    }
}
