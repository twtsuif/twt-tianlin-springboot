package com.twt.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class LoginParam implements Serializable {

    @NotNull
    private String email;

    @NotNull
    private String password;
}
