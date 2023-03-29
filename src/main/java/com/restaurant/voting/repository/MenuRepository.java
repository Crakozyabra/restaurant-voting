package com.restaurant.voting.repository;

import com.restaurant.voting.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

    List<Menu> findAllByPriceBetween(Double startPrice, Double endPrice);
}
