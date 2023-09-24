package com.ohgiraffers.comprehensive0921.member.service;

import com.ohgiraffers.comprehensive0921.member.dao.MemberMapper;
import com.ohgiraffers.comprehensive0921.member.dto.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Slf4j // 로깅 지원
@Service
public class AuthenticationService implements UserDetailsService {
    /* UserDetailsService 인터페이스를 구현한다. Spring Security에서 사용자 정보를 로드하고 인증하는 데 사용 */

    private final MemberMapper memberMapper;

    public AuthenticationService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }
    /* 의존 - 생성자 주입 */

    @Override // 메서드 재정의
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        /* loadUserByUsername 메서드는 사용자 이름 또는 ID를 기반으로 사용자 정보를 DB에서 검색하고 반환하는 역할 */

        log.info("memberId: {}", memberId);
        /* 현재 사용자 이름을 로그 출력 */

        MemberDTO member = memberMapper.findByMemberId(memberId);
        /* memberMapper를 사용하여 DB에서 주어진 사용자 이름을 가진 회원 정보 조회 */

        log.info("member : {}", member);
        /* 검색된 회원 정보를 로그에 출력 */

        if(member == null) throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        /* 회원 정보가 DB에 검색되지 않을 경우 (null), UsernameNotFoundException을 발생시켜 사용자가 존재하지
         * 않음을 나타낸다. */

        return member;
        /* 검색된 회원 정보를 UserDetails 인터페이스를 구현한 객체로 반환.
         * 이 객체에는 사용자의 인증 및 권한 정보가 포함 */
    }
}

/* => 이 클래스는 Spring Security의 인증 프로세스에서 사용자 정보를 로드하고 인증하는 데 사용 */
