package com.deltaweather.deltaweather.service;

import com.deltaweather.deltaweather.domain.dto.UpdateMemberDto;
import com.deltaweather.deltaweather.domain.entity.Member;
import com.deltaweather.deltaweather.domain.dto.SignUpDto;
import com.deltaweather.deltaweather.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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

    @Transactional
    public Long memberUpdate(Long id, UpdateMemberDto memberDto){
        Member findMember = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));
        findMember.update(memberDto);

        return findMember.getId();
    }

}
