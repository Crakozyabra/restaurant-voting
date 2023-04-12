package com.restaurant.voting.to;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public abstract class AbstractEnabledDto extends AbstractNamedDto {

    @NotNull
    protected Boolean enabled;

    public AbstractEnabledDto(Integer id, String name, Boolean enabled) {
        super(id, name);
        this.enabled = enabled;
    }
}
