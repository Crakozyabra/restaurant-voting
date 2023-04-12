package com.restaurant.voting.util;

import com.restaurant.voting.to.menu.AdminMenuDto;
import com.restaurant.voting.to.menu.UserMenuDto;
import com.restaurant.voting.to.restaurant.AdminRestaurantDto;
import com.restaurant.voting.to.restaurant.RestaurantWithoutMenuDto;
import com.restaurant.voting.to.restaurant.UserRestaurantDto;
import com.restaurant.voting.to.user.UserDto;
import com.restaurant.voting.to.vote.VotesDto;
import com.restaurant.voting.model.Menu;
import com.restaurant.voting.model.Restaurant;
import com.restaurant.voting.model.User;
import com.restaurant.voting.model.Vote;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ToUtil {


    public static AdminMenuDto menuToAdminMenuDto(Menu menu) {
        return new AdminMenuDto(menu.getId(), menu.getName(), menu.getEnabled(), menu.getRestaurant().getId(),
                menu.getPrice());
    }

    public static Menu adminMenuDtoToMenu(AdminMenuDto adminMenuDto) {
        return new Menu(adminMenuDto.getId(), adminMenuDto.getName(), adminMenuDto.getPrice(),
                adminMenuDto.getEnabled(), null);
    }

    public static List<AdminMenuDto> menusToAdminMenusDto(List<Menu> menus) {
        List<AdminMenuDto> menusDto = new ArrayList<>();
        for (Menu menu : menus) {
            menusDto.add(menuToAdminMenuDto(menu));
        }
        return menusDto;
    }

    public static AdminRestaurantDto restaurantToAdminRestaurantDto(Restaurant restaurant) {
        return new AdminRestaurantDto(restaurant.getId(), restaurant.getName(), ToUtil.menusToAdminMenusDto(restaurant.getMenus()));
    }

    public static Restaurant restaurantWithoutMenuDtoToRestaurant(RestaurantWithoutMenuDto restaurantWithoutMenuDto) {
        return new Restaurant(restaurantWithoutMenuDto.getId(), restaurantWithoutMenuDto.getName(), null, null);
    }

    public static RestaurantWithoutMenuDto restaurantToRestaurantWithoutMenuDto(Restaurant restaurant) {
        return new RestaurantWithoutMenuDto(restaurant.getId(), restaurant.getName());
    }

    public static Restaurant restaurantDtoToRestaurant(AdminRestaurantDto adminRestaurantDto) {
        return new Restaurant(adminRestaurantDto.getId(), adminRestaurantDto.getName(), null, null);
    }

    public static List<VotesDto> votesToVotesDto(List<Vote> votes) {
        Map<String, Integer> voting = new LinkedHashMap<>();
        for (Vote vote : votes) {
            voting.merge(vote.getRestaurant().getName(), 1, (oldValue, newValue) -> oldValue + 1);
        }
        List<VotesDto> votesDto = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : voting.entrySet()) {
            votesDto.add(new VotesDto(entry.getKey(), entry.getValue()));
        }
        return votesDto;
    }

    public static UserDto userToUserDto(User user) {
        return new UserDto(user.getId(), user.getName(), user.getEnabled(), user.getEmail(), user.getPassword(),
                user.getRoles());
    }

    public static User userDtoToUser(UserDto userDto) {
        return new User(userDto.getId(), userDto.getName(), userDto.getEmail(), userDto.getEnabled(),
                userDto.getPassword(), userDto.getRoles(), null);
    }

    public static UserMenuDto menuToUserMenuDto(Menu menu) {
        return new UserMenuDto(menu.getId(), menu.getName(), menu.getPrice());
    }

    public static List<UserMenuDto> menusToUserMenusDto(List<Menu> menus) {
        List<UserMenuDto> userMenusDto = new ArrayList<>();
        menus.forEach(menu -> userMenusDto.add(menuToUserMenuDto(menu)));
        return userMenusDto;
    }

    public static UserRestaurantDto restautantToUserRestaurantTo(Restaurant restaurant) {
        return new UserRestaurantDto(restaurant.getId(), restaurant.getName(),
                ToUtil.menusToUserMenusDto(restaurant.getMenus()));
    }

    public static List<AdminRestaurantDto> restaurantsToRestaurantsTos(List<Restaurant> restaurants) {
        List<AdminRestaurantDto> adminRestaurantDtos = new ArrayList<>();
        restaurants.forEach(restaurant -> adminRestaurantDtos.add(restaurantToAdminRestaurantDto(restaurant)));
        return adminRestaurantDtos;
    }
}
