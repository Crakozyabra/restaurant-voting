package com.restaurant.voting.dto.supplier;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductSupplierDto {

    private Integer id;

    private Set<Integer> restaurantIds;

    private String name;
}
