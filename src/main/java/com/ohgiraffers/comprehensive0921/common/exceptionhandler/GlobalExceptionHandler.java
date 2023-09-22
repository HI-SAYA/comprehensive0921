package com.ohgiraffers.comprehensive0921.common.exceptionhandler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/* @Component 어노테이션의 특수한 케이스로, 스프링 부트 애플리케이션에서 전역적으로
* 예외를 핸들링할 수 있게 해주는 어노테이션이다.
* => 이를 통해 코드의 중복을 해결할 수 있다.
* 또한, 하나의 클래스 내에서 정상 동작 시 호출되는 코드와 예외를 처리하는 코드를 분리할 수 있다. */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    /* 해당 메서드가 특정 예외 클래스인 'Exception'을 처리하는 핸들러 메서드 */
    public String errorView(Exception e, Model model) {

        model.addAttribute("errorMessage", e.getMessage());

        return "common/error";
        /* Model model
        * Model은 Spring MVC 프레임쿼크에서 사용되는 인터페이스로,
        * 컨트롤러에서 뷰로 데이터를 전달하는 데 사용한다. */
        /* model.addAttribute() 메서드는 Model 객체에 데이터를 추가하는 역할을 한다.
        * errorMessage라는 이름으로 예외 객체 e의 메세지를 model에 추가하고 있다.
        * => 뷰 템플릿에서 errorMessage라는 이름으로 이 메세지에 접근할 수 있다. */
    }
}
