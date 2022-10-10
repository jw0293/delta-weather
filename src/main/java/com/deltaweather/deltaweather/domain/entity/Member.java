package com.deltaweather.deltaweather.domain.entity;

import com.deltaweather.deltaweather.domain.dto.UpdateMemberDto;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "name")
    private String memberName;

    @Column(name = "email")
    private String memberEmail;

    @Column(name = "password")
    private String memberPassword;

    public void update(UpdateMemberDto memberDto){
        this.memberName = memberDto.getMemberName();
        this.memberEmail = memberDto.getMemberEmail();
        this.memberPassword = memberDto.getMemberPassword();
    }
}
