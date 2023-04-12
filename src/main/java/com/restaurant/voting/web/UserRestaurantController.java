package com.restaurant.voting.web;

import com.restaurant.voting.to.restaurant.UserRestaurantDto;
import com.restaurant.voting.service.RestaurantWithVisibleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = UserRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestaurantController {

    public static final String REST_URL = "/user/restaurants";

    private RestaurantWithVisibleMenuService restaurantWithVisibleMenuService;

    @Autowired
    public UserRestaurantController(RestaurantWithVisibleMenuService restaurantWithVisibleMenuService) {
        this.restaurantWithVisibleMenuService = restaurantWithVisibleMenuService;
    }

    @GetMapping
    public List<UserRestaurantDto> getAllWithVisibleMenu() {
        return restaurantWithVisibleMenuService.getAllWithVisibleMenu();
    }
}
