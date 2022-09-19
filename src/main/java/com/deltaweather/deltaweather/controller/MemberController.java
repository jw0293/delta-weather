package com.deltaweather.deltaweather.controller;

import com.deltaweather.deltaweather.domain.dto.SignUpDto;
import com.deltaweather.deltaweather.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Log4j2
@RestController
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member")
    public String members(){
        return "Success";
    }

    @ResponseBody
    @PostMapping("/members")
    public ResponseEntity<String> signUp(@RequestBody SignUpDto signUpDto, HttpServletRequest request, HttpServletResponse response) throws IOException{

        log.info("회원 이름 : {}", signUpDto.getName());
        log.info("회원 이메일 : {}", signUpDto.getEmail());
        log.info("회원 비밀번호 : {}", signUpDto.getPassword());

        if(memberService.isEmailDuplicated(signUpDto.getEmail())){
            log.error("이미 존재하는 회원 이메일입니다.");
            ResponseEntity.status(403);
            return ResponseEntity.badRequest().build();
        } else{
            memberService.signUp(signUpDto);
            ResponseEntity.status(200);
            return ResponseEntity.ok("회원가입에 성공하였습니다.");
        }
    }
}
