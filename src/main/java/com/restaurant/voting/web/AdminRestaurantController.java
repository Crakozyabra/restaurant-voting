package com.restaurant.voting.web;

import com.restaurant.voting.to.restaurant.AdminRestaurantDto;
import com.restaurant.voting.to.restaurant.RestaurantWithoutMenuDto;
import com.restaurant.voting.service.RestaurantService;
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
import java.util.List;
import java.util.Objects;

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
    public ResponseEntity<String> create(
            @Valid @RequestBody RestaurantWithoutMenuDto restaurantWithoutMenuDto, BindingResult bindingResult) {
        ValidationUtil.checkNew(restaurantWithoutMenuDto);
        ResponseEntity<String> response = ValidationUtil.bindError(bindingResult);
        if (Objects.nonNull(response)) {
            return response;
        }
        RestaurantWithoutMenuDto created = adminRestaurantService.create(restaurantWithoutMenuDto);
        URI uriOfCreatedResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfCreatedResource).build();
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
    public ResponseEntity<String> update(
            @Valid @RequestBody RestaurantWithoutMenuDto restaurantWithoutMenuDto, BindingResult bindingResult) {
        Assert.isTrue(!restaurantWithoutMenuDto.isNew(), "id must be not null");
        ResponseEntity<String> response = ValidationUtil.bindError(bindingResult);
        if (Objects.nonNull(response)) {
            return response;
        }
        adminRestaurantService.update(restaurantWithoutMenuDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        adminRestaurantService.delete(id);
    }
}
