package com.restaurant.voting.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"votes", "menus"})
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @OrderBy("name")
    @OneToMany(mappedBy = "restaurant")
    private List<Menu> menus;

    @OneToMany(mappedBy = "restaurant")
    private Set<Vote> votes;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "restaurant_supplier",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "supplier_id"))
    private Set<ProductSupplier> suppliers;
}
