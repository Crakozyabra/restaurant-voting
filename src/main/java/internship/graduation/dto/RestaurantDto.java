package internship.graduation.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RestaurantDto {

    private String name;

    private List<MenuDto> menus;
}
