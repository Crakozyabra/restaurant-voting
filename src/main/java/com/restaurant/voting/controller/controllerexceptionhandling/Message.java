package com.restaurant.voting.controller.controllerexceptionhandling;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class Message {

    private String message;

    @Override
    public String toString() {
        return "{\"message\": " + message + "}";
    }
}
