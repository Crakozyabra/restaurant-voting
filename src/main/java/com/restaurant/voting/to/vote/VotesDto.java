package com.restaurant.voting.to.vote;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class VotesDto {

    @NotBlank
    @Size(min = 2, max = 128)
    private String restaurantName;

    @NotNull
    @Min(1)
    private Integer votesCount;

    public VotesDto(String restaurantName, Integer votesCount) {
        this.restaurantName = restaurantName;
        this.votesCount = votesCount;
    }
}
