package com.suyuri.ttodokproject.member.service;

import com.suyuri.ttodokproject.member.dto.MemberDTO;
import com.suyuri.ttodokproject.member.entity.MemberEntity;
import com.suyuri.ttodokproject.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
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


    //아이디 중복확인
    public String idCheck(String memberId) {
        Optional<MemberEntity> byMemberId = memberRepository.findByMemberId(memberId);
        if(byMemberId.isPresent()) {
            // 조회결과가 있다 -> 사용할 수 없다. 기존에 누가 쓰고있음.
            return null;
        } else {
            // 조회결과가 없다 -> 사용할 수 있다.
            return "ok";
        }
    }

    public String passwordCheck(String memberPw) {
        Optional<MemberEntity> byMemberPw = memberRepository.findByMemberPw(memberPw);
        if(byMemberPw.isPresent()) {
            return null;
        } else {
            return "ok";
        }
    }
}
