package internship.graduation.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String email;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    @CollectionTable(name = "role", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<Role> roles;

    @OneToMany(mappedBy = "user")
    private List<Vote> votes;
}
