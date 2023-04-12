package com.restaurant.voting.to.menu;

import com.restaurant.voting.to.AbstractNamedDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
public class UserMenuDto extends AbstractNamedDto {

    @Min(0)
    @NotNull
    private Double price;

    public UserMenuDto(Integer id, String name, Double price) {
        super(id, name);
        this.price = price;
    }
}
