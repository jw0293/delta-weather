package com.deltaweather.deltaweather.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class SignUpDto {

    private String name;
    private String email;
    private String password;
}
