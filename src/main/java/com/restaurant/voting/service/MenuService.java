package com.restaurant.voting.service;

import com.restaurant.voting.model.Menu;
import com.restaurant.voting.util.DtoUtil;
import com.restaurant.voting.dto.menu.AdminMenuDto;
import com.restaurant.voting.model.Restaurant;
import com.restaurant.voting.repository.MenuRepository;
import com.restaurant.voting.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
        return menuRepository.findById(id).map(DtoUtil::menuToAdminMenuDto).orElse(new AdminMenuDto());
    }

    public List<AdminMenuDto> getAllByPriceBetween(Double startPrice, Double endPrice) {
        return DtoUtil.menusToAdminMenusDto(menuRepository.findAllByPriceBetween(startPrice, endPrice));
    }

    public List<AdminMenuDto> getAllPaged(int start, int limit) {
        Page<Menu> page = menuRepository.findAll(PageRequest.of(start, limit));
        return page.get().map(DtoUtil::menuToAdminMenuDto).collect(Collectors.toList());
    }

    @Transactional
    public AdminMenuDto update(AdminMenuDto adminMenuDto) {
        return save(adminMenuDto);
    }

    public void delete(int id) {
        menuRepository.deleteById(id);
    }

    private AdminMenuDto save(AdminMenuDto adminMenuDto) {
        Restaurant restaurant = restaurantRepository.getReferenceById(adminMenuDto.getRestaurantId());
        Menu menu = DtoUtil.adminMenuDtoToMenu(adminMenuDto);
        menu.setRestaurant(restaurant);
        Menu savedMenu = menuRepository.save(menu);
        return DtoUtil.menuToAdminMenuDto(savedMenu);
    }
}
