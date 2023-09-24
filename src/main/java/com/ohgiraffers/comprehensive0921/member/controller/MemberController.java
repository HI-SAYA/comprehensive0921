package com.ohgiraffers.comprehensive0921.member.controller;

import com.ohgiraffers.comprehensive0921.member.dto.MemberDTO;
import com.ohgiraffers.comprehensive0921.member.service.AuthenticationService;
import com.ohgiraffers.comprehensive0921.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
/* annotation.GetMapping으로도 가능하나 여기서는 * 로 설정하여 어노테이션 모든 설정을 임포트한다. */


@Slf4j
@Controller
@RequestMapping("/member")
/* 이 클래스의 모든 메서드에 대한 요청 URL이 /member로 시작하도록 설정 */
public class MemberController {

    private final MemberService memberService;
    private final AuthenticationService authenticationService;
    /* 사용자 인증 처리 / 로그인, 로그아웃 등 */
    private final MessageSourceAccessor messageSourceAccessor;


    public MemberController(MemberService memberService, AuthenticationService authenticationService,
                            MessageSourceAccessor messageSourceAccessor) {
        this.memberService = memberService;
        this.authenticationService = authenticationService;
        this.messageSourceAccessor = messageSourceAccessor;
    }
    /* 생성자 메서드 - MemberService 등 객체를 주입받아 필드에 할당 */

    /* *** 짤막 상식 GET vs POST
    * GET : 주로 정보를 조회하거나 데이터를 요청하는 겨우 사용 URL에 데이터 노출
    * POST : 주로 데이터를 서버로 제출하거나 변경하는 경우 사용되며, 데이터는 요청 본문(Request Body)에
    * 포함되어 전송된다. 로그인과 같이 민감한 정보를 전송할 때는 주로 POST 요청을 사용 */


    /* 로그인 페이지 이동 */
    @GetMapping("/login")
    public void loginPage(){}
    /* void일 경우 따로 뷰를 렌더링하지 않는다. 단순히 요청 url에 해당하는 뷰(html)를 보여준다
    * 클래스 단위로 @RequestMapping("/member")를 해뒀기 때문에 /member/login 화면을 보여준다. */


    /* 로그인 실패 시 */
    @PostMapping("/loginfail")
    public String loginFailed(RedirectAttributes rttr) {
        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("error.login"));
        return "redirect:/member/login";
    }
    /* RedirectAttribute rttr : 이 객체는 리다이렉트 시에 속성(attribute)를 전달할 수 있는 기능 제공
    * 따라서 위 메소드는 로그인 실패 시의 동작을 정의하는 메서드이다.
    *
    * rttr 객체를 사용하여 리다이렉트 시에 message라는 속성에 메세지를 추가한다.
    * 메세지는 messageSourceAccessor.getMessage("error.login")을 사용하여 가져온다.
    * 이는 다국어 지원을 위한 메세지 소스에서 해당 메세지를 가져오는 것을 의미한다.
    *
    * 이 메서드는 로그인 실패시 로그인 페이지로 되돌아가도록 리턴 값을 설정하여다.*/



}