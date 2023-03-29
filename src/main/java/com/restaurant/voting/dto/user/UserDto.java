package com.restaurant.voting.dto.user;

import com.restaurant.voting.model.Role;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserDto {

    private Integer id;

    private String name;

    private String email;

    private Set<Role> roles;
}
