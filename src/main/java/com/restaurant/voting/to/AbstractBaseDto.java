package com.restaurant.voting.to;

import com.restaurant.voting.HasId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public abstract class AbstractBaseDto implements HasId {
    public Integer id;

    public AbstractBaseDto(Integer id) {
        this.id = id;
    }
}
