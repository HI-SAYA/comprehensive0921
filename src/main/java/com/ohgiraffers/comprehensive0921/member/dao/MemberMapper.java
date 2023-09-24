package com.ohgiraffers.comprehensive0921.member.dao;

import com.ohgiraffers.comprehensive0921.member.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    MemberDTO findByMemberId(String memberId);
    /* 이 메서드는 DB에서 특정회원(Member) 조회하는 데 사용
    * MemberDTO 객체를 반환하며 String memberId 매개변수를 통해 검색할 회원을 식별
    * Mybatis와 함께 사용되며, Mybatis가 DB와 상호 작용하고 SQL 쿼리를 실행하는 데 필요한
    * 코드를 자동으로 생성한다. 실제로 실행하면 MemberDTO 객체가 반환된다. */

    String selectMemberById(String memberId);

    int insertMember(MemberDTO member);

    int insertMemberRole();


}
