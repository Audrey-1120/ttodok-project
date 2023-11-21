package com.suyuri.ttodokproject.service;

import com.suyuri.ttodokproject.dto.MemberDTO;
import com.suyuri.ttodokproject.entity.MemberEntity;
import com.suyuri.ttodokproject.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {


    //생성자 의존성 주입
    private final MemberRepository memberRepository;


    //회원가입 관련 메소드
    public void save(MemberDTO memberDTO) {
        //1. dto -> entity 변환
        //2. repository의 save 메소드 호출
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity); //여기서 save는 jpa가 제공해주는 메소드임.
        //repository의 save 메소드 호출 (조건. entity객체를 넘겨줘야 함.)

    }

    //로그인 관련 메소드
    public Map<String, String> login(MemberDTO memberDTO) {
        Map<String, String> resultMap = new HashMap<>();

        Optional<MemberEntity> byMemberId = memberRepository.findByMemberId(memberDTO.getMemberId());
        if (byMemberId.isPresent()) {
            MemberEntity memberEntity = byMemberId.get();
            if (memberEntity.getMemberPw().equals(memberDTO.getMemberPw())) {
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                resultMap.put("status", "success");
                resultMap.put("message", "로그인 성공");
                // 성공 시에는 필요한 데이터 추가
                resultMap.put("data", dto.toString());
            } else {
                resultMap.put("status", "error");
                resultMap.put("message", "비밀번호가 일치하지 않습니다.");
            }
        } else {
            resultMap.put("status", "error");
            resultMap.put("message", "아이디가 일치하지 않습니다.");
        }

        return resultMap;
    }


    //아이디 중복확인 및 유효성 검사
    public String idCheck(String memberId) {
        Optional<MemberEntity> byMemberId = memberRepository.findByMemberId(memberId);
        System.out.println("byMemberId: " + byMemberId);
        if(byMemberId.isPresent()) {
            // 조회결과가 있다 -> 사용할 수 없다. 기존에 누가 쓰고있음.
            return null;
            //231021 수정해봄
        } else {
            // 조회결과가 없다 -> 사용할 수 있다.
            return "ok";
        }
    }

    //패스워드 중복확인 및 유효성 검사
    public String passwordCheck(String memberPw) {
        Optional<MemberEntity> byMemberPw = memberRepository.findByMemberPw(memberPw);
        if(byMemberPw.isPresent()) {
            return null;
        } else {
            return "ok";
        }
    }


    // 짐니이의 회원 닉네임 가져오기
    public String getNickName(String memberId) {
        Optional<MemberEntity> byMemberId = memberRepository.findByMemberId(memberId);
        return byMemberId.map(MemberEntity::getMemberNick).orElse(null);
    }

    // 포인트
    public int getMemberPoint(String memberId) {
        Optional<MemberEntity> optionalMember = memberRepository.findByMemberId(memberId);
        return optionalMember.map(MemberEntity::getPoint).orElse(0);
    }

    //지민이의 회원탈퇴
    public boolean deleteMember(String memberId) {
        Optional<MemberEntity> optionalMember = memberRepository.findByMemberId(memberId);

        if (optionalMember.isPresent()) {
            memberRepository.delete(optionalMember.get());
            return true;
        } else {
            return false;
        }
    }



    //오드리 적음
    //퀴즈 데이터 받아와서 수정

    //MemberDTO통해서 로그인한 유저 아이디와 100점 추가된 해당 유저의 포인트 값 받아와서 memberEntity 값에 수정.
    @Transactional
    public String updateMemberPoint(MemberDTO memberDTO) {
        String loginId = memberDTO.getMemberId();
        int updatedPoint = memberDTO.getMemberPoint();

        Optional<MemberEntity> byMemberId = memberRepository.findByMemberId(loginId);

        if (byMemberId.isPresent()) {
            MemberEntity memberEntity = byMemberId.get();
            memberEntity.setPoint(updatedPoint);
            memberRepository.save(memberEntity);
            return "/main_ver2";
        } else {
            return "/main_ver2";
        }
    }
}