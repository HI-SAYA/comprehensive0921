package com.ohgiraffers.comprehensive0921.common.exception.member;

public class LoginFailedException extends Exception {
    /* Exception 클래스는 Throwable 클래스를 상속한다.
    * 예외 처리를 할 수 있는 최상위 클래스이다. Exception과 Error는 Thowable을 상속 받는다. */

    public LoginFailedException(String msg) {
        super(msg);
        /* 문자열 메세지를 인수로 받아서 상위 클래스인 Exception 클래스의 생성자를 호출하여
        * 예외 메세지를 설정한다.
        * -> 예외가 발생했을 때 나중에 오류 메세지를 이해하고 디버깅 할 때 유용하다. */
    }
}
