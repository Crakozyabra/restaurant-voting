package com.restaurant.voting.service;

import com.restaurant.voting.repository.RestaurantRepository;
import com.restaurant.voting.repository.UserRepository;
import com.restaurant.voting.repository.VoteRepository;
import com.restaurant.voting.util.ToUtil;
import com.restaurant.voting.to.restaurant.UserRestaurantDto;
import com.restaurant.voting.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class RestaurantWithVisibleMenuService {

    private RestaurantRepository restaurantRepository;

    private VoteRepository voteRepository;

    private UserRepository userRepository;

    @Autowired
    public RestaurantWithVisibleMenuService(RestaurantRepository restaurantRepository, VoteRepository voteRepository, UserRepository userRepository) {
        this.restaurantRepository = restaurantRepository;
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
    }

    public List<UserRestaurantDto> getAllWithVisibleMenu() {
        List<Restaurant> restaurants = restaurantRepository.getAllWithVisibleMenu();
        List<UserRestaurantDto> userRestaurantsTo = new ArrayList<>();
        restaurants.forEach(restaurant -> userRestaurantsTo.add(ToUtil.restautantToUserRestaurantTo(restaurant)));
        return userRestaurantsTo;
    }
}
