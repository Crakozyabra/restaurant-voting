package internship.graduation.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class VoteDto {
    private String restaurantName;

    private Integer countOfvoice;
}
