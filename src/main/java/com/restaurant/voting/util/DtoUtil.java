package com.restaurant.voting.util;

import com.restaurant.voting.dto.restaurant.RestaurantWithoutMenuDto;
import com.restaurant.voting.model.*;
import com.restaurant.voting.dto.menu.AdminMenuDto;
import com.restaurant.voting.dto.menu.UserMenuDto;
import com.restaurant.voting.dto.restaurant.AdminRestaurantDto;
import com.restaurant.voting.dto.restaurant.UserRestaurantDto;
import com.restaurant.voting.dto.supplier.ProductSupplierDto;
import com.restaurant.voting.dto.user.UserDto;
import com.restaurant.voting.dto.vote.VotesDto;


import java.util.*;

public class DtoUtil {


    public static AdminMenuDto menuToAdminMenuDto(Menu menu) {
        return new AdminMenuDto(menu.getId(), menu.getRestaurant().getId(), menu.getName(), menu.getPrice(),
                menu.getIsVisible());
    }

    public static Menu adminMenuDtoToMenu(AdminMenuDto adminMenuDto) {
        return new Menu(adminMenuDto.getId(), adminMenuDto.getName(), adminMenuDto.getPrice(),
                adminMenuDto.getIsVisible(), null);
    }

    public static List<AdminMenuDto> menusToAdminMenusDto(List<Menu> menus) {
        List<AdminMenuDto> menusDto = new ArrayList<>();
        for (Menu menu : menus) {
            menusDto.add(menuToAdminMenuDto(menu));
        }
        return menusDto;
    }


    public static AdminRestaurantDto restaurantToAdminRestaurantDto(Restaurant restaurant) {
        return new AdminRestaurantDto(restaurant.getId(), restaurant.getName(), DtoUtil.menusToAdminMenusDto(restaurant.getMenus()));
    }

    public static Restaurant restaurantWithoutMenuDtoToRestaurant(RestaurantWithoutMenuDto restaurantWithoutMenuDto) {
        return Restaurant.builder().id(restaurantWithoutMenuDto.getId()).name(restaurantWithoutMenuDto.getName()).build();
    }

    public static RestaurantWithoutMenuDto restaurantToRestaurantWithoutMenuDto(Restaurant restaurant) {
        return RestaurantWithoutMenuDto.builder().id(restaurant.getId()).name(restaurant.getName()).build();
    }

    public static Restaurant restaurantDtoToRestaurant(AdminRestaurantDto adminRestaurantDto) {
        return new Restaurant(adminRestaurantDto.getId(), adminRestaurantDto.getName(), null, null, null);
    }

    public static List<VotesDto> votesToVotesDto(List<Vote> votes) {
        Map<String, Integer> voting = new LinkedHashMap<>();
        for (Vote vote : votes) {
            voting.merge(vote.getRestaurant().getName(), 1, (oldValue, newValue) -> oldValue + 1);
        }
        List<VotesDto> votesDto = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : voting.entrySet()) {
            votesDto.add(VotesDto.builder().restaurantName(entry.getKey()).votesCount(entry.getValue()).build());
        }
        return votesDto;
    }

    public static UserDto userToUserDto(User user) {
        return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getRoles());
    }

    public static User userDtoToUser(UserDto userDto) {
        return new User(userDto.getId(), userDto.getName(), userDto.getEmail(), userDto.getRoles(), null);
    }

    public static UserMenuDto menuToUserMenuDto(Menu menu) {
        return new UserMenuDto(menu.getName(), menu.getPrice());
    }

    public static List<UserMenuDto> menusToUserMenusDto(List<Menu> menus) {
        List<UserMenuDto> userMenusDto = new ArrayList<>();
        menus.forEach(menu -> userMenusDto.add(menuToUserMenuDto(menu)));
        return userMenusDto;
    }

    public static UserRestaurantDto restautantToUserRestaurantDto(Restaurant restaurant) {
        return new UserRestaurantDto(restaurant.getId(), restaurant.getName(),
                DtoUtil.menusToUserMenusDto(restaurant.getMenus()));
    }

    public static List<AdminRestaurantDto> restaurantsToRestaurantsDtos(List<Restaurant> restaurants) {
        List<AdminRestaurantDto> adminRestaurantDtos = new ArrayList<>();
        restaurants.forEach(restaurant -> adminRestaurantDtos.add(restaurantToAdminRestaurantDto(restaurant)));
        return adminRestaurantDtos;
    }

    public static ProductSupplierDto productSupplierToProductSupplierDto(ProductSupplier supplier) {
       return new ProductSupplierDto(supplier.getId(), null, supplier.getName());
    }

    public static ProductSupplier productSupplierDtoToProductSupplier(ProductSupplierDto productSupplierDto) {
        return new ProductSupplier(productSupplierDto.getId(), productSupplierDto.getName(), null);
    }

    public static Set<ProductSupplierDto> productSuppliersToProductSupplierDtos(List<ProductSupplier> productSuppliers) {
        Set<ProductSupplierDto> productSupplierDtos = new LinkedHashSet<>();
        productSuppliers.forEach(productSupplier -> productSupplierDtos.add(DtoUtil.productSupplierToProductSupplierDto(productSupplier)));
        return productSupplierDtos;
    }

}
