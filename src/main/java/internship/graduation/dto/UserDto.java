package internship.graduation.dto;

import internship.graduation.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

public class UserDto {
    private String name;

    private String email;

    private Set<Role> roles;
}
