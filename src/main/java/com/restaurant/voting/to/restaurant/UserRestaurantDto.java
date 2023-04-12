package com.restaurant.voting.to.restaurant;

import com.restaurant.voting.to.AbstractNamedDto;
import com.restaurant.voting.to.menu.UserMenuDto;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@Getter
public class UserRestaurantDto extends AbstractNamedDto {

    @NotNull
    private List<UserMenuDto> menus;

    public UserRestaurantDto(Integer id, String name, List<UserMenuDto> menus) {
        super(id, name);
        this.menus = menus;
    }
}
