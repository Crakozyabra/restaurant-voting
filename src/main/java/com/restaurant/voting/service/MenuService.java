package com.restaurant.voting.service;

import com.restaurant.voting.model.Menu;
import com.restaurant.voting.util.ToUtil;
import com.restaurant.voting.to.menu.AdminMenuDto;
import com.restaurant.voting.model.Restaurant;
import com.restaurant.voting.repository.MenuRepository;
import com.restaurant.voting.repository.RestaurantRepository;
import com.restaurant.voting.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional(readOnly = true)
public class MenuService {

    private MenuRepository menuRepository;

    private RestaurantRepository restaurantRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository, RestaurantRepository restaurantRepository) {
        this.menuRepository = menuRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Transactional
    public AdminMenuDto create(AdminMenuDto adminMenuDto) {
        return save(adminMenuDto);
    }

    public AdminMenuDto get(int id) {
        return ValidationUtil.checkNotFoundWithId(
                menuRepository.findById(id).map(ToUtil::menuToAdminMenuDto).orElse(null), id
        );
    }

    @Transactional
    public AdminMenuDto update(AdminMenuDto adminMenuDto) {
        return ValidationUtil.checkNotFoundWithId(save(adminMenuDto),adminMenuDto.getId());
    }

    public void delete(int id) {
        menuRepository.deleteById(id);
    }

    private AdminMenuDto save(AdminMenuDto adminMenuDto) {
        Restaurant restaurant = restaurantRepository.getReferenceById(adminMenuDto.getRestaurantId());
        Menu menu = ToUtil.adminMenuDtoToMenu(adminMenuDto);
        menu.setRestaurant(restaurant);
        Menu savedMenu = menuRepository.save(menu);
        return ToUtil.menuToAdminMenuDto(savedMenu);
    }
}
