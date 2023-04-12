package com.restaurant.voting.to.restaurant;

import com.restaurant.voting.to.AbstractNamedDto;
import lombok.*;

@NoArgsConstructor
@Getter
public class RestaurantWithoutMenuDto extends AbstractNamedDto {

    public RestaurantWithoutMenuDto(Integer id, String name) {
        super(id, name);
    }
}
