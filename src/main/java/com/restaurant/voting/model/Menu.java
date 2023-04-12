package com.restaurant.voting.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "menu")
@Getter
@Setter
@NoArgsConstructor
public class Menu extends AbstractNamedEntity {

    @Column(nullable = false)
    @Min(0)
    @NotNull
    private Double price;

    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
    @NotNull
    private Boolean enabled = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public Menu(Integer id, String name, Double price, Boolean enabled, Restaurant restaurant) {
        super(id, name);
        this.price = price;
        this.enabled = enabled;
        this.restaurant = restaurant;
    }
}
