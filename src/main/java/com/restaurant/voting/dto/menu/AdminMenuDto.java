package com.restaurant.voting.dto.menu;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AdminMenuDto {

    private Integer id;

    private Integer restaurantId;

    private String name;

    private Double price;

    private Boolean isVisible;
}
