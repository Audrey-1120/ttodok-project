package com.suyuri.ttodokproject.member.entity;

import com.suyuri.ttodokproject.member.dto.MemberDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@Table(name = "tbl_member")
public class MemberEntity { //Entity클래스는 테이블 역할을 한다. 스프링 데이터 JPA는 테이블을 일종의 자바 객체처럼 활용한다.
    @Id //pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Long id;

    @Column(unique = true, length = 20, nullable = false) //unique 제약조건 추가
    private String memberId;

    @Column(length = 20, nullable = false)
    private String memberPw;

    @Column(length = 20, nullable = false)
    private String memberName;

    @Column(length = 20, nullable = false)
    private String memberPhone;

    @Column(length = 20, nullable = false)
    private String memberNick; //닉네임 필드 추가

    @Column(nullable = false, columnDefinition = "int default 0")
    private int point;

    @Column
    private LocalDate birthDate;


    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberId(memberDTO.getMemberId()); //엔티티객체.setter메소드 - DTO에 담긴 값을 Entity로 넘긴다.
        memberEntity.setMemberPw(memberDTO.getMemberPw());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setMemberPhone(memberDTO.getMemberPhone());
        memberEntity.setMemberNick(memberDTO.getMemberNick());

        LocalDate birthDate = LocalDate.of(memberDTO.getYear(), memberDTO.getMonth(), memberDTO.getDay());
        memberEntity.setBirthDate(birthDate);

        return memberEntity;
    }




}
