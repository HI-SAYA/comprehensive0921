package com.ohgiraffers.comprehensive0921.member.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Getter
@Setter
@ToString
public class MemberDTO implements UserDetails {
    /* MemberDTO는 abstract로 선언되거나 'UserDetails'에서 추상 메서드
    * 'getAuthorities()을(를) 구현해야 합니다.
    * 메서드 구현 - 전부 다 하기 */
    private Long MemberNo;
    private String memberId;
    private String memberPwd;
    private String nickname;
    private String phone;
    private String email;
    private String address;
    private Date enrollDate;
    private String memberStatus;
    private List<MemberRoleDTO> memberRoleList;
    /* 한 회원이 여러 개의 권한을 가질 수 있는 경우가 많다.
    * => 여러 개의 권한을 담을 수 있는 자료 구조 필요 => List 사용 */


    /* Jackson 라이브러리를 사용하며 json 직렬화 시에 이 필드를 무시하도록 지정하는 어노테이션 */
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        for(MemberRoleDTO role : memberRoleList) {
            roles.add(new SimpleGrantedAuthority(role.getAuthority().getName()));
        }
        return roles;
    }

    /* GrantedAuthority - 사용자에게 부여된 권한을 GrantedAuthority 객체로 표현
    * 사용자의 접근 권한을 검사한다.
    * 여기서는 DB에서 회원을 조회한 후 그 회원의 권한이 무엇인지 체크하는 것으로 보인다.
    * MemberRoleDTO 객체의 권한 정보를 가져와 SimpleGrantedAuthority로 변환 후
    * 권한 목록(Set)으로 반환한다. */
    /* * HashSet은 중복을 허용하지 않는 컬렉션으로 사용자가 가진 권한을 중복 없이 저장하기 위해 사용 */

    /* 요약 정리 - 여기서는 HashSet 객체인 roles를 생성한다. -> SimpleGrantedAuthority 객체로 변환
     * -> roles라는 HashSet에 추가 */
    /* HashSet - 중복x, 빠른 검색 및 접근, 순서 유지x, null 허용, 스레드 안정성 낮음 */


    /* UserDetails 인터페이스의 다른 메서드들을 구현
    * => 앞서 위에서 UserDeatails 부분을 메서드 구현할 때 모두 return null이나 false로
    * 최초 구현되지만(하드코딩) 작성자(개발자)가 수정하면 된다. 여기서는 member**과 true로 수정*/
    @Override
    public String getPassword() {
        return memberPwd;
    }

    @Override
    public String getUsername() {
        return memberId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
