package com.twt.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class ApplyParam {
    private Integer uid;
    private String name;
    private Integer gender;
    private String nation;
    private String nativePlace;
    private String idcard;
    private String birthDate;
    private String phone;
    private String qq;
    private String email;
    private String fromPlace;
    private String highSchool;
    private String household;
    private String score;
    private String identity;
    private String identityDetail;
    private Integer partyWill;
    private String major;
    private String clothesSize;

    private String highSchoolExp;
    private String highSchoolHonour;
    private String hobby;
    private String introduction;

    @NotBlank(message = "请重新上传照片")
    private String photoPath;
    @NotBlank(message = "请重新上传压缩文件")
    private String filePath;
}
