package com.restaurant.voting.to;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public abstract class AbstractNamedDto extends AbstractBaseDto {

    @NotBlank
    @Size(min = 2, max = 128)
    protected String name;

    public AbstractNamedDto(Integer id, String name) {
        super(id);
        this.name = name;
    }
}
