package com.twt.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class RegisterParam implements Serializable {

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String password;
}
