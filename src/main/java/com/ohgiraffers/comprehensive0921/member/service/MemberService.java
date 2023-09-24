package com.ohgiraffers.comprehensive0921.member.service;

import com.ohgiraffers.comprehensive0921.common.exception.member.MemberRegistException;
import com.ohgiraffers.comprehensive0921.member.dao.MemberMapper;
import com.ohgiraffers.comprehensive0921.member.dto.MemberDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {
    /* 실제 비니즈니스 로직을 구현하는 곳 */

    private final MemberMapper memberMapper;

    public MemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }
    /* MemberMapper와 연결한다. 생성자 의존성 주입 */


    public boolean selectMemberById(String memberId) {

        String result = memberMapper.selectMemberById(memberId);

        return result != null;
    }
    /* 주어진 memberId를 이용하여 회원 정보를 DB에서 조회하는 메서드
    * 조회된 결과가 null이 아닌 경우 해당 회원이 존재하는 것으로 간주하여 true를 반환한다. */


    @Transactional
    public void registMember(MemberDTO member) throws MemberRegistException {

        int result1 = memberMapper.insertMember(member);
        int result2 = memberMapper.insertMemberRole();

        if(!(result1 > 0 && result2 > 0 )) throw new MemberRegistException("회원 가입에 실패하였습니다.");
    }

}