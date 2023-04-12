package com.restaurant.voting.to.menu;

import com.restaurant.voting.to.AbstractEnabledDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class AdminMenuDto extends AbstractEnabledDto {

    @Min(1)
    @NotNull
    private Integer restaurantId;

    @Min(0)
    @NotNull
    private Double price;

    public AdminMenuDto(Integer id, String name, Boolean enabled, Integer restaurantId, Double price) {
        super(id, name, enabled);
        this.restaurantId = restaurantId;
        this.price = price;
    }
}
