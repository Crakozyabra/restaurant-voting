package com.restaurant.voting.to.restaurant;

import com.restaurant.voting.to.AbstractNamedDto;
import com.restaurant.voting.to.menu.AdminMenuDto;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
public class AdminRestaurantDto extends AbstractNamedDto {

    @NotNull
    private List<AdminMenuDto> menus;

    public AdminRestaurantDto(Integer id, String name, List<AdminMenuDto> menus) {
        super(id, name);
        this.menus = menus;
    }
}
