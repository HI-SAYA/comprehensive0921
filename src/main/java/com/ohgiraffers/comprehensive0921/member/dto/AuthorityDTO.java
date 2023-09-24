package com.ohgiraffers.comprehensive0921.member.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AuthorityDTO {
    /* 웹 회원들의 등급을 나눈 테이블을 DTO화 */
    private int code;
    private String name;
    private String desc;
}
