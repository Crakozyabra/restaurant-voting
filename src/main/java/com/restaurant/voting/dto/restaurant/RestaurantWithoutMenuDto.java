package com.restaurant.voting.dto.restaurant;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RestaurantWithoutMenuDto {

    private Integer id;

    private String name;
}
