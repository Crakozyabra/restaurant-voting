package com.restaurant.voting.service;

import com.restaurant.voting.dto.restaurant.RestaurantWithoutMenuDto;
import com.restaurant.voting.util.DtoUtil;
import com.restaurant.voting.dto.restaurant.AdminRestaurantDto;
import com.restaurant.voting.model.Restaurant;
import com.restaurant.voting.repository.MenuRepository;
import com.restaurant.voting.repository.RestaurantRepository;
import com.restaurant.voting.repository.UserRepository;
import com.restaurant.voting.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
public class RestaurantService {

    RestaurantRepository restaurantRepository;

    VoteRepository voteRepository;

    UserRepository userRepository;

    MenuRepository menuRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository, VoteRepository voteRepository,
                             UserRepository userRepository, MenuRepository menuRepository) {
        this.restaurantRepository = restaurantRepository;
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.menuRepository = menuRepository;
    }

    @Transactional
    public RestaurantWithoutMenuDto create(RestaurantWithoutMenuDto restaurantWithoutMenuDto) {
        return save(restaurantWithoutMenuDto);
    }

    public AdminRestaurantDto get(int id) {
        Restaurant restaurant = restaurantRepository.get(id);
        return Objects.nonNull(restaurant) ? DtoUtil.restaurantToAdminRestaurantDto(restaurant) : null;
    }

    public List<AdminRestaurantDto> getAllWithMenu() {
        List<Restaurant> restaurants = restaurantRepository.getAllWithMenu();
        List<AdminRestaurantDto> adminRestaurantsDto = new ArrayList<>();
        restaurants.forEach(restaurant -> adminRestaurantsDto.add(DtoUtil.restaurantToAdminRestaurantDto(restaurant)));
        return adminRestaurantsDto;
    }

    @Transactional
    public RestaurantWithoutMenuDto update(RestaurantWithoutMenuDto restaurantWithoutMenuDto) {
        return save(restaurantWithoutMenuDto);
    }

    public void delete(int id) {
        restaurantRepository.deleteById(id);
    }

    private RestaurantWithoutMenuDto save(RestaurantWithoutMenuDto restaurantWithoutMenuDto) {
        Restaurant restaurant = restaurantRepository.save(
                DtoUtil.restaurantWithoutMenuDtoToRestaurant(restaurantWithoutMenuDto));
        return DtoUtil.restaurantToRestaurantWithoutMenuDto(restaurant);
    }
}
