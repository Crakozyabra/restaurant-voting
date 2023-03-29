package com.restaurant.voting.dto.restaurant;

import com.restaurant.voting.dto.menu.UserMenuDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserRestaurantDto {

    private Integer id;

    private String name;

    private List<UserMenuDto> menus;
}
