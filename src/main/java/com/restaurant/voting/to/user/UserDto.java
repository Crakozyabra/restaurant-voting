package com.restaurant.voting.to.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.restaurant.voting.model.Role;
import com.restaurant.voting.to.AbstractEnabledDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;


@NoArgsConstructor
@Getter
public class UserDto extends AbstractEnabledDto {

    @Email
    @NotBlank
    @Size(max = 128)
    private String email;

    @NotBlank
    @Size(min = 5, max = 128)
    private String password;

    @NotNull
    private Set<Role> roles;

    public UserDto(Integer id, String name, Boolean enabled, String email, String password, Set<Role> roles) {
        super(id, name, enabled);
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}
