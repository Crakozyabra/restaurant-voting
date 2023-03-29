package com.restaurant.voting.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "supplier")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductSupplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToMany(mappedBy = "suppliers", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    Set<Restaurant> restaurants;
}
