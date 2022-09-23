package com.deltaweather.deltaweather.controller;

import com.deltaweather.deltaweather.domain.dto.SignUpDto;
import com.deltaweather.deltaweather.domain.dto.UpdateMemberDto;
import com.deltaweather.deltaweather.domain.entity.Member;
import com.deltaweather.deltaweather.repository.MemberRepository;
import com.deltaweather.deltaweather.service.MemberService;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MemberControllerTest {


    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;


    @Test
    public void memberUpdateTest(){
        SignUpDto signUpDto = postInitMember("김중원", "k38836919@gmail.com", "1234");
        memberService.signUp(signUpDto);

        UpdateMemberDto updateMemberDto = updateMember("정재우", "k38836919@gmail.com", "222");
        Member member = memberRepository.findByMemberEmail(updateMemberDto.getMemberEmail())
                .orElseThrow(() -> new RuntimeException("해당 이메일의 사용자를 찾을 수 없습니다."));
        Long id = memberService.memberUpdate(member.getId(), updateMemberDto);

        Member findMember = memberRepository.findById(id).orElseThrow(() -> new RuntimeException("에러 발생"));

        System.out.println("이름 : " + findMember.getMemberName());
        System.out.println("이메일 : " + findMember.getMemberEmail());
        System.out.println("비밀번호 : " + findMember.getMemberPassword());

    }

    private SignUpDto postInitMember(String name, String email, String password){
        SignUpDto signUpDto = new SignUpDto();
        signUpDto.setName(name);
        signUpDto.setEmail(email);
        signUpDto.setPassword(password);

        return signUpDto;
    }

    private UpdateMemberDto updateMember(String name, String email, String password){
        UpdateMemberDto updateMemberDto = new UpdateMemberDto();
        updateMemberDto.setMemberName(name);
        updateMemberDto.setMemberEmail(email);
        updateMemberDto.setMemberPassword(password);

        return updateMemberDto;
    }

}