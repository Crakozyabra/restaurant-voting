package com.restaurant.voting.dto.restaurant;

import com.restaurant.voting.dto.menu.AdminMenuDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AdminRestaurantDto {

    private Integer id;

    private String name;

    private List<AdminMenuDto> menus;
}
