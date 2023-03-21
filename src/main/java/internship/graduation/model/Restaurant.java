package internship.graduation.model;


import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "restaurant")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"votes","menus"})
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "restaurant")
    private List<Menu> menus;

    @OneToMany(mappedBy = "restaurant")
    private Set<Vote> votes;
}
