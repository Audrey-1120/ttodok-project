package com.suyuri.ttodokproject.service;

import com.suyuri.ttodokproject.dto.MemberDTO;
import com.suyuri.ttodokproject.entity.MemberEntity;
import com.suyuri.ttodokproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service //스프링이 관리해주는 객체 == 스프링 빈
@RequiredArgsConstructor //controller와 같이 final 멤버변수 생성자 만드는 역할
public class MemberService {

    private final MemberRepository memberRepository; //먼저 jpa, mysql dependency 추가

    public void save(MemberDTO memberDTO){
        //repository의 save 메소드 호출
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
        //Repository의 save 메소드 호출(조건 : entity 객체를 넘겨줘야 함.)
    }
}
