package com.restaurant.voting.dto.vote;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class VoteDto {

    private Integer id;

    private Integer userId;

    private Integer restaurantId;
}
