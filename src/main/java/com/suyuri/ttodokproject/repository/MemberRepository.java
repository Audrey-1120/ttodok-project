package com.suyuri.ttodokproject.repository;
import org.springframework.stereotype.Repository;

import com.suyuri.ttodokproject.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    //<첫번째인자,두번째인자> - 첫번째 : 어떤 엔티티 클래스 다룰건가요?, 두번째 : 엔티티 클래스의 pk는 어떤 타입인가요?

    //아이디로 회원정보 조회 (select * from tbl_member where member_id=?)
    Optional<MemberEntity> findByMemberId(String memberId);

    //패스워드로 회원정보 조회 (select * from tbl_member where member_pw=?)
    Optional<MemberEntity> findByMemberPw(String memberPw);
}
