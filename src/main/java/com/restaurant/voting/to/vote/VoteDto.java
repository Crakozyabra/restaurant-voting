package com.restaurant.voting.to.vote;

import com.restaurant.voting.to.AbstractBaseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
public class VoteDto extends AbstractBaseDto {

    @NotNull
    @Min(1)
    private Integer userId;

    @NotNull
    @Min(1)
    private Integer restaurantId;

    public VoteDto(Integer id, Integer userId, Integer restaurantId) {
        super(id);
        this.userId = userId;
        this.restaurantId = restaurantId;
    }
}
