package com.deltaweather.deltaweather.service;

import com.deltaweather.deltaweather.domain.entity.Member;
import com.deltaweather.deltaweather.domain.dto.SignUpDto;
import com.deltaweather.deltaweather.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    // private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void signUp(final SignUpDto signUpDto){
        final Member member = Member.builder()
                .memberName(signUpDto.getName())
                .memberEmail(signUpDto.getEmail())
                .memberPassword(signUpDto.getPassword())
                .build();

        memberRepository.save(member);
    }

    public boolean isEmailDuplicated(final String email){
        return memberRepository.existsByMemberEmail(email);
    }

}
