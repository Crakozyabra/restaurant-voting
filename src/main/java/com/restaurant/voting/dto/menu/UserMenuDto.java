package com.restaurant.voting.dto.menu;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserMenuDto {

    private String name;

    private Double price;
}
