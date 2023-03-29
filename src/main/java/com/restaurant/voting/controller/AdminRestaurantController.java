package com.restaurant.voting.controller;

import com.restaurant.voting.dto.restaurant.AdminRestaurantDto;
import com.restaurant.voting.dto.restaurant.RestaurantWithoutMenuDto;
import com.restaurant.voting.service.RestaurantService;
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
@RequestMapping(value = AdminRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestaurantController {

    public static final String REST_URL = "/admin/restaurants";

    private RestaurantService adminRestaurantService;

    @Autowired
    public AdminRestaurantController(RestaurantService restaurantService) {
        this.adminRestaurantService = restaurantService;
    }

    @PostMapping
    public ResponseEntity<RestaurantWithoutMenuDto> create(@RequestBody RestaurantWithoutMenuDto restaurantWithoutMenuDto) {
        Assert.notNull(restaurantWithoutMenuDto.getId(),"must not be null");
        RestaurantWithoutMenuDto created = adminRestaurantService.create(restaurantWithoutMenuDto);
        URI uriOfCreatedResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfCreatedResource).body(created);
    }

    @GetMapping("/{id}")
    public AdminRestaurantDto get(@PathVariable Integer id) {
        return adminRestaurantService.get(id);
    }

    @GetMapping
    public List<AdminRestaurantDto> getAllWithMenu() {
        return adminRestaurantService.getAllWithMenu();
    }

    @PutMapping
    public RestaurantWithoutMenuDto update(@RequestBody RestaurantWithoutMenuDto restaurantWithoutMenuDto) {
        Assert.isNull(restaurantWithoutMenuDto.getId(),"must be null");
        return adminRestaurantService.update(restaurantWithoutMenuDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        adminRestaurantService.delete(id);
    }
}
