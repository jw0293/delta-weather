package com.deltaweather.deltaweather.domain.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class UpdateMemberDto {

    private String memberName;
    private String memberEmail;
    private String memberPassword;

}
