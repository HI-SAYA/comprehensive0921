package com.ohgiraffers.comprehensive0921.member.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberRoleDTO {
    /* 어떤 회원이 어떤 권한을 가지고 있는가 하는 테이블을 DTO화 */
    private int memberNo;
    private int authorityCode;
    private AuthorityDTO authority;
}
