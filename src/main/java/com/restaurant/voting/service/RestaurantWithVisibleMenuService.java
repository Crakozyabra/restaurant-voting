package com.restaurant.voting.service;

import com.restaurant.voting.repository.RestaurantRepository;
import com.restaurant.voting.repository.UserRepository;
import com.restaurant.voting.repository.VoteRepository;
import com.restaurant.voting.util.DtoUtil;
import com.restaurant.voting.dto.restaurant.UserRestaurantDto;
import com.restaurant.voting.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class RestaurantWithVisibleMenuService {

    RestaurantRepository restaurantRepository;

    VoteRepository voteRepository;

    UserRepository userRepository;

    @Autowired
    public RestaurantWithVisibleMenuService(RestaurantRepository restaurantRepository, VoteRepository voteRepository, UserRepository userRepository) {
        this.restaurantRepository = restaurantRepository;
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
    }

    public List<UserRestaurantDto> getAllWithVisibleMenu() {
        List<Restaurant> restaurants = restaurantRepository.getAllWithVisibleMenu();
        List<UserRestaurantDto> userRestaurantsDto = new ArrayList<>();
        restaurants.forEach(restaurant -> userRestaurantsDto.add(DtoUtil.restautantToUserRestaurantDto(restaurant)));
        return userRestaurantsDto;
    }
}
