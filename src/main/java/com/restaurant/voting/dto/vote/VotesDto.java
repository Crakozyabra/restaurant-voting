package com.restaurant.voting.dto.vote;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class VotesDto {

    private String restaurantName;

    private Integer votesCount;
}
