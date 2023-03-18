package com.twt.vo;

import lombok.Data;

@Data
public class UserVO {
    private Integer id;
    private String name;
    private String email;
    private String role;
    private String token;
}
